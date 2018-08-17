package com.ifarm.console.mapper;
import java.util.List;

import com.ifarm.console.shared.domain.po.ZhiDeContractPO;
public interface ZhiDeContractMapper {

	int insertContractwithEmptyData(ZhiDeContractPO po);
	List<ZhiDeContractPO> getAll();
	
	int update(ZhiDeContractPO po);
	
	int deleteContractByID(Integer id);
	
	int updateData(ZhiDeContractPO po);
	
	ZhiDeContractPO getFileDataByID(Integer id);
}
