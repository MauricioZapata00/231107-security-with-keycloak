package com.msbills.repositories;

import com.msbills.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, String> {

    List<Bill> findAllByCustomerBill(String customerBill);
    List<Bill> findBillByCustomerId(String customerId);
}
