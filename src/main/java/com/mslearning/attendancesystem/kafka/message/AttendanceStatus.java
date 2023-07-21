package com.mslearning.attendancesystem.kafka.message;

public class AttendanceStatus {
    int empId;
    String attendanceStatus;;

    public AttendanceStatus(int empId, String attendanceStatus) {
        this.empId = empId;
        this.attendanceStatus = attendanceStatus;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
