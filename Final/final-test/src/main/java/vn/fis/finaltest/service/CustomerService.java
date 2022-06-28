package vn.fis.finaltest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.fis.finaltest.dto.CustomerDTO;
import vn.fis.finaltest.model.Customer;

public interface CustomerService {
    Page<CustomerDTO> findAll(Pageable pageable);

    CustomerDTO findById(Long customerId);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long customerId,CustomerDTO customerDTO);

    void deleteCustomerById(Long customerId);
}
