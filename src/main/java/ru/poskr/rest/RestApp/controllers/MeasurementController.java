package ru.poskr.rest.RestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.poskr.rest.RestApp.DTO.MeasurementDTO;
import ru.poskr.rest.RestApp.DTO.SensorDTO;
import ru.poskr.rest.RestApp.model.Measurement;
import ru.poskr.rest.RestApp.model.Sensor;
import ru.poskr.rest.RestApp.services.MeasurementService;
import ru.poskr.rest.RestApp.services.SensorService;
import ru.poskr.rest.RestApp.util.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final MeasurementDTOValidator measurementDTOValidator;
    @Autowired
    public MeasurementController(MeasurementService measurementService, SensorService sensorService, ModelMapper modelMapper, MeasurementDTOValidator measurementDTOValidator) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.measurementDTOValidator = measurementDTOValidator;
    }

    @GetMapping()
    public MeasurementsResponse findAll(){
        List<Measurement> measurements= measurementService.findAll();
        List<MeasurementDTO> result = new ArrayList<>();
        for(Measurement m : measurements){
            result.add(convertToMeasurementDTO(m));
        }

        return new MeasurementsResponse(result);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult result){

        measurementDTOValidator.validate(measurementDTO,result);

        if(result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError error : errors){
                sb.append(error.getField()).append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementWritingException(sb.toString());
        }
        measurementService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/rainyDaysCount")
    public int rainyDaysCount(){
        return measurementService.rainyDaysCount();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementWritingException e){
        return new ResponseEntity<>(new MeasurementErrorResponse(e.getMessage()),HttpStatus.NOT_FOUND);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        MeasurementDTO result = modelMapper.map(measurement,MeasurementDTO.class);
        return result;
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        Measurement result = modelMapper.map(measurementDTO, Measurement.class);
        result.setSensor(sensorService.findByName(measurementDTO.getSensor().getName()));
        return  result;
    }
}
