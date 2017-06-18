package ir.suchme.common.dto.product;

import ir.suchme.common.dto.base.BaseResponseDTO;

import java.util.List;

/**
 * Created by mohammad on 6/18/17.
 */
public class ResponseSearchProductDTO extends BaseResponseDTO{
    private List<ProductDTO> productDTOS;

    public List<ProductDTO> getProductDTOS() {
        return productDTOS;
    }

    public ResponseSearchProductDTO(String error, String responseCode, String userId, List<ProductDTO> productDTOS) {
        super(error, responseCode, userId);
        this.productDTOS = productDTOS;
    }

    public ResponseSearchProductDTO() {
    }

    public ResponseSearchProductDTO(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }

    public void setProductDTOS(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }
}
