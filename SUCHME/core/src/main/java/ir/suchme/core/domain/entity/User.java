package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mohammad on 5/22/17.
 */

@Entity
@Table(name = "T_CORE_USER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "USER_TYPE")
public class User extends BaseEntity{
    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "T_USER_ROLE", joinColumns = {
            @JoinColumn(name = "USERID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "ROLEID",
                    nullable = false, updatable = false) })
    private Set<Role> roles;


    public User(String userName, String password, String name, String email) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User() {
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
