package spring.boot.webflux.modal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoTest {

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
}
                                                                        