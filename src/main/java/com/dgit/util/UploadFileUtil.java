package com.dgit.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtil {
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception{
	      File dirPath = new File(uploadPath);
	      if (!dirPath.exists()) {
	         dirPath.mkdirs();
	      }               
                                 
	      UUID uid = UUID.randomUUID();
	      String saveName = uid.toString() + "_" + originalName;
	  	//년월이 폴더 만들기
			//한폴더에 저장할 수있는 용량이 제한되어있다.
	      String savePath = calPath(uploadPath);
	      
	      
	      File target = new File(uploadPath+savePath, saveName);

	      try {
	         FileCopyUtils.copy(fileData, target);
	      } catch (IOException e) {
	         e.printStackTrace();
	      }

	      //Thumbnail 처리
	      String thumbName = makeThumbnail(uploadPath, savePath, saveName);
	      
	     /* return savePath+"/"+saveName;*/
	      return thumbName;
	   }
	                               
	
	public static void makeDir(String uploadPath,String ...paths){
		for(String path:paths){
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()){
				dirPath.mkdir();
			}
		}
	}
	
	private static String calPath(String uploadPath){
		Calendar cal = Calendar.getInstance();
		String yearPath = "/"+cal.get(Calendar.YEAR);
	/*	String monthPath = yearPath +"/"+cal.get(Calendar.MONDAY)+1;*/
		String monthPath = String.format("%s/%02d",yearPath, cal.get(Calendar.MONDAY)+1);
		String datePath = String.format("%s/%02d",monthPath, cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath,monthPath,datePath);
		return datePath;
	}
	   
	
	private static String makeThumbnail(String uploadPath,String path,String filename) throws Exception{
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath+path,filename));
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC,Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumbnailName = uploadPath+path+"/s_"+filename;
		
		File newFile = new File(thumbnailName);
		String formatName = filename.substring(filename.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length());
	}
	
}
