package com.performanceTesting.k6;

import java.io.FileWriter;
import java.io.IOException;

public class write_scipts {
	static void write(String API_name, String API_Method, String API_Url, String Payload, String headers,
			String Time_duration) {

		Object function_desired_API = null;
		try {

			switch (API_Method) {
			case "POST":
				Object Post_API = "export default function () {\r\n" + "  const url = '" + API_Url + "';\r\n"
						+ "  const payload = JSON.stringify({\r\n" + " " + Payload + " " + "});\r\n" + "\r\n"
						+ "  const params = {\r\n" + "    headers: {\r\n" + " " + headers + " " + " \r\n" + "    },\r\n"
						+ "  };\r\n" + "\r\n" + "  http.post(url, payload, params);\r\n" + "}\r\n" + "";
				function_desired_API = Post_API;
				break;
//-----------------------------Get-------------------------------------------------------------------------------------------------------------------------------------------------				 

			case "GET":
				Object GET_API = "export default function () {\r\n" + "  let response\r\n" + "\r\n"
						+ "  response = http.get('" + API_Url + "')\r\n" + "\r\n" + "  // Automatically added sleep\r\n"
						+ "  sleep(1)\r\n" + "}";
				function_desired_API = GET_API;
				break;
//-----------------------------Put-------------------------------------------------------------------------------------------------------------------------------------------------				 
			case "PUT":
				Object PUT_API = "export default function () {\r\n" + "const url ='" + API_Url + "';\r\n"
						+ "  const headers = {" + headers + "};\r\n" + "  const data = {" + Payload + "};\r\n" + "\r\n"
						+ "  const res = http.put(url, JSON.stringify(data), { headers: headers });\r\n" + "\r\n" + "}";

				function_desired_API = PUT_API;
//=============================================================================================================================
			default:
				System.out.println("Null API method");
			}

			Object Base_API_script = "import { sleep } from 'k6'\r\n" + "import http from 'k6/http'\r\n"
					+ "import { htmlReport } from \"https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js\";\r\n"
					+ "\r\n" + "export const options = {\r\n" + "  ext: {\r\n" + "    loadimpact: {\r\n"
					+ "      distribution: { 'amazon:us:ashburn': { loadZone: 'amazon:us:ashburn', percent: 100 } },\r\n"
					+ "      apm: [],\r\n" + "    },\r\n" + "  },\r\n" + "  thresholds: {},\r\n" + "  scenarios: {\r\n"
					+ "    Scenario_1: {\r\n" + "      executor: 'ramping-vus',\r\n" + "      gracefulStop: '30s',\r\n"
					+ "      stages: [\r\n" + " " + Time_duration + " " + "],\r\n"
					+ "      gracefulRampDown: '30s',\r\n" + "      //exec: 'scenario_1',\r\n" + "    },\r\n"
					+ "  },\r\n" + "}\n" + " " + function_desired_API + "" + "export function handleSummary(data) {\r\n"
					+ "  return {\r\n" + "\n" + "'" + API_name + ".html': htmlReport(data)," + "  };\r\n" + "}";

			FileWriter myWriter = new FileWriter("Test_scripts/" + API_name + ".JS");
			myWriter.write(Base_API_script.toString());
			myWriter.close();
			System.out.println("Successfully wrote "+ API_name + "file.");
		} 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		System.out.println("<----------  creation of script is complete ---------->");
	}
}
