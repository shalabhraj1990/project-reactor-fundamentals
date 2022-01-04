package spring.boot.webflux.demo;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import spring.boot.webflux.modal.MonoTest;

public class TestFlux {
	MonoTest monoTest = new MonoTest();

	@Test
	public void testFlux() {

		Flux<String> flux = monoTest.createFlux().log();
		StepVerifier.create(flux).expectSubscription().expectNext("Hello", "Flux").expectComplete().verify();
	}

	@Test
	public void testErrorFlux() {
		Flux<String> flux = monoTest.createErrorFlux().log();
		StepVerifier.create(flux).expectSubscription().expectError(RuntimeException.class).verify();
	}
}
