package ir.suchme.core.component;

import ir.suchme.common.dto.base.RequestDTO;
import ir.suchme.core.domain.entity.UserActivity;
import ir.suchme.core.domain.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mohammad on 2017-06-13.
 */

@Component
public class UserLoggingComponent {
    private final UserActivityRepository userActivityRepository;

    @Autowired
    public UserLoggingComponent(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }


    public void logUserActivity(String user, String componentName, String methodName,RequestDTO requestDTO){
        userActivityRepository.save(new UserActivity(componentName,methodName,user,requestDTO.toString()));
    }
}
