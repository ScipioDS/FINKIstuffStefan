package ddd.eshop.producthandler.services.implementation;

import ddd.eshop.producthandler.domain.exceptions.ProductNotFoundException;
import ddd.eshop.producthandler.domain.models.Product;
import lombok.AllArgsConstructor;
import ddd.eshop.producthandler.domain.models.ProductId;
import ddd.eshop.producthandler.domain.repository.ProductRepository;
import ddd.eshop.producthandler.services.ProductService;
import ddd.eshop.producthandler.services.forms.ProductForm;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product findById(ProductId id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product createProduct(ProductForm form) {
        Product p = Product.build(form.getProductName(), form.getPrice(), form.getSales());
        productRepository.save(p);
        return p;
    }

    @Override
    public Product orderItemCreated(ProductId productId, int quantity) {
        Product p = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        p.addSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public Product orderItemRemoved(ProductId productId, int quantity) {
        Product p = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        p.removeSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

}
