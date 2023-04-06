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
  const url = 'https://lifeai-core-uat.api-hdfclife.com/voicebot/get_session/';
  const payload = JSON.stringify({
      Department: "CCC",
      User: "8074252429",
      EmpID: "8074252429",
      source: "alexa",
      user_input: "hi",
      language: "hindi",
      contextMeta: "{}"
 });

  const params = {
    headers: {
    'Content-type': 'application/json'
              
    },
  };

  http.post(url, payload, params);
}
export function handleSummary(data) {
  return {

'GET_SESSION_API.html': htmlReport(data),  };
}