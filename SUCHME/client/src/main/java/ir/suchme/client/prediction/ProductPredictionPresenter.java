package ir.suchme.client.prediction;

import ir.suchme.client.util.NotificationUtil;
import ir.suchme.client.util.SuchmeClient;
import ir.suchme.common.dto.prediction.RequestPredictionDTO;
import ir.suchme.common.dto.prediction.ResponsePredictDeliveryTimeDTO;
import ir.suchme.common.dto.prediction.ResponsePredictPriceDTO;
import ir.suchme.common.dto.product.ProductDTO;
import ir.suchme.common.dto.product.RequestSearchProductDTO;
import ir.suchme.common.dto.product.ResponseSearchProductDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Farzin on 6/23/2017.
 */
public class ProductPredictionPresenter implements Initializable {

    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private ListView<ProductDTO> resultList;
    @FXML private ListView<ProductDTO> similarProductsList;
    @FXML private Label name;
    @FXML private Label priceText;
    @FXML private Label timeToDeliverText;
    @FXML private RadioButton priceCheck;
    @FXML private RadioButton timeCheck;
    @FXML private Group pricePrediction;
    @FXML private Group timePrediction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ToggleGroup buttonGroup = new ToggleGroup();
        timeCheck.disableProperty().set(true);
        timeCheck.setToggleGroup(buttonGroup);
        priceCheck.setToggleGroup(buttonGroup);

        buttonGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton selected = (RadioButton)newValue;
                if(selected.equals(timeCheck))
                {
                    timePrediction.setVisible(true);
                    pricePrediction.setVisible(false);
                }
                if(selected.equals(priceCheck))
                {
                    timePrediction.setVisible(false);
                    pricePrediction.setVisible(true);
                }
            }
        });

        searchButton.setOnAction(event -> {
            resultList.getItems().clear();
            similarProductsList.getItems().clear();
            SuchmeClient client = SuchmeClient.getInstance();
            ResponseSearchProductDTO out = client.postRequestAndWaitForResponse("/search/product", new RequestSearchProductDTO(searchField.getText()),ResponseSearchProductDTO.class);
            NotificationUtil.check(out);
            resultList.setCellFactory(new Callback<ListView<ProductDTO>, ListCell<ProductDTO>>() {
                @Override
                public ListCell<ProductDTO> call(ListView<ProductDTO> param) {
                    final ListCell<ProductDTO> cell = new ListCell<ProductDTO>(){
                        @Override
                        protected void updateItem(ProductDTO item, boolean empty) {
                            super.updateItem(item, empty);
                            if(item != null)
                                setText(item.getName());
                        }
                    };
                    return cell;
                }
            });
            ObservableList<ProductDTO> items= FXCollections.observableArrayList(out.getProductDTOS());
            resultList.setItems(items);
        });


        resultList.setOnMouseClicked(event -> {
            ProductDTO selected=(ProductDTO)resultList.getSelectionModel().getSelectedItem();
            RequestPredictionDTO requestPredictionDTO = new RequestPredictionDTO();
            requestPredictionDTO.setId(selected.getId());
            requestPredictionDTO.setName(selected.getName());
            ResponsePredictPriceDTO out = null;
            if(priceCheck.selectedProperty().getValue())
                out = SuchmeClient.getInstance().postRequestAndWaitForResponse("/predict/price", requestPredictionDTO, ResponsePredictPriceDTO.class);
            else
                out = SuchmeClient.getInstance().postRequestAndWaitForResponse("/predict/time", requestPredictionDTO, ResponsePredictPriceDTO.class);
            NotificationUtil.check(out);
            similarProductsList.setCellFactory(new Callback<ListView<ProductDTO>, ListCell<ProductDTO>>() {
                @Override
                public ListCell<ProductDTO> call(ListView<ProductDTO> param) {
                    final ListCell<ProductDTO> cell = new ListCell<ProductDTO>() {
                        @Override
                        public void updateItem(ProductDTO item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item.getName());
                            }
                        }
                    };
                    return cell;
                }
            });
            ObservableList<ProductDTO> items= FXCollections.observableArrayList(out.getProductDTOS());
            similarProductsList.setItems(items);

        });

        similarProductsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ProductDTO dto = similarProductsList.getSelectionModel().getSelectedItem();
                name.setText(dto.getName());
                if(priceCheck.selectedProperty().getValue())
                    priceText.setText(dto.getPrice().toString());
                else
                    timeToDeliverText.setText("1000");
            }
        });

    }
}
