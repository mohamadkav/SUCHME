package ir.suchme.core.controller;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.RequestUpdateComponentMinMaxDTO;
import ir.suchme.common.dto.product.RequestChangeSupplyPathDTO;
import ir.suchme.common.dto.product.RequestGetDifferentPathsDTO;
import ir.suchme.common.dto.product.ResponseGetCurrentPathDTO;
import ir.suchme.common.dto.product.ResponseGetDifferentPathsDTO;
import ir.suchme.core.component.UserLoggingComponent;
import ir.suchme.core.domain.entity.User;
import ir.suchme.core.service.WarehouseService;
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
 * Created by mohammad on 6/9/17.
 */
@RestController
@RequestMapping("warehouse")
public class WarehouseController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final WarehouseService warehouseService;
    private final UserLoggingComponent userLoggingComponent;

    @Autowired
    public WarehouseController(WarehouseService warehouseService, UserLoggingComponent userLoggingComponent) {
        this.warehouseService = warehouseService;
        this.userLoggingComponent = userLoggingComponent;
    }

    @RequestMapping( method = RequestMethod.POST,value = "/updateminmax")
    public BaseResponseDTO updateMinMax(@RequestBody RequestUpdateComponentMinMaxDTO dto) {
        userLoggingComponent.logUserActivity(SecurityContextHolder.getContext().getAuthentication().getName(),getClass().getSimpleName(),new Object(){}.getClass().getEnclosingMethod().getName(),dto);

        long startTime = System.currentTimeMillis();
        BaseResponseDTO baseResponseDTO =new BaseResponseDTO();
        try {
            dto.validation();
            baseResponseDTO = warehouseService.updateMinMax(dto);
            LOG.info("updateMinMax : Success | Code: {}", baseResponseDTO.getResponseCode());
            return baseResponseDTO;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            baseResponseDTO.setError(e.getMessage());
            baseResponseDTO.setResponseCode("-1");
            LOG.error("updateMinMax : Failed | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return baseResponseDTO;
        }
    }
    @RequestMapping( method = RequestMethod.POST,value = "/getDifferentPaths")
    public ResponseGetDifferentPathsDTO getDifferentPaths(@RequestBody RequestGetDifferentPathsDTO dto) {
        userLoggingComponent.logUserActivity(SecurityContextHolder.getContext().getAuthentication().getName(),getClass().getSimpleName(),new Object(){}.getClass().getEnclosingMethod().getName(),dto);

        long startTime = System.currentTimeMillis();
        ResponseGetDifferentPathsDTO response =new ResponseGetDifferentPathsDTO();
        try {
            dto.validation();
            response = warehouseService.getDifferentPaths(dto);
            LOG.info("getDifferentPaths : Success | Code: {}", response.getResponseCode());
            return response;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            response.setError(e.getMessage());
            response.setResponseCode("-1");
            LOG.error("getDifferentPaths : Failed | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return response;
        }
    }
    @RequestMapping( method = RequestMethod.POST,value = "/getCurrentPath")
    public ResponseGetCurrentPathDTO getCurrentPath(@RequestBody RequestGetDifferentPathsDTO dto) {
        userLoggingComponent.logUserActivity(SecurityContextHolder.getContext().getAuthentication().getName(),getClass().getSimpleName(),new Object(){}.getClass().getEnclosingMethod().getName(),dto);

        long startTime = System.currentTimeMillis();
        ResponseGetCurrentPathDTO response =new ResponseGetCurrentPathDTO();
        try {
            dto.validation();
            response = warehouseService.getCurrentPath(dto);
            LOG.info("getCurrentPath : Success | Code: {}", response.getResponseCode());
            return response;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            response.setError(e.getMessage());
            response.setResponseCode("-1");
            LOG.error("getCurrentPath : Failed | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return response;
        }
    }
    @RequestMapping( method = RequestMethod.POST,value = "/changePath")
    public BaseResponseDTO changePath(@RequestBody RequestChangeSupplyPathDTO dto) {
        userLoggingComponent.logUserActivity(SecurityContextHolder.getContext().getAuthentication().getName(),getClass().getSimpleName(),new Object(){}.getClass().getEnclosingMethod().getName(),dto);

        long startTime = System.currentTimeMillis();
        BaseResponseDTO response =new BaseResponseDTO();
        try {
            dto.validation();
            response = warehouseService.changeCurrentPath(dto);
            LOG.info("changePath : Success | Code: {}", response.getResponseCode());
            return response;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            response.setError(e.getMessage());
            response.setResponseCode("-1");
            LOG.error("changePath : Failed | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return response;
        }
    }
}
