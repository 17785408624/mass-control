package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.AcidAndProductForecastEntity;
import com.example.mapper.AcidAndProductForecastMapper;
@Service
public class AcidAndProductForecastServiceImpl implements AcidAndProductForecastService{

	@Autowired
	private AcidAndProductForecastMapper acidAndProductForecastMapper;
	@Override
	//查询预测记录 设置查询的记录时间范围与预测类型，不设置视作不设置此条件
	public List<AcidAndProductForecastEntity> findAcidAndProductForecast(
			Long forecastTimeStart, Long forecastTimeEnd, Byte forecasType) {
		// TODO Auto-generated method stub
		
		return acidAndProductForecastMapper.selectAcidAndProductForecast(forecastTimeStart, forecastTimeEnd, forecasType);
	}

}
