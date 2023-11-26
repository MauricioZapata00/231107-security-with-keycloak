package com.example.msusers.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BillDto {
    private String idBill;
    private String customerBill;
    private String productBill;
    private Double totalPrice;
}
