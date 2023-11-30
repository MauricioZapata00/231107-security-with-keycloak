package com.msbills.controller;

import com.msbills.models.Bill;
import com.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('LEFT_BIG_EAR_USER_RIGHT_BIG_EAR')")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok().body(service.getAllBill());
    }

    @GetMapping("customerBill/{id}")
    @PreAuthorize("hasAnyAuthority('LEFT_BIG_EAR_USER_RIGHT_BIG_EAR')")
    public ResponseEntity<List<Bill>> getBillByCustomerBill(@PathVariable("id") String customerBill) {
        return ResponseEntity.ok().body(service.getBillsByCustomerBill(customerBill));
    }

    @GetMapping("customerId/{id}")
    @PreAuthorize("hasAnyAuthority('LEFT_BIG_EAR_USER_RIGHT_BIG_EAR')")
    public ResponseEntity<List<Bill>> getBillByCustomerId(@PathVariable("id") String customerId) {
        return ResponseEntity.ok().body(service.getBillsByCustomerId(customerId));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ELVES_RACE/PROVIDERS')")
    public ResponseEntity<Bill> createBill(@RequestBody Bill billToCreate){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createBill(billToCreate));
    }

}
