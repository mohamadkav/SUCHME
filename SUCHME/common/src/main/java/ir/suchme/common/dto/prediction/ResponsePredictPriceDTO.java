package ir.suchme.common.dto.prediction;

import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.product.ProductDTO;

import java.util.List;

/**
 * Created by Farzin on 6/23/2017.
 */
public class ResponsePredictPriceDTO extends BaseResponseDTO {

    private List<ProductDTO> productDTOS;

    public List<ProductDTO> getProductDTOS() {
        return productDTOS;
    }

    public ResponsePredictPriceDTO(String error, String responseCode, String userId, List<ProductDTO> productDTOS) {
        super(error, responseCode, userId);
        this.productDTOS = productDTOS;
    }

    public ResponsePredictPriceDTO() {
    }

    public ResponsePredictPriceDTO(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }

    public void setProductDTOS(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }
}
