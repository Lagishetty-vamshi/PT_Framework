
package com.performanceTesting.k6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.codoid.products.exception.FilloException;

public class Create_File {
	
	public static String filenaem="API_Test_Report_"+Runner.Date+"_"+Runner.Time+".xlsx";
	
//	static Paths=""+controller.path+"\"+Date+\"\\\\\"+Time+\"\\\\\"+API_Name+\"\"+Date+"";
 
	/*
	 * Create folders TEst script and test report
	 */
	public static void createFolder()
	{
		 File f1 = new File("./Test_report");
		 File f2 = new File("./Test_scripts");
		try {
			if(!f1.exists() || !f2 .exists())
			{  
		 f1.mkdir();
		 f2.mkdir();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		
	}
	
	static void createFile(String API_name) throws IOException, FilloException, InterruptedException {
		/* Create scripts files with API Names */
		@SuppressWarnings("unused")

		File Test_scripts_files = new File("Test_scripts/" + API_name + ".JS");

//		 File Test_report_file =     new File("Test_Report/API_Test_Report.xlsx");
//		 myObj = new File("API_Test_Report.xlsx");

	}
	
 /*
  * screen shoot method
  */
	static void screenshot(WebDriver driver,String API_Name)
	{	
		

		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		File image = new File(controller.path+"\\Test_Report\\Images\\"+Runner.Date+"\\"+ Runner.Time+"\\"+API_Name+""+Runner.Date+".png");
		try 
		{
			FileUtils.copyFile(src, image);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}
	/*
	 * Copy Excel file to resource to test sheet
	 */
	
	static void copy_reource_File() {
	    
//	File File_create = new File(controller.path+"//Test_scripts//"+Runner.Date+"//"+ Runner.Time+"");
//	File_create.mkdirs();
//	System.out.println(image.exists());
	
		Path sourcePath = Paths.get("./Resources", "API_Test_Report.xlsx");
        Path destPath = Paths.get("./Test_report", filenaem);
    
    try {
		Files.copy(sourcePath, destPath);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("<-------File already exits please check------->");
	}
	}
	
}
