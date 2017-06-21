package ir.suchme.core.controller;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.user.*;
import ir.suchme.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by mohammad on 5/21/17.
 */
@RestController
@RequestMapping("user")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping( method = RequestMethod.POST,value = "/login")
    public ResponseUserDTO auth(HttpServletRequest request,@RequestBody RequestAuthenticateDTO dto) {
        System.out.println(request.getSession().getId());
        long startTime = System.currentTimeMillis();
        ResponseUserDTO responseUserDTO =new ResponseUserDTO();
        try {
            dto.validation();
            responseUserDTO = userService.auth(request,dto);
            LOG.info("Login : Success | Auth: {}", responseUserDTO.getResponseCode());
            return responseUserDTO;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            responseUserDTO.setError(e.getMessage());
            responseUserDTO.setResponseCode("-1");
            LOG.error("UserController : auth | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return responseUserDTO;
        }
    }

    @RequestMapping( method = RequestMethod.POST,value = "/create")
    public BaseResponseDTO create(@RequestBody RequestCreateUserDTO request) {
        long startTime = System.currentTimeMillis();
        BaseResponseDTO baseResponseDTO =new BaseResponseDTO();
        try {
            request.validation();
            baseResponseDTO = userService.create(request);
            LOG.info("create : Success | Auth: {}", baseResponseDTO.getResponseCode());
            return baseResponseDTO;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            baseResponseDTO.setError(e.getMessage());
            baseResponseDTO.setResponseCode("-1");
            LOG.error("UserController : create | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return baseResponseDTO;
        }
    }

    @RequestMapping( method = RequestMethod.POST,value = "/activity")
    public ResponseUserActivityListDTO activities(@RequestBody RequestUserActivityListDTO request) {
        long startTime = System.currentTimeMillis();
        ResponseUserActivityListDTO response =new ResponseUserActivityListDTO();
        try {
            request.validation();
            response = userService.getActivities(request);
            LOG.info("UserController : activities : Success | : {}", response.getResponseCode());
            return response;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            response.setError(e.getMessage());
            response.setResponseCode("-1");
            LOG.error("UserController : activities | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return response;
        }
    }

    @RequestMapping( method = RequestMethod.GET,value = "/list")
    public ArrayList<ResponseUserDTO> list() {
        long startTime = System.currentTimeMillis();
        BaseResponseDTO baseResponseDTO =new BaseResponseDTO();
        return userService.list();
    }


    @RequestMapping(method = RequestMethod.GET,value = "/test")
    public String test() {
        return "OK";
    }
}