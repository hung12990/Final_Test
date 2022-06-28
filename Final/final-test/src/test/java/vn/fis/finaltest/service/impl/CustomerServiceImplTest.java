package vn.fis.finaltest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import vn.fis.finaltest.dto.CustomerDTO;
import vn.fis.finaltest.service.CustomerService;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    @Order(1)
    void findAll() {
        Page<CustomerDTO> customerDTOs = customerService.findAll(PageRequest.of(0,4));
        customerDTOs.forEach(customerDTO ->  log.info("CustomerDTO: {}",customerDTO));
        assertEquals(4,customerDTOs.getSize());
    }

    @Test
    @Order(2)
    void findById() {
        CustomerDTO customerDTO = customerService.findById(1L);
        assertEquals("Hà Nội",customerDTO.getAddress());
        assertEquals("0384690214",customerDTO.getMobile());
        assertEquals("Hoàng Văn Hưng",customerDTO.getName());
    }

    @Test
    @Order(3)
    void createCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("Đỗ Anh Thái")
                .address("Hà Nội")
                .mobile("0943667702")
                .build();

        customerService.createCustomer(customerDTO);

        CustomerDTO c = customerService.findById(10L);
        assertEquals("Hà Nội",c.getAddress());
        assertEquals("0943667702",c.getMobile());
        assertEquals("Đỗ Anh Thái",c.getName());
    }

    @Test
    @Order(4)
    void updateCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("Hoàng Lan Thái")
                .address("Hà Nội")
                .mobile("0978233333")
                .build();

        customerService.updateCustomer(10L,customerDTO);
        CustomerDTO c = customerService.findById(10L);
        assertEquals("Hà Nội",c.getAddress());
        assertEquals("0978233333",c.getMobile());
        assertEquals("Hoàng Lan Thái",c.getName());
    }

    @Test
    @Order(5)
    void deleteCustomerById() {
        customerService.deleteCustomerById(10L);
    }
}