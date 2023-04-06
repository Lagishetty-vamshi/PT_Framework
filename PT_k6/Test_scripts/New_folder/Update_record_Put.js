import { sleep } from 'k6'
import http from 'k6/http'
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js'

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
                { target: 20, duration: '30s' },
            ],
            gracefulRampDown: '30s',
            exec: 'scenario_1',
        },
    },
}

export function scenario_1() {
    let response

    const url = 'https://dummy.restapiexample.com/api/v1/update/21';

    const payload = JSON.stringify({
        { "name": "test", "salary": "123", "age": "23" })
    };

    const params = {
        headers: {
            'Content-Type': 'application/json',

        },
    };



    http.put(

        url, payload, params

    );

    // Automatically added sleep
    sleep(1)
}
export function handleSummary(data) {
    return {
        "Update_record_Put.html": htmlReport(data),
    };
}