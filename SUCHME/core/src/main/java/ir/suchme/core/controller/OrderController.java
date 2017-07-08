package ir.suchme.core.controller;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.order.RequestOrderComponentDTO;
import ir.suchme.common.dto.order.RequestOrderConfirmDTO;
import ir.suchme.common.dto.order.RequestOrderListDTO;
import ir.suchme.common.dto.order.ResponseOrderListDTO;
import ir.suchme.common.dto.process.RequestProductManufactureProcess;
import ir.suchme.common.dto.product.RequestCreateMiddlewareProduct;
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
    @RequestMapping( method = RequestMethod.POST,value = "/list")
    public ResponseOrderListDTO listOrders(@RequestBody RequestOrderListDTO request) {
        userLoggingComponent.logUserActivity(SecurityContextHolder.getContext().getAuthentication().getName(),getClass().getSimpleName(),new Object(){}.getClass().getEnclosingMethod().getName(),request);

        long startTime = System.currentTimeMillis();
        ResponseOrderListDTO response =new ResponseOrderListDTO();
        try {
            request.validation();
            response = orderService.list(request);
            LOG.info("OrderController : list : Success | : {}", response.getResponseCode());
            return response;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            response.setError(e.getMessage());
            response.setResponseCode("-1");
            LOG.error("OrderController : list | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return response;
        }
    }

    @RequestMapping( method = RequestMethod.POST,value = "/confirm")
    public BaseResponseDTO confirm(@RequestBody RequestOrderConfirmDTO request) {
        userLoggingComponent.logUserActivity(SecurityContextHolder.getContext().getAuthentication().getName(),getClass().getSimpleName(),new Object(){}.getClass().getEnclosingMethod().getName(),request);

        long startTime = System.currentTimeMillis();
        BaseResponseDTO response =new BaseResponseDTO();
        try {
            request.validation();
            response = orderService.confirm(request);
            LOG.info("OrderController : confirm : Success | : {}", response.getResponseCode());
            return response;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            response.setError(e.getMessage());
            response.setResponseCode("-1");
            LOG.error("OrderController : confirm | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return response;
        }
    }

    @RequestMapping( method = RequestMethod.POST,value = "/process")
    public BaseResponseDTO confirm(@RequestBody RequestProductManufactureProcess request) {
        userLoggingComponent.logUserActivity(SecurityContextHolder.getContext().getAuthentication().getName(),getClass().getSimpleName(),new Object(){}.getClass().getEnclosingMethod().getName(),request);

        long startTime = System.currentTimeMillis();
        BaseResponseDTO response =new BaseResponseDTO();
        try {
            request.validation();
            response = orderService.createManufactureProductProcess(request);
            LOG.info("OrderController : manufacture process : Success | : {}", response.getResponseCode());
            return response;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            response.setError(e.getMessage());
            response.setResponseCode("-1");
            LOG.error("OrderController : manufacture process | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return response;
        }
    }

    @RequestMapping( method = RequestMethod.POST,value = "/middleware_product")
    public BaseResponseDTO confirm(@RequestBody RequestCreateMiddlewareProduct request) {
        userLoggingComponent.logUserActivity(SecurityContextHolder.getContext().getAuthentication().getName(),getClass().getSimpleName(),new Object(){}.getClass().getEnclosingMethod().getName(),request);

        long startTime = System.currentTimeMillis();
        BaseResponseDTO response =new BaseResponseDTO();
        try {
            request.validation();
            response = orderService.createMiddlewareProduct(request);
            LOG.info("OrderController : create middleware process : Success | : {}", response.getResponseCode());
            return response;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            response.setError(e.getMessage());
            response.setResponseCode("-1");
            LOG.error("OrderController : create middleware | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return response;
        }
    }

}
