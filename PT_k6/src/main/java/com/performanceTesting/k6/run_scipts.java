package com.performanceTesting.k6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.codoid.products.exception.FilloException;

public class run_scipts {

	static void run_k6_script() throws IOException, InterruptedException, FilloException {

		if (controller.Execute_scripts.equalsIgnoreCase("y")) {

			for (int i = 0; i < Runner.API_name.size(); i++) {

				String sleep = Runner.sleep.get(i);
				int sl = Integer.parseInt(sleep);

				if (Runner.osnname.contains("Windows")) {

					System.out.println("start");

					Runtime.getRuntime().exec(
							"cmd /c start cmd.exe /K \"cd Test_scripts && k6 run " + Runner.API_name.get(i) + ".js");
					Thread.sleep(sl);

					Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
					Thread.sleep(10000);

					System.out.println("<------scipt execution complete------>");

				}
//----------------------------------------------------------------------------
				/*
				 * mac os
				 */
				if (Runner.osnname.contains("Mac")) {
					try {
						String[] command = {"cd  " +controller.path + "/Test_scripts",
								"k6 run " + Runner.API_name.get(i) + ".js" };
						Mac_run(command, sl);
					} catch (Exception e) {
						System.out.println("Error :" + e.getMessage());

					}
				}

			}
		}
	}

//	------------------------------------------------------------------------------------
	public static void Mac_run(String[] command, int sl) {

		List<String> cms = new ArrayList<>();
		cms.add("/bin/sh");
		cms.add("-c");
		cms.add("osascript -e 'tell application \"Terminal\" to do script \"" + String.join("; ", command) + "\"'");

		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command(cms);
		try {

			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

//			boolean success = process.waitFor(1, TimeUnit.MINUTES);

			Thread.sleep(sl);

			Runtime.getRuntime()
					.exec(new String[] { "osascript", "-e", "tell application \"Terminal\" to close window 1" });
		} catch (IOException | InterruptedException e) {
			System.out.println("Error" + e.getMessage());
		}
	}

}
