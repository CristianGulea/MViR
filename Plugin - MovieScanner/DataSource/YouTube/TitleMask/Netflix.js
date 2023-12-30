function NetflixYouTubeTitleMask(title) {
    switch (true) {
        case /\| Official Teaser Trailer/.test(title):
            return title.split("| Official Teaser Trailer")[0];
        case /\| Official Trailer/.test(title):
            return title.split("| Official Trailer")[0];
        case /\| Final Trailer/.test(title):
            return title.split("| Final Trailer")[0];
        case /\| Official Teaser/.test(title):
            return title.split("| Official Teaser")[0];
        case /\| Official Game Trailer/.test(title):
            return title.split("| Official Game Trailer")[0];
        case /\| First Look Clip/.test(title):
            return title.split("| First Look Clip")[0];
        case /\| Official Clip/.test(title):
            return title.split("| Official Clip")[0];
        case /\| Exclusive Sneak Peek/.test(title):
            return title.split("| Exclusive Sneak Peek")[0];
        case /\| Sneak Peek/.test(title):
            return title.split("| Sneak Peek")[0];
        case /\| Date Announcement/.test(title):
            return title.split("| Date Announcement")[0];
    }
    return title;
}

