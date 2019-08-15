package com.example.entity;

public class AcidAndProductEntity {// 浓磷酸与产品质量的关系记录
	private Integer acid_and_product_id;// 主键
	private Double acid_p2o5;// 浓磷酸p2o5
	private Double acid_contain_solid;// 浓磷酸含固
	private Double acid_sulfate_radical;// 浓磷酸硫酸根
	private Double product_total_nitrogen;// 产品总氮
	private Double product_available_phosphate;// 产品有效磷
	private Double product_total_nutrient;// 产品总养分
	public Integer getAcid_and_product_id() {
		return acid_and_product_id;
	}
	public void setAcid_and_product_id(Integer acid_and_product_id) {
		this.acid_and_product_id = acid_and_product_id;
	}
	public Double getAcid_p2o5() {
		return acid_p2o5;
	}
	public void setAcid_p2o5(Double acid_p2o5) {
		this.acid_p2o5 = acid_p2o5;
	}
	public Double getAcid_contain_solid() {
		return acid_contain_solid;
	}
	public void setAcid_contain_solid(Double acid_contain_solid) {
		this.acid_contain_solid = acid_contain_solid;
	}
	public Double getAcid_sulfate_radical() {
		return acid_sulfate_radical;
	}
	public void setAcid_sulfate_radical(Double acid_sulfate_radical) {
		this.acid_sulfate_radical = acid_sulfate_radical;
	}
	public Double getProduct_total_nitrogen() {
		return product_total_nitrogen;
	}
	public void setProduct_total_nitrogen(Double product_total_nitrogen) {
		this.product_total_nitrogen = product_total_nitrogen;
	}
	public Double getProduct_available_phosphate() {
		return product_available_phosphate;
	}
	public void setProduct_available_phosphate(Double product_available_phosphate) {
		this.product_available_phosphate = product_available_phosphate;
	}
	public Double getProduct_total_nutrient() {
		return product_total_nutrient;
	}
	public void setProduct_total_nutrient(Double product_total_nutrient) {
		this.product_total_nutrient = product_total_nutrient;
	}


}
