package com.example.mywebproject.dtos.reservationDtos;

import com.example.mywebproject.entities.Holiday;

public class CreateReservationDto {
    private String contactName;
    private String phoneNumber;
    private String holiday;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }
}
