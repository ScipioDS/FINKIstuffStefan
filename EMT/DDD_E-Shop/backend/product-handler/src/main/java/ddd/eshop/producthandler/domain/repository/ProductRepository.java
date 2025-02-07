package ddd.eshop.producthandler.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ddd.eshop.producthandler.domain.models.Product;
import ddd.eshop.producthandler.domain.models.ProductId;


public interface ProductRepository extends JpaRepository<Product, ProductId> {
}
