package ddd.eshop.producthandler.services.forms;

import ddd.eshop.sharedkernel.domain.financial.Money;
import lombok.Data;

@Data
public class ProductForm {

    private String productName;
    private Money price;
    private int sales;
}
