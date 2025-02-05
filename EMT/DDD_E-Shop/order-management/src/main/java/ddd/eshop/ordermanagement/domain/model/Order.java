package ddd.eshop.ordermanagement.domain.model;

import ddd.eshop.sharedkernel.domain.base.AbstractEntity;
import ddd.eshop.sharedkernel.domain.financial.Money;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

//AGREGATE ROOT
@Entity
@Table(name="orders")
public class Order extends AbstractEntity<OrderId> {


    private Instant orderedOn;

    @Enumerated(EnumType.STRING)
    private OrderStatus  orderStatus;

    private Money totalSum;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemList;


    public Order(){
        super(OrderId.randomId(OrderId.class));
    }


}
