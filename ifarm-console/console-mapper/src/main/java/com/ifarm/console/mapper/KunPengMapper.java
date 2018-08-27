package com.ifarm.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ifarm.console.shared.domain.po.KunPengCartypePO;
import com.ifarm.console.shared.domain.po.KunPengCityPO;
import com.ifarm.console.shared.domain.po.KunPengCompanyPO;
import com.ifarm.console.shared.domain.po.KunPengPO;

public interface KunPengMapper {

	

	
	List<KunPengCityPO> getKunPengCityAll();
	int insertKunPengCity(KunPengCityPO po);
	int updateCity(KunPengCityPO po);
	int deleteCityByID(Integer id);
	
	List<KunPengCompanyPO> getKunPengCompanyAll(); 
	int insertKunPengCompany(KunPengCompanyPO po);
	int updateCompany(KunPengCompanyPO po);
	int deleteCompanyByID(Integer id);
	
	List<KunPengCartypePO> getKunPengCartypeAll(); 
	int insertKunPengCartype(KunPengCartypePO po);
	int updateCartype(KunPengCartypePO po);
	int deleteCartypeByID(Integer id);
	
	
	List<KunPengPO> getKunPengByMonth(@Param("t1")String t1,@Param("t2")String t2);
	List<KunPengPO> list(); 
	int insert(KunPengPO po);
	int deleteByID(Integer id);
	int update(KunPengPO po);
}
