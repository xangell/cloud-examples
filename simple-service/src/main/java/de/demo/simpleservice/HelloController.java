package de.demo.simpleservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/service")
public class HelloController {
	
	@GetMapping("/sayhello")
	public Mono<String> sayHello() {
	    return Mono.just("Hello");
	}
	
	@GetMapping("/sayhello/{name}")
	public Mono<String> sayHelloToPerson(@PathVariable(value="name") String name) {
	    return Mono.just(String.format("Hello %s", name));
	}
	
	@GetMapping("/error")
	public Mono<String> generateError() {
	    return Mono.error(new WebClientResponseException(403, "This did not work", null, null, null));
	}

}
