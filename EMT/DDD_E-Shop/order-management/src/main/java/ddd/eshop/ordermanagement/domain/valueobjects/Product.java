package ddd.eshop.ordermanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import ddd.eshop.sharedkernel.domain.base.ValueObject;
import ddd.eshop.sharedkernel.domain.financial.Money;
import lombok.Getter;

import java.util.Currency;
import java.util.Locale;

@Getter
public class Product implements ValueObject {

    private final ProductId id;
    private final String name;
    private final Money price;
    private final String description;


    @JsonCreator
    public Product(ProductId id, String name, Money price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    private Product(){
        this.id=ProductId.randomId(ProductId.class);
        this.name= "";
        this.price= Money.valueOf(Currency.getInstance(Locale.getDefault()), 0);
        this.description = "";
    }
}
