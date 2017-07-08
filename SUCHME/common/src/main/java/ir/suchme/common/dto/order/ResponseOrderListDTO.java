package ir.suchme.common.dto.order;

import ir.suchme.common.dto.base.BaseResponseDTO;

import java.util.List;

/**
 * Created by mohammad on 7/8/17.
 */
public class ResponseOrderListDTO extends BaseResponseDTO{
    public ResponseOrderListDTO(Integer totalPages, List<OrderDTO> orderDTOS) {
        this.totalPages = totalPages;
        this.orderDTOS = orderDTOS;
    }

    public ResponseOrderListDTO() {
    }

    private Integer totalPages;


    private List<OrderDTO>orderDTOS;

    public List<OrderDTO> getOrderDTOS() {
        return orderDTOS;
    }

    public void setOrderDTOS(List<OrderDTO> orderDTOS) {
        this.orderDTOS = orderDTOS;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
