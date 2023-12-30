function getFilmsAndTimeSpend() {

    switch (window.location.hostname) {
        case "www.youtube.com":
            getFilmTitleFromYouTube();
            break;
        case 'www.rottentomatoes.com':
            calculateTimeSpent(getFilmTitleFromRottenTomatoes, 'www.rottentomatoes.com');
            break;
        case 'www.imdb.com':
            calculateTimeSpent(getFilmTitleFromIMDB, 'www.imdb.com');
            break;
        case 'www.themoviedb.org':
            calculateTimeSpent(getFilmTitleFromTMDB, 'www.themoviedb.org');
            break;
        case 'localhost':
            chrome.storage.local.get('mvirCode', function (result) {
                var jsonObject = JSON.parse(result.mvirCode);
                var hiddenDiv = document.createElement('div');

                hiddenDiv.id = 'mvirCode';
                hiddenDiv.innerText = jsonObject.value;

                hiddenDiv.style.display = 'none';

                document.body.appendChild(hiddenDiv);
            });
        default:
            getFilmTitleFromGoogleDescription();
            break;
    }
}


getFilmsAndTimeSpend();