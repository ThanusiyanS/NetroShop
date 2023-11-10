package com.deltax.inventorymanagement.Controler;

import com.deltax.inventorymanagement.DTO.TransactionRequest;
import com.deltax.inventorymanagement.Entity.InventoryTransaction;
import com.deltax.inventorymanagement.Exception.InventoryNotFoundException;
import com.deltax.inventorymanagement.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest transactionRequest) throws InventoryNotFoundException {
        transactionService.createTransaction(transactionRequest);

        return new ResponseEntity<>("Transaction created successfully", HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<InventoryTransaction>> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransactions(),HttpStatus.OK);
    }

}
