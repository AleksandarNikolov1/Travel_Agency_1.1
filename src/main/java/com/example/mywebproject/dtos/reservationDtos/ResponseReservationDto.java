package com.example.mywebproject.dtos.reservationDtos;

import com.example.mywebproject.dtos.holidayDtos.ResponseHolidayDto;

public class ResponseReservationDto {
    private Long id;
    private String contactName;
    private String phoneNumber;
    private ResponseHolidayDto holiday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ResponseHolidayDto getHoliday() {
        return holiday;
    }

    public void setHoliday(ResponseHolidayDto holiday) {
        this.holiday = holiday;
    }
}
