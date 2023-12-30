const endpointUrl = "http://localhost:8080/rating/data";

function sendDataToServer(title, timeSpent, hostname) {
  
    chrome.storage.local.get('mvirCode', function (result) {
        const now = new Date().getTime();
        const startCountingTime = now - (timeSpent * 1000);
        const startDate = new Date(startCountingTime);
        dateFromStartDate = startDate.getFullYear() + "-" + (startDate.getMonth() + 1).toString().padStart(2, '0') + "-" + startDate.getDate().toString().padStart(2, '0');
        timeFromStartDate = startDate.getHours().toString().padStart(2, '0') + ":" + startDate.getMinutes().toString().padStart(2, '0');
        var jsonObject = JSON.parse(result.mvirCode);
        const data = { id: -1, userId: jsonObject.value, startDate: dateFromStartDate, startTime: timeFromStartDate, timeSpend: timeSpent, title: title, hostname: hostname };
        if (timeSpent > 0 && title.length != 0) {
            fetch(endpointUrl, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .then(daata => console.log(data))
                .catch(error => console.error(error));
        }
    });
};
