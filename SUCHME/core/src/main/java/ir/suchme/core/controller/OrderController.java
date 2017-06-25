package ir.suchme.core.controller;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.order.RequestOrderComponentDTO;
import ir.suchme.core.component.UserLoggingComponent;
import ir.suchme.core.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by mohammad on 6/25/17.
 */

@RestController
@RequestMapping("order")
public class OrderController {

    private final UserLoggingComponent userLoggingComponent;
    private final OrderService orderService;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public OrderController(UserLoggingComponent userLoggingComponent, OrderService orderService) {
        this.userLoggingComponent = userLoggingComponent;
        this.orderService = orderService;
    }

    @RequestMapping( method = RequestMethod.POST,value = "/component")
    public BaseResponseDTO orderComponent(@RequestBody RequestOrderComponentDTO request) {
        userLoggingComponent.logUserActivity(SecurityContextHolder.getContext().getAuthentication().getName(),getClass().getSimpleName(),new Object(){}.getClass().getEnclosingMethod().getName(),request);

        long startTime = System.currentTimeMillis();
        BaseResponseDTO response =new BaseResponseDTO();
        try {
            request.validation();
            response = orderService.orderComponent(request);
            LOG.info("OrderController : orderComponent : Success | : {}", response.getResponseCode());
            return response;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            response.setError(e.getMessage());
            response.setResponseCode("-1");
            LOG.error("OrderController : orderComponent | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return response;
        }
    }

}
