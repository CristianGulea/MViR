function FilmSelectTrailerYouTubeTitleMask(title) {
    switch (true) {
        case / Trailer/.test(title):
            return title.split(" Trailer")[0];
        case / Teaser Trailer/.test(title):
            return title.split(" Teaser Trailer")[0];
    }
    return title;
}