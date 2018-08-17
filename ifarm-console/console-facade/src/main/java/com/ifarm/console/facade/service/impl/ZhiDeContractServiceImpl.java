package com.ifarm.console.facade.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifarm.console.facade.service.IZhiDeContractService;
import com.ifarm.console.mapper.UserMapper;
import com.ifarm.console.mapper.ZhiDeContractMapper;
import com.ifarm.console.shared.domain.po.ZhiDeContractPO;

@Service
public class ZhiDeContractServiceImpl implements IZhiDeContractService {
	private Logger logger = LoggerFactory.getLogger(ZhiDeContractServiceImpl.class);
	
	
	  @Autowired
	    private ZhiDeContractMapper contractMapper;;
	@Override
	public int insertContractwithEmptyData(ZhiDeContractPO po) {
		// TODO Auto-generated method stub
		int rt = contractMapper.insertContractwithEmptyData(po);
		return rt;
	}
	@Override
	public List<ZhiDeContractPO> getAll() {
		return contractMapper.getAll();
		
	}
	@Override
	public void update(ZhiDeContractPO po) {
		// TODO Auto-generated method stub
		contractMapper.update(po);
	}
	@Override
	public int deleteContractByID(Integer id) {
		// TODO Auto-generated method stub
		return contractMapper.deleteContractByID(id);
	}
	@Override
	public int updateData(ZhiDeContractPO po) {
		// TODO Auto-generated method stub
		return contractMapper.updateData(po);
	}
	@Override
	public ZhiDeContractPO getFileDataByID(Integer id) {
		// TODO Auto-generated method stub
		return contractMapper.getFileDataByID(id);
	}

}
