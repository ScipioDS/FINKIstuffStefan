package ddd.eshop.ordermanagement.domain.model;

import ddd.eshop.ordermanagement.domain.valueobjects.ProductId;
import ddd.eshop.sharedkernel.domain.base.AbstractEntity;
import ddd.eshop.sharedkernel.domain.base.DomainObjectId;
import ddd.eshop.sharedkernel.domain.financial.Money;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NonNull;

@Entity
@Table(name="order_item")
public class OrderItem extends AbstractEntity<OrderItemId> {

    private Money itemPrice;

    @Column(name="quantityOrder", nullable = false)
    private int quantity;

    @AttributeOverride(name="id", column = @Column(name = "product_id", nullable = false))
    private ProductId productId;

    public Money sub_total(){
        return itemPrice.multiply(quantity);
    }

    public OrderItem (@NonNull ProductId productId, @NonNull Money productPrice, int quantity){
        super(DomainObjectId.randomId(OrderItemId.class));
        this.productId = productId;
        this.itemPrice= productPrice;
        this.quantity = quantity;
    }
}
