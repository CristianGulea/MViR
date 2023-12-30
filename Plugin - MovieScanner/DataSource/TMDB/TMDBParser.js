function parserTMDB() {
    let title = "";
    responseQuery = document.querySelector('div.title h2 a');
    if (responseQuery != null) {
        title = responseQuery.textContent;
    }
    return title;
}

function getFilmTitleFromTMDB() {
    title = parserTMDB();
    return title;
}