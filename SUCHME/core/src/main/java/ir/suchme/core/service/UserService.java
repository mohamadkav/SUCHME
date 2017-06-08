package ir.suchme.core.service;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.user.RequestAuthenticateDTO;
import ir.suchme.common.dto.user.ResponseUserDTO;
import ir.suchme.core.domain.entity.User;
import ir.suchme.core.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by mohammad on 5/21/17.
 */
@Service
public class UserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public ResponseUserDTO auth(HttpServletRequest httpServletRequest, RequestAuthenticateDTO request) throws Exception {
        User user=userRepository.findByUserNameAndDeletedIsFalse(request.getUserName());
        ResponseUserDTO response;
        if(user==null)
            response=new ResponseUserDTO("User not found",null,"301");
        else {
            response = new ResponseUserDTO(user.getId().toString(), "0");
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());

            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(authRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            // Create a new session and add the security context.
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        }
        return response;
    }

    public BaseResponseDTO create(RequestAuthenticateDTO request){
        if(userRepository.findByUserNameAndDeletedIsFalse(request.getUserName())!=null)
            return new BaseResponseDTO("User already exists","400",null);
        User user=new User();
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setCreated(new Date());
        userRepository.save(user);
        return new BaseResponseDTO(null,"0",null);
    }

    public ArrayList<ResponseUserDTO> list(){
        ArrayList<ResponseUserDTO> dtos = new ArrayList<>();
        for (User u: userRepository.findAll())
        {
            ResponseUserDTO dto = new ResponseUserDTO();
            dto.setUserId(u.getId().toString());
            dtos.add(dto);
        }
        return dtos;
    }
}
