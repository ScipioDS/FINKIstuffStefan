package ddd.eshop.ordermanagement.domain.model;

import ddd.eshop.ordermanagement.domain.model.valueobjects.ProductId;
import ddd.eshop.sharedkernel.domain.base.AbstractEntity;
import ddd.eshop.sharedkernel.domain.financial.Money;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItem extends AbstractEntity<OrderItemId> {

    private Money itemPrice;

    @Column(name="quantityOrder", nullable = false)
    private int quantity;

    @AttributeOverride(name="id", column = @Column(name = "product_id", nullable = false))
    private ProductId productId;
}
