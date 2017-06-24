package ir.suchme.common.dto.prediction;

import ir.suchme.common.dto.base.RequestDTO;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;

/**
 * Created by Farzin on 6/23/2017.
 */
public class RequestPredictionDTO implements RequestDTO{

    private String id;

    private String name;

    @Override
    public void validation() {
        Assertions.assertThat(id).isNotNull();
        Assertions.assertThat(name).isNotNull();
    }

    @Override
    public String toString() {
        return "RequestPredictionDTO{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
