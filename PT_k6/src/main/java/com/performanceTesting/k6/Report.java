package com.performanceTesting.k6;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
//import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Report {

//	static Date date = new Date();
//	static SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy");
//	static String  Date= DateFor.format(date);
//	DateFor = new SimpleDateFormat("HH:mm");
//	String  Time= DateFor.format(date);

	static void Read_result_from_web() throws InterruptedException, FilloException {

//		
//		File Test_report = new File("API_Test_Report.xlsx");

//		System.out.println("<----- Report file created : " + Test_report.getName());
//		Create_File.copy_reource_File();
		System.out.println(Runner.API_name);
		System.out.println(Runner.API_Url1);
		System.out.println(Runner.API_Method1);

		ChromeOptions options = new ChromeOptions();
		WebDriver driver = null;

		options.addArguments("--headless");
//		try {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Fillo fillo2 = new Fillo();
		Connection connection1 = fillo2.getConnection(controller.path + "/Test_report/" + Create_File.filenaem);

		String iteration = "NA";
		String Average_time_duration = "NA";
		String Max_Throughput = "NA";
		String nine = "NA";

		System.out.println(controller.path);
		for (int i = 0; i < Runner.API_name.size(); i++) {
			String url = "" + controller.path + "/Test_scripts/" + Runner.API_name.get(i) + ".html";

			driver.get(url);

			iteration = driver.findElement(By.xpath("//h4[text()='Total Requests']/following-sibling::div[1]"))
					.getText();

			Average_time_duration = driver.findElement(By.xpath("//*[@class='tab']//table/tbody[2]/tr[1]/td[4]"))
					.getText();

			nine = driver.findElement(By.xpath("(//tbody/tr/td[8])[1]")).getText();

			// <---------------screen shot------------->
			Create_File.screenshot(driver, Runner.API_name.get(i));

			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[contains(text(),' Other Stats')]")).click();

			Max_Throughput = driver.findElement(By.xpath("(//*[text()='Rate'])[2]//following-sibling::div")).getText();

			// <---------------screen shot------------->
			Create_File.screenshot(driver, Runner.API_name.get(i));

			String report_url = Runner.API_Url1.get(i);
			String report_API_Name = Runner.API_name.get(i);
			String report_API_method = Runner.API_Method1.get(i);
			String report_testdata = Runner.Payload1.get(i);

			Runner.Avg_time.add(Average_time_duration);
			Runner.ninety_perecent_time.add(nine);
			Runner.Max_time.add(Max_Throughput);
			Runner.Iteration.add(iteration);

			System.out.println(Runner.API_name.get(i));
			Thread.sleep(4000);

//		String query1 = "INSERT INTO Report"
//				+ "(API_Name,API_Method,Test_data,Iterations,Avg_Response_Time,Max_Throughput) VALUES("+report_API_Name+","+ report_API_method+","+report_url+","+iteration+ ","+Average_time_duration+","+Max_Throughput+")";

			// <----------Copy report file with resource folder------------->

			Thread.sleep(4000);
			String query1 = "INSERT INTO Report(APIName,API_Method,APIUrl,Iterations,Avg_Response_Time,Max_Throughput,Test_data) VALUES("
					+ "'" + report_API_Name + "'" + "," + "'" + report_API_method + "'" + "," + "'" + report_url + "'"
					+ "," + iteration + "," + Average_time_duration + "," + Max_Throughput + "," + "'" + report_testdata
					+ "'" + ")";
			connection1.executeUpdate(query1);
		}
		connection1.close();
		Thread.sleep(5000);
//	}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//			System.out.println(e);

		driver.quit();
//		}
		System.out.println("<---------- Html report read is complete ---------->");
		
		Thread.sleep(3000);
		Mail_send.mail();

	}
}
