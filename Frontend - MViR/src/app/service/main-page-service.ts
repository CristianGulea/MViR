import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class MainPageService{

  constructor(){}

  changeHistoryColour(){
    const documentHistoryText = document.getElementById("history-text");
    const documentMoviesText = document.getElementById("movies-text");
    const documentSuggestionsText = document.getElementById("suggestions-text");
    const documentHistoryIcon = document.getElementById("history-icon");
    const documentMoviesIcon = document.getElementById("movies-icon");
    const documentSuggestionsIcon = document.getElementById("suggestions-icon");
    if (documentHistoryText != null && documentMoviesText != null && documentSuggestionsText != null &&
      documentHistoryIcon != null && documentMoviesIcon != null && documentSuggestionsIcon != null) {
      documentHistoryText.style.color = "ghostwhite";
      documentHistoryIcon.style.color = "ghostwhite";
      documentMoviesText.style.color = "rgba(246, 245, 245, 0.56)";
      documentMoviesIcon.style.color = "rgba(246, 245, 245, 0.56)";
      documentSuggestionsText.style.color = "rgba(246, 245, 245, 0.56)";
      documentSuggestionsIcon.style.color = "rgba(246, 245, 245, 0.56)";
    }
  }

  changeMoviesColour(){
    const documentHistoryText = document.getElementById("history-text");
    const documentMoviesText = document.getElementById("movies-text");
    const documentSuggestionsText = document.getElementById("suggestions-text");
    const documentHistoryIcon = document.getElementById("history-icon");
    const documentMoviesIcon = document.getElementById("movies-icon");
    const documentSuggestionsIcon = document.getElementById("suggestions-icon");
    if (documentHistoryText != null && documentMoviesText != null && documentSuggestionsText != null &&
      documentHistoryIcon != null && documentMoviesIcon != null && documentSuggestionsIcon != null) {
      documentHistoryText.style.color = "rgba(246, 245, 245, 0.56)";
      documentHistoryIcon.style.color = "rgba(246, 245, 245, 0.56)";
      documentMoviesText.style.color = "ghostwhite";
      documentMoviesIcon.style.color = "ghostwhite";
      documentSuggestionsText.style.color = "rgba(246, 245, 245, 0.56)";
      documentSuggestionsIcon.style.color = "rgba(246, 245, 245, 0.56)";
    }
  }

  changeSuggestionsColour() {
    const documentHistoryText = document.getElementById("history-text");
    const documentMoviesText = document.getElementById("movies-text");
    const documentSuggestionsText = document.getElementById("suggestions-text");
    const documentHistoryIcon = document.getElementById("history-icon");
    const documentMoviesIcon = document.getElementById("movies-icon");
    const documentSuggestionsIcon = document.getElementById("suggestions-icon");
    if (documentHistoryText != null && documentMoviesText != null && documentSuggestionsText != null &&
      documentHistoryIcon != null && documentMoviesIcon != null && documentSuggestionsIcon != null) {
      documentHistoryText.style.color = "rgba(246, 245, 245, 0.56)";
      documentHistoryIcon.style.color = "rgba(246, 245, 245, 0.56)";
      documentMoviesText.style.color = "rgba(246, 245, 245, 0.56)";
      documentMoviesIcon.style.color = "rgba(246, 245, 245, 0.56)";
      documentSuggestionsText.style.color = "ghostwhite";
      documentSuggestionsIcon.style.color = "ghostwhite";
    }
  }

}
