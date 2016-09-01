package edu.proyectofinal.integradorrs.services.transaction.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyectofinal.integradorrs.model.Transaction;
import edu.proyectofinal.integradorrs.repositorys.Repository;
import edu.proyectofinal.integradorrs.services.transaction.TransactionService;

import java.util.*;

/**
 * Created by mariano on 24/03/16.
 */
@Service("TransactionServiceMongo")
public class TransactionServiceMongo implements TransactionService{
    @Autowired
    private Repository repository;

    public Collection<Transaction> getAll(){
        Collection<Transaction> result = repository.findAll();
        return result;
    }

    @Override
    public Transaction getById(String id) {
        return repository.findOne(id);
    }

    public Transaction save(Transaction t){
        return repository.insert(t);
    }
}
