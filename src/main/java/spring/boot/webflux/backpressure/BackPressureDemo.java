package spring.boot.webflux.backpressure;

import java.util.List;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureDemo {
	public static void main(String[] args) {
		Flux<Integer> numbers = Flux.range(1, 10).log();
		
//Client 1st need all data
		// numbers.subscribe(i -> System.out.println("client1 :" + i));
		
		// Client 2nd need data in chucnks of 2 - backpressure
		
		// 1st way backpressure- Subscriber
//		numbers.subscribe(new org.reactivestreams.Subscriber() {
//			private org.reactivestreams.Subscription s;
//			private int counter = 0;
//
//			@Override
//			public void onSubscribe(org.reactivestreams.Subscription subscription) {
//				this.s = subscription;
//				System.out.println("onSubscribe");
//				s.request(2);
//			}
//
//			@Override
//			public void onNext(Object t) {
//				counter++;
//				System.out.println("onNext");
//				if (counter >= 2) {
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				counter = 0;
//				s.request(2);
//			}
//
//			@Override
//			public void onError(Throwable t) {
//				// TODO Auto-generated method stub
//				System.out.println("onError");
//			}
//
//			@Override
//			public void onComplete() {
//				// TODO Auto-generated method stub
//				System.out.println("onComplete");
//			}
//		});

		// 2st way backpressure- BaseSubscriber
//		numbers.subscribe(new BaseSubscriber() {
//			private Subscription s;
//			private int counter = 0;
//
//			@Override
//			protected void hookOnSubscribe(Subscription subscription) {
//				this.s = subscription;
//				System.out.println("hookOnSubscribe");
//				s.request(2);
//			}
//
//			@Override
//			protected void hookOnNext(Object value) {
//				// TODO Auto-generated method stub
//				counter++;
//				System.out.println("onNext");
//				if (counter >= 2) {
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				counter = 0;
//				s.request(2);
//			}
//		});
		
		
		//3rd BackPressure with LimitRate
		Flux<Integer> number2 = Flux.range(20, 10).limitRate(2).log();
		number2.subscribe(i -> System.out.println("client1 :" + i));
		
	}
}
