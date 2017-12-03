package edu.pps.integradorrs.services.log.impl;

import edu.pps.integradorrs.model.Log;
import edu.pps.integradorrs.repositorys.LogRepository;
import java.util.Calendar;
import java.util.Collection;

import edu.pps.integradorrs.services.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;


@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository repository;


    @Override
    public void save(String s) {
        Log log = new Log();
        log.setMessage(s);
        log.setCreationDate(new Date());
        repository.save(log);
    }
}
