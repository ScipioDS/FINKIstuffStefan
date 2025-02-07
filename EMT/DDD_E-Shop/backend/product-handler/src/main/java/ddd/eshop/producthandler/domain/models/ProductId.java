package ddd.eshop.producthandler.domain.models;

import ddd.eshop.sharedkernel.domain.base.DomainObjectId;

import org.springframework.lang.NonNull;

public class ProductId extends DomainObjectId {

    private ProductId() {
        super(ProductId.randomId(ProductId.class).getId());
    }

    public ProductId(@NonNull String uuid) {
        super(uuid);
    }

    public static ProductId of(String uuid) {
        ProductId p = new ProductId(uuid);
        return p;
    }

}
