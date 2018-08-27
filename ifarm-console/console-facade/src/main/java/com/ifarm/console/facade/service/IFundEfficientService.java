package com.ifarm.console.facade.service;

import java.text.ParseException;
import java.util.List;

import com.ifarm.console.shared.domain.po.FundEfficientCompanyPO;
import com.ifarm.console.shared.domain.po.FundEfficientFundUsagePO;
import com.ifarm.console.shared.domain.po.FundEfficientPO;

public interface IFundEfficientService {
	List<FundEfficientPO> getAll();
	List<FundEfficientPO> getByMonth(String t1,String t2);
	void insert(FundEfficientPO po);
	void insertByList(List<List<String>>values) throws ParseException;
	
	void update(FundEfficientPO fund);
	int delete(Integer id);
	
	int insertCompany(FundEfficientCompanyPO po);
	List<FundEfficientCompanyPO> listCompany();
	int deleteCompany(Integer id);
	void updateCompany(FundEfficientCompanyPO po);
	
	
	int insertFundUsage(FundEfficientFundUsagePO po);
	List<FundEfficientFundUsagePO> listFundUsage();
	int deleteFundUsage(Integer id);
	void updateFundUsage(FundEfficientFundUsagePO po);
}
