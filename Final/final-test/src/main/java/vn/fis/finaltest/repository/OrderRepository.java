package vn.fis.finaltest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.finaltest.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
