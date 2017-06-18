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

    @Column
    private String userName;

    @Column
    private String description;


    public UserActivity(String component, String method, String userName, String description) {
        this.component = component;
        this.method = method;
        this.userName = userName;
        this.description = description;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
