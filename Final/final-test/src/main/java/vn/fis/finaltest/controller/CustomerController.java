package vn.fis.finaltest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import vn.fis.finaltest.dto.CustomerDTO;
import vn.fis.finaltest.model.Customer;
import vn.fis.finaltest.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<CustomerDTO> findAll(@PathVariable(name="pageNumber") Integer pageNumber,
                                     @PathVariable(name="pageSize") Integer pageSize) {
        log.info("Request All Customer. PageNumber: {}, PageSize: {}", pageNumber, pageSize);
        return customerService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/{customerId}")
    public CustomerDTO findById(@PathVariable(name = "customerId") Long customerId) {
        return customerService.findById(customerId);
    }

    @PutMapping("/{pageNumber}/{pageSize}")
    public Page<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO,
                                            @PathVariable(name="pageNumber") Integer pageNumber,
                                            @PathVariable(name="pageSize") Integer pageSize) {
        customerService.createCustomer(customerDTO);
        return customerService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @PostMapping("/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable(name = "customerId") Long customerId,
                                      @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerId, customerDTO);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable(name = "customerId") Long customerId) {
        customerService.deleteCustomerById(customerId);
    }
}
