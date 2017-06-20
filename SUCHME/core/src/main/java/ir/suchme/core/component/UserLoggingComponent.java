package ir.suchme.core.component;

import ir.suchme.common.dto.base.RequestDTO;
import ir.suchme.core.domain.entity.UserActivity;
import ir.suchme.core.domain.repository.UserActivityRepository;
import ir.suchme.core.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mohammad on 2017-06-13.
 */

@Component
public class UserLoggingComponent {
    private final UserActivityRepository userActivityRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserLoggingComponent(UserActivityRepository userActivityRepository, UserRepository userRepository) {
        this.userActivityRepository = userActivityRepository;
        this.userRepository = userRepository;
    }


    public void logUserActivity(String user, String componentName, String methodName,RequestDTO requestDTO){
        userActivityRepository.save(new UserActivity(componentName,methodName,userRepository.findByUserNameAndDeletedIsFalse(user),requestDTO.toString()));
    }
}
