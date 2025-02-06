package ddd.eshop.sharedkernel.domain.events.orders;

import ddd.eshop.sharedkernel.domain.configs.TopicHolder;
import ddd.eshop.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class OrderItemRemoved extends DomainEvent {
    private String productId;
    private int quantity;

    public OrderItemRemoved(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
    }

    public OrderItemRemoved(String topic, String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
        this.productId = productId;
        this.quantity = quantity;
    }

}
