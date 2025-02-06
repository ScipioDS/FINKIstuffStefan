package ddd.eshop.ordermanagement.service;

import ddd.eshop.ordermanagement.domain.model.Order;
import ddd.eshop.ordermanagement.domain.model.OrderId;
import ddd.eshop.ordermanagement.service.forms.OrderForm;
import ddd.eshop.ordermanagement.service.forms.OrderItemForm;

import java.util.List;
import java.util.Optional;

//Service kade shto se izvrshuvaat site logiki na transakciite
public interface OrderService {
    OrderId placeOrder(OrderForm orderForm);

    List<Order> finaAll();

    Optional<Order> findById();

    void deleteItem ();

    void addItem(OrderId orderId, OrderItemForm orderItemForm);
}
