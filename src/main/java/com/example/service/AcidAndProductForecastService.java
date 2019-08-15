package com.example.service;

import java.util.List;



import com.example.entity.AcidAndProductForecastEntity;

public interface AcidAndProductForecastService {
	/**
	 * 
	 * @Description: TODO 查询预测记录 设置查询的记录时间范围与预测类型，不设置视作不设置此条件
	 * @param @param forecastTimeStart 时间范围 开始时间
	 * @param @param forecastTimeEnd 时间范围 结束时间
	 * @param @param forecasType 预测类型 预测方式：1数据分析；2公式a；3公式b
	 * @param @return   
	 * @return List<AcidAndProductForecastEntity>  
	 * @throws
	 * @author superman
	 * @date 2019年7月14日
	 */
	List<AcidAndProductForecastEntity> findAcidAndProductForecast(
			Long forecastTimeStart,Long forecastTimeEnd,Byte forecasType);

}
