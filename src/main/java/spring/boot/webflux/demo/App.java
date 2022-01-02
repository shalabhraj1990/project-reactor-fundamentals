package spring.boot.webflux.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class App {
	public static void main(String[] args) {
		Mono<String> monoObject = createMono();
		monoObject.subscribe(data -> System.out.println(data));

		Flux<String> fluxObject = createFlux();
		fluxObject.subscribe(data -> System.out.println(data));
	}

	private static Mono<String> createMono() {
		return Mono.just("Hellow Mono");
	}

	private static Flux<String> createFlux() {
		return Flux.just("Hellow", "Flux");
	}
}
