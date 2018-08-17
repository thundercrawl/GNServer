package com.bigvision.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.mail.internet.MimeUtility;
public class WebUtil {

	public static String convertFilenameByAgent(String userAgent,String filename) throws UnsupportedEncodingException
	{
		String rtn ="";
		String new_filename = filename;
		if (userAgent != null)  
		{  
		     userAgent = userAgent.toLowerCase();  
		      // IE浏览器，只能采用URLEncoder编码  
		     if (userAgent.indexOf("msie") != -1||userAgent.indexOf("edge") != -1)  
		    {  
		    	 new_filename = URLEncoder.encode(filename, "UTF8"); 
		        rtn = "filename=\"" + new_filename + "\"";  
		    }  
		     // Opera浏览器只能采用filename*  
		     else if (userAgent.indexOf("opera") != -1)  
		     {  
		        rtn = "filename*=UTF-8''" + new_filename;  
		    }  
		    // Safari浏览器，只能采用ISO编码的中文输出  
		      else if (userAgent.indexOf("safari") != -1 )  
		      {  
		          rtn = "filename=\"" + new String(filename.getBytes("UTF-8"),"ISO8859-1") + "\"";  
		      }  
		      // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出  
		      else if (userAgent.indexOf("applewebkit") != -1 )  
		       {  
		    	  rtn = "filename*=UTF-8''" + new_filename;    
		       }  
		      // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出  
		       else if (userAgent.indexOf("mozilla") != -1)  
		       {  
		    	   new_filename = MimeUtility.encodeText(filename, "UTF8", "B");  
		           rtn = "filename=\"" + new_filename + "\"";  
		         
		      }  
		   } 
		else 
			throw new RuntimeException("Null User agent");
		
	
	return rtn;	
	}
}
