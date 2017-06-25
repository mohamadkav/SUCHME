package ir.suchme.core.controller;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.prediction.RequestPredictionDTO;
import ir.suchme.common.dto.prediction.ResponsePredictPriceDTO;
import ir.suchme.common.dto.product.ProductDTO;
import ir.suchme.core.component.UserLoggingComponent;
import ir.suchme.core.domain.entity.Supplier;
import ir.suchme.core.domain.repository.SupplierRepository;
import ir.suchme.core.service.PredictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Farzin on 6/21/2017.
 */
@RestController
@RequestMapping("predict")
public class PredictionController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final PredictionService predictionService;
    private final UserLoggingComponent userLoggingComponent;

    @Autowired
    public PredictionController(PredictionService predictionService, UserLoggingComponent userLoggingComponent) {
        this.predictionService = predictionService;
        this.userLoggingComponent = userLoggingComponent;
    }

    @RequestMapping( method = RequestMethod.POST,value = "/price")
    public ResponsePredictPriceDTO predictProductPrice(@RequestBody RequestPredictionDTO dto)
    {
        userLoggingComponent.logUserActivity(SecurityContextHolder.getContext().getAuthentication().getName(),getClass().getSimpleName(),new Object(){}.getClass().getEnclosingMethod().getName(),dto);

        long startTime = System.currentTimeMillis();
        ResponsePredictPriceDTO baseResponseDTO =new ResponsePredictPriceDTO();
        try {
            dto.validation();
            baseResponseDTO = predictionService.predictProductPrice(dto);
            LOG.info("predict product price : Success | Code: {}", baseResponseDTO.getResponseCode());
            return baseResponseDTO;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            baseResponseDTO.setError(e.getMessage());
            baseResponseDTO.setResponseCode("-1");
            LOG.error("predict product price : Failed | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return baseResponseDTO;
        }
    }
}
