package ru.poskr.rest.RestApp.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import ru.poskr.rest.RestApp.model.Sensor;

public class MeasurementDTO {

    @Min(value = -100)
    @Max(value = 100)
    private Double value;
    private Boolean raining;

    private SensorDTO sensor;

    public MeasurementDTO(Double value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public MeasurementDTO() {
    }

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
