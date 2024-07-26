package com.dio.parking_system.domain.dto;

import lombok.Data;

@Data
public class ParkingHistoryDTO {
    private Long id;
    private String checkin;  // Should be in ISO-8601 format like "2024-07-25T08:30:00"
    private String checkout; // Should be in ISO-8601 format like "2024-07-25T09:30:00"

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}

