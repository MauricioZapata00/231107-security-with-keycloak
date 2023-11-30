package com.example.msusers.client;

import com.example.msusers.configuration.feign.FeignConfiguration;
import com.example.msusers.dto.BillDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-bill", configuration = FeignConfiguration.class)
public interface BillClient {
    @GetMapping("/bills/all")
    ResponseEntity<List<BillDto>> getListOfBills();

    @GetMapping("/bills/customerId/{id}")
    @PreAuthorize("hasAnyAuthority('LEFT_BIG_EAR_USER_RIGHT_BIG_EAR')")
    ResponseEntity<List<BillDto>> getBillsByCustomerId(@PathVariable("id") String customerId);
}
