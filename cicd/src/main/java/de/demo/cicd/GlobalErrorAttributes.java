package de.demo.cicd;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
@Order(-2)
public class GlobalErrorAttributes extends DefaultErrorAttributes{
    
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, 
      ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(
          request, options);
        map.put("status", HttpStatus.BAD_REQUEST);
        map.put("message", "Something went wrong");
        return map;
    }

}
