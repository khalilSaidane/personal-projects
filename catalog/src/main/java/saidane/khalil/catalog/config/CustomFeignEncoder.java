package saidane.khalil.catalog.config;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomFeignEncoder implements Encoder {

    private final SpringFormEncoder delegate;

    public CustomFeignEncoder() {
        this.delegate = new SpringFormEncoder();
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (object instanceof Map) {
            // Convert the Map to a form-like structure
            Map<?, ?> map = (Map<?, ?>) object;
            delegate.encode(mapToMultiValueMap(map), bodyType, template);
        } else {
            // For other types, delegate to the default encoder
            delegate.encode(object, bodyType, template);
        }
    }

    private Map<String, Collection<String>> mapToMultiValueMap(Map<?, ?> map) {
        Map<String, Collection<String>> result = new HashMap<>();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key != null && value != null) {
                result.put(key.toString(), List.of(value.toString()));
            }
        }
        return result;
    }
}
