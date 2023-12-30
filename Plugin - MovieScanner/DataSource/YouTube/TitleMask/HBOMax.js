function HBOMaxYouTubeTitleMask(title) {
    switch (true) {
        case /\| Official Trailer/.test(title):
            return title.split("| Official Trailer")[0];
        case /\| Official Teaser/.test(title):
            return title.split("| Official Teaser")[0];
        case /\| Inside the Episode/.test(title):
            return title.split("| Inside the Episode")[0];
    }
    return title;
}