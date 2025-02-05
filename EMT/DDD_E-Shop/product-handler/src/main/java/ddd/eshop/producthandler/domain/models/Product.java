package ddd.eshop.producthandler.domain.models;

import ddd.eshop.producthandler.domain.models.valueobjects.Quantity;
import ddd.eshop.sharedkernel.domain.base.AbstractEntity;
import ddd.eshop.sharedkernel.domain.financial.Money;
import jakarta.persistence.*;

@Entity
@Table(name="product")
public class Product extends AbstractEntity <ProductId>{

    private String productName;

    private Quantity quantity;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column= @Column(name = "price_amount")),
            @AttributeOverride(name ="currency", column = @Column(name = "price_currency"))
    })
    private Money price;

}
