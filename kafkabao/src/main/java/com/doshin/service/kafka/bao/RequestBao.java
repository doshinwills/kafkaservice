package com.doshin.service.kafka.bao;

import com.doshin.service.kafka.model.RequestVO;

public interface RequestBao {
	
	public RequestVO save(RequestVO request);
	public void update(RequestVO request);
	public void delete(RequestVO request);
	public RequestVO findByRequestId(Integer request);

}
