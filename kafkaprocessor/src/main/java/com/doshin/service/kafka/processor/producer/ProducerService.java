package com.doshin.service.kafka.processor.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service("producerService")
public class ProducerService {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendRequest(String payload) {

		try {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("SpringKafkaTopic", payload);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("Sent message: " + result);
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Failed to send message");
			}
		});
		} catch(Exception exception) {
			
		}
	}
}
