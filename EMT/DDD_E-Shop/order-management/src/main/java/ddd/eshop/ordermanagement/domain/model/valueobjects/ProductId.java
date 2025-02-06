package ddd.eshop.ordermanagement.domain.model.valueobjects;

import ddd.eshop.sharedkernel.domain.base.DomainObjectId;
import jakarta.persistence.Embeddable;
import org.apache.kafka.common.protocol.types.Field;

@Embeddable
public class ProductId extends DomainObjectId {
    private ProductId() {
        super(ProductId.randomId(ProductId.class).getId());
    }

    public ProductId(String uuid) {
        super(uuid);
    }
}
