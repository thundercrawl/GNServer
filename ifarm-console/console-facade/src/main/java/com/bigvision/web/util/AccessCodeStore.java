package com.bigvision.web.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class  AccessCodeStore {

	private static AccessCodeStore self;
	private  HashMap<String,String> accesscode2Token = new HashMap<String,String>();
	private static ReentrantLock lock = new ReentrantLock();
	private HashMap<String,Long> accesscode2Time = new HashMap<String,Long>(); 
	
	private Long timeout = new Long(1000*30);
	private Long timeline = (long) 0;
	public static AccessCodeStore getInstance()
	{
		if(self==null)
		{
			self= new AccessCodeStore();
		}
		return self;
	}
	
	public  void setToken(String id, String value)
	{
		try
		{
		lock.lock();
		clearTimeout();
		this.accesscode2Token.put(id, value);
		accesscode2Time.put(id, System.currentTimeMillis());
		}
		finally
		{
		lock.unlock();
		}
	}
	public  String getToken(String id)
	{String token =null;
		try
		{
		lock.lock();
		token= (String)accesscode2Token.get(id);
		}
		finally
		{
		lock.unlock();
		}
		return token;
	}
	private void clearTimeout()
	{
		if((System.currentTimeMillis() - this.timeline)<this.timeout)
		{
			return;
		}
		Logger.getLogger("clear access token store");
		
		Iterator<Map.Entry<String,String>> iterToken = accesscode2Token.entrySet().iterator();
		//Iterator<Map.Entry<String,Long>> iterTime = accesscode2Time.entrySet().iterator();
		while (iterToken.hasNext()) {
		    Map.Entry<String,String> entryToken = iterToken.next();
		   
		    Iterator<Map.Entry<String,Long>> iterTime = accesscode2Time.entrySet().iterator();
		    while(iterTime.hasNext())
		    {
		    	Map.Entry<String,Long> entryTime = iterTime.next();
		    	if(entryTime.getKey().equals(entryToken.getKey()))
		    	{
		    		Long past = entryTime.getValue();
					if((System.currentTimeMillis()-past)>this.timeout)
					{
						iterToken.remove();
						iterTime.remove();
					}
					}
		    	}
		    }
		    
		    
	/*	
		for(String id:accesscode2Token.keySet())
		{
			if(accesscode2Time.get(id)!=null)
			{
				Long past = accesscode2Time.get(id);
				if((System.currentTimeMillis()-past)>this.timeout)
				{
					accesscode2Token.remove(id);
					accesscode2Time.remove(id);
					continue;
				}
			}
			else
			{
				accesscode2Token.remove(id);
			}
		}*/
		
	}
	private AccessCodeStore()
	{
		timeline = System.currentTimeMillis();
	}
}
