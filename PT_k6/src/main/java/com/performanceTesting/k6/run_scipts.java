package com.performanceTesting.k6;

import java.io.IOException;
//import java.util.ArrayList;

import com.codoid.products.exception.FilloException;
//import com.codoid.products.fillo.Connection;
//import com.codoid.products.fillo.Fillo;
//import com.codoid.products.fillo.Recordset;

public class run_scipts {

	static void run_k6_script() throws IOException, InterruptedException, FilloException {

		if (controller.Execute_scripts.equalsIgnoreCase("y")) {
			if(Runner.osnname.contains("Windows")) {
				
			for (int i = 0; i < Runner.API_name.size(); i++) {
				System.out.println("start");

				Runtime.getRuntime()
						.exec("cmd /c start cmd.exe /K \"cd Test_scripts && k6 run " + Runner.API_name.get(i) + ".js");
//				
				String sleep=Runner.sleep.get(i);
				int sl=Integer.parseInt(sleep);
				Thread.sleep(sl);

				Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
				Thread.sleep(10000);

				System.out.println("<------scipt execution complete------>");

				System.out.println("end");
			}

		}
		}

		/*
		 * Parallel script execution
		 */
		if (controller.Parallel_script_execution.equalsIgnoreCase("y")) {
			if(Runner.osnname.contains("Windows")) {
//			int Size = 10;
			for (int i = 0; i < Runner.API_name.size(); i++) {
				System.out.println("start");
				int p = 6;
//			        	Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd Test_scripts && k6 run address "+'\u0022'+"localhost:656"+p+'\u0022'+" "+Runner.API_name.get(i)+".js");

				String command = "--address \"localhost:656" + p + "\" " + Runner.API_name.get(i) + ".js";
				@SuppressWarnings("unused")
				Process process = Runtime.getRuntime()
						.exec("cmd /c start cmd.exe /k \"cd Test_scripts && k6 run " + command);
				Thread.sleep(5000);
				p++;
			}

			Thread.sleep(60000);

			Thread.sleep(10000);

			System.out.println("<------scipt execution complete------>");
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");

			System.out.println("end");
			}

		}
	
		if(Runner.osnname.contains("Mac os"))
		{
			String[] args = new String[] {"/bin/bash", "-c", "cd /path/to/directory && your_command with args"};
			Process proc = new ProcessBuilder(args).start();
		}
	}
	
}