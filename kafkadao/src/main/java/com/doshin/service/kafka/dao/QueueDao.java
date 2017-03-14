package com.doshin.service.kafka.dao;

import com.doshin.service.kafka.dao.model.QueueDO;

public interface QueueDao {

	public QueueDO save(QueueDO queue);
	public void update(QueueDO queue);
	public void delete(QueueDO queue);
	public QueueDO findByQueueId(Integer queueId);

}
