package ddd.eshop.ordermanagement.domain.model;

import ddd.eshop.ordermanagement.domain.valueobjects.Product;
import ddd.eshop.sharedkernel.domain.base.AbstractEntity;
import ddd.eshop.sharedkernel.domain.financial.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.Currency;
import java.util.Objects;
import java.util.Set;

//AGREGATE ROOT
@Getter
@Entity
@Table(name="orders")
public class Order extends AbstractEntity<OrderId> {

    @Column(name = "order_currency")
    private Currency currency;

    private Instant orderedOn;

    @Enumerated(EnumType.STRING)
    private OrderStatus  orderStatus;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemList;


    public Order(Currency currency, Money money){
        super(OrderId.randomId(OrderId.class));
    }
    public Order(Instant now, Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.orderedOn = now;
        this.currency = currency;
    }

    public OrderItem addItem(@NonNull Product product, int quantity) {
        Objects.requireNonNull(product,"Product cannot be null!");
        var item  = new OrderItem(product.getId(), product.getPrice(),quantity);
        orderItemList.add(item);
        return item;
    }
    public void removeItem(@NonNull OrderItemId orderItemId){
        Objects.requireNonNull(orderItemId, "Product cannot be null");
        orderItemList.removeIf(v->v.getId().equals(orderItemId));
    }
    public Money totalSum(){
        return  orderItemList.stream().map(OrderItem::sub_total).reduce(new Money(currency, 0), Money::add);
    }
}
