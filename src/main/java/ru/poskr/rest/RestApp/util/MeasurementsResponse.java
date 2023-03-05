package ru.poskr.rest.RestApp.util;

import ru.poskr.rest.RestApp.DTO.MeasurementDTO;
import ru.poskr.rest.RestApp.model.Measurement;

import java.util.List;

public class MeasurementsResponse {

    private List<MeasurementDTO> measurementsDTO;

    public MeasurementsResponse() {
    }

    public List<MeasurementDTO> getMeasurementsDTO() {
        return measurementsDTO;
    }
    public MeasurementsResponse(List<MeasurementDTO> measurementsDTO) {
        this.measurementsDTO = measurementsDTO;
    }
    public void setMeasurementsDTO(List<MeasurementDTO> measurementsDTO) {
        this.measurementsDTO = measurementsDTO;
    }


}
