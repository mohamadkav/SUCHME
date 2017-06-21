package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Farzin on 6/21/2017.
 */
@Entity
@Table(name = "SUPPLIER")
public class Supplier extends BaseEntity {

    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", cascade = CascadeType.ALL)
    private Set<Component> components;


}
