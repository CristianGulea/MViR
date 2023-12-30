function parserYoutube() {
    const ytFormattedString = document.querySelector('h1.style-scope.ytd-watch-metadata yt-formatted-string');
    const channelName = document.querySelector('yt-formatted-string#text a.yt-simple-endpoint.style-scope.yt-formatted-string');
    let channel;
    let title;
    if (ytFormattedString != null && channelName != null) {
        title = ytFormattedString.textContent;
        channel = channelName.textContent;
        if (title.length != 0) {
            switch (channel.trim()) {
                case "Netflix": return { title: NetflixYouTubeTitleMask(title).trim(), channel: channel.trim(), oldTitle: title.trim() };
                case "Rotten Tomatoes Trailers": return { title: RottenTomatoesTrailersYouTubeTitleMask(title).trim(), channel: channel.trim(), oldTitle: title.trim() };
                case "FilmSelect Trailer": return { title: FilmSelectTrailerYouTubeTitleMask(title).trim(), channel: channel.trim(), oldTitle: title.trim() };
                case "Prime Video": return { title: PrimeVideoYouTubeTitleMask(title).trim(), channel: channel.trim(), oldTitle: title.trim() };
                case "HBO Max": return { title: HBOMaxYouTubeTitleMask(title).trim(), channel: channel.trim(), oldTitle: title.trim() };
                case "Hulu": return { title: HuluYouTubeTitleMask(title).trim(), channel: channel.trim(), oldTitle: title.trim() };
                case "Disney Plus": return { title: DisneyPlusYouTubeTitleMask(title).trim(), channel: channel.trim(), oldTitle: title.trim() };
                case "Sony Pictures Entertainment": return { title: SonyPicturesEntertainmentYouTubeTitleMask(title).trim(), channel: channel.trim(), oldTitle: title.trim() };
                default: return { title: NoMatchYouTubeTitleMask(title).trim(), channel: channel.trim(), oldTitle: title.trim() };
            }

        }
    }
    return { title: "", channel: channel.trim() };
}

function waitForTitleLoad(maxWaitTimeInSeconds) {
    return new Promise((resolve, reject) => {
        const startTime = new Date().getTime();
        const maxWaitTimeInMilliseconds = maxWaitTimeInSeconds * 1000;

        function checkString() {
            let { title, channel, oldTitle } = parserYoutube();

            if (title !== "" && channel != "") {
                resolve({ title: title, channel: channel, oldTitle: oldTitle });
            } else {
                const currentTime = new Date().getTime();
                const elapsedTime = currentTime - startTime;

                if (elapsedTime < maxWaitTimeInMilliseconds) {
                    setTimeout(checkString, 3000);
                } else {
                    reject("Maximum wait time exceeded, giving up.");
                }
            }
        }
        checkString();
    });
}


async function getFilmTitleFromYouTube() {

    let waitForTitleCalled = false;

    async function checkVideo() {
        let video = document.querySelector('video');
        startTime = null;
        cacheTitle = "";
        oldCacheTitle = "";
        let titleVideo, channelVideo, oldTitleVideo;
        if (video != null && /www\.youtube\.com\/watch/.test(window.location)) {
            setTimeout(async () => {
                let result = await waitForTitleLoad(500);
                channelVideo = result.channel;
                titleVideo = result.title;
                oldTitleVideo = result.oldTitle;
                startTime = new Date();
                cacheTitle = titleVideo;
                oldCacheTitle = oldTitleVideo;
            }, 2000);
            waitForTitleCalled = true;
            window.onbeforeunload = function () {
                endTime = new Date();
                let timeSpent = Math.round((endTime - startTime) / 1000);
                if (cacheTitle !== oldCacheTitle) {
                    sendDataToServer(cacheTitle, timeSpent, "www.youtube.com");
                }
            }
            video.addEventListener('playing', function () {
                if (!waitForTitleCalled) {
                    setTimeout(async () => {
                        let result = await waitForTitleLoad(500);
                        if (result.title + "***" + result.channel != titleVideo + "***" + channelVideo) {
                            channelVideo = result.channel;
                            titleVideo = result.title;
                            oldTitleVideo = result.oldTitle;
                            if (startTime != null) {
                                endTime = new Date();
                                let timeSpent = Math.round((endTime - startTime) / 1000);
                                if (cacheTitle !== oldCacheTitle) {
                                    sendDataToServer(cacheTitle, timeSpent, "www.youtube.com");
                                }
                                cacheTitle = titleVideo;
                                oldCacheTitle = oldTitleVideo;
                                startTime = new Date();
                            } else {
                                startTime = new Date();
                                cacheTitle = titleVideo;
                                oldCacheTitle = oldTitleVideo;
                            }
                        }
                    }, 2000);
                }
                waitForTitleCalled = false;
            });
        } else {
            setTimeout(checkVideo, 2000);
        }
    }

    checkVideo();

}