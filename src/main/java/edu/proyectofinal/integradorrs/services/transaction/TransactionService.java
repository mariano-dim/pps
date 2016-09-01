package edu.proyectofinal.integradorrs.services.transaction;

import edu.proyectofinal.integradorrs.model.Transaction;

import java.util.Collection;

/**
 * Created by mariano on 25/03/16.
 */
public interface TransactionService {
    Collection<Transaction> getAll();
    Transaction getById(String id);
    Transaction save(Transaction t);

}
