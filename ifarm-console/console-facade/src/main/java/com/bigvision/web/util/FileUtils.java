package com.bigvision.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils
{
   private static Log log = LogFactory.getLog(FileUtils.class);

   private static void prepareFolder(String path)
   {
      File folder = new File(path);

      if (!folder.exists())
         folder.mkdir();
      else if (!folder.isDirectory())
      {
         folder.delete();
         folder.mkdir();
      }
   }

   public static void deletFile(String pathtofile)
   {
	   File f = new File(pathtofile);

	      if (f.exists())
	    	  f.delete();
   }
   public static void uploadMultipartFile(MultipartFile file, String fileName, String folder, String md5)
         throws IOException, NoSuchAlgorithmException
   {
      log.info("upload multipart file " + fileName + " to folder " + folder);
      FileUtils.prepareFolder(folder);
      File uploadFile = new File(folder + fileName);
     uploadFile.createNewFile();
      InputStream is = file.getInputStream();
   
      FileOutputStream fos = new FileOutputStream(uploadFile);
    //  FileOutputStream md5fos = new FileOutputStream(new File(folder + fileName + ".MD5"));
      byte buffer[] = new byte[1024];

      int i = -1;
      MessageDigest digest = MessageDigest.getInstance("MD5");

      try
      {
         while ((i = is.read(buffer, 0, 1024)) != -1)
         {
            if (i == 1024)
            {
               fos.write(buffer);
               log.info("write 1024 bytes to file:"+fileName);
             //  digest.update(buffer, 0, 1024);
            }
            else
            {
               byte[] bt = Arrays.copyOf(buffer, i);
               fos.write(bt);
            //   digest.update(bt, 0, i);
            }
         }
/*
         byte[] md5bytes = new BigInteger(1, digest.digest()).toString(16).getBytes();

         md5 = md5.trim();
         if (md5 != null && !md5.isEmpty() && !md5.getBytes().equals(md5bytes)) throw new RuntimeException(
               "Fail to validate file content with md5");

         md5fos.write(md5bytes);*/
      }
      catch (Exception e)
      {
    	  e.printStackTrace();
         if (fos != null) fos.close();
         uploadFile.delete();
      }
      finally
      {
         if (is != null) is.close();
         if (fos != null) fos.close();
      //   if (md5fos != null) md5fos.close();
      }
   }
}
