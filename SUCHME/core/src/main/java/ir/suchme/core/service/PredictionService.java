package ir.suchme.core.service;

import ir.suchme.common.dto.prediction.RequestPredictionDTO;
import ir.suchme.common.dto.prediction.ResponsePredictPriceDTO;
import ir.suchme.common.dto.product.ProductDTO;
import ir.suchme.common.dto.product.RequestSearchProductDTO;
import ir.suchme.common.dto.product.ResponseSearchProductDTO;
import ir.suchme.core.catalogue.ProductCatalogue;
import ir.suchme.core.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farzin on 6/23/2017.
 */

@Service
public class PredictionService {


    private ProductCatalogue productCatalogue;

    @Autowired
    public PredictionService(ProductCatalogue productCatalogue) {
        this.productCatalogue = productCatalogue;
    }

    public ResponsePredictPriceDTO predictProductPrice(RequestPredictionDTO request)
    {
        List<ProductDTO> productDTOS=new ArrayList<>();
        Product p = productCatalogue.findById(request.getId());
        for (Product product : productCatalogue.findSimilarProducts(p))
        {
            ProductDTO dto = new ProductDTO(product.getId().toString(), product.getPrice(), product.getName(), product.getQuantity());
            productDTOS.add(dto);
        }
        return new ResponsePredictPriceDTO(null, "0", null, productDTOS);
    }
}
