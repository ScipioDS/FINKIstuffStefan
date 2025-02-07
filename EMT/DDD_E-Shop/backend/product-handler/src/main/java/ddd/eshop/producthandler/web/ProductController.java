package ddd.eshop.producthandler.web;

import ddd.eshop.producthandler.domain.models.Product;
import ddd.eshop.producthandler.domain.models.ProductId;
import ddd.eshop.producthandler.services.ProductService;
import ddd.eshop.producthandler.services.forms.ProductForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable ProductId id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductForm form) {
        Product product = productService.createProduct(form);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}/order-created/{quantity}")
    public ResponseEntity<Product> orderCreated(@PathVariable ProductId id, @PathVariable int quantity) {
        Product product = productService.orderItemCreated(id, quantity);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}/order-removed/{quantity}")
    public ResponseEntity<Product> orderRemoved(@PathVariable ProductId id, @PathVariable int quantity) {
        Product product = productService.orderItemRemoved(id, quantity);
        return ResponseEntity.ok(product);
    }
}
