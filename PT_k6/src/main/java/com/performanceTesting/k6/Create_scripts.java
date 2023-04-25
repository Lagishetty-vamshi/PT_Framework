package com.performanceTesting.k6;

import java.io.IOException;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Create_scripts {

	static String filepath = Runner.filePath;

	static void create_API_JS_File() throws FilloException, IOException, InterruptedException {
		String query = "SELECT * FROM Sheet1";

		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(filepath);
		Recordset recordset = connection.executeQuery(query);
		while (recordset.next()) {
			String API_name = recordset.getField("API_Name");
			String API_Url = recordset.getField("API_Url");
			String Payload = recordset.getField("Payload");
			String headers = recordset.getField("headers");
			String API_Method = recordset.getField("API_Method");
			String Time_duration = recordset.getField("Time_duration");

//StringBuilder Edit_time_duration = new StringBuilder(Time_duration);

			Create_File.createFile(API_name);
			write_scipts.write(API_name, API_Method, API_Url, Payload, headers, Time_duration);

			Thread.sleep(10000);
		}
		recordset.close();
		connection.close();

		System.out.println("<----------  creation of script is complete ---------->");
	}
}
