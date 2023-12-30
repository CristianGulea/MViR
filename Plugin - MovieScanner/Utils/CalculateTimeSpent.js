function calculateTimeSpent(func, hostname) {
    window.onload = function () {
        startTime = new Date();
        title = func();
    };

    window.onbeforeunload = function () {
        endTime = new Date();
        let timeSpent = Math.round((endTime - startTime) / 1000);
        sendDataToServer(title, timeSpent, hostname);
    }
}