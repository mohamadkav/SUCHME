package ir.suchme.core.service;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.user.*;
import ir.suchme.core.catalogue.CustomerCatalogue;
import ir.suchme.core.catalogue.UserCatalogue;
import ir.suchme.core.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        ResponseUserActivityListDTO res=new ResponseUserActivityListDTO();
        UserActivityDTO dto=new UserActivityDTO();
        dto.setComponent("comp");
        dto.setDescription("desc");
        dto.setMethod("method");
        dto.setName("hasan");
        dto.setUserName("hasan123");
        List<UserActivityDTO> list=new ArrayList<>();
        list.add(dto);
        res.setUserActivityDTOS(list);
        res.setResponseCode("0");
        return res;
    }
}
