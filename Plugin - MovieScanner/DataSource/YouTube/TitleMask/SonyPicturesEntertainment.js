function SonyPicturesEntertainmentYouTubeTitleMask(title) {
    let cacheTitle;
    switch (true) {
        case /- Official Trailer/.test(title):
            return title.split("- Official Trailer")[0];
        case /- Official Teaser Trailer/.test(title):
            return title.split("- Official Teaser Trailer")[0];
        case / Official Red Band Trailer/.test(title):
            cacheTitle = title.split(" Official Red Band Trailer")[0];
            return cacheTitle.substring(0, cacheTitle.length - 2)
        case / Behind The Scenes/.test(title):
            cacheTitle = title.split(" Behind The Scenes")[0];
            return cacheTitle.substring(0, cacheTitle.length - 2)
        case / Extended Preview/.test(title):
            cacheTitle = title.split(" Extended Preview")[0];
            return cacheTitle.substring(0, cacheTitle.length - 2)
        case / Special Features Preview/.test(title):
            cacheTitle = title.split(" Special Features Preview")[0];
            return cacheTitle.substring(0, cacheTitle.length - 2)
    }
    return title;
}