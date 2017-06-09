package ir.suchme.core.service;

import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.RequestSearchComponentDTO;
import ir.suchme.common.dto.component.ResponseSearchComponentDTO;
import ir.suchme.core.catalogue.ComponentCatalogue;
import ir.suchme.core.domain.entity.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohammad on 6/9/17.
 */

@Service
public class SearchService {

    private final ComponentCatalogue componentCatalogue;

    @Autowired
    public SearchService(ComponentCatalogue componentCatalogue) {
        this.componentCatalogue = componentCatalogue;
    }

    public ResponseSearchComponentDTO searchComponent(RequestSearchComponentDTO request){
        List<ComponentDTO> componentDTOs=new ArrayList<>();
        for(Component component:componentCatalogue.search(request.getName()))
            componentDTOs.add(new ComponentDTO(component.getName(),component.getPrice(),component.getMaxValue(),component.getMinValue(),component.getId().toString()));
        return new ResponseSearchComponentDTO(null,"0",null,componentDTOs);
    }
}
