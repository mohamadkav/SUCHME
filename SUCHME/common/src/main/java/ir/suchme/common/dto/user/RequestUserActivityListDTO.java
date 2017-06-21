package ir.suchme.common.dto.user;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assertions;

import java.util.Date;

/**
 * Created by mohammad on 6/21/17.
 */
public class RequestUserActivityListDTO implements RequestDTO{
    private boolean customer;

    private boolean employee;

    private Long fromDate;

    private Long toDate;

    private Integer page;

    private Integer size;

    public RequestUserActivityListDTO() {
    }

    public RequestUserActivityListDTO(boolean customer, boolean employee, Long fromDate, Long toDate, Integer page, Integer size) {
        this.customer = customer;
        this.employee = employee;
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

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
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
                "customer=" + customer +
                ", employee=" + employee +
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
