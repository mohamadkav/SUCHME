package ir.suchme.core.controller.base;

import ir.suchme.core.domain.repository.ComponentRepository;
import ir.suchme.core.domain.repository.ProductRepository;
import ir.suchme.core.domain.repository.SupplierRepository;
import ir.suchme.core.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mohammad on 5/22/17.
 */

public class BaseControllerTest {
    protected HttpMessageConverter mappingJackson2HttpMessageConverter;

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected ProductRepository productRepository;

    @MockBean
    protected SupplierRepository supplierRepository;

    @MockBean
    protected ComponentRepository componentRepository;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
}
