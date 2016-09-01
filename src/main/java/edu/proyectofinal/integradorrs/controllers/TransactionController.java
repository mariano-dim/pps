package edu.proyectofinal.integradorrs.controllers;

import edu.proyectofinal.integradorrs.exceptions.EmptyResultException;
import edu.proyectofinal.integradorrs.model.Transaction;
import edu.proyectofinal.integradorrs.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by mariano on 24/03/16.
 */
@RestController
@RequestMapping("/api/transaction")
public class TransactionController extends AbstractController<Transaction> {

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Transaction>> getAll() {
		System.out.println("getAll");
		Collection<Transaction> result = transactionService.getAll();
		return super.collectionResult(result);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Transaction> getTxById(@PathVariable("id") String id) {

		System.out.println("getById");
		System.out.println("Id: " + id);
		Transaction result = transactionService.getById(id);
		if (result == null) {
			throw new EmptyResultException(Transaction.class);
		}
		return super.singleResult(result);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Transaction> save(@ModelAttribute Transaction t) {
		if (StringUtils.isEmpty(t.getAmount())) {

		}
		Transaction result = transactionService.save(t);
		return super.createdResult(result);
	}

}
