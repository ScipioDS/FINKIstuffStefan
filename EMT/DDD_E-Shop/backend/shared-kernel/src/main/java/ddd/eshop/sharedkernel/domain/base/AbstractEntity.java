package ddd.eshop.sharedkernel.domain.base;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.util.ProxyUtils;

import java.util.Objects;
@Getter
@MappedSuperclass
public class AbstractEntity<ID extends DomainObjectId> {

    @EmbeddedId
    private ID id;

    protected AbstractEntity() {
    }

    /**
     * Copy constructor
     *
     * @param source the entity to copy from.
     */
    protected AbstractEntity(@NonNull AbstractEntity<ID> source) {
        Objects.requireNonNull(source, "source must not be null");
        this.id = source.id;
    }

    protected AbstractEntity(@NonNull ID id) {
        this.id = Objects.requireNonNull(id, "id must not be null");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        var other = (AbstractEntity<?>) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", getClass().getSimpleName(), id);
    }

}
