package com.performanceTesting.k6;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Runner {

//	public static final String Iterations = null;

	static {
		System.out.println("Script  started");
	}

	static String osnname=System.getProperty("os.name");
	static Fillo fillo = new Fillo();
	static String filePath = "Performance_testing_sheet.xlsx";
	static Date date = new Date();
	static SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy");
	static String Date = DateFor.format(date);
	static SimpleDateFormat Time1 = new SimpleDateFormat("HH-MM-SS");
	static String Time = Time1.format(date);

	// static DateFor = new SimpleDateFormat("HH:mm");
//	static String  Time= DateFor.format(date);

	public static ArrayList<String> API_name = new ArrayList<String>();

	public static ArrayList<String> sleep = new ArrayList<String>();
	public static ArrayList<String> API_Url1 = new ArrayList<String>();
	public static ArrayList<String> Payload1 = new ArrayList<String>();
	public static ArrayList<String> headers1 = new ArrayList<String>();
	public static ArrayList<String> API_Method1 = new ArrayList<String>();
	
	public static ArrayList<String> Avg_time = new ArrayList<String>();
	public static ArrayList<String> Max_time = new ArrayList<String>();
	public static ArrayList<String> ninety_perecent_time = new ArrayList<String>();
	public static ArrayList<String> Iteration = new ArrayList<String>();
	

	static void read_API_name() throws FilloException {

		String query1 = "SELECT * FROM Sheet1";

		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(filePath);
		Recordset recordset = connection.executeQuery(query1);
		while (recordset.next()) {
			String API_name1 = recordset.getField("API_Name");
			String API_Url = recordset.getField("API_Url");
			String API_Method = recordset.getField("API_Method");
			String Sleep = recordset.getField("Sleep");
			String Test_data = recordset.getField("Payload");

			sleep.add(Sleep);
			API_Url1.add(API_Url);
			API_Method1.add(API_Method);
			API_name.add(API_name1);
			Payload1.add(Test_data);

//			int i = 0;
//		  Fillo fillo12= new Fillo();
//		 Connection connection1 = fillo12.getConnection("API_Test_Report.xlsx");
//String query2 = "INSERT INTO Report (API_Name,API_Method) VALUES("+"'"+API_name.get(i)+"'"+","+"'"+ API_Method1.get(i)+"'"+")";
// connection1.executeUpdate(query2);
//			i++;

		}
		recordset.close();
		connection.close();
		}

	public static void main(String[] args) throws IOException, InterruptedException, FilloException {

		Create_File.copy_reource_File();
		read_API_name();
		controller.control();
		

	}

}
