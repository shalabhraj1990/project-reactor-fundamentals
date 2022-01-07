package spring.boot.webflux.demo;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

public class TestOperators {
//	@Test
//	public void testSwitchIfEmpty() {
//
//		Flux<String> flux = createEmptyFlux().switchIfEmpty(Flux.just("default")).log();
//		// flux.subscribe(System.out::println);
//		StepVerifier.create(flux).expectSubscription().expectNext("default").expectComplete().verify();
//	}
//
//	private Flux<String> createEmptyFlux() {
//		return Flux.empty();
//	}
//
//	@Test
//	public void testConcat() {
//
//		Flux<Integer> flux1 = Flux.range(1, 5);
//		Flux<Integer> flux2 = Flux.range(6, 5);
//		Flux<Integer> result = Flux.concat(flux1, flux2).log();
//		// OR Flux<Integer> result = flux1.concatWith(flux2).log();
//		StepVerifier.create(result).expectSubscription().expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).expectComplete()
//				.verify();
//	}

	@Test
	public void testConcatDelayError() {

		Flux<Integer> flux1 = Flux.range(1, 5).map(i -> {
			if (i == 3)
				throw new RuntimeException("error");
			return i;
		});
		Flux<Integer> flux2 = Flux.range(6, 5);
		Flux<Integer> result = Flux.concatDelayError(flux1, flux2).log();
		StepVerifier.create(result).expectSubscription().expectNext(1, 2, 6, 7, 8, 9, 10).expectError().verify();
	}

//	@Test
//	public void testMerge() {
//
//		Flux<Integer> flux1 = Flux.range(1, 5).delayElements(Duration.ofMillis(100)).log();
//		Flux<Integer> flux2 = Flux.range(6, 5);
//		Flux<Integer> result = Flux.merge(flux1, flux2).log();
//		//OR Flux<Integer> result = flux1.mergetWith(flux2).log();
//		StepVerifier.create(result).expectSubscription().expectNext( 6, 7, 8, 9, 10,1, 2, 3, 4, 5).expectComplete()
//				.verify();
//	}

	@Test
	public void testMergeWithError() {

		Flux<Integer> flux1 = Flux.range(1, 5).map(i -> {
			if (i == 3)
				throw new RuntimeException("error");
			return i;
		});
		Flux<Integer> flux2 = Flux.range(6, 5);
		Flux<Integer> result = Flux.mergeDelayError(0, flux1, flux2).log();
		StepVerifier.create(result).expectSubscription().expectNext(1, 2, 6, 7, 8, 9, 10).expectError().verify();
	}

//	@Test
//	public void testZip() {
//
//		Flux<Integer> flux1 = Flux.range(1, 5);
//		Flux<String> flux2 = Flux.just("shalabh", "raj");
//		Flux<Tuple2<Integer, String>> zip = Flux.zip(flux1, flux2).log();
//		zip.subscribe(tuple -> {
//			Integer number = tuple.getT1();
//			String name = tuple.getT2();
//			System.out.println(number + "-" + name);
//		});
//	}
}
