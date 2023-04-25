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

	public static String osnname = System.getProperty("os.name");
	public static Fillo fillo = new Fillo();
	public static String filePath = "Performance_testing_sheet.xlsx";
	public static SimpleDateFormat Time1 = new SimpleDateFormat("HH-MM-SS");
	public static String Date;
	public static String Time;

	static {
		System.out.println("<------------ K6_Load_Testing_Tool ------------>");
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy");
		Date = DateFor.format(date);
		Time = Time1.format(date);

		Create_File.createFolder();
	}

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
