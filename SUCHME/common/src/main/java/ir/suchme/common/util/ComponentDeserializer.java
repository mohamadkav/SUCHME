package ir.suchme.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import ir.suchme.common.dto.component.ComponentDTO;

import java.io.IOException;

/**
 * Created by mohammad on 7/9/17.
 */
public class ComponentDeserializer  extends KeyDeserializer {
    @Override
    public ComponentDTO deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return new ComponentDTO(key.substring(key.indexOf("name")+6,key.indexOf(",")-1),null,
                null,null,key.substring(key.indexOf("id")+4),null,null,null);
    }
}