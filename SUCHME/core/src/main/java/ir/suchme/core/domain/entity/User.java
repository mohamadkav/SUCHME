package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mohammad on 5/22/17.
 */

@Entity
@Table(name = "T_CORE_USER")
public class User extends BaseEntity{
    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "T_USER_ROLE", joinColumns = {
            @JoinColumn(name = "USERID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "ROLEID",
                    nullable = false, updatable = false) })
    private Set<Role> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
