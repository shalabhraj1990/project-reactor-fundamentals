package spring.boot.webflux.demo;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;
import spring.boot.webflux.modal.MonoTest;

public class TestMono {

	@Test
	public void testMono() {
		MonoTest monoTest = new MonoTest();
		StepVerifier.create(monoTest.createMono().log()).expectSubscription().expectNext("Hellow Mono").expectComplete()
				.verify();
	}

	@Test
	public void testErrorMono() {
		MonoTest monoTest = new MonoTest();
		StepVerifier.create(monoTest.createErrorMono().log()).expectSubscription().expectError(RuntimeException.class)
				.verify();
	}

}
 