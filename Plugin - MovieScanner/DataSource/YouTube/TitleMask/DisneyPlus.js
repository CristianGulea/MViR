function DisneyPlusYouTubeTitleMask(title) {
    switch (true) {
        case /\| Official Trailer/.test(title):
            return title.split("| Official Trailer")[0];
        case /\| Teaser/.test(title):
            return title.split(" Teaser")[0];
    }
    return title;
}