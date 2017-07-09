package ir.suchme.common.dto.process;

import ir.suchme.common.dto.component.SupplyComponentDTO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Farzin on 7/9/2017.
 */
public class ProcessDTO {

    private String productName;

    private List<SupplyComponentDTO> manufactureProcess;

    private String manufactureProcessReport;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<SupplyComponentDTO> getManufactureProcess() {
        return manufactureProcess;
    }

    public void setManufactureProcess(List<SupplyComponentDTO> manufactureProcess) {
        this.manufactureProcess = manufactureProcess;
    }

    public String getManufactureProcessReport() {
        return manufactureProcessReport;
    }

    public void setManufactureProcessReport(String manufactureProcessReport) {
        this.manufactureProcessReport = manufactureProcessReport;
    }
}
