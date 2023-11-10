package com.deltax.ordermanagement.DTO;

import com.deltax.ordermanagement.ENUM.TransactionType;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRequest {
    private String userId;
    private List<OrderItem> inventories;
    private TransactionType transactionType;
}

