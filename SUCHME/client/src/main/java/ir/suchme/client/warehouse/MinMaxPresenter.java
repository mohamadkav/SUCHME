package ir.suchme.client.warehouse;

import ir.suchme.client.SuchmeClient;
import ir.suchme.client.util.NotificationUtil;
import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.RequestSearchComponentDTO;
import ir.suchme.common.dto.component.RequestUpdateComponentMinMaxDTO;
import ir.suchme.common.dto.component.ResponseSearchComponentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mohammad on 6/9/17.
 */
public class MinMaxPresenter  implements Initializable {

    @FXML private TextField searchField;
    @FXML private TextField minValue;
    @FXML private TextField maxValue;
    @FXML private Button searchButton;
    @FXML private Button acceptButton;
    @FXML private ListView resultList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        searchButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            ResponseSearchComponentDTO out = client.postRequestAndWaitForResponse("/search/component", new RequestSearchComponentDTO(searchField.getText()),ResponseSearchComponentDTO.class);
            NotificationUtil.check(out);
            ObservableList<ComponentDTO> items= FXCollections.observableArrayList(out.getComponentDTOS());
            resultList.setItems(items);
        });
        acceptButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            BaseResponseDTO out = client.postRequestAndWaitForResponse("/warehouse/updateminmax",
                    new RequestUpdateComponentMinMaxDTO(((ComponentDTO)resultList.getSelectionModel().getSelectedItem()).getId(),
                            Integer.parseInt(minValue.getText()),Integer.parseInt(maxValue.getText())),BaseResponseDTO.class);
            NotificationUtil.OK(out);
        });

        resultList.setOnMouseClicked(event -> {
            ComponentDTO selected=(ComponentDTO)resultList.getSelectionModel().getSelectedItem();
            maxValue.setText(selected.getMaxValue()+"");
            minValue.setText(selected.getMinValue()+"");
        });

    }
}