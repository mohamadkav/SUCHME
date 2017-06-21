package ir.suchme.client.report;

import ir.suchme.client.util.NotificationUtil;
import ir.suchme.client.util.SuchmeClient;
import ir.suchme.common.dto.user.RequestUserActivityListDTO;
import ir.suchme.common.dto.user.ResponseUserActivityListDTO;
import ir.suchme.common.dto.user.UserActivityDTO;
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
 * Created by mohammad on 6/21/17.
 */
public class UserActivityPresenter implements Initializable {
    @FXML private TableView<UserActivityDTO> table;
    @FXML private DatePicker from;
    @FXML private DatePicker to;
    @FXML private CheckBox employeesCheckbox;
    @FXML private CheckBox customersCheckbox;
    @FXML private Button submit;
    @FXML private TableColumn<UserActivityDTO, String> component;
    @FXML private TableColumn<UserActivityDTO, String> method;
    @FXML private TableColumn<UserActivityDTO, String> description;
    @FXML private TableColumn<UserActivityDTO, String> userName;
    @FXML private TableColumn<UserActivityDTO, String> name;
    @FXML private TableColumn<UserActivityDTO, Date> date;
    @FXML private ChoiceBox<Integer> numberPerPage;
    @FXML private ChoiceBox<Integer> page;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        component.setCellValueFactory(new PropertyValueFactory<>("component"));
        method.setCellValueFactory(new PropertyValueFactory<>("method"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        numberPerPage.setItems(FXCollections.observableArrayList(10,50,100));
        numberPerPage.getSelectionModel().selectFirst();

        submit.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            ResponseUserActivityListDTO out = client.postRequestAndWaitForResponse("/user/activity", new RequestUserActivityListDTO(customersCheckbox.isSelected(),
                    employeesCheckbox.isSelected(),from.getValue()!=null?Date.valueOf(from.getValue()).getTime():null,
                    to.getValue()!=null?Date.valueOf(to.getValue()).getTime():null,
                    page.getValue()==null?0:page.getValue()-1,numberPerPage.getValue()), ResponseUserActivityListDTO.class);
            ObservableList<UserActivityDTO> items = FXCollections.observableArrayList(out.getUserActivityDTOS());
            table.setItems(items);

            List<Integer> range = IntStream.rangeClosed(1, out.getTotalPages())
                    .boxed().collect(Collectors.toList());
            page.setItems(FXCollections.observableArrayList(range));
            if(page.getSelectionModel().isEmpty())
                page.getSelectionModel().selectFirst();
        });

        page.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            ResponseUserActivityListDTO out = client.postRequestAndWaitForResponse("/user/activity", new RequestUserActivityListDTO(customersCheckbox.isSelected(),
                    employeesCheckbox.isSelected(),from.getValue()!=null?Date.valueOf(from.getValue()).getTime():null,
                    to.getValue()!=null?Date.valueOf(to.getValue()).getTime():null,
                    page.getValue()==null?0:page.getValue()-1,numberPerPage.getValue()), ResponseUserActivityListDTO.class);
            ObservableList<UserActivityDTO> items = FXCollections.observableArrayList(out.getUserActivityDTOS());
            table.setItems(items);

        });
    }
}
