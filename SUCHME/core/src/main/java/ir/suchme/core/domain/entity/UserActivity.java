package ir.suchme.core.domain.entity;

import ir.suchme.core.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mohammad on 2017-06-13.
 */
@Entity
@Table(name = "USER_ACTIVITY")
public class UserActivity extends BaseEntity{
    @Column
    private String component;

    @Column
    private String method;

    @ManyToOne
    @JoinColumn(name = "USERID")
    private User user;

    @Column
    private String description;


    public UserActivity(String component, String method, User user, String description) {
        this.component = component;
        this.method = method;
        this.user = user;
        this.description = description;
    }

    public UserActivity() {
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
