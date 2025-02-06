package ddd.eshop.ordermanagement.service.implementation;

import ddd.eshop.ordermanagement.domain.model.Order;
import ddd.eshop.ordermanagement.domain.model.OrderId;
import ddd.eshop.ordermanagement.domain.model.repository.OrderRepository;
import ddd.eshop.ordermanagement.service.OrderService;
import ddd.eshop.ordermanagement.service.forms.OrderForm;
import ddd.eshop.ordermanagement.service.forms.OrderItemForm;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.xml.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final Validator validator;
    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "Order cannot be null!");
        var constraintViolations = validator.validate(orderForm);
        if(constraintViolations.size()>0){
            throw new ConstraintViolationException("Invalid Form!", constraintViolations);
        }
    }

    @Override
    public List<Order> finaAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById() {
        return Optional.empty();
    }

    @Override
    public void deleteItem() {

    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteItem(OrderId id) {
        return;
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) {

    }
}
