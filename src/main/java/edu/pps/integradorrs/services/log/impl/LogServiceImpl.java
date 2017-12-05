package edu.pps.integradorrs.services.log.impl;

import edu.pps.integradorrs.model.Log;
import edu.pps.integradorrs.model.Hit;
import edu.pps.integradorrs.repositorys.LogRepository;

import java.util.Calendar;
import java.util.Collection;

import edu.pps.integradorrs.services.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import org.springframework.kafka.core.KafkaTemplate;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository repository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    String kafkaTopic = "hits_topic";

    @Override
    public void save(String s) {
        Log log = new Log();
        log.setMessage(s);
        log.setCreationDate(new Date());
        repository.save(log);
    }


    @Override
    public void send(String codigoMensaje, String mensaje) {

        kafkaTemplate.send(kafkaTopic, codigoMensaje + " : " + mensaje );
    }
}
