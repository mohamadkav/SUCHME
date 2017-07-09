package ir.suchme.client.report;

import ir.suchme.client.util.SuchmeClient;
import ir.suchme.common.dto.process.ProcessDTO;
import ir.suchme.common.dto.process.RequestReportManufactureDTO;
import ir.suchme.common.dto.process.ResponseProcessReportDTO;
import ir.suchme.common.dto.product.ProductDTO;
import ir.suchme.common.dto.product.RequestSearchProductDTO;
import ir.suchme.common.dto.product.ResponseSearchProductDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Farzin on 7/9/2017.
 */
public class ReportManufactureProcessPresenter implements Initializable {


    @FXML private Button productSearchButton;
    @FXML private javafx.scene.control.TableView<ProcessDTO> processTable;
    @FXML private javafx.scene.control.TableView<ProductDTO> productsTable;
    @FXML private javafx.scene.control.TableColumn<ProcessDTO, String> productName;
    @FXML private javafx.scene.control.TableColumn<ProcessDTO, String> productProcess;
    @FXML private javafx.scene.control.TableColumn<ProductDTO, String> productName1;
    @FXML private javafx.scene.control.TextField productSearchBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productProcess.setCellValueFactory(new PropertyValueFactory<>("manufactureProcessReport"));

        productName1.setCellValueFactory(new PropertyValueFactory<>("name"));

        productSearchButton.setOnAction(event -> {
            ObservableList<ProductDTO> productDTOS = FXCollections.observableArrayList(searchAvailableProducts(productSearchBox.getText()));
            productsTable.setItems(productDTOS);
        });

        productsTable.setOnMouseClicked(event -> {
            ProductDTO dto = productsTable.getSelectionModel().getSelectedItem();
            List<ProcessDTO> dtos = getProcesses(dto);
            processTable.setItems(FXCollections.observableArrayList(dtos));
        });

    }

    private List<ProductDTO> searchAvailableProducts(String name)
    {
        RequestSearchProductDTO request = new RequestSearchProductDTO();
        request.setName(name);
        request.setState("AVAILABLE");
        ResponseSearchProductDTO response = SuchmeClient.getInstance().postRequestAndWaitForResponse("/search/product", request, ResponseSearchProductDTO.class);
        return response.getProductDTOS();
    }

    private List<ProcessDTO> getProcesses(ProductDTO dto)
    {
        RequestReportManufactureDTO reques = new RequestReportManufactureDTO();
        reques.setProduct(dto);
        ResponseProcessReportDTO out = SuchmeClient.getInstance().postRequestAndWaitForResponse("/order/get-process", reques, ResponseProcessReportDTO.class);
        return out.getProcessDTOS();
    }


}
