package ddd.eshop.sharedkernel.domain.events.orders;

import ddd.eshop.sharedkernel.domain.configs.TopicHolder;
import ddd.eshop.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class OrderItemCreated extends DomainEvent {
    private String productId;
    private int quantity;

    public OrderItemCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }

    public OrderItemCreated(String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.productId = productId;
        this.quantity = quantity;
    }

}
