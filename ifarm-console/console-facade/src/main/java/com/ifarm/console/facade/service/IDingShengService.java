package com.ifarm.console.facade.service;

import java.text.ParseException;
import java.util.List;

import com.ifarm.console.shared.domain.po.FundEfficientCompanyPO;
import com.ifarm.console.shared.domain.po.FundEfficientPO;
import com.ifarm.console.shared.domain.po.KunPengCartypePO;
import com.ifarm.console.shared.domain.po.KunPengCityPO;
import com.ifarm.console.shared.domain.po.KunPengCompanyPO;
import com.ifarm.console.shared.domain.po.KunPengPO;

public interface IDingShengService {



	void insertByList(List<List<String>>values) throws ParseException;
	
	
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
	
	
	List<KunPengPO> list(); 
	int update(KunPengPO po);
	int insert(KunPengPO po);
	List<KunPengPO> getKunPengByMonth(String t1,String t2);
	int deleteByID(Integer id);
}
