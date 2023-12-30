function parserRottenTomatoes() {
    let title = "";
    let contentQuery = document.querySelector('h1[data-qa="score-panel-movie-title"]');
    if (contentQuery == null) {
        contentQuery = document.querySelector('p[data-qa="score-panel-movie-title"]');
    }
    if (contentQuery != null) {
        title = contentQuery.textContent;
    }
    return title;
}

function getFilmTitleFromRottenTomatoes() {
    title = parserRottenTomatoes();
    return title;
}