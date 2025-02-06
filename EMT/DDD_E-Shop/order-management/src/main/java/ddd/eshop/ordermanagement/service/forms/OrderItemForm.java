package ddd.eshop.ordermanagement.service.forms;

import ddd.eshop.ordermanagement.domain.model.valueobjects.Product;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class OrderItemForm {

    @NotNull
    private Product product;

    @Min(3)
    private int quantity = 1;

}
