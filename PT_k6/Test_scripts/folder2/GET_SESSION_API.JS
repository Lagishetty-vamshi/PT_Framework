import { sleep } from 'k6'
import http from 'k6/http'
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

export const options = {
  ext: {
    loadimpact: {
      distribution: { 'amazon:us:ashburn': { loadZone: 'amazon:us:ashburn', percent: 100 } },
      apm: [],
    },
  },
  thresholds: {},
  scenarios: {
    Scenario_1: {
      executor: 'ramping-vus',
      gracefulStop: '30s',
      stages: [
 { target: 20, duration: '30s' } ],
      gracefulRampDown: '30s',
      //exec: 'scenario_1',
    },
  },
}
 export default function () {
const url ='https://dummy.restapiexample.com/api/v1/update/21';
  const headers = {    'content-type': 'application/json'
            };
  const data = {name: "test", 
salary: "123", 
age: "23"};

  const res = http.put(url, JSON.stringify(data), { headers: headers });

}export function handleSummary(data) {
  return {

'GET_SESSION_API.html': htmlReport(data),  };
}