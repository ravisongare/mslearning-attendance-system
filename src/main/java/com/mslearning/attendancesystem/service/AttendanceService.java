package com.mslearning.attendancesystem.service;

import com.mslearning.attendancesystem.entity.AttendanceRecord;
import com.mslearning.attendancesystem.enums.EmployeeStatus;
import com.mslearning.attendancesystem.enums.TYPE;
import com.mslearning.attendancesystem.repository.IAttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    IAttendanceRepo attendanceRepo;

    public AttendanceRecord saveData(AttendanceRecord attendanceRecord) {
        if (attendanceRecord.getType().equalsIgnoreCase(TYPE.IN.name()))
            attendanceRecord.setInTime(new Timestamp(System.currentTimeMillis()));//
        else
            attendanceRecord.setOutTime(new Timestamp(System.currentTimeMillis()));
        attendanceRecord.setDate(new Date(System.currentTimeMillis()));
       return attendanceRepo.save(attendanceRecord);
    }

    public String getTotalTime(int empId) {
        long totalTime = 0;
        String employeeStatus = "";
       Optional<Timestamp> inTime= attendanceRepo.getByEmpIdAndDateAndType(empId,new Date(System.currentTimeMillis()),TYPE.IN.name().toLowerCase())
               .stream().min((e1, e2)-> e1.getInTime().compareTo(e2.getInTime())).map(e->e.getInTime());
        Optional<Timestamp> outTime= attendanceRepo.getByEmpIdAndDateAndType(empId,new Date(System.currentTimeMillis()),TYPE.OUT.name().toLowerCase())
                .stream().max((e1, e2)-> e1.getOutTime().compareTo(e2.getOutTime())).map(e->e.getOutTime());
        System.out.println("List:");
        totalTime = outTime.get().getTime() - inTime.get().getTime();
        totalTime = totalTime/60000;
        if(totalTime>8*60)
            employeeStatus = EmployeeStatus.PRESENT.name();
        else if(totalTime> 4*60 && totalTime < 8*60)
            employeeStatus = EmployeeStatus.HALFDAY.name();
        else employeeStatus = EmployeeStatus.ABSENT.name();

            return employeeStatus;
    }

    void getTotalTime(){

    }
}
