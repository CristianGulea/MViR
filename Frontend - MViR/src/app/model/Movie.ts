export interface Movie{
  id: number;
  name: String;
  overview: String;
  urlPoster: String;
  urlBanner: String;
  genres: { id: number, name: String, numberOfMovies: number }[] | null;
  releaseDate: String;
}
