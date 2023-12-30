function parserIMDB() {
    let title = "";
    element = document.querySelector('.sc-afe43def-3.EpHJp');
    if (element == null) {
        element = document.querySelector('.sc-afe43def-1.fDTGTb');
        if (element != null) {
            title = element.textContent;
        }
    } else {
        title = element.textContent.substring(16);
    }
    return title;
}

function getFilmTitleFromIMDB() {
    title = parserIMDB();
    return title;
}