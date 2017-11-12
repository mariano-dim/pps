package edu.pps.integradorrs.services.llave.impl;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import edu.pps.integradorrs.model.Llave;

import edu.pps.integradorrs.repositorys.LlaveRepository;
import edu.pps.integradorrs.services.llave.LlaveService;

import java.util.Date;

@Service
public class LlaveServiceImpl implements LlaveService {

	@Autowired
	private LlaveRepository repository;

	@Override
	public Collection<Llave> getAllLlaves() {
		Collection<Llave> result = (Collection<Llave>) repository.findAll();
		return result;
	}



}
