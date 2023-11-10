package com.deltax.inventorymanagement.Entity;

import com.deltax.inventorymanagement.DTO.InventoryRequest;
import com.deltax.inventorymanagement.DTO.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Data
@Document
public class InventoryTransaction {
    @Id
    private String transactionId;
    private String userId;
    private List<InventoryRequest> inventories;
    private TransactionType transactionType;
    private LocalDateTime transactionTime;


    public void setCTransactionTime() {
        LocalDateTime now = LocalDateTime.now();
        this.transactionTime = now;

    }
}


