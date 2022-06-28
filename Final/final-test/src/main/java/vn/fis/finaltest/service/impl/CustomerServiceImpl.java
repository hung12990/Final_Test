package vn.fis.finaltest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.fis.finaltest.controller.exceptionhandler.notfoundexception.CustomerNotFoundException;
import vn.fis.finaltest.controller.exceptionhandler.otherexception.CustomerMobileException;
import vn.fis.finaltest.dto.CustomerDTO;
import vn.fis.finaltest.model.Customer;
import vn.fis.finaltest.repository.CustomerRepository;
import vn.fis.finaltest.service.CustomerService;

import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        log.info("Query all Customer. PageNumber: {}, PageSize: {}"
                , pageable.getPageNumber(), pageable.getPageSize());
        return customerRepository.findAll(pageable).map(CustomerDTO.Mapper::fromEntity);
    }

    @Override
    public CustomerDTO findById(Long customerId) {
        return CustomerDTO.Mapper.fromEntity(customerRepository.findById(customerId).orElseThrow(() -> {
            throw new CustomerNotFoundException(customerId);
        }));
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Optional<Customer> opt = customerRepository.findByMobile(customerDTO.getMobile());
        if(opt.isPresent()) {
            throw new CustomerMobileException(customerDTO.getMobile());
        }
        Customer customer = Customer.builder()
                .address(customerDTO.getAddress())
                .name(customerDTO.getName())
                .mobile(customerDTO.getMobile())
                .build();
        customerRepository.save(customer);
        return CustomerDTO.Mapper.fromEntity(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId,CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> {
            throw new CustomerNotFoundException(customerId);
        });
        customer.setAddress(customerDTO.getAddress());
        customer.setMobile(customerDTO.getMobile());
        customer.setName(customerDTO.getName());
        customerRepository.save(customer);
        return CustomerDTO.Mapper.fromEntity(customer);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        try{
            customerRepository.deleteById(customerId);
        } catch (IllegalArgumentException ex) {
            log.info(String.format("Id %s khong ton tai",customerId));
        }
    }

}
