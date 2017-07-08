package ir.suchme.common.dto.order;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assertions;

/**
 * Created by mohammad on 6/21/17.
 */
public class RequestOrderListDTO implements RequestDTO{
    private boolean product;

    private boolean component;

    private Long fromDate;

    private Long toDate;

    private Integer page;

    private Integer size;

    public RequestOrderListDTO() {
    }

    public RequestOrderListDTO(boolean product, boolean component, Long fromDate, Long toDate, Integer page, Integer size) {
        this.product = product;
        this.component = component;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.page = page;
        this.size = size;
    }

    @Override
    public void validation() {
        Assertions.assertThat(page).isNotNull();
        Assertions.assertThat(size).isNotNull();
    }

    public boolean isProduct() {
        return product;
    }

    public void setProduct(boolean product) {
        this.product = product;
    }

    public boolean isComponent() {
        return component;
    }

    public void setComponent(boolean component) {
        this.component = component;
    }

    public Long getFromDate() {
        return fromDate;
    }

    public void setFromDate(Long fromDate) {
        this.fromDate = fromDate;
    }

    public Long getToDate() {
        return toDate;
    }

    public void setToDate(Long toDate) {
        this.toDate = toDate;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "{" +
                "product=" + product +
                ", component=" + component +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
