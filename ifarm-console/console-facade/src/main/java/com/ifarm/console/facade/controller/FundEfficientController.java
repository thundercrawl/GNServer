package com.ifarm.console.facade.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifarm.console.facade.service.IFundEfficientService;
import com.ifarm.console.shared.domain.po.FundEfficientCompanyPO;
import com.ifarm.console.shared.domain.po.FundEfficientFundUsagePO;
import com.ifarm.console.shared.domain.po.FundEfficientPO;
import com.ifarm.console.shared.domain.po.KunPengPO;
import com.ifarm.console.shared.domain.vo.ResponseVO;


@RequestMapping("/fundEfficient")
@RestController
public class FundEfficientController extends AbstractController {
	private static Logger logger = LoggerFactory.getLogger(FundEfficientController.class);
	@Autowired
	IFundEfficientService fundService;
	
	 @RequestMapping("/listbyMonth")
	    public ResponseVO listbyMonth(String t1, String t2) {
		 
		 ResponseVO<List<FundEfficientPO>> responseVO = returnSuccess();
		 
		 try {
	            List<FundEfficientPO> fundList = fundService.getByMonth(t1, t2);
	            responseVO.setResult(fundList);
	          //  logger.info("permission is:"+responseVO.toString());
	        } catch (Exception e) {
	            logger.error("", e);
	            return returnError(e.getMessage());
	        }
	        return responseVO;
	 }
	 
	 @RequestMapping("/list")
	    public ResponseVO getFundByMonth() {
		 
		 ResponseVO<List<FundEfficientPO>> responseVO = returnSuccess();
		 
		 try {
	            List<FundEfficientPO> fundList = fundService.getAll();
	       
	            responseVO.setResult(fundList);
	          
	        } catch (Exception e) {
	            logger.error("", e);
	            return returnError(e.getMessage());
	        }
	        return responseVO;
	 }
	 
	 @RequestMapping("/insert")
		public ResponseVO insert(@RequestBody FundEfficientPO po)
		{  ResponseVO responseVO = returnSuccess();
			try  
			{  
				po.setCreateTime(new Date());
				po.setModifyTime(new Date());
				logger.info("fund leftsum:"+po.getLeftFundSum());
				fundService.insert(po);
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
		public ResponseVO delete(@RequestBody FundEfficientPO po)
		{  ResponseVO responseVO = returnSuccess();
			try  
			{ 
				fundService.delete(po.getTid());
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
		public ResponseVO update(@RequestBody FundEfficientPO po)
		{  ResponseVO responseVO = returnSuccess();
			try  
			{ 
				po.setModifyTime(new Date());
				fundService.update(po);
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
		public ResponseVO insertCompany(@RequestBody FundEfficientCompanyPO po)
		{  ResponseVO responseVO = returnSuccess();
			try  
			{  
				po.setCreateTime(new Date());
				po.setModifyTime(new Date());
				fundService.insertCompany(po);
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
	    public ResponseVO listCompanyALL() {
		 
		 ResponseVO responseVO = returnSuccess();
		 
		 try {
	            List<FundEfficientCompanyPO> fundList = fundService.listCompany();
	       
	            responseVO.setResult(fundList);
	          
	        } catch (Exception e) {
	            logger.error("", e);
	            return returnError(e.getMessage());
	        }
	        return responseVO;
	 }
	 @RequestMapping("/company/delete")
		public ResponseVO deleteCompany(@RequestBody FundEfficientCompanyPO po)
		{  ResponseVO responseVO = returnSuccess();
			try  
			{ 
				fundService.deleteCompany(po.getTid());
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
		public ResponseVO update(@RequestBody FundEfficientCompanyPO po)
		{  ResponseVO responseVO = returnSuccess();
			try  
			{ 
				po.setModifyTime(new Date());
				fundService.updateCompany(po);
			}  
			 catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
			return responseVO;
			
		}
	 
	 @RequestMapping("/fundUsage/insert")
		public ResponseVO insertFundUsage(@RequestBody FundEfficientFundUsagePO po)
		
		{  ResponseVO responseVO = returnSuccess();
			try  
			{  
				po.setCreateTime(new Date());
				po.setModifyTime(new Date());
				fundService.insertFundUsage(po);
			}  
			 catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
			return responseVO;
			
		}
	 
	 @RequestMapping("/fundUsage/list")
	    public ResponseVO listFundUsageALL() {
		 
		 ResponseVO responseVO = returnSuccess();
		 
		 try {
	            List<FundEfficientFundUsagePO> fundList = fundService.listFundUsage();
	       
	            responseVO.setResult(fundList);
	          
	        } catch (Exception e) {
	            logger.error("", e);
	            return returnError(e.getMessage());
	        }
	        return responseVO;
	 }
	 @RequestMapping("/fundUsage/delete")
		public ResponseVO deleteFundUsage(@RequestBody FundEfficientFundUsagePO po)
		{  ResponseVO responseVO = returnSuccess();
			try  
			{ 
				fundService.deleteFundUsage(po.getTid());
			}  
			 catch(Exception e)
	        {
	        	e.printStackTrace();
	        	responseVO = this.returnError();
	        	responseVO.setMessage(e.getMessage());
	        }
			return responseVO;
			
		}
	 
	 @RequestMapping("/fundUsage/update")
		public ResponseVO updateFundUsage(@RequestBody FundEfficientFundUsagePO po)
		{  ResponseVO responseVO = returnSuccess();
			try  
			{ 
				po.setModifyTime(new Date());
				fundService.updateFundUsage(po);
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
