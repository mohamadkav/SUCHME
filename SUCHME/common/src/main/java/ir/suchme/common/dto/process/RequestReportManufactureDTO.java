package ir.suchme.common.dto.process;

import ir.suchme.common.dto.base.RequestDTO;
import ir.suchme.common.dto.product.ProductDTO;

/**
 * Created by Farzin on 7/9/2017.
 */
public class RequestReportManufactureDTO implements RequestDTO{


    @Override
    public void validation() {

    }

    private ProductDTO product;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
