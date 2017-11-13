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



	@Override
	public Llave save(Llave llave) {

		llave.setCreationDate(new Date());
		repository.save(llave);
		return llave;
	}

	@Override
	public Llave getByPublicIdentification(String publicIdentification){
		return repository.findByPublicIdentification(publicIdentification);
	}

	
	@Override
	public void addPuerta(String llavepublicIdentification, String puertapublicIdentification) {

		repository.addPuerta(llavepublicIdentification, puertapublicIdentification);

	}


	@Override
	public void removePuerta(String llavepublicIdentification, String puertapublicIdentification) {

		repository.removePuerta(llavepublicIdentification, puertapublicIdentification);

	}

}
