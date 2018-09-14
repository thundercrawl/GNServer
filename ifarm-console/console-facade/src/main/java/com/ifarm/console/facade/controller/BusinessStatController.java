package com.ifarm.console.facade.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigvision.web.util.Constants;
import com.ifarm.console.facade.service.IDingShengService;
import com.ifarm.console.facade.service.IFundEfficientService;
import com.ifarm.console.facade.service.IKunPengService;
import com.ifarm.console.shared.domain.po.DingShengPO;
import com.ifarm.console.shared.domain.po.FundEfficientCompanyPO;
import com.ifarm.console.shared.domain.po.FundEfficientPO;
import com.ifarm.console.shared.domain.po.KunPengPO;
import com.ifarm.console.shared.domain.vo.BusinessStatItemVO;
import com.ifarm.console.shared.domain.vo.BusinessStatVO;
import com.ifarm.console.shared.domain.vo.ResponseVO;

@RequestMapping("/businessStat")
@RestController
public class BusinessStatController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(DingShengController.class);
	@Autowired
	IDingShengService dingshengservice;
	
	@Autowired
	IKunPengService kunpengservice;
	
	@Autowired
	IFundEfficientService fundservice;
	
	 @RequestMapping("/list")
	    public ResponseVO list() {
	        ResponseVO responseVO = returnSuccess();
	        try {
	        	BusinessStatVO vo = new BusinessStatVO();
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	        }
	 
	 
	 @RequestMapping("/listbyMonth")
	    public ResponseVO listbyMonth(String t1, String t2) {
	        ResponseVO responseVO = returnSuccess();
	        try {
	        	
	        	List<FundEfficientPO> dingsheng= new ArrayList<FundEfficientPO>();
	        	List<FundEfficientPO> kunpeng =  new ArrayList<FundEfficientPO>();
	        	BusinessStatItemVO itemKunPeng = new BusinessStatItemVO();
	        	BusinessStatItemVO  itemDingSheng = new BusinessStatItemVO();
	        	
	        	List<FundEfficientPO> fundpos = fundservice.getByMonth(t1, t2);
	        	
	        	for(FundEfficientPO index:fundpos)
	        	{
	        		if(index.getFundSource().equals(Constants.DING_SHENG))
	        		{ 
	        			dingsheng.add(index);
	        		}
	        		else if (index.getFundSource().equals(Constants.KUN_PENG))
	        		{
	        			kunpeng.add(index);
	        		}
	        	}
	        	itemKunPeng.setValues(kunpeng);
	        	itemDingSheng.setValues(dingsheng);
	        
	        	Float v1 = new Float(0),v2 =new Float(0),v3 =new Float(0),v4 =new Float(0), v5 =new Float(0), v6 = new Float(0);
	        	
	        	for(FundEfficientPO index:kunpeng)
	        	{
	        		v1+=new Float(index.getFundSum());
	        		v2+= new Float(index.getLeftFundSum());
	        		v3+= new Float(index.getMonthlyFundSum());
	        	}
	        	itemKunPeng.setFundSum(v1);
	        	itemKunPeng.setLeftfundSum(v2);
	        	itemKunPeng.setMonthlyFundSum(v3);
	        	for(FundEfficientPO index:dingsheng)
	        	{
	        		v4+= new Float(index.getFundSum());
	        		v5+= new Float(index.getLeftFundSum());
	        		v6+= new Float(index.getMonthlyFundSum());
	        	}
	        	
	        	
	        	itemDingSheng.setFundSum(v4);
	        	itemDingSheng.setLeftfundSum(v5);
	        	itemDingSheng.setMonthlyFundSum(v6);
	        	
	        	logger.info("kun peng fundsource:"+kunpeng.size()+" dingsheng fundsource:"+dingsheng.size());
	        	BusinessStatVO vo = new BusinessStatVO();
	        	
	        	List<KunPengPO>kunpengPOS =  kunpengservice.getKunPengByMonth(t1, t2);
	        	List<KunPengPO>dingshengPOS =  dingshengservice.getKunPengByMonth(t1, t2);
	        	vo.setKunpeng(kunpengPOS);
	        	vo.setDingsheng(dingshengPOS);
	        	Integer v11 =0, v22 =0, v33=0,  v44 =0, v55 =0, v66 = 0;
	        	for(KunPengPO index:kunpengPOS)
	        	{
	        		v11+=index.getPassedpeople();
	        		v22+=index.getFinishpeople();
	        		
	        	}
	        	v33 = v11-v22;
	        	
	        	itemKunPeng.setPassedpeople(v11);
	        	itemKunPeng.setFinishpeople(v22);
	        	itemKunPeng.setWaitPeople(v33);
	        	
	        	for(KunPengPO index:dingshengPOS)
	        	{
	        		v44+=index.getPassedpeople();
	        		v55+=index.getFinishpeople();
	        		
	        	}
	        	v66 = v44-v55;
	        	itemDingSheng.setPassedpeople(v44);
	        	itemDingSheng.setFinishpeople(v55);
	        	itemDingSheng.setWaitPeople(v66);
	        	vo.setDingshengitem(itemDingSheng);
	        	vo.setKunpengitem(itemKunPeng);
	        	responseVO.setResult(vo);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	        }
}
