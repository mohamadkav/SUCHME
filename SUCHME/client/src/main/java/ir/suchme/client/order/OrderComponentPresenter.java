package ir.suchme.client.order;

import ir.suchme.client.util.NotificationUtil;
import ir.suchme.client.util.SuchmeClient;
import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.RequestSearchComponentDTO;
import ir.suchme.common.dto.component.ResponseSearchComponentDTO;
import ir.suchme.common.dto.order.RequestOrderComponentDTO;
import ir.suchme.common.dto.user.UserActivityDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by mohammad on 6/25/17.
 */
public class OrderComponentPresenter implements Initializable {
    @FXML private TextField searchField;
    @FXML private TextField numOfValues;
    @FXML private Button searchButton;
    @FXML private Button acceptButton;
    @FXML private TableView<ComponentDTO> table;
    @FXML private TableColumn<UserActivityDTO, String> name;
    @FXML private TableColumn<UserActivityDTO, Integer> maxValue;
    @FXML private TableColumn<UserActivityDTO, Integer> minValue;
    @FXML private TableColumn<UserActivityDTO, String> supplierName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        maxValue.setCellValueFactory(new PropertyValueFactory<>("maxValue"));
        minValue.setCellValueFactory(new PropertyValueFactory<>("minValue"));
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));


        searchButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            ResponseSearchComponentDTO out = client.postRequestAndWaitForResponse("/search/component", new RequestSearchComponentDTO(searchField.getText()),ResponseSearchComponentDTO.class);
            NotificationUtil.check(out);
            ObservableList<ComponentDTO> items= FXCollections.observableArrayList(out.getComponentDTOS());
            table.setItems(items);
        });

        acceptButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            BaseResponseDTO out = client.postRequestAndWaitForResponse("/order/component", new RequestOrderComponentDTO(
                    table.getSelectionModel().getSelectedItem().getId(),Integer.parseInt(numOfValues.getText())
            ), BaseResponseDTO.class);
            NotificationUtil.OK(out);
        });

        numOfValues.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numOfValues.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

}
