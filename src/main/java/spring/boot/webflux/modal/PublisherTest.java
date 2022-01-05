 package spring.boot.webflux.modal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PublisherTest {

	public Mono<String> createMono() {
		return Mono.just("Hellow Mono");
	}

	public Mono<String> createErrorMono() {
		return Mono.error(new RuntimeException("error"));
	}

	public static Flux<String> createFlux() {
		return Flux.just("Hello", "Flux");
	}

	public static Flux<String> createErrorFlux() {
		return Flux.just("Hello", "Flux").error(new RuntimeException("error"));
	}

	public Flux<String> flux1() {
		return Flux.just("a", "b");
	}

	public Flux<String> flux2(String input) {
		if (input.equals("a"))
			return Flux.just("fulxA1", "fulxA2");

		return Flux.just("fulxB1", "fulxB2");
	}
}
