package com.msbills.service;

import com.msbills.models.Bill;
import com.msbills.repositories.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository repository;

    public List<Bill> getAllBill() {
        return repository.findAll();
    }

    public List<Bill> getBillsByCustomerBill(String customerBill){
        return repository.findAllByCustomerBill(customerBill);
    }

    public List<Bill> getBillsByCustomerId(String customerId){
        return repository.findBillByCustomerId(customerId);
    }

    public Bill createBill(Bill bill){
        return this.repository.save(bill);
    }

}
