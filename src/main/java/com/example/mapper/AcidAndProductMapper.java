package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.entity.AcidAndProductEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AcidAndProductMapper {
	
	
	
	
	List<AcidAndProductEntity>selectAcidAndProductByAProductEntity(
			@Param("acidAndProductEntity")AcidAndProductEntity acidAndProductEntity);//根据浓硫酸参数查询记录
	Double selectMaxAcid_p2o5();//查询p2o5最大的记录值
	Double selectMinAcid_p2o5();//查询p2o5最小的记录值
	Double selectMaxAcid_contain_solid();//查询浓磷酸含固最大值的记录
	Double selectMinAcid_contain_solid();//查询浓磷酸含固最小值的记录
	Double selectMaxAcid_sulfate_radical();//查询浓磷酸硫酸根最大值的记录
	Double selectMinAcid_sulfate_radical();//查询浓磷酸硫酸根最小值的记录
	
	List<AcidAndProductEntity>selectAcidAndProductByAcid_p2o5(
			@Param("acid_p2o5")Double acid_p2o5);//根据浓硫酸 p2o5 参数查询记录 selectAcidAndProductAll
	List<AcidAndProductEntity>selectAcidAndProduct1();// 
	/**
	 * 
	 * @Description: TODO 根据参数上下限范围查询记录
	 * @param @param minAcid_p2o5 p2o5最小值 下限
	 * @param @param maxAcid_p2o5 p2o5最大值 上限
	 * @param @param minAcid_contain_solid 含固最小值 下限
	 * @param @param maxAcid_contain_solid 含固最小值  上限
	 * @param @param minAcid_sulfate_radical 硫酸根最小值 下限
	 * @param @param maxAcid_sulfate_radical 硫酸根最大值 上限
	 * @param @return   
	 * @return List<AcidAndProductEntity>  
	 * @throws
	 * @author superman
	 * @date 2019年7月14日
	 */
	List<AcidAndProductEntity>selectAcidAndProductByAProductRange(@Param("minAcid_p2o5")Double minAcid_p2o5,
			@Param("maxAcid_p2o5")Double maxAcid_p2o5,@Param("minAcid_contain_solid")double minAcid_contain_solid,
			@Param("maxAcid_contain_solid")double maxAcid_contain_solid,@Param("minAcid_sulfate_radical")double minAcid_sulfate_radical,
			@Param("maxAcid_sulfate_radical")double maxAcid_sulfate_radical);//根据浓硫酸参数 范围查询记录

}
