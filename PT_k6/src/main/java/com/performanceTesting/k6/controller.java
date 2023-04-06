package com.performanceTesting.k6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.codoid.products.exception.FilloException;

public class controller {

	static String create_scripts;
	static String Execute_scripts;
	static String Execute_Web_scripts;
	static String path;
	static String Parallel_script_execution;
	static String to_mail;
	static String from_mail;
	static String Host;
	static String port;
	static String username;
	static String password;

	static String condtion = "y";
	static String condtion2 = "n";

	static void control() throws FilloException, IOException, InterruptedException {
		BufferedReader reader = new BufferedReader(new FileReader("Controll.properties"));
		Properties properties = new Properties();
		properties.load(reader);
		reader.close();

		create_scripts = properties.getProperty("Create_scripts");
		Execute_scripts = properties.getProperty("Execute_scripts");
		Execute_Web_scripts = properties.getProperty("Execute_Web_scripts");
		Parallel_script_execution = properties.getProperty("Parallel_script_execution");
		to_mail=properties.getProperty("to");
		from_mail=properties.getProperty("From");
		Host=properties.getProperty("host");
		port=properties.getProperty("port");
		username=properties.getProperty("username");
		

		path = properties.getProperty("path");

		String condtion = "y";
		String condtion2 = "n";

		if (create_scripts.contains(condtion)) {
			Create_scripts.create_API_JS_File();
		} else if (create_scripts.contains(condtion2)) {
			System.out.println("<---- Unable to write script coz control is n ---->");
		}

		if (Execute_scripts.contains(condtion)) {
			System.out.println("-----------");
			run_scipts.run_k6_script();
		} else if (Execute_scripts.contains(condtion2)) {
			System.out.println("<---- Unable to run script on command line coz control is n ---->");
		}
		if (Execute_Web_scripts.contains(condtion)) {

			Report.Read_result_from_web();
		} else if (Execute_Web_scripts.contains(condtion2)) {
			System.out.println("<---- Unable to read html report coz control is n ---->");
		}

	}
	
//	
//	String demo="  {   Department: \"CCC\",\r\n"
//			+ "      User: \"8074252429\",\r\n"
//			+ "      EmpID: \"8074252429\",\r\n"
//			+ "      source: \"alexa\",\r\n"
//			+ "      user_input: \"hi\",\r\n"
//			+ "      language: \"hindi\",\r\n"
//			+ "      contextMeta: '{}'\r\n"
////			+ "}";
}
