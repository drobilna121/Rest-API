package ru.poskr.rest.RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.poskr.rest.RestApp.model.Measurement;
import ru.poskr.rest.RestApp.repositories.MeasurementRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public int rainyDaysCount() {
        return measurementRepository.findByRaining(true).size();
    }

    @Transactional
    public void save(Measurement measurement){
        measurementRepository.save(measurement);
    }
}
