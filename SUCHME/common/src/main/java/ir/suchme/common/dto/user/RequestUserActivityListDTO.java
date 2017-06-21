package ir.suchme.common.dto.user;

import ir.suchme.common.dto.base.RequestDTO;

import java.util.Date;

/**
 * Created by mohammad on 6/21/17.
 */
public class RequestUserActivityListDTO implements RequestDTO{
    private boolean customer;

    private boolean employee;

    private Long fromDate;

    private Long toDate;

    @Override
    public void validation() {

    }
}
