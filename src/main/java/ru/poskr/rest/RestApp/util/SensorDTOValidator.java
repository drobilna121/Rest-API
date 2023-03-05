package ru.poskr.rest.RestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.poskr.rest.RestApp.DTO.SensorDTO;
import ru.poskr.rest.RestApp.services.SensorService;

@Component
public class SensorDTOValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SensorDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if(sensorService.sensorWithNameExists(sensorDTO.getName())){
            errors.rejectValue("name","","a sensor with this name already exists");
        }
    }
}
