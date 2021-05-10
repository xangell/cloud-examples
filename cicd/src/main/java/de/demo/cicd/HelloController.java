package de.demo.cicd;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/demo")
public class HelloController {
	
	@Autowired
	private Config configuration;

	@GetMapping("/sayhello")
	public Mono<String> sayHello() {
		WebClient client = WebClient.create(configuration.service);
		return client.get().uri("/service/sayhello").accept(MediaType.TEXT_PLAIN)
				.retrieve().bodyToMono(String.class);
	}
	
	
	@GetMapping("/sayhello/{name}")
	public Mono<String> sayHelloToPerson(@PathVariable(value="name") String name) {
		WebClient client = WebClient.create(configuration.service);
		String url = String.format("/service/sayhello/%s", name);
		return client.get().uri(url).accept(MediaType.TEXT_PLAIN)
				.retrieve().bodyToMono(String.class);
	}

	@GetMapping("/error")
	public Mono<String> generateError() {
		WebClient client = WebClient.create(configuration.service);
		return client.get().uri("/service/error").accept(MediaType.TEXT_PLAIN)
				.retrieve().bodyToMono(String.class);
	}
	
	@GetMapping("/load")
	public Mono<String> generateLoad() {
		for(int i = 0; i < 10000; i++)
		{
			 try {
				Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
				cipher.update(new byte[] {1, 5, 2, 7, 9 ,9});
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				
				e.printStackTrace();
			}
		}
		return Mono.just("done");
	}

}
