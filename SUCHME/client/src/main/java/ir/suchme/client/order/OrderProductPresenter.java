package ir.suchme.client.order;

import ir.suchme.client.util.NotificationUtil;
import ir.suchme.client.util.SuchmeClient;
import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.RequestSearchComponentDTO;
import ir.suchme.common.dto.component.ResponseSearchComponentDTO;
import ir.suchme.common.dto.order.RequestOrderComponentDTO;
import ir.suchme.common.dto.order.RequestOrderProductDTO;
import ir.suchme.common.dto.supplier.RequestSearchSupplierDTO;
import ir.suchme.common.dto.supplier.ResponseSearchSupplierDTO;
import ir.suchme.common.dto.supplier.SupplierDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by mohammad on 6/25/17.
 */
public class OrderProductPresenter implements Initializable {
    @FXML private TextField productName;
    @FXML private Button submitButton;
    @FXML private Button addRequirement;
    @FXML private TextField requirementDescription;
    @FXML private TextField quantity;
    @FXML private Label numberOfRequirementText;

    private List<String> requirements=new ArrayList<>();
    private Integer numberOfRequirement=1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addRequirement.setOnAction(event -> {
            if(!requirementDescription.getText().trim().isEmpty()){
                requirements.add(requirementDescription.getText());
                numberOfRequirementText.setText(++numberOfRequirement+"");
                requirementDescription.setText("");
            }
        });

        submitButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            BaseResponseDTO out = client.postRequestAndWaitForResponse("/order/product", new RequestOrderProductDTO(requirements,productName.getText(),Integer.parseInt(quantity.getText())),BaseResponseDTO.class);
            NotificationUtil.OK(out);
            requirements=new ArrayList<>();
            productName.setText("");
            quantity.setText("");
        });

        quantity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                quantity.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

}
