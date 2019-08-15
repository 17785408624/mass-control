package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.entity.AcidAndProductEntity;
import com.example.entity.AcidAndProductForecastEntity;

public interface AcidAndProductForecastMapper {
	/**
	 * 
	 * @Description: TODO 添加浓硫酸与成品预测记录
	 * @param @param acidAndProductEntity 浓硫酸与成品关系
	 * @param @param addTime 添加时间
	 * @param @param forecastType 预测方式1数据分析 2公式a预测 3公式b预测
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author superman
	 * @date 2019年7月14日
	 */
	int insertAcidAndProductForecast(@Param("acidAndProductForecastEntity")AcidAndProductForecastEntity acidAndProductForecastEntity);
	/**
	 * 
	 * @Description: TODO 查询预测记录
	 * @param @param forecastTimeStart 参数作为开始时间
	 * @param @param forecastTimeEnd 参数作为结束时间
	 * @param @param forecasType  预测类型 预测方式：1数据分析；2公式a；3公式b
	 * @param @return   
	 * @return List<AcidAndProductForecastEntity>  
	 * @throws
	 * @author superman
	 * @date 2019年7月14日
	 */
	List<AcidAndProductForecastEntity> selectAcidAndProductForecast(
			@Param("forecastTimeStart")Long forecastTimeStart,@Param("forecastTimeEnd")Long forecastTimeEnd,
			@Param("forecasType")Byte forecasType);

}
