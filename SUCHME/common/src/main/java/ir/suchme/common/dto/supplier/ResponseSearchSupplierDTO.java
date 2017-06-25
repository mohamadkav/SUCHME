package ir.suchme.common.dto.supplier;

import ir.suchme.common.dto.base.BaseResponseDTO;

import java.util.List;

/**
 * Created by mohammad on 6/26/17.
 */
public class ResponseSearchSupplierDTO extends BaseResponseDTO {

    private List<SupplierDTO> supplierDTOS;

    public ResponseSearchSupplierDTO(String error, String responseCode, String userId, List<SupplierDTO> supplierDTOS) {
        super(error, responseCode, userId);
        this.supplierDTOS = supplierDTOS;
    }

    public ResponseSearchSupplierDTO() {
    }

    public List<SupplierDTO> getSupplierDTOS() {
        return supplierDTOS;
    }

    public void setSupplierDTOS(List<SupplierDTO> supplierDTOS) {
        this.supplierDTOS = supplierDTOS;
    }
}
