package vn.fis.finaltest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.finaltest.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
