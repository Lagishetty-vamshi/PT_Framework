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
                { target: 20, duration: '30s' }
            ],
            gracefulRampDown: '30s',
            exec: 'scenario_1',
        },
    },
}

export function scenario_1() {
    let response

    response = http.del('\thttps://dummy.restapiexample.com/api/v1/delete/2')

    // Automatically added sleep
    sleep(1)
}
export function handleSummary(data) {
    return {
        "Delete_data_delete.html": htmlReport(data),
    };
}