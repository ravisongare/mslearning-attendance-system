package com.mslearning.attendancesystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mslearning.attendancesystem.entity.AttendanceRecord;
import com.mslearning.attendancesystem.kafka.KafkaProducerService;
import com.mslearning.attendancesystem.kafka.message.AttendanceStatus;
import com.mslearning.attendancesystem.response.AttendanceResponse;
import com.mslearning.attendancesystem.service.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AttendanceController {
    Logger logger = LoggerFactory.getLogger(AttendanceController.class);
    @Autowired
    AttendanceService attendanceService;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @PostMapping("/attendance")
    ResponseEntity<AttendanceResponse> saveAttendance(@RequestBody AttendanceRecord attendanceRecord) {
        logger.info("Inside saveAttendance");
        //JSon Property=> "inTime" : "2019-10-11T09:21:04.981Z"
        AttendanceRecord response = attendanceService.saveData(attendanceRecord);

        return new ResponseEntity<AttendanceResponse>(new AttendanceResponse(response.getId(), "Data Saved"), HttpStatus.OK);
    }

    @GetMapping("attendance/total-time/{empId}")
    ResponseEntity<AttendanceResponse> getTotalTime(@PathVariable int empId, @RequestBody AttendanceRecord record) throws JsonProcessingException {
        logger.info("Inside getTotalTime");
        ObjectMapper mapper = new ObjectMapper();
        String empStatus = attendanceService.getTotalTime(empId);
        System.out.println("sending to kafka " + record);
        // String message =mapper.writeValueAsString( new AttendanceStatus(empId,empStatus));
        kafkaProducerService.sendAttendanceStatus(new AttendanceStatus(empId, empStatus));
        System.out.println("sent to kafka " + record);

        return new ResponseEntity<AttendanceResponse>(new AttendanceResponse(empId, ""), HttpStatus.OK);

    }

}
