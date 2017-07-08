package ir.suchme.client.order;

import ir.suchme.client.util.SuchmeClient;
import ir.suchme.common.dto.order.OrderDTO;
import ir.suchme.common.dto.order.RequestOrderListDTO;
import ir.suchme.common.dto.order.ResponseOrderListDTO;
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
import java.util.stream.IntStream;

/**
 * Created by mohammad on 7/8/17.
 */
public class OrderListPresenter implements Initializable {
    @FXML
    private TableView<OrderDTO> table;
    @FXML private DatePicker from;
    @FXML private DatePicker to;
    @FXML private CheckBox productsCheckbox;
    @FXML private CheckBox componentsCheckbox;
    @FXML private Button submit;
    @FXML private TableColumn<OrderDTO, String> objectName;
    @FXML private TableColumn<OrderDTO, String> quantity;
    @FXML private TableColumn<OrderDTO, String> created;
    @FXML private ChoiceBox<Integer> numberPerPage;
    @FXML private ChoiceBox<Integer> page;
    @FXML private Label orderName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        objectName.setCellValueFactory(new PropertyValueFactory<>("objectName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        created.setCellValueFactory(new PropertyValueFactory<>("created"));
        numberPerPage.setItems(FXCollections.observableArrayList(10,50,100));
        numberPerPage.getSelectionModel().selectFirst();

        submit.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            ResponseOrderListDTO out = client.postRequestAndWaitForResponse("/order/list", new RequestOrderListDTO(productsCheckbox.isSelected(),
                    componentsCheckbox.isSelected(),from.getValue()!=null?Date.valueOf(from.getValue()).getTime():null,
                    to.getValue()!=null?Date.valueOf(to.getValue()).getTime():null,
                    page.getValue()==null?0:page.getValue()-1,numberPerPage.getValue()), ResponseOrderListDTO.class);
            ObservableList<OrderDTO> items = FXCollections.observableArrayList(out.getOrderDTOS());
            table.setItems(items);

            List<Integer> range = IntStream.rangeClosed(1, out.getTotalPages())
                    .boxed().collect(Collectors.toList());
            page.setItems(FXCollections.observableArrayList(range));
            if(page.getSelectionModel().isEmpty())
                page.getSelectionModel().selectFirst();
        });

        page.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            ResponseOrderListDTO out = client.postRequestAndWaitForResponse("/order/list", new RequestOrderListDTO(productsCheckbox.isSelected(),
                    componentsCheckbox.isSelected(),from.getValue()!=null?Date.valueOf(from.getValue()).getTime():null,
                    to.getValue()!=null?Date.valueOf(to.getValue()).getTime():null,
                    page.getValue()==null?0:page.getValue()-1,numberPerPage.getValue()), ResponseOrderListDTO.class);
            ObservableList<OrderDTO> items = FXCollections.observableArrayList(out.getOrderDTOS());
            table.setItems(items);
        });


        table.setOnMouseClicked(event -> {
            OrderDTO selected=table.getSelectionModel().getSelectedItem();
            orderName.setText(selected.getObjectName());
        });

    }
}
