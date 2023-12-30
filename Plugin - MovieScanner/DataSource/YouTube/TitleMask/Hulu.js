function HuluYouTubeTitleMask(title) {
    switch (true) {
        case /\| Official Trailer/.test(title):
            return title.split("| Official Trailer")[0];
        case /\| Trailer/.test(title):
            return title.split("| Trailer")[0];
        case / Trailer/.test(title):
            return title.split(" Trailer")[0];
        case /\| Inside Look/.test(title):
            return title.split("| Inside Look")[0];

    }
    return title;
}