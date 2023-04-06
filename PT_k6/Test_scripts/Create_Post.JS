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
  let response

  response = http.get('https://lifeai-core-uat.api-hdfclife.com/voicebot/health-check')

  // Automatically added sleep
  sleep(1)
}export function handleSummary(data) {
  return {

'Create_Post.html': htmlReport(data),  };
}