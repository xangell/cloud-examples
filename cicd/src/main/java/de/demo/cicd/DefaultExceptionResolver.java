package de.demo.cicd;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class DefaultExceptionResolver extends AbstractErrorWebExceptionHandler {

	public DefaultExceptionResolver(ErrorAttributes errorAttributes, Resources resources,
			ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer) {
		super(errorAttributes, resources, applicationContext);
		setMessageWriters(serverCodecConfigurer.getWriters());
		setMessageReaders(serverCodecConfigurer.getReaders());
		
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(
		          RequestPredicates.all(), this::renderErrorResponse);
	}
	
	 private Mono<ServerResponse> renderErrorResponse(
		       ServerRequest request) {

		       Map<String, Object> errorPropertiesMap = getErrorAttributes(request, 
		         ErrorAttributeOptions.defaults());

		       return ServerResponse.status(HttpStatus.BAD_REQUEST)
		         .contentType(MediaType.APPLICATION_JSON)
		         .body(BodyInserters.fromValue(errorPropertiesMap));
		    }



}
