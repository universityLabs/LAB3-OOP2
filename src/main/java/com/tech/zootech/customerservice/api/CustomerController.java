package com.tech.zootech.customerservice.api;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.dto.CustomerDto;
import com.tech.zootech.customerservice.domain.dto.CustomerFullName;
import com.tech.zootech.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerCustomer(@Valid @RequestBody CustomerRegistrationData customerData) {
        return ResponseEntity.ok(customerService.registerCustomer(customerData));
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadCustomers(@RequestParam MultipartFile file) {
        customerService.uploadCustomers(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/names")
    public ResponseEntity<List<CustomerFullName>> getNames() {
        return ResponseEntity.ok(customerService.getNames());
    }
}
