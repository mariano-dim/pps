package edu.pps.integradorrs.services.puerta.impl;

import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import edu.pps.integradorrs.model.Puerta;

import edu.pps.integradorrs.repositorys.PuertaRepository;
import edu.pps.integradorrs.services.puerta.PuertaService;

import java.util.Date;

@Service
public class PuertaServiceImpl implements PuertaService {

	@Autowired
	private PuertaRepository repository;

	@Override
	public Collection<Puerta> getAllPuertas() {
		Collection<Puerta> result = (Collection<Puerta>) repository.findAll();
		return result;
	}

	@Override
	public Puerta save(Puerta puerta) {

		puerta.setCreationDate(new Date());
		repository.save(puerta);
		return puerta;
	}

	@Override
	public Puerta getByPublicIdentification(String publicIdentification){
       return repository.findByPublicIdentification(publicIdentification);
	}


}
