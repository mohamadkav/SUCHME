package ir.suchme.common.dto.product;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.SupplyComponentDTO;
import ir.suchme.common.dto.supplier.SupplierDTO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mohammad on 6/18/17.
 */
public class ResponseGetDifferentPathsDTO extends BaseResponseDTO{
    private HashMap<ComponentDTO,List<SupplierDTO>> pathDTOs;

    @Override
    public String toString() {
        return "{" +
                "pathDTOs=" + pathDTOs +
                '}';
    }

    public HashMap<ComponentDTO, List<SupplierDTO>> getPathDTOs() {
        return pathDTOs;
    }

    public void setPathDTOs(HashMap<ComponentDTO, List<SupplierDTO>> pathDTOs) {
        this.pathDTOs = pathDTOs;
    }

    public ResponseGetDifferentPathsDTO() {

    }

    public ResponseGetDifferentPathsDTO(String error, String responseCode, String userId, HashMap<ComponentDTO, List<SupplierDTO>> pathDTOs) {

        super(error, responseCode, userId);
        this.pathDTOs = pathDTOs;
    }

    public ResponseGetDifferentPathsDTO(HashMap<ComponentDTO, List<SupplierDTO>> pathDTOs) {
        this.pathDTOs = pathDTOs;
    }
}
