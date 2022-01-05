package spring.boot.webflux.demo;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TestOperators {
	@Test
	public void testSwitchIfEmpty() {

		Flux<String> flux = createEmptyFlux().switchIfEmpty(Flux.just("default")).log();
		// flux.subscribe(System.out::println);
		StepVerifier.create(flux).expectSubscription().expectNext("default").expectComplete().verify();
	}

	private Flux<String> createEmptyFlux() {
		return Flux.empty();
	}
}
