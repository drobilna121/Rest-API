package ru.poskr.rest.RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.poskr.rest.RestApp.model.Measurement;
import ru.poskr.rest.RestApp.model.Sensor;
import ru.poskr.rest.RestApp.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public boolean sensorWithNameExists(String name)
    {
        return !sensorRepository.findByName(name).isEmpty();
    }

    public Sensor findByName(String name){
        return sensorRepository.findByName(name).get();
    }
    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

}
