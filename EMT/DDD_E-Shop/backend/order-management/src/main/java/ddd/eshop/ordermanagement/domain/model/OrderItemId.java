package ddd.eshop.ordermanagement.domain.model;

import ddd.eshop.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

public class OrderItemId extends DomainObjectId {
    protected OrderItemId(@NonNull String uuid) {
        super(uuid);
    }
}
