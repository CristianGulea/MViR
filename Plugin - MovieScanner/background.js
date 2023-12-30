chrome.storage.local.get('mvirCode', function (result) {
    if (result.mvirCode == undefined) {
        const xhr = new XMLHttpRequest();
        const url = "http://localhost:8080/login/code";
        xhr.open("GET", url);
        xhr.onload = () => {
            chrome.storage.local.set({ 'mvirCode': xhr.responseText }, function () {
                console.log('Value stored to ' + xhr.responseText);
            });
        };
        xhr.send();
    }
});
