package ru.poskr.rest.RestApp.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty
    @Size(min = 3,max=30,message = "Size name should be between 3 and 30 symbol")
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
