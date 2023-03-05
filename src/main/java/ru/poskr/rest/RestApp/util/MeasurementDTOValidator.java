package ru.poskr.rest.RestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.poskr.rest.RestApp.DTO.MeasurementDTO;
import ru.poskr.rest.RestApp.services.SensorService;

@Component
public class MeasurementDTOValidator implements Validator {

    private final SensorService sensorService;
    @Autowired
    public MeasurementDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        if(measurementDTO.getValue() ==null){
            errors.rejectValue("value","","value should be not empty");
        }

        if(measurementDTO.getRaining() ==null){
            errors.rejectValue("raining","","raining should be not empty");
        }
        if(measurementDTO.getSensor() ==null){
            errors.rejectValue("sensor","","sensor should be not empty");
        }else if(!sensorService.sensorWithNameExists(measurementDTO.getSensor().getName())){
            errors.rejectValue("sensor","","sensor don`t exists");
        }
    }
}
