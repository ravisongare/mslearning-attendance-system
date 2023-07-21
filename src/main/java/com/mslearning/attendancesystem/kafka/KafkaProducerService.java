package com.mslearning.attendancesystem.kafka;

import com.mslearning.attendancesystem.controller.AttendanceController;
import com.mslearning.attendancesystem.entity.AttendanceRecord;
import com.mslearning.attendancesystem.kafka.message.AttendanceStatus;
import com.mslearning.attendancesystem.response.AttendanceResponse;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
public class KafkaProducerService {

	Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
	Properties properties = new Properties();
	@Value(value = "${spring.kafka.bootstrap-servers}")
	String Boot_Strap_Server;
	String Topic = "attendance";



	public String sendAttendanceStatus(AttendanceStatus empStatus) {
		logger.info("Boot_Strap_Server ::::>{}",Boot_Strap_Server);
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Boot_Strap_Server);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
		
		KafkaProducer<String,AttendanceStatus> producer = new KafkaProducer<String, AttendanceStatus>(properties);
		//ProducerRecord<String, AttendanceResponse> record=new ProducerRecord<String, AttendanceResponse>(Topic,new AttendanceResponse(1,"present"));
		ProducerRecord<String, AttendanceStatus> record=new ProducerRecord<String, AttendanceStatus>(Topic,empStatus);
		producer.send(record);
			
		producer.flush();
		producer.close();
		return "success";
		
	}
	
//	public static void main(String[] args) {
//		System.out.println("Calling sendWelcomeMessage");
//		new KafkaProducerService().sendWelcomeMessage();
//	}
	
}
