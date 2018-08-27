package com.ifarm.console.facade.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigvision.web.util.DateTimeUtil;
import com.ifarm.console.facade.service.IKunPengService;
import com.ifarm.console.mapper.KunPengMapper;
import com.ifarm.console.shared.domain.po.FundEfficientPO;
import com.ifarm.console.shared.domain.po.KunPengCartypePO;
import com.ifarm.console.shared.domain.po.KunPengCityPO;
import com.ifarm.console.shared.domain.po.KunPengCompanyPO;
import com.ifarm.console.shared.domain.po.KunPengPO;

@Service
public class KunPengServiceImpl implements IKunPengService {

	@Autowired 
	KunPengMapper kunpengmapper;
	


	@Override
	public List<KunPengPO> getKunPengByMonth(String t1, String t2) {
		// TODO Auto-generated method stub
		return kunpengmapper.getKunPengByMonth(t1, t2);
	}

	@Override
	public List<KunPengCityPO> getKunPengCityAll() {
		// TODO Auto-generated method stub
		return kunpengmapper.getKunPengCityAll();
	}

	@Override
	public int insertKunPengCity(KunPengCityPO po) {
		// TODO Auto-generated method stub
		return kunpengmapper.insertKunPengCity(po);
	}

	@Override
	public List<KunPengCompanyPO> getKunPengCompanyAll() {
		// TODO Auto-generated method stub
		return kunpengmapper.getKunPengCompanyAll();
	}

	@Override
	public int insertKunPengCompany(KunPengCompanyPO po) {
		// TODO Auto-generated method stub
		return kunpengmapper.insertKunPengCompany(po);
	}

	@Override
	public List<KunPengCartypePO> getKunPengCartypeAll() {
		// TODO Auto-generated method stub
		return kunpengmapper.getKunPengCartypeAll();
	}

	@Override
	public int insertKunPengCartype(KunPengCartypePO po) {
		// TODO Auto-generated method stub
		return kunpengmapper.insertKunPengCartype(po);
	}

	@Override
	public List<KunPengPO> list() {
		// TODO Auto-generated method stub
		return kunpengmapper.list();
	}

	@Override
	public int insert(KunPengPO po) {
		// TODO Auto-generated method stub
		return kunpengmapper.insert(po);
	}

	@Override
	public int deleteByID(Integer id) {
		// TODO Auto-generated method stub
		return kunpengmapper.deleteByID(id);
	}

	@Override
	public int update(KunPengPO po) {
		// TODO Auto-generated method stub
		return kunpengmapper.update(po);
	}

	@Override
	public void insertByList(List<List<String>> values) throws ParseException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				for(List<String> item:values)
				{
					KunPengPO po = new KunPengPO();
					po.setKunpengDate(DateTimeUtil.getDateByString((String)item.get(0)));
					po.setCity(item.get(1));
					po.setCompany(item.get(2));
					po.setCartype(item.get(3));
					po.setCarsalingprice(item.get(4));
					po.setCarinvoiceprice(item.get(5));
					po.setCarinsurance(item.get(6));
					po.setInvestsum(item.get(7));
					po.setBeianpeople(new Integer(item.get(8)));
					po.setPassedpeople(new Integer(item.get(9)));
					po.setOrdertime(DateTimeUtil.getDateByString((String)item.get(10)));
					po.setFinishtime(DateTimeUtil.getDateByString((String)item.get(11)));
					po.setFinishpeople(new Integer(item.get(12)));
					po.setFundsum(new Integer(item.get(13)));
					
					po.setModifyTime(new Date());
					po.setCreateTime(new Date());
				//	logger.info(item.get(0)+" "+item.get(1)+" "+item.get(2)+" "+item.get(3)+" "+item.get(4)+" "+item.get(5)+" "+po.getModifyTime()+" "+po.getCreateTime());
					kunpengmapper.insert(po);
				}
	}

	@Override
	public int updateCity(KunPengCityPO po) {
		// TODO Auto-generated method stub
		return kunpengmapper.updateCity(po);
	}

	@Override
	public int deleteCityByID(Integer id) {
		// TODO Auto-generated method stub
		return kunpengmapper.deleteCityByID(id);
	}

	@Override
	public int updateCompany(KunPengCompanyPO po) {
		// TODO Auto-generated method stub
		return kunpengmapper.updateCompany(po);
	}

	@Override
	public int deleteCompanyByID(Integer id) {
		// TODO Auto-generated method stub
		return kunpengmapper.deleteCompanyByID(id);
	}


	@Override
	public int deleteCartypeByID(Integer id) {
		// TODO Auto-generated method stub
		return kunpengmapper.deleteCartypeByID(id);
	}

	@Override
	public int updateCartype(KunPengCartypePO po) {
		// TODO Auto-generated method stub
		return kunpengmapper.updateCartype(po);
	}



}
