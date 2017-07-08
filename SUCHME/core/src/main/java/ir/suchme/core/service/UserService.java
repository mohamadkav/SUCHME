package ir.suchme.core.service;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.user.*;
import ir.suchme.core.catalogue.CustomerCatalogue;
import ir.suchme.core.catalogue.UserCatalogue;
import ir.suchme.core.domain.entity.User;
import ir.suchme.core.domain.entity.UserActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mohammad on 5/21/17.
 */
@Service
public class UserService {

    private final UserCatalogue userCatalogue;
    private final CustomerCatalogue customerCatalogue;
    @Autowired
    public UserService(UserCatalogue userCatalogue, CustomerCatalogue customerCatalogue) {
        this.userCatalogue=userCatalogue;
        this.customerCatalogue = customerCatalogue;
    }

    public ResponseUserDTO auth(HttpServletRequest httpServletRequest, RequestAuthenticateDTO request) throws Exception {
        User user=userCatalogue.findUser(request.getUserName());
        ResponseUserDTO response;
        if(user==null)
            response=new ResponseUserDTO("User not found",null,"301");
        else {
            response = new ResponseUserDTO(user.getId().toString(), "0");
            userCatalogue.loginSpring(httpServletRequest,request);
        }
        return response;
    }

    public BaseResponseDTO create(RequestCreateUserDTO request){
        if(userCatalogue.findUser(request.getUserName())!=null)
            return new BaseResponseDTO("User already exists","400",null);
         customerCatalogue.addCustomer(request.getUserName(),request.getPassword(),request.getName(),request.getEmail());
        return new BaseResponseDTO(null,"0",null);
    }

    public ArrayList<ResponseUserDTO> list(){
        ArrayList<ResponseUserDTO> dtos = new ArrayList<>();
        for (User u: userCatalogue.findAll())
        {
            ResponseUserDTO dto = new ResponseUserDTO();
            dto.setUserId(u.getId().toString());
            dtos.add(dto);
        }
        return dtos;
    }

    public ResponseUserActivityListDTO getActivities(RequestUserActivityListDTO request){
        Pageable pageable = new PageRequest(request.getPage(), request.getSize());
        //Null values need specification. I don't have the time for that. TOF:
        if(request.getFromDate()==null)
            request.setFromDate(0L);
        if(request.getToDate()==null)
            request.setToDate(new Date().getTime());

        ResponseUserActivityListDTO res=new ResponseUserActivityListDTO();
        List<UserActivity> userActivities=userCatalogue.getUserActivitiesByProperties(new Date(request.getFromDate()),
                new Date(request.getToDate()),pageable,request.isEmployee(),request.isCustomer());
        List<UserActivityDTO> userActivityDTOS=new ArrayList<>();
        for(UserActivity userActivity:userActivities)
            userActivityDTOS.add(new UserActivityDTO(userActivity.getComponent(),userActivity.getMethod(),userActivity.getDescription(),
                    userActivity.getUser().getUserName(),userActivity.getUser().getName(),userActivity.getCreated()));
        res.setUserActivityDTOS(userActivityDTOS);
        res.setTotalPages(userCatalogue.totalPages(new Date(request.getFromDate()),new Date(request.getToDate()),request.getSize(),
                request.isEmployee(),request.isCustomer()));
        res.setResponseCode("0");
        return res;
    }

    public BaseResponseDTO forgotPassword(RequestUserForgotPasswordDTO request){
        userCatalogue.changePassword(request.getUserName());
        return new BaseResponseDTO(null,"0",null);
    }
}
