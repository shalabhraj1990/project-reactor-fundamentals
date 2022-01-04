package spring.boot.webflux.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflux.modal.PublisherTest;

public class App {
	static PublisherTest publisherTest = new PublisherTest();

	public static void main(String[] args) {
//		Mono<String> monoObject = createMono();
//		monoObject.subscribe(data -> System.out.println(data));
//
//		Flux<String> fluxObject = createFlux();
//		fluxObject.subscribe(data -> System.out.println(data));

//success , failiure , finally  handlers
		Flux<String> flux = publisherTest.createFlux().log();
		// Map
		Flux<String> fluxWithUpperCase = flux.map(String::toUpperCase);
		Flux<Flux<String>> map  = publisherTest.flux1().map(item -> publisherTest.flux2(item));
		  //FlatMap
		Flux<String> flatmap  = publisherTest.flux1().flatMap(item -> publisherTest.flux2(item));
		
		flatmap.subscribe(item -> {
			System.out.println(item);
		}, th -> {
			System.out.println("Error from flux");
		}, () -> {
			System.out.println("flux excecution completed");
		});

	}

	private static Mono<String> createMono() {
		return Mono.just("Hellow Mono");
	}

	private static Flux<String> createFlux() {
		return Flux.just("Hellow", "Flux");
	}
}
