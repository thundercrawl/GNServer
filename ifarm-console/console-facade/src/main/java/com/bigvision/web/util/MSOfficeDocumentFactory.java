package com.bigvision.web.util;
import  java.io.*;
import java.util.ArrayList;
import java.util.List;

import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifarm.console.facade.controller.CommonController;

import  org.apache.poi.hssf.usermodel.HSSFRow;
public class MSOfficeDocumentFactory {

		
	private static Logger logger = LoggerFactory.getLogger(MSOfficeDocumentFactory.class);
	private static final String EXCEL_XLS = "xls";  
    private static final String EXCEL_XLSX = "xlsx";  
    
    
    public static Workbook getWorkbok(InputStream in,File file) throws IOException{  
        Workbook wb = null;  
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003  
            wb = new HSSFWorkbook(in);  
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010  
            wb = new XSSFWorkbook(in);  
        }  
        return wb;  
    }  
    
    public static List<List<String>> getExcelDataByName(String path,List<String> columns) {  
       
    	List<List<String>> values = new ArrayList<List<String>>();
        try {  
        
            File excelFile = new File(path); // 创建文件对象  
            FileInputStream in = new FileInputStream(excelFile); // 文件流  
            
            Workbook workbook = getWorkbok(in,excelFile);  
          
            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量  
            logger.info("excel have "+Integer.toString(sheetCount)+" sheet");
            Sheet sheet = workbook.getSheetAt(0);  

            // 为跳过第一行目录设置count  
            int count = 0;
            for (Row row : sheet) {
            	try {
            		//skip first columns
                    if(count < 1) {
                        count++;  
                        continue;  
                    }
                    
                    //如果当前行没有数据，跳出循环  
                    if(row.getCell(0).toString().equals("")){  
                    	logger.error("reach emtpy row, return error back from scan sheet");
                    	return values;
                    }
                    List<String> rowItems = new ArrayList<String>();
                    //获取总列数(空格的不计算)
                    int columnTotalNum = row.getPhysicalNumberOfCells();
                    logger.info("total columns：" + columnTotalNum);
                    
                    logger.info("max columns：" + row.getLastCellNum());
                    
                    //for循环的，不扫描空格的列
//                    for (Cell cell : row) { 
//                    	System.out.println(cell);
//                    }
                    int end = row.getLastCellNum();
                      
                    for (int i = 0; i < end; i++) {
                    	Cell cell = row.getCell(i);
                    	if(cell == null) {
                    		logger.error("null" + "\t");
                    		continue;
                    	}
                    	
                    	Object obj = getValue(cell);
                    	rowItems.add(obj.toString());
					}
                    values.add(rowItems);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return values;
    }
    
    private static Object getValue(Cell cell) {
    	Object obj = null;
    	switch (cell.getCellTypeEnum()) {
	        case BOOLEAN:
	            obj = cell.getBooleanCellValue(); 
	            break;
	        case ERROR:
	            obj = cell.getErrorCellValue(); 
	            break;
	        case NUMERIC:
	            obj = cell.getNumericCellValue(); 
	            break;
	        case STRING:
	            obj = cell.getStringCellValue(); 
	            break;
	        default:
	            break;
    	}
    	return obj;
    }
		public static void CreateExlFile(List<String> column, List<List<String>> values,String filename)
		{
		        try {
		        	
		        	if(values==null||filename==null||column==null) 
		        	{
		        		logger.error("empty data,  create  empty excel for it");
		        		
		        	}
		        	else
		        		logger.info("create excel file, col:"+column.size()+" values:"+values.size()+" filename:"+filename);
		            HSSFWorkbook workbook = new HSSFWorkbook();
		            HSSFSheet sheet = workbook.createSheet("FirstSheet");  

		            HSSFRow rowhead = sheet.createRow((short)0);
		            for(int cl=0;cl<column.size();cl++)
		            {
		            	   rowhead.createCell(cl).setCellValue(column.get(cl));
		            }
		         

		            for(int rowindex=1;rowindex<=values.size();rowindex++)
		            {
		            HSSFRow row = sheet.createRow((short)rowindex);
		            for(int cl=0;cl<column.size();cl++)
		            {
		            	row.createCell(cl).setCellValue(values.get(rowindex-1).get(cl));
		            }
		            
		            }
		            FileOutputStream fileOut = new FileOutputStream(filename);
		            workbook.write(fileOut);
		            fileOut.close();
		            workbook.close();
		            logger.info("Your excel file has been generated!");
		        } catch ( Exception ex ) {
		        	ex.printStackTrace();
		        	logger.error(ex.getMessage());
		        }
		        
		    }
		
		public static void CreateExlFile(List<String> column, String filename)
		{
		        try {
		        	logger.info("create excel file, col:"+column.size()+" filename:"+filename +"this is empty file");
		            HSSFWorkbook workbook = new HSSFWorkbook();
		            HSSFSheet sheet = workbook.createSheet("FirstSheet");  

		            HSSFRow rowhead = sheet.createRow((short)0);
		            for(int cl=0;cl<column.size();cl++)
		            {
		            	   rowhead.createCell(cl).setCellValue(column.get(cl));
		            }
		           
		            FileOutputStream fileOut = new FileOutputStream(filename);
		            workbook.write(fileOut);
		            fileOut.close();
		            workbook.close();
		            System.out.println("Your excel file has been generated!");
		        } catch ( Exception ex ) {
		            System.out.println(ex);
		        }
		        
		    }
		
	
}
