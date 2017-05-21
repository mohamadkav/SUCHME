package ir.suchme.core.domain.entity.base;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by mohammad on 5/22/17.
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator="system-uuid")
    @Column(name = "c_id", unique = true)
    @Type(type= "org.hibernate.type.PostgresUUIDType")// for postgre
  //  @Type(type="uuid-char")
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "c_modified")
    private Date modified = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "c_created")
    private Date created = new Date();


    @Column(name = "c_deleted")
    private Boolean deleted=false;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getCreated() {
        return created;
    }


    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
