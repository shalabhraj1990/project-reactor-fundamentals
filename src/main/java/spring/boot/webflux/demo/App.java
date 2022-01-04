package spring.boot.webflux.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflux.modal.MonoTest;

public class App {
	static MonoTest monoTest = new MonoTest();

	public static void main(String[] args) {
//		Mono<String> monoObject = createMono();
//		monoObject.subscribe(data -> System.out.println(data));
//
//		Flux<String> fluxObject = createFlux();
//		fluxObject.subscribe(data -> System.out.println(data));

//success , failiure , finally  handlers
		Flux<String> flux = monoTest.createFlux().log();
		// Map
		Flux<String> fluxWithUpperCase = flux.map(String::toUpperCase);
		
		fluxWithUpperCase.subscribe(item -> {
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
