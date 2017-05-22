package ir.suchme.core.controller;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.user.RequestAuthenticateDTO;
import ir.suchme.common.dto.user.ResponseUserDTO;
import ir.suchme.core.controller.base.BaseControllerTest;
import ir.suchme.core.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by mohammad on 5/22/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends BaseControllerTest{
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void login(){
        given(userRepository.findByUserNameAndDeletedIsFalse(any(String.class))).willReturn(mockUser());
        ResponseEntity<ResponseUserDTO> responseEntity =
                restTemplate.postForEntity("/user/login",mockReqAuthDTO(), ResponseUserDTO.class);
        ResponseUserDTO client = responseEntity.getBody();
        System.out.println(responseEntity.toString());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("0", client.getResponseCode());
    }

    @Test
    public void create(){
        given(userRepository.findByUserNameAndDeletedIsFalse(any(String.class))).willReturn(null);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            User i=(User) args[0];
            if(i.getId()==null)
                i.setId(UUID.randomUUID());
            return i;
        });
        ResponseEntity<BaseResponseDTO> responseEntity =
                restTemplate.postForEntity("/user/create",mockReqAuthDTO(), BaseResponseDTO.class);
        BaseResponseDTO client = responseEntity.getBody();
        System.out.println(responseEntity.toString());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("0", client.getResponseCode());
    }

    private User mockUser(){
        User user=new User();
        user.setPassword("123456");
        user.setUserName("ali");
        user.setId(UUID.randomUUID());
        return user;
    }

    private RequestAuthenticateDTO mockReqAuthDTO(){
        RequestAuthenticateDTO requestAuthenticateDTO=new RequestAuthenticateDTO();
        requestAuthenticateDTO.setUserName("ali");
        requestAuthenticateDTO.setPassword("123456");
        return requestAuthenticateDTO;
    }
}
