package spring.boot.webflux.demo;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class TestMutithreding {
	@Test
	public void testFluxWithSubscribeOn() {

		Flux<Integer> fluxNumber = Flux.range(0, 10).map(i -> {
			// before subscribeOn

			System.out.println(i + "map 1" + Thread.currentThread().getName());
			return i;
		}).subscribeOn(Schedulers.boundedElastic()).map(i -> {
			// after subscribeOn
			System.out.println(i + "map 2" + Thread.currentThread().getName());
			return i;
		});
		fluxNumber.subscribe(i -> System.out.println(i + ""));
	}
	
	@Test
	public void testFluxWithPublishOn() {

		Flux<Integer> fluxNumber = Flux.range(0, 10).map(i -> {
			// before subscribeOn

			System.out.println(i + "map 1" + Thread.currentThread().getName());
			return i;
		}).publishOn(Schedulers.boundedElastic()).map(i -> {
			// after subscribeOn
			System.out.println(i + "map 2" + Thread.currentThread().getName());
			return i;
		});
		fluxNumber.subscribe(i -> System.out.println(i + ""));
	}
}
