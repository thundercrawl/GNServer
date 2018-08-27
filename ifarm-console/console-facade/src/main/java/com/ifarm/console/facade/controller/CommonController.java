package com.ifarm.console.facade.controller;

import static com.ifarm.console.shared.define.ResponseCode.SUCCESS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;


import com.bigvision.web.util.Constants;
import com.bigvision.web.util.DateTimeUtil;
import com.bigvision.web.util.MSOfficeDocumentFactory;
import com.bigvision.web.util.FileUtils;
import com.ifarm.console.facade.service.IDingShengService;
import com.ifarm.console.facade.service.IFundEfficientService;
import com.ifarm.console.facade.service.IKunPengService;
import com.ifarm.console.shared.domain.po.FundEfficientPO;
import com.ifarm.console.shared.domain.po.KunPengPO;
import com.ifarm.console.shared.domain.vo.BusinessStatItemVO;
import com.ifarm.console.shared.domain.vo.BusinessStatVO;
import com.ifarm.console.shared.domain.vo.ResponseVO;


@RestController
public class CommonController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(CommonController.class);
	@Autowired
	IFundEfficientService fundService;
	
	@Autowired
	IKunPengService kunpengService;
	
	@Autowired
	IDingShengService dingshengService;
	
	@RequestMapping("/ExportExcelByMonthName") 
	 public StreamingResponseBody exportExcel(String name,String Month,HttpServletResponse resp)
	 {
		 return null;
	 }

	@RequestMapping("/ExportExcelAllByName") 
	 public StreamingResponseBody exportExcelALL(String name,String date,HttpServletResponse resp)
	 {
		
		logger.info("start to download excel by name:" + name+" date="+date);
		boolean emtpydata = false;
		try
		{
	      resp.setContentType("application/x-msdownload");
	      List<String> cols = null;
	      List<List<String>> values = new ArrayList<List<String>>();
	       final String filename ;
	      if(name.equals("fundefficient"))
	      {
	    	  logger.info("enter fundefficient");
	    	  filename =  "./fundefficient"+UUID.randomUUID()+".xls";
	    	  String filenamedownload="fundefficient.xls";
	    	  resp.setHeader("Content-Disposition", "filename=" +"\""+filenamedownload+"\"");
	    	  List<FundEfficientPO> pos = null;
	    	  if(date!=null &&(date.equals("ALL")||date.equals('-')))
	    		  pos = fundService.getAll();
	    	  else
	    	  {
	    		  logger.info("export search by month");
	    		  pos = fundService.getByMonth(date+"-01", date+"-31");
	    	  }
	    	  if(pos.isEmpty()||pos==null)
	    	  {
	    		  logger.info("empty data from db, return an empty excel");
	    		  emtpydata = true;
	    	  }
	    	  else
	    	  {
		    	  logger.info("get data from db,size:"+pos.size());
		    	  for(int i =0;i<pos.size();i++)
		    	  {
		    		  FundEfficientPO tmpPO = pos.get(i);
		    		  List<String> rowItem = new ArrayList<String>();
		    		  rowItem.add(DateTimeUtil.getLocaldate(tmpPO.getFundDate()));
		    		  rowItem.add(tmpPO.getFundSource());
		    		  rowItem.add(tmpPO.getFundSum().toString());
		    		  rowItem.add(tmpPO.getFundUsage());
		    		  rowItem.add(tmpPO.getMonthlyFundSum().toString());
		    		  rowItem.add(tmpPO.getAlreadyLentFundSum().toString());
		    		  rowItem.add(tmpPO.getLeftFundSum().toString());
		    		  values.add(rowItem);
		    	  }
		    	  cols = Constants.getFundEfficientCol();
	    	  }
	      }
	      else if(name.equals("kunpeng")||name.equals("dingsheng"))
	      {

	    	  logger.info("enter kunpneg and dingsheng");
	    	  filename =  "./kunpengdingsheng"+UUID.randomUUID()+".xls";
	    	  String filenamedownload=name+".xls";
	    	  resp.setHeader("Content-Disposition", "filename=" +"\""+filenamedownload+"\"");
	    	  List<KunPengPO> pos = new ArrayList<KunPengPO>();
	    	  if(date!=null &&(date.equals("ALL")||date.equals('-')))
	    	  {
	    		  if(name.equals("kunpeng"))
	    			  pos = kunpengService.list();
	    		  else
	    			  pos=dingshengService.list();
	    	  }
	    	  else
	    	  {
	    		  logger.info("export search by month");
	    		  if(name.equals("kunpeng"))
	    			  pos = kunpengService.getKunPengByMonth(date+"-01", date+"-31");
	    		  else
	    			  pos=dingshengService.getKunPengByMonth(date+"-01", date+"-31");
	    		 
	    	  }
	    	  if(pos.isEmpty()||pos==null)
	    	  {
	    		  logger.info("empty data from db, return an empty excel");
	    		  emtpydata = true;
	    	  }
	    	  else
	    	  {
		    	  logger.info("get data from db,size:"+pos.size());
		    	  for(int i =0;i<pos.size();i++)
		    	  {
		    		  KunPengPO tmpPO = pos.get(i);
		    		  List<String> rowItem = new ArrayList<String>();
		    		  rowItem.add(DateTimeUtil.getLocaldate(tmpPO.getKunpengDate()));
		    		  rowItem.add(tmpPO.getCity());
		    		  rowItem.add(tmpPO.getCompany());
		    		  rowItem.add(tmpPO.getCartype());
		    		  rowItem.add(tmpPO.getCarsalingprice());
		    		  rowItem.add(tmpPO.getCarinvoiceprice());
		    		  rowItem.add(tmpPO.getCarinsurance());
		    		  rowItem.add(tmpPO.getInvestsum());
		    		  rowItem.add(tmpPO.getBeianpeople().toString());
		    		  rowItem.add(tmpPO.getPassedpeople().toString());
		    		  rowItem.add(DateTimeUtil.getLocaldate(tmpPO.getOrdertime())) ;
		    		  rowItem.add(DateTimeUtil.getLocaldate(tmpPO.getFinishtime())) ;
		    		  rowItem.add(tmpPO.getFinishpeople().toString());
		    		  rowItem.add(tmpPO.getFundsum().toString());
		    		  
		    		  values.add(rowItem);
		    	  }
		    	  cols = Constants.getKunpengDingshengCol();
	    	  }
	      
	      }
	      else if(name.equals("bussstat"))
	      {
	    	  logger.info("enter bussstat ");
	    	  filename =  "./bussstat"+UUID.randomUUID()+".xls";
	    	  String filenamedownload=name+".xls";
	    	  resp.setHeader("Content-Disposition", "filename=" +"\""+filenamedownload+"\"");
	    	  BusinessStatVO vo = new BusinessStatVO();
	    	  if(date!=null &&(date.equals("ALL")||date.equals('-')))
	    	  {}
	    	  else
	    	  {
	    		  logger.info("export search by month");
		        	
		        	List<FundEfficientPO> dingsheng= new ArrayList<FundEfficientPO>();
		        	List<FundEfficientPO> kunpeng =  new ArrayList<FundEfficientPO>();
		        	BusinessStatItemVO itemKunPeng = new BusinessStatItemVO();
		        	BusinessStatItemVO  itemDingSheng = new BusinessStatItemVO();
		        	
		        	List<FundEfficientPO> fundpos = fundService.getByMonth(date+"-01", date+"-31");
		        	
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
		        
		        	Integer v1 = 0,v2 =0, v3 =0,v4 =0, v5 =0, v6 = 0;
		        	
		        	for(FundEfficientPO index:kunpeng)
		        	{
		        		v1+=index.getFundSum();
		        		v2+= index.getLeftFundSum();
		        		v3+= index.getMonthlyFundSum();
		        	}
		        	itemKunPeng.setFundSum(v1);
		        	itemKunPeng.setLeftfundSum(v2);
		        	itemKunPeng.setMonthlyFundSum(v3);
		        	for(FundEfficientPO index:dingsheng)
		        	{
		        		v4+= index.getFundSum();
		        		v5+= index.getLeftFundSum();
		        		v6+= index.getMonthlyFundSum();
		        	}
		        	
		        	
		        	itemDingSheng.setFundSum(v4);
		        	itemDingSheng.setLeftfundSum(v5);
		        	itemDingSheng.setMonthlyFundSum(v6);
		        	
		        	logger.info("kun peng fundsource:"+kunpeng.size()+" dingsheng fundsource:"+dingsheng.size());
		        	
		        	
		        	List<KunPengPO>kunpengPOS =  kunpengService.getKunPengByMonth(date+"-01", date+"-31");
		        	List<KunPengPO>dingshengPOS =  dingshengService.getKunPengByMonth(date+"-01", date+"-31");
		        	vo.setKunpeng(kunpengPOS);
		        	vo.setDingsheng(dingshengPOS);
		        	v1 = v2 = v3 = v4 = v5 = v6 = 0;
		        	for(KunPengPO index:kunpengPOS)
		        	{
		        		v1+=index.getPassedpeople();
		        		v2+=index.getFinishpeople();
		        		
		        	}
		        	v3 = v1-v2;
		        	
		        	itemKunPeng.setPassedpeople(v1);
		        	itemKunPeng.setFinishpeople(v2);
		        	itemKunPeng.setWaitPeople(v3);
		        	
		        	for(KunPengPO index:dingshengPOS)
		        	{
		        		v4+=index.getPassedpeople();
		        		v5+=index.getFinishpeople();
		        		
		        	}
		        	v6 = v4-v5;
		        	itemDingSheng.setPassedpeople(v4);
		        	itemDingSheng.setFinishpeople(v5);
		        	itemDingSheng.setWaitPeople(v6);
		        	vo.setDingshengitem(itemDingSheng);
		        	vo.setKunpengitem(itemKunPeng);
		        
		        
	    		
	    	  
	    	
	    	  }
	    	  if(vo.getDingshengitem()==null&&vo.getKunpengitem()==null)
	    	  {
	    		  logger.info("empty data from db, return an empty excel");
	    		  emtpydata = true;
	    	  }
	    	  else
	    	  {
		    	 if(vo.getDingshengitem() == null)
		    		 vo.setDingshengitem(new BusinessStatItemVO() );
		    	 if(vo.getKunpengitem() == null)
		    		 vo.setKunpengitem(new BusinessStatItemVO());
	    		  BusinessStatItemVO dingItem= 	vo.getDingshengitem();
	    		  BusinessStatItemVO kunItem = vo.getKunpengitem();
		    	
	    		  List<String> rowkunItem = new ArrayList<String>();
	    		  rowkunItem.add("坤鹏业务");
	    		  if(kunItem.getPassedpeople()==null)
	    			  rowkunItem.add("0");
	    		  else rowkunItem.add(kunItem.getPassedpeople().toString());
	    		  
	    		  if(kunItem.getFinishpeople() ==null)
	    			  rowkunItem.add("0");
	    		  else
	    			  rowkunItem.add(kunItem.getFinishpeople().toString());
	    		  if(kunItem.getWaitPeople() == null)
	    			  rowkunItem.add("0");
	    		  else
	    		  rowkunItem.add(kunItem.getWaitPeople().toString());
	    		  
	    		  if(kunItem.getFundSum() == null)
	    			  rowkunItem.add("0");
	    		  else
	    			  rowkunItem.add(kunItem.getFundSum().toString());
	    		  
	    		  if(kunItem.getLeftfundSum() == null)
	    			  rowkunItem.add("0");
	    		  else
	    		  rowkunItem.add(kunItem.getLeftfundSum().toString());
	    		  
	    		  if(kunItem.getMonthlyFundSum() == null)
	    			  rowkunItem.add("0");
	    		  else
	    		  rowkunItem.add(kunItem.getMonthlyFundSum().toString());
	    		  
	    		  values.add(rowkunItem);
	    		  
	    		  List<String> rowdingItem = new ArrayList<String>();
	    		  rowdingItem.add("鼎盛业务");
	    		  if(dingItem.getPassedpeople()==null)
	    			  rowdingItem.add("0");
	    		  else rowdingItem.add(dingItem.getPassedpeople().toString());
	    		  
	    		  if(dingItem.getFinishpeople() ==null)
	    			  rowdingItem.add("0");
	    		  else
	    			  rowdingItem.add(dingItem.getFinishpeople().toString());
	    		  if(dingItem.getWaitPeople() == null)
	    			  rowdingItem.add("0");
	    		  else
	    			  rowdingItem.add(dingItem.getWaitPeople().toString());
	    		  
	    		  if(dingItem.getFundSum() == null)
	    			  rowdingItem.add("0");
	    		  else
	    			  rowdingItem.add(dingItem.getFundSum().toString());
	    		  
	    		  if(dingItem.getLeftfundSum() == null)
	    			  rowdingItem.add("0");
	    		  else
	    			  rowdingItem.add(dingItem.getLeftfundSum().toString());
	    		  
	    		  if(dingItem.getMonthlyFundSum() == null)
	    			  rowdingItem.add("0");
	    		  else
	    			  rowdingItem.add(dingItem.getMonthlyFundSum().toString());
	    		  logger.info("rowkunItem:"+rowkunItem.size()+" dingshengItem:"+rowdingItem.size());
	    		  values.add(rowdingItem);
	    		  
		    	  
		    	  cols = Constants.getBussstatCol();
	    	  }
	      
	      
	      }
	      else
	      {
	    	  filename= "";
	    	  return null;
	      }
	      logger.info("start create excel file");
	      if(!filename.equals(""))
	    	  MSOfficeDocumentFactory.CreateExlFile(cols, values, filename );
	      else
	      {
	    	  logger.error("excel file name is empty");
	    	  return null;
	      }
	      if(emtpydata)
	      {
	    	  MSOfficeDocumentFactory.CreateExlFile( Constants.getFundEfficientCol(),  filename);
	      }
	      File report = new File(filename);
	      if(!report.exists())
	      {
	    	  logger.error("excel file not exist, name="+filename);
	    	  throw new  RuntimeException("Excel文件不存在");
	      }
	      
	      StreamingResponseBody streamBody =new StreamingResponseBody()
	      {
	    	  
	         @Override
	         public void writeTo(OutputStream os) throws IOException
	         {
	            InputStream is = new FileInputStream(filename);
	            byte[] buf = new byte[512 * 1024];
	            int count = 0;

	            try
	            {
	               while ((count = is.read(buf, 0, 512 * 1024)) > 0)
	               {
	                  os.write(buf, 0, count);
	                  os.flush();
	               }
	            }
	            catch(Exception e)
	            {
	            	e.printStackTrace();
	            }
	            finally
	            {
	               os.close();
	               is.close();
	               FileUtils.deletFile(filename);
	            }
	         }
	      };
			return streamBody;
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
			
		}
		return null;
	

	 }
	
	@PostMapping("/ImportExcelFileByName")
 public ResponseVO importExcelByName(@RequestHeader("content-length") long fileSize,
	                                  @RequestParam("name") String name,
	                                  @RequestParam("file") MultipartFile file,
	                                  HttpServletRequest req) throws IOException, NoSuchAlgorithmException
	   {
		
		ResponseVO responseVO = new  ResponseVO(true, SUCCESS.getCode(),SUCCESS.getMessage());
		String filename = name+"_"+UUID.randomUUID()+".xls";
		try {
		if(!Constants.isComponents(name))
		{
			logger.error("request name is not recognized by server:"+name);
			return responseVO;
		}
			
		if (file != null && !file.isEmpty())
	      {
	         FileUtils.uploadMultipartFile(file, filename, "./", "");
	         List<List<String>> values =MSOfficeDocumentFactory.getExcelDataByName(filename, Constants.getFundEfficientCol());
	         logger.info("import Excel data size:"+values.size());
	         if(name.equals("fundefficient"))
	         fundService.insertByList(values);
	         else if(name.equals("kunpeng"))
	        	 kunpengService.insertByList(values);
	         else if(name.equals("dingsheng"))
	        	 dingshengService.insertByList(values);
	        	 
	      }
		else
		{
			logger.error("file is empty for upload:"+name);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			FileUtils.deletFile(filename);
		}
		return responseVO;
	   }
}
