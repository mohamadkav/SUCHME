package ir.suchme.client.warehouse;

import ir.suchme.client.util.SuchmeClient;
import ir.suchme.client.util.NotificationUtil;
import ir.suchme.common.dto.product.ProductDTO;
import ir.suchme.common.dto.product.RequestSearchProductDTO;
import ir.suchme.common.dto.product.ResponseSearchProductDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mohammad on 6/18/17.
 */
public class ProductQuantityPresenter implements Initializable{
    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private ListView resultList;
    @FXML private Label name;
    @FXML private Label price;
    @FXML private Label quantity;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        searchButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            ResponseSearchProductDTO out = client.postRequestAndWaitForResponse("/search/product", new RequestSearchProductDTO(searchField.getText()),ResponseSearchProductDTO.class);
            NotificationUtil.check(out);
            ObservableList<ProductDTO> items= FXCollections.observableArrayList(out.getProductDTOS());
            resultList.setItems(items);
        });

        resultList.setOnMouseClicked(event -> {
            ProductDTO selected=(ProductDTO)resultList.getSelectionModel().getSelectedItem();
            name.setText(selected.getName());
            price.setText(selected.getPrice()+"");
            quantity.setText(selected.getQuantity()+"");
        });

    }

}
