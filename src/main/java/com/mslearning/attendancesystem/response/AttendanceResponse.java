package com.mslearning.attendancesystem.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttendanceResponse {
    int id;
     String message;


  // String employeeStatus ;

    public AttendanceResponse(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

//    public String getEmployeeStatus() {
//        return employeeStatus;
//    }
//
//    public void setEmployeeStatus(String employeeStatus) {
//        this.employeeStatus = employeeStatus;
//    }
}
