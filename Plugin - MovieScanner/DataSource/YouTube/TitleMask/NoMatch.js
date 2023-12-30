function NoMatchYouTubeTitleMask(title) {
    let cacheTitle = title;
    if (cacheTitle.length > NetflixYouTubeTitleMask(title).length) cacheTitle = NetflixYouTubeTitleMask(title);
    if (cacheTitle.length > PrimeVideoYouTubeTitleMask(title).length) cacheTitle = PrimeVideoYouTubeTitleMask(title);
    if (cacheTitle.length > RottenTomatoesTrailersYouTubeTitleMask(title).length) cacheTitle = RottenTomatoesTrailersYouTubeTitleMask(title);
    if (cacheTitle.length > FilmSelectTrailerYouTubeTitleMask(title).length) cacheTitle = FilmSelectTrailerYouTubeTitleMask(title);
    if (cacheTitle.length > HBOMaxYouTubeTitleMask(title).length) cacheTitle = HBOMaxYouTubeTitleMask(title);
    if (cacheTitle.length > HuluYouTubeTitleMask(title).length) cacheTitle = HuluYouTubeTitleMask(title);
    if (cacheTitle.length > DisneyPlusYouTubeTitleMask(title).length) cacheTitle = DisneyPlusYouTubeTitleMask(title);
    if (cacheTitle.length > SonyPicturesEntertainmentYouTubeTitleMask(title).length) cacheTitle = SonyPicturesEntertainmentYouTubeTitleMask(title);
    return cacheTitle;
}