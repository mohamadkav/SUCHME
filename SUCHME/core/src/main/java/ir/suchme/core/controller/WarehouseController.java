package ir.suchme.core.controller;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.RequestUpdateComponentMinMaxDTO;
import ir.suchme.core.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @RequestMapping( method = RequestMethod.POST,value = "/updateminmax")
    public BaseResponseDTO updateMinMax(@RequestBody RequestUpdateComponentMinMaxDTO dto) {
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
}
