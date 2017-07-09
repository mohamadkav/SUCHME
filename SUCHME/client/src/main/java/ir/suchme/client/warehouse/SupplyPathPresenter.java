package ir.suchme.client.warehouse;

import ir.suchme.client.util.NotificationUtil;
import ir.suchme.client.util.SuchmeClient;
import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.SupplyComponentDTO;
import ir.suchme.common.dto.product.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by mohammad on 7/9/17.
 */
public class SupplyPathPresenter implements Initializable {
    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private Button showPathButton;
    @FXML private Button acceptInitial;
    @FXML private Button acceptButton;
    @FXML private ListView<ProductDTO> resultList;
    @FXML private Label pathString;
    @FXML private ChoiceBox<ComponentDTO> componentChoicebox;
    @FXML private ChoiceBox<SupplyComponentDTO> supplierChoiceBox;

    private HashMap<ComponentDTO,List<SupplyComponentDTO>> pathDTOs;
    private HashMap<ComponentDTO,SupplyComponentDTO> newPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            ResponseSearchProductDTO out = client.postRequestAndWaitForResponse("/search/product", new RequestSearchProductDTO(searchField.getText()), ResponseSearchProductDTO.class);
            NotificationUtil.check(out);
            ObservableList<ProductDTO> items = FXCollections.observableArrayList(out.getProductDTOS());
            resultList.setItems(items);
        });

        showPathButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            ResponseGetCurrentPathDTO out = client.postRequestAndWaitForResponse("/warehouse/getCurrentPath", new RequestGetDifferentPathsDTO(resultList.getSelectionModel().getSelectedItem().getId()), ResponseGetCurrentPathDTO.class);
            NotificationUtil.check(out);
            StringBuilder pathString1= new StringBuilder();
            for(ComponentDTO componentDTO:out.getPathDTOs().keySet())
                pathString1.append(componentDTO.getName()).append(": ").append(out.getPathDTOs().get(componentDTO).getSupplier().getName()).append("\n");
            pathString.setText(pathString1.toString());

            ResponseGetDifferentPathsDTO out2 = client.postRequestAndWaitForResponse("/warehouse/getDifferentPaths", new RequestGetDifferentPathsDTO(resultList.getSelectionModel().getSelectedItem().getId()), ResponseGetDifferentPathsDTO.class);
            NotificationUtil.check(out2);
            pathDTOs=out2.getPathDTOs();
            componentChoicebox.setConverter(new MyClassConverter());
            componentChoicebox.setItems(FXCollections.observableArrayList(pathDTOs.keySet()));
            newPath=new HashMap<>();
        });

        componentChoicebox.setOnAction(event -> {
            supplierChoiceBox.setItems(FXCollections.observableArrayList(pathDTOs.get(componentChoicebox.getSelectionModel().getSelectedItem())));
        });
        acceptInitial.setOnAction(event -> {
            newPath.put(componentChoicebox.getSelectionModel().getSelectedItem(),supplierChoiceBox.getSelectionModel().getSelectedItem());
        });

        acceptButton.setOnAction(event -> {
            SuchmeClient client = SuchmeClient.getInstance();
            List<ComponentDTO>componentDTOS=new ArrayList<>(newPath.keySet());
            List<SupplyComponentDTO>supplyComponentDTOS=new ArrayList<>(newPath.values());
            BaseResponseDTO out = client.postRequestAndWaitForResponse("/warehouse/changePath", new RequestChangeSupplyPathDTO(resultList.getSelectionModel().getSelectedItem(),componentDTOS,supplyComponentDTOS), BaseResponseDTO.class);
            NotificationUtil.OK(out);
        });


    }
    class MyClassConverter extends StringConverter<ComponentDTO> {

        public ComponentDTO fromString(String string) {
            for(ComponentDTO componentDTO:pathDTOs.keySet())
                if(string.equals(componentDTO.getName()))
                    return componentDTO;
            return null;
        }

        public String toString(ComponentDTO myClassinstance) {
            return myClassinstance.getName();
        }
    }

}
