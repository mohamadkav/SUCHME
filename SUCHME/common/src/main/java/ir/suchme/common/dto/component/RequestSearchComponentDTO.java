package ir.suchme.common.dto.component;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assertions;

/**
 * Created by mohammad on 6/9/17.
 */
public class RequestUpdateComponentMinMaxDTO implements RequestDTO {

    private String productId;

    private Integer minimum;

    private Integer maximum;

    @Override
    public void validation() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }
}
