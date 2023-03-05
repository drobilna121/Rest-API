package ru.poskr.rest.RestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.poskr.rest.RestApp.DTO.SensorDTO;
import ru.poskr.rest.RestApp.model.Sensor;
import ru.poskr.rest.RestApp.services.SensorService;
import ru.poskr.rest.RestApp.util.SensorCreateException;
import ru.poskr.rest.RestApp.util.SensorDTOValidator;
import ru.poskr.rest.RestApp.util.SensorErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorDTOValidator sensorDTOValidator;
@Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorDTOValidator sensorDTOValidator) {
        this.sensorService = sensorService;
    this.modelMapper = modelMapper;
    this.sensorDTOValidator = sensorDTOValidator;
}

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult result){

        sensorDTOValidator.validate(sensorDTO,result);

        if(result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError error : errors){
                sb.append(error.getField()).append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorCreateException(sb.toString());
        }

        sensorService.save(ConvertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorCreateException e){
        return new ResponseEntity<>(new SensorErrorResponse(e.getMessage()),HttpStatus.NOT_FOUND);
    }

    private Sensor ConvertToSensor(SensorDTO sensorDTO){
        return  modelMapper.map(sensorDTO, Sensor.class);
    }

}
