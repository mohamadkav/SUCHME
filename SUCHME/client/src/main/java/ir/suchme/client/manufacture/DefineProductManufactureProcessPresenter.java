package ir.suchme.client.manufacture;

import ir.suchme.client.util.NotificationUtil;
import ir.suchme.client.util.SuchmeClient;
import ir.suchme.common.dto.base.BaseResponseDTO;
import ir.suchme.common.dto.component.ComponentDTO;
import ir.suchme.common.dto.component.RequestSearchComponentDTO;
import ir.suchme.common.dto.component.ResponseSearchComponentDTO;
import ir.suchme.common.dto.process.RequestProductManufactureProcess;
import ir.suchme.common.dto.product.ProductDTO;
import ir.suchme.common.dto.product.RequestCreateMiddlewareProduct;
import ir.suchme.common.dto.product.RequestSearchProductDTO;
import ir.suchme.common.dto.product.ResponseSearchProductDTO;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

/**
 * Created by Farzin on 7/8/2017.
 */
public class DefineProductManufactureProcessPresenter implements Initializable{

    @FXML private TextField componentsSearchBox;
    @FXML private TextField productsSearchBox;
    @FXML private Button componentsSearchButton;
    @FXML private Button productsSearchButton;
    @FXML private TableView<ComponentDTO> componentsTable;
    @FXML private TableColumn<ComponentDTO, String> name;
    @FXML private TableColumn<ComponentDTO, String> supplierName;
    @FXML private TableView<ProductDTO> productsTable;
    @FXML private TableColumn<ProductDTO, String> productName;
    @FXML private Button addComponentsAndProductsButton;
    @FXML private TextField middlewareBox;
    @FXML private TableView<ProductDTO> middlewareTable;
    @FXML private TableColumn<ProductDTO, String> middlewareName;
    @FXML private Button middlewareButton;
    @FXML private Label middlewareLable;

    private ProductDTO selectedMiddleware = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        componentsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        productsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        middlewareName.setCellValueFactory(new PropertyValueFactory<>("name"));

        List<ComponentDTO> selectedComponetnsId = new LinkedList<>();
        Set<String> selectedProductsId = new HashSet<>();

        componentsTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<ComponentDTO>() {
            @Override
            public void onChanged(Change<? extends ComponentDTO> c) {
                if(componentsTable.getSelectionModel().getSelectedItems() != null)
                {
                    for (ComponentDTO dto : componentsTable.getSelectionModel().getSelectedItems())
                    {
                        selectedComponetnsId.add(dto);
                    }
                }
            }
        });

        productsTable.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<ProductDTO>() {
            @Override
            public void onChanged(Change<? extends ProductDTO> c) {
                if(productsTable.getSelectionModel().getSelectedItems() != null)
                {
                    for (ProductDTO dto : productsTable.getSelectionModel().getSelectedItems())
                    {
                        selectedProductsId.add(dto.getId());
                    }
                }
            }
        });


        middlewareButton.setOnAction(event -> {
            if(middlewareBox.getText() != null) {
//                componentsTable.getSelectionModel().getSelectedItems().clear();
//                productsTable.getSelectionModel().getSelectedItems().clear();
                middlewareLable.setText(middlewareBox.getText());
                selectedMiddleware.setName(middlewareBox.getText());
            }
        });

        addComponentsAndProductsButton.setOnAction(event -> {
            requestToCreateManufactureProcess(selectedMiddleware, selectedComponetnsId, selectedProductsId);
        });

        componentsSearchButton.setOnAction(event -> {
            ObservableList<ComponentDTO> items = FXCollections.observableArrayList(getComponents(componentsSearchBox.getText()));
            componentsTable.setItems(items);
        });

        productsSearchButton.setOnAction(event -> {
            ObservableList<ProductDTO> items = FXCollections.observableArrayList(getMaufacturedMiddlewareProducts(productsSearchBox.getText()));
            productsTable.setItems(items);
        });

        middlewareButton.setOnAction(event -> {
            ObservableList<ProductDTO> items = FXCollections.observableArrayList(searchNotManufacturedMiddlewareProduct(middlewareBox.getText()));
            middlewareTable.setItems(items);
        });

        middlewareTable.setOnMouseClicked(event -> {
            middlewareLable.setText(middlewareTable.getSelectionModel().getSelectedItem().getName());
            selectedMiddleware = middlewareTable.getSelectionModel().getSelectedItem();
        });



    }

    private List<ComponentDTO> getComponents(String name)
    {
        RequestSearchComponentDTO request = new RequestSearchComponentDTO();
        request.setName(name);
        ResponseSearchComponentDTO response = SuchmeClient.getInstance().postRequestAndWaitForResponse("/search/component", request, ResponseSearchComponentDTO.class);
        return response.getComponentDTOS();
    }

    private List<ProductDTO> getMaufacturedMiddlewareProducts(String name)
    {
        RequestSearchProductDTO request = new RequestSearchProductDTO();
        request.setName(name);
        ResponseSearchProductDTO response = SuchmeClient.getInstance().postRequestAndWaitForResponse("/search/product", request, ResponseSearchProductDTO.class);
        return response.getProductDTOS();
    }

    private List<ProductDTO> searchNotManufacturedMiddlewareProduct(String name)
    {
        //add type ordered to product
        RequestSearchProductDTO request = new RequestSearchProductDTO(name);
        ResponseSearchProductDTO response = SuchmeClient.getInstance().postRequestAndWaitForResponse("/search/product", request, ResponseSearchProductDTO.class);
//        NotificationUtil.OK(response);
        return response.getProductDTOS();
    }

    private void showAlert()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "آیا از اضافه کردن فرایند تولید به محصول " + "تست" + " مطمئن هستید؟", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //do stuff
        }
    }

    private void requestToCreateManufactureProcess(ProductDTO middlewareProduct, List<ComponentDTO> componentDTOS, Set<String> productsId)
    {
        RequestProductManufactureProcess request = new RequestProductManufactureProcess();
        request.setProductId(middlewareProduct.getId());
        request.setProductsId(productsId);
        request.setComponentDTOS(componentDTOS);
        BaseResponseDTO response = SuchmeClient.getInstance().postRequestAndWaitForResponse("/order/process", request, BaseResponseDTO.class);
        NotificationUtil.OK(response);
    }


}
