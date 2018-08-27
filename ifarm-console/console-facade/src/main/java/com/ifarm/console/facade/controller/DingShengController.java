package com.ifarm.console.facade.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifarm.console.facade.service.IDingShengService;
import com.ifarm.console.facade.service.IKunPengService;
import com.ifarm.console.shared.domain.po.KunPengCartypePO;
import com.ifarm.console.shared.domain.po.KunPengCityPO;
import com.ifarm.console.shared.domain.po.KunPengCompanyPO;
import com.ifarm.console.shared.domain.po.KunPengPO;
import com.ifarm.console.shared.domain.vo.ResponseVO;

@RequestMapping("/dingSheng")
@RestController
public class DingShengController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(KunPengController.class);
	@Autowired
	IDingShengService kunpengservice;
	
	
	@RequestMapping("/insert")
	public ResponseVO insert(@RequestBody KunPengPO po)
	{  ResponseVO responseVO = returnSuccess();
		try  
		{  
			po.setCreateTime(new Date());
			po.setModifyTime(new Date());
			kunpengservice.insert(po);
		}  
		 catch(Exception e)
        {
        	e.printStackTrace();
        	responseVO = this.returnError();
        	responseVO.setMessage(e.getMessage());
        }
		return responseVO;
		
	}
	
	@RequestMapping("/update")
	public ResponseVO update(@RequestBody KunPengPO po)
	{  ResponseVO responseVO = returnSuccess();
		try  
		{  
			po.setModifyTime(new Date());
			kunpengservice.update(po);
		}  
		 catch(Exception e)
        {
        	e.printStackTrace();
        	responseVO = this.returnError();
        	responseVO.setMessage(e.getMessage());
        }
		return responseVO;
		
	}
	
	 
	  @RequestMapping("/list")
	    public ResponseVO list() {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  
		    List<KunPengPO>  pos = kunpengservice.list();
		    responseVO.setResult(pos);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  
	  @RequestMapping("/delete")
	    public ResponseVO delete(@RequestBody KunPengPO po) {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  
	        	kunpengservice.deleteByID(po.getTid());
		    
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
		  
		    List<KunPengPO>  pos = kunpengservice.getKunPengByMonth(t1, t2);
		    if(pos!=null)
		    responseVO.setResult(pos);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  @RequestMapping("/city/insert")
	    public ResponseVO insertCity(@RequestBody KunPengCityPO po) {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  po.setCreateTime(new Date());
		  po.setModifyTime(new Date());
		  kunpengservice.insertKunPengCity(po);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  
	  @RequestMapping("/city/update")
	    public ResponseVO updateCity(@RequestBody KunPengCityPO po) {
	        ResponseVO responseVO = returnSuccess();
	        try {
		 
		  po.setModifyTime(new Date());
		  kunpengservice.updateCity(po);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  
	  @RequestMapping("/city/list")
	    public ResponseVO listCity() {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  
		    List<KunPengCityPO>  pos = kunpengservice.getKunPengCityAll();
		    responseVO.setResult(pos);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  @RequestMapping("/city/delete")
	    public ResponseVO deleteCity(@RequestBody KunPengCityPO po) {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  
	        	kunpengservice.deleteCityByID(po.getTid());
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  
	  @RequestMapping("/company/insert")
	    public ResponseVO insertCompany(@RequestBody KunPengCompanyPO po) {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  po.setCreateTime(new Date());
		  po.setModifyTime(new Date());
		  kunpengservice.insertKunPengCompany(po);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  @RequestMapping("/company/list")
	    public ResponseVO listCompany() {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  
		    List<KunPengCompanyPO>  pos = kunpengservice.getKunPengCompanyAll();
		    responseVO.setResult(pos);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  
	  @RequestMapping("/company/update")
	    public ResponseVO updateCompany(@RequestBody KunPengCompanyPO po) {
	        ResponseVO responseVO = returnSuccess();
	        try {
		 
		  po.setModifyTime(new Date());
		  kunpengservice.updateCompany(po);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  
	  @RequestMapping("/company/delete")
	    public ResponseVO deleteCity(@RequestBody KunPengCompanyPO po) {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  
	        	kunpengservice.deleteCompanyByID(po.getTid());
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  @RequestMapping("/cartype/insert")
	    public ResponseVO insertCartype(@RequestBody KunPengCartypePO po) {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  po.setCreateTime(new Date());
		  po.setModifyTime(new Date());
		  kunpengservice.insertKunPengCartype(po);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	
	  
	  @RequestMapping("/cartype/list")
	    public ResponseVO listCartype() {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  
		    List<KunPengCartypePO>  pos = kunpengservice.getKunPengCartypeAll();
		    responseVO.setResult(pos);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  
	  @RequestMapping("/cartype/update")
	    public ResponseVO updateCompany(@RequestBody KunPengCartypePO po) {
	        ResponseVO responseVO = returnSuccess();
	        try {
		 
		  po.setModifyTime(new Date());
		  kunpengservice.updateCartype(po);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
		  return responseVO;
	  }
	  
	  @RequestMapping("/cartype/delete")
	    public ResponseVO deleteCity(@RequestBody KunPengCartypePO po) {
	        ResponseVO responseVO = returnSuccess();
	        try {
		  
	        	kunpengservice.deleteCartypeByID(po.getTid());
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
