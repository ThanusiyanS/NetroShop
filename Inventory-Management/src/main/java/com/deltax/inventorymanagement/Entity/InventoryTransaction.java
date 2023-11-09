package com.deltax.inventorymanagement.Entity;

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

@Data
@Document
public class InventoryTransaction {
    @Id
    private String transactionId;
    private String skuCode;
    private long quantity;
    private TransactionType transactionType;
    private LocalDateTime transactionTime;


    public void setCTransactionTime() {
        LocalDateTime now = LocalDateTime.now();
        ZoneId sriLankaTimeZone = ZoneId.of("Asia/Colombo");
        ZonedDateTime sriLankaTime = now.atZone(sriLankaTimeZone);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedTime = sriLankaTime.format(formatter);

        this.transactionTime = sriLankaTime.toLocalDateTime();

    }
}


