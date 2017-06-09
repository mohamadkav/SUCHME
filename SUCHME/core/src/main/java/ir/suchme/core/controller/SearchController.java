package ir.suchme.core.controller;

import ir.suchme.common.dto.component.RequestSearchComponentDTO;
import ir.suchme.common.dto.component.ResponseSearchComponentDTO;
import ir.suchme.core.service.SearchService;
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
@RequestMapping("search")
public class SearchController {

    private final SearchService searchService;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping( method = RequestMethod.POST,value = "/component")
    public ResponseSearchComponentDTO searchComponent(@RequestBody RequestSearchComponentDTO dto) {
        long startTime = System.currentTimeMillis();
        ResponseSearchComponentDTO response =new ResponseSearchComponentDTO();
        try {
            dto.validation();
            response = searchService.searchComponent(dto);
            LOG.info("search : Success | Code: {}", response.getResponseCode());
            return response;
        } catch (Exception|AssertionError e) {
            long finishTime = System.currentTimeMillis();
            response.setError(e.getMessage());
            response.setResponseCode("-1");
            LOG.error("search : Failed | finished in {} ms", String.valueOf(TimeUnit.MILLISECONDS.toMillis(finishTime - startTime)), e);
            return response;
        }
    }
}
