package com.performanceTesting.k6;

import java.io.IOException;

public class Parallel_exceution {

	/*
	 * Parallel script execution
	 */
	static void parallel_execution() throws IOException, InterruptedException {
		if (controller.Parallel_script_execution.equalsIgnoreCase("y")) {
			if (Runner.osnname.contains("Windows")) {
//		int Size = 10;
				for (int i = 0; i < Runner.API_name.size(); i++) {
					System.out.println("start");
					int p = 6;
//		        	Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd Test_scripts && k6 run address "+'\u0022'+"localhost:656"+p+'\u0022'+" "+Runner.API_name.get(i)+".js");

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

//			if (Runner.osnname.contains("Mac os")) {
//				String[] args = new String[] { "/bin/bash", "-c",
//						"cd /path/to/directory && k6 run " + Runner.API_name.get(i) + ".js" };
//				Process proc = new ProcessBuilder(args).start();
//			}/
//		}
		}

	}
}
