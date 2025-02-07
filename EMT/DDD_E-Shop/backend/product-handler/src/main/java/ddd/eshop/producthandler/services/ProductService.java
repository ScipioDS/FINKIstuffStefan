package ddd.eshop.producthandler.services;

import ddd.eshop.producthandler.domain.models.Product;
import ddd.eshop.producthandler.domain.models.ProductId;
import ddd.eshop.producthandler.services.forms.ProductForm;

import java.util.List;

public interface ProductService {
    Product findById(ProductId id);
    Product createProduct(ProductForm form);
    Product orderItemCreated(ProductId productId, int quantity);
    Product orderItemRemoved(ProductId productId, int quantity);
    List<Product> getAll();

}
