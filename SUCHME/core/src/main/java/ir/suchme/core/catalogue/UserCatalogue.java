package ir.suchme.core.catalogue;

import ir.suchme.common.dto.user.RequestAuthenticateDTO;
import ir.suchme.core.domain.entity.User;
import ir.suchme.core.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by mohammad on 6/8/17.
 */
@Component
public class UserCatalogue {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public UserCatalogue(AuthenticationManager authenticationManager,UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository=userRepository;
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

}
