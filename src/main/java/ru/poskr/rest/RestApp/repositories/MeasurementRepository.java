package ru.poskr.rest.RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.poskr.rest.RestApp.model.Measurement;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {

    List<Measurement> findByRaining(boolean raining);
}
