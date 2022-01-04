package spring.boot.webflux.demo;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;
import spring.boot.webflux.modal.PublisherTest;

public class TestMono {

	@Test
	public void testMono() {
		PublisherTest monoTest = new PublisherTest();
		StepVerifier.create(monoTest.createMono().log()).expectSubscription().expectNext("Hellow Mono").expectComplete()
				.verify();
	}

	@Test
	public void testErrorMono() {
		PublisherTest monoTest = new PublisherTest();
		StepVerifier.create(monoTest.createErrorMono().log()).expectSubscription().expectError(RuntimeException.class)
				.verify();
	}

}
 