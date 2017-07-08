package ir.suchme.core.catalogue;

import ir.suchme.common.dto.user.RequestAuthenticateDTO;
import ir.suchme.core.domain.entity.User;
import ir.suchme.core.domain.entity.UserActivity;
import ir.suchme.core.domain.repository.UserActivityRepository;
import ir.suchme.core.domain.repository.UserRepository;
import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;
import net.sargue.mailgun.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by mohammad on 6/8/17.
 */
@Component
public class UserCatalogue {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserActivityRepository userActivityRepository;

    @Autowired
    public UserCatalogue(AuthenticationManager authenticationManager, UserRepository userRepository, UserActivityRepository userActivityRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository=userRepository;
        this.userActivityRepository = userActivityRepository;
    }

    public void loginSpring(HttpServletRequest httpServletRequest, RequestAuthenticateDTO request){
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }

    public User findUser(String userName){
        return userRepository.findByUserNameAndDeletedIsFalse(userName);
    }
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public Integer totalPages(Date from,Date to,Integer size,boolean employee,boolean customer){
        Integer count=0;
        if(customer&&employee)
            count=userActivityRepository.countAllByCreatedBetween(from, to).intValue();
        else if (customer)
            count=userActivityRepository.countAllCustomersByCreatedBetween(from, to).intValue();
        else if(employee)
            count=userActivityRepository.countAllEmployeesByCreatedBetween(from, to).intValue();
        return count==0?1:count % size == 0 ? count / size : (count/size)+1;
    }

    public void changePassword(String username){
        User user=userRepository.findByUserNameAndDeletedIsFalse(username);
        if(user==null)
            throw new AssertionError("User not found");
        String pass=UUID.randomUUID().toString();
        user.setPassword(pass);
        userRepository.save(user);
        sendMail(user.getEmail(),pass,"NEW PASSWORD");
    }

    public void sendMail(String email,String text,String subject){
        Configuration configuration = new Configuration()
                .domain("rdcint.ir")
                .apiKey("key-5twn29es60z1suy8jei6qhn6v2z6zur9")
                .from("Webmaster", "noreply@suchme.ir");
        Response response=Mail.using(configuration)
                .to(email)
                .subject(subject)
                .text(text)
                .build()
                .send();
        if(!response.isOk())
            throw new AssertionError(response.responseType().toString());
    }

    public List<UserActivity> getUserActivitiesByProperties(Date from, Date to, Pageable pageable, boolean employee, boolean customer){
        List<UserActivity> userActivities=new ArrayList<>();
        if(employee&&customer)
            userActivities.addAll(userActivityRepository.findAllByCreatedBetween(from,to,pageable));
        else if(employee)
            userActivities.addAll(userActivityRepository.findAllEmployees(from,to,pageable));
        else if(customer)
            userActivities.addAll(userActivityRepository.findAllCustomers(from,to,pageable));
        return userActivities;
    }

}
