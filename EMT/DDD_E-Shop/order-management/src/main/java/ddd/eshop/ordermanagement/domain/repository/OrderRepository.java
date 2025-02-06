package ddd.eshop.ordermanagement.domain.repository;

import ddd.eshop.ordermanagement.domain.model.Order;
import ddd.eshop.ordermanagement.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {


}
