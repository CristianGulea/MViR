function RottenTomatoesTrailersYouTubeTitleMask(title) {
    switch (true) {
        case / Teaser Trailer/.test(title):
            return title.split(" Teaser Trailer")[0];
        case /\| Teaser Trailer/.test(title):
            return title.split(" Teaser Trailer")[0];
        case / TV Spot/.test(title):
            return title.split(" TV Spot")[0];
        case / Teaser/.test(title):
            return title.split(" Teaser")[0];
        case / Trailer/.test(title):
            return title.split(" Trailer")[0];
        case / Final Trailer/.test(title):
            return title.split(" Final Trailer")[0];
    }
    return title;
}