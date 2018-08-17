package com.ifarm.console.facade.service;

import java.util.List;

import com.ifarm.console.shared.domain.po.ZhiDeContractPO;

public interface IZhiDeContractService {
	int insertContractwithEmptyData(ZhiDeContractPO po);
	List<ZhiDeContractPO> getAll();
	void update(ZhiDeContractPO po);
	int deleteContractByID(Integer id);
	int updateData(ZhiDeContractPO po);
	ZhiDeContractPO getFileDataByID(Integer id);
}
