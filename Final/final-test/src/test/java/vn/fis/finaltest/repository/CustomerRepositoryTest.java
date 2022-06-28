package vn.fis.finaltest.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.finaltest.model.Customer;
import org.assertj.core.api.Assertions;


import java.util.List;


@SpringBootTest
@Slf4j
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testFindAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        log.info("Size Customer: {}", customers.size());
        customers.forEach(customer -> log.info("Customer: {}",customer));
        Assertions.assertThat(3).isEqualTo(customers.size());
    }

    @Test
    void testFindCustomerById() {
        Customer customer = customerRepository.findById(1L).get();
        log.info("Customer: {}", customer);
        Assertions.assertThat("0384690214").isEqualTo(customer.getMobile());
    }
}