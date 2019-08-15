package com.example.entity;
/**
 * 
 * ClassName: AcidAndProductForecast 浓磷酸预测成品
 * @Description: TODO
 * @author superman
 * @date 2019年7月14日
 */
public class AcidAndProductForecastEntity {
	Integer acid_and_product_forecast_id;//数据主键
	Long forecast_time;//预测时间 记录时间
	Double forecast_acid_p2o5;//预测记录中的p2o5值
	Double forecast_acid_contain_solid;//预测记录中的含固值
	Double forecast_acid_sulfate_radical;//预测记录中的硫酸根值
	Double forecast_product_total_nitrogen;//预测记录中的总氮值
	Double forecast_product_available_phosphate;//预测记录中的有效磷值
	Double forecast_product_total_nutrient;//预测记录中的总养分值
	Byte forecast_type;//预测方式：1数据分析；2公式a；3公式b
	public Integer getAcid_and_product_forecast_id() {
		return acid_and_product_forecast_id;
	}
	public void setAcid_and_product_forecast_id(Integer acid_and_product_forecast_id) {
		this.acid_and_product_forecast_id = acid_and_product_forecast_id;
	}
	public Long getForecast_time() {
		return forecast_time;
	}
	public void setForecast_time(Long forecast_time) {
		this.forecast_time = forecast_time;
	}
	public Double getForecast_acid_p2o5() {
		return forecast_acid_p2o5;
	}
	public void setForecast_acid_p2o5(Double forecast_acid_p2o5) {
		this.forecast_acid_p2o5 = forecast_acid_p2o5;
	}
	public Double getForecast_acid_contain_solid() {
		return forecast_acid_contain_solid;
	}
	public void setForecast_acid_contain_solid(Double forecast_acid_contain_solid) {
		this.forecast_acid_contain_solid = forecast_acid_contain_solid;
	}
	public Double getForecast_acid_sulfate_radical() {
		return forecast_acid_sulfate_radical;
	}
	public void setForecast_acid_sulfate_radical(Double forecast_acid_sulfate_radical) {
		this.forecast_acid_sulfate_radical = forecast_acid_sulfate_radical;
	}
	public Double getForecast_product_total_nitrogen() {
		return forecast_product_total_nitrogen;
	}
	public void setForecast_product_total_nitrogen(Double forecast_product_total_nitrogen) {
		this.forecast_product_total_nitrogen = forecast_product_total_nitrogen;
	}
	public Double getForecast_product_available_phosphate() {
		return forecast_product_available_phosphate;
	}
	public void setForecast_product_available_phosphate(Double forecast_product_available_phosphate) {
		this.forecast_product_available_phosphate = forecast_product_available_phosphate;
	}
	public Double getForecast_product_total_nutrient() {
		return forecast_product_total_nutrient;
	}
	public void setForecast_product_total_nutrient(Double forecast_product_total_nutrient) {
		this.forecast_product_total_nutrient = forecast_product_total_nutrient;
	}
	public Byte getForecast_type() {
		return forecast_type;
	}
	public void setForecast_type(Byte forecast_type) {
		this.forecast_type = forecast_type;
	}
	

}
