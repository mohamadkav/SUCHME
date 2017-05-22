package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mohammad on 5/22/17.
 */
@Entity
@Table(name = "T_CORE_ROLE")
public class Role extends BaseEntity {
    @Column(name = "NAME")
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User>users;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
