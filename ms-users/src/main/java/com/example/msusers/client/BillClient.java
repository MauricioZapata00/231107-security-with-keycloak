package com.example.msusers.client;

import com.example.msusers.configuration.feign.FeignConfiguration;
import com.example.msusers.dto.BillDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-bill", configuration = FeignConfiguration.class)
public interface BillClient {
    @GetMapping("/bills/all")
    ResponseEntity<List<BillDto>> getListOfBills();

//    @PreAuthorize("#oauth2.hasScope('breed')")
//    @GetMapping("/my-endpoint")
//    public ResponseEntity<MyObject> getMyObject() {
//        // Your code here
//    }
}
