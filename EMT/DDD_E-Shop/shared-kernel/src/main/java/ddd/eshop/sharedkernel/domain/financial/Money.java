package ddd.eshop.sharedkernel.domain.financial;

import ddd.eshop.sharedkernel.domain.base.ValueObject;
import jakarta.persistence.Embeddable;
import lombok.NonNull;


import java.util.Currency;



@Embeddable
public class Money implements ValueObject {

    private final Currency currency;

    private final double amount;


    protected Money(){
        this.currency = null;
        this.amount=0;
    }

    public Money (@NonNull Currency currency, @NonNull double amount ){
        this.currency = currency;
        this.amount = amount;
    }

    public static Money valueOf(Currency currency, double amount){
        return new Money(currency, amount);
    }

    public Money add(Money money){
        if(!currency.equals(money.currency))
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        return  new Money(currency, amount + money.amount);
    }

    public Money substractMoney(Money money){
        //TODO: FIX  money substraction when money object is greater than the referenced object or vice versa!
        if(!currency.equals(money.currency))
            throw new IllegalArgumentException("Cannot substract two Money objects with different currencies");
        return new Money(currency, amount - money.amount);
    }

    public Money  multiply(int times){
        return new Money(currency, amount * times);
    }
}
