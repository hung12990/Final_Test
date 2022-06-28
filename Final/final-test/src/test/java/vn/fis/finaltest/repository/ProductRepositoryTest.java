package vn.fis.finaltest.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.finaltest.model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindAllProduct() {
        List<Product> products = productRepository.findAll();
        log.info("Size Product: {}", products.size());
        products.forEach(product -> log.info("Product: {}",product));
        Assertions.assertThat(3).isEqualTo(products.size());
    }

    @Test
    void testFindProductById() {
        Product product = productRepository.findById(1L).get();
        log.info("Product: {}", product);
        Assertions.assertThat("gao viet").isEqualTo(product.getName());
    }

}