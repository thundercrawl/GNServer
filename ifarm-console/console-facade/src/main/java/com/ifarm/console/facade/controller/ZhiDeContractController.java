package com.ifarm.console.facade.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bigvision.web.util.Constants;
import com.bigvision.web.util.WebUtil;
import com.ifarm.console.facade.service.IFundEfficientService;
import com.ifarm.console.facade.service.IZhiDeContractService;
import com.ifarm.console.shared.domain.po.ZhiDeContractPO;
import com.ifarm.console.shared.domain.vo.ResponseVO;
import com.ifarm.console.shared.domain.vo.UserInfoVO;
@RequestMapping("/zhideContract")
@RestController
public class ZhiDeContractController extends AbstractController {

	
	private static Logger logger = LoggerFactory.getLogger(ZhiDeContractController.class);
	@Autowired
	IZhiDeContractService contractService;
	
    @RequestMapping("/insert")
    public ResponseVO insert(@RequestBody ZhiDeContractPO po) {
        ResponseVO responseVO = returnSuccess();
        byte[] empty =new String().getBytes();
        try {
        	po.setFiledata(empty);
        	po.setModifyTime(new Date());
        	po.setCreateTime(new Date());
        	contractService.insertContractwithEmptyData(po);
        } catch (Exception e) {
            logger.error("", e);
            return returnError(e.getMessage());
        }
        return responseVO;
    }
    
    @RequestMapping("/list")
    public ResponseVO list() {
        ResponseVO responseVO = returnSuccess();
        
        try {
        	responseVO.setResult(contractService.getAll());
        } catch (Exception e) {
            logger.error("", e);
            return returnError(e.getMessage());
        }
        return responseVO;
    }	
    
    @RequestMapping("/delete")
    public ResponseVO deleteContractByID(@RequestBody ZhiDeContractPO po) {
        ResponseVO responseVO = returnSuccess();
        
        try {
        	contractService.deleteContractByID(po.getTid());
        } catch (Exception e) {
            logger.error("", e);
            return returnError(e.getMessage());
        }
        return responseVO;
    }	
    @RequestMapping("/update")
    public ResponseVO update(@RequestBody ZhiDeContractPO po) {
        ResponseVO responseVO = returnSuccess();
        byte[] empty =new String().getBytes();
        try {
        	po.setModifyTime(new Date());
        	
        	contractService.update(po);
        } catch (Exception e) {
            logger.error("", e);
            return returnError(e.getMessage());
        }
        return responseVO;
    }
    @RequestMapping("/updateData")
    public ResponseVO updateData( @RequestParam("tid") String id,@RequestParam("file") MultipartFile file,HttpServletRequest req) {
        ResponseVO responseVO = returnSuccess();
       
        try {
        	logger.info("upload data for id:"+id+" filename:"+file.getOriginalFilename()+" size:"+file.getSize());
        	if((file.getContentType().indexOf("pdf") == -1 ) && ( file.getContentType().indexOf("doc") ==-1)&& (file.getContentType().indexOf("docx") == -1) &&(file.getContentType().indexOf("msword") == -1))
        		throw new  RuntimeException("Unsupport file type:"+file.getContentType());
        	ZhiDeContractPO po = new ZhiDeContractPO();
        	po.setModifyTime(new Date());
        	po.setFiledata(file.getBytes());
        	po.setFilename(file.getOriginalFilename());
        	po.setTid(new Integer(id));
        	contractService.updateData(po);
        } catch (Exception e) {
            logger.error("", e);
            return returnError(e.getMessage());
        }
        return responseVO;
    }
    
    @RequestMapping("/exportData")
    public void exportData(String id,HttpServletResponse response,HttpServletRequest req)
    {
    	
    	ZhiDeContractPO po = contractService.getFileDataByID(new Integer(id));
    	String usragent = req.getHeader("User-Agent");
    	logger.info("export data id:"+id+ "userAgent:"+usragent);
    	try {
    		String downloadFIlename = WebUtil.convertFilenameByAgent(usragent,po.getFilename());
    		logger.info("convert downlod file name to:"+downloadFIlename);
    		response.setContentType("application/octet-stream");
    		response.setContentLength(po.getFiledata().length);
    	
    		 response.setHeader("Content-Disposition", "attachment;"+downloadFIlename);
    		
			response.getOutputStream().write(po.getFiledata());
			response.getOutputStream().flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
    	
    }
}
