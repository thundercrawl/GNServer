package com.ifarm.console.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ifarm.console.shared.domain.po.FundEfficientCompanyPO;
import com.ifarm.console.shared.domain.po.FundEfficientPO;

public interface FundEfficientMapper {
	
	int insertCompany(FundEfficientCompanyPO po);
	List<FundEfficientCompanyPO> listCompany();
	
	
	List<FundEfficientPO> getAll();
	List<FundEfficientPO> getByMonth(@Param("t1")String t1,@Param("t2")String t2);
	void update(FundEfficientPO fund);
	void insert(FundEfficientPO fund);
	int delete(Integer id);
}
