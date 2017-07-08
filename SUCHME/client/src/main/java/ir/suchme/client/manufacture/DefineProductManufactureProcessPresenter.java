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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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


        Set<String> selectedComponetnsId = new HashSet<>();
        Set<String> selectedProductsId = new HashSet<>();

        for(ComponentDTO componentDTO : componentsTable.getSelectionModel().getSelectedItems())
        {
            selectedComponetnsId.add(componentDTO.getId());
        }

        for(ProductDTO productDTO : productsTable.getSelectionModel().getSelectedItems())
        {
            selectedProductsId.add(productDTO.getId());
        }

        middlewareButton.setOnAction(event -> {
            if(middlewareBox.getText() != null) {
                componentsTable.getSelectionModel().getSelectedItems().clear();
                productsTable.getSelectionModel().getSelectedItems().clear();
                middlewareLable.setText(middlewareBox.getText());
                createMiddlewareProduct(middlewareBox.getText());
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
            ObservableList<ProductDTO> items = FXCollections.observableArrayList(getMiddlewareProducts(productsSearchBox.getText()));
            productsTable.setItems(items);
        });



    }

    private List<ComponentDTO> getComponents(String name)
    {
        RequestSearchComponentDTO request = new RequestSearchComponentDTO();
        request.setName(name);
        ResponseSearchComponentDTO response = SuchmeClient.getInstance().postRequestAndWaitForResponse("/search/component", request, ResponseSearchComponentDTO.class);
        return response.getComponentDTOS();
    }

    private List<ProductDTO> getMiddlewareProducts(String name)
    {
        RequestSearchProductDTO request = new RequestSearchProductDTO();
        request.setName(name);
        ResponseSearchProductDTO response = SuchmeClient.getInstance().postRequestAndWaitForResponse("/search/product", request, ResponseSearchProductDTO.class);
        return response.getProductDTOS();
    }

    private void createMiddlewareProduct(String name)
    {
        RequestCreateMiddlewareProduct request = new RequestCreateMiddlewareProduct(name);
        BaseResponseDTO response = SuchmeClient.getInstance().postRequestAndWaitForResponse("/search/middleware_product", request, BaseResponseDTO.class);
        NotificationUtil.OK(response);
    }

    private void showAlert()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "آیا از اضافه کردن فرایند تولید به محصول " + "تست" + " مطمئن هستید؟", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //do stuff
        }
    }

    private void requestToCreateManufactureProcess(ProductDTO middlewareProduct, Set<String> componentsId, Set<String> productsId)
    {
        RequestProductManufactureProcess request = new RequestProductManufactureProcess();
        request.setProductId(middlewareProduct.getId());
        request.setProductsId(productsId);
        request.setSupplyComponentsId(componentsId);
        BaseResponseDTO response = SuchmeClient.getInstance().postRequestAndWaitForResponse("/order/process", request, BaseResponseDTO.class);
        NotificationUtil.OK(response);
    }

}
