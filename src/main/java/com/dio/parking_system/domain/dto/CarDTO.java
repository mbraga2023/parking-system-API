package com.dio.parking_system.domain.dto;

import com.dio.parking_system.domain.dto.ParkingHistoryDTO;
import lombok.Data;

import java.util.List;

@Data
public class CarDTO {
    private Long id;
    private String manufacturer;
    private String model;
    private String color;
    private int year;
    private String plate;
    private String type;
    private List<ParkingHistoryDTO> history;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ParkingHistoryDTO> getHistory() {
        return history;
    }

    public void setHistory(List<ParkingHistoryDTO> history) {
        this.history = history;
    }
}
