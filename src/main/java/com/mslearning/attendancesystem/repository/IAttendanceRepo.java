package com.mslearning.attendancesystem.repository;

import com.mslearning.attendancesystem.entity.AttendanceRecord;
import com.mslearning.attendancesystem.enums.TYPE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IAttendanceRepo extends JpaRepository<AttendanceRecord,Integer>{

    List<AttendanceRecord> getByEmpIdAndDateAndType(int empid, Date date,String type);
}
