package de.demo.cicd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/demo")
public class HelloController {
	
	@GetMapping("/sayhello")
	public Mono<String> sayHello() {
	    return Mono.just("Hello");
	}

}
