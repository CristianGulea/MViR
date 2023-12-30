startTime = -1;
title = "";

function parserGoogleDescription() {
    var element = document.querySelector(".mR2gOd[jsname='haAclf']");
    if (element != null) {

        window.onclick = function () {

            setTimeout(() => {
                if (startTime != -1) {
                    endTime = new Date();
                    let timeSpent = Math.round((endTime - startTime) / 1000);
                    sendDataToServer(title, timeSpent, "www.google.com");
                    startTime = new Date();
                }
                startTime = new Date();
                title = document.querySelector('div span.yKMVIe').textContent
            }, 3000);

        }

        window.onbeforeunload = function () {
            if (startTime != -1) {
                endTime = new Date();
                let timeSpent = Math.round((endTime - startTime) / 1000);
                sendDataToServer(title, timeSpent, "www.google.com");
            }
        }
    }
}

function getFilmTitleFromGoogleDescription() {
    title = parserGoogleDescription();
    return title;
}