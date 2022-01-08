package spring.boot.webflux.errorhandling;

import reactor.core.publisher.Mono;

public class ReactorErrorHandling {
	public static void main(String[] args) {
		// testDoOnError();
		// testDoOnErrorResume();
		testOnErrorMap();
	}

	public static void testDoOnError() {
		Mono<Object> errorMono = Mono.error(new RuntimeException("RuntimeException"))
				.doOnError(th -> System.out.println("log error msg into db")).log();
		errorMono.subscribe(data -> System.out.println(data), th -> System.out.println(th.getStackTrace()),
				() -> System.out.println("finally"));
	}

	public static void testDoOnErrorResume() {
		Mono<Object> errorMono = Mono.error(new RuntimeException("RuntimeException"))
				.doOnError(th -> System.out.println("log error msg into db")).onErrorResume(th -> {
					System.out.println("log exception");
					return Mono.just("error handled");
				}).log();
		errorMono.subscribe(data -> System.out.println(data), th -> System.out.println(th.getStackTrace()),
				() -> System.out.println("finally"));
	}

	public static void testOnErrorMap() {
		Mono<Object> errorMono = Mono.error(new RuntimeException("RuntimeException"))
				.onErrorMap(th -> new IllegalArgumentException("convert to IllegalArgumentException"))
				.onErrorResume(th -> {
					System.out.println("log exception" + th.getClass().getName());
					return Mono.just("error handled");
				})
				// doOnError will become dummy as it's after onErrorResume
				.doOnError(th -> System.out.println("log error msg into db")).log();
		errorMono.subscribe(data -> System.out.println(data), th -> System.out.println(th.getStackTrace()),
				() -> System.out.println("finally"));

	}
}
