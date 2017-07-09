package ir.suchme.common.dto.prediction;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.product.ProductDTO;

import java.util.List;

/**
 * Created by Farzin on 6/23/2017.
 */
public class ResponsePredictDeliveryTimeDTO extends BaseResponseDTO {

    private List<ProductDTO> productDTOS;

    public List<ProductDTO> getProductDTOS() {
        return productDTOS;
    }

    public ResponsePredictDeliveryTimeDTO(String error, String responseCode, String userId, List<ProductDTO> productDTOS) {
        super(error, responseCode, userId);
        this.productDTOS = productDTOS;
    }

    public ResponsePredictDeliveryTimeDTO() {
    }

    public ResponsePredictDeliveryTimeDTO(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }

    public void setProductDTOS(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }
}
