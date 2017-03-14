package com.doshin.service.kafka.bao;

import com.doshin.service.kafka.model.QueueVO;

public interface QueueBao {
	
	public QueueVO save(QueueVO queue);
	public void update(QueueVO queue);
	public void delete(QueueVO queue);
	public QueueVO findByQueueId(Integer queueId);

}
