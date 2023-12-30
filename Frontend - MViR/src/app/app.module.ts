import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MainPageComponent } from './main-page/main-page.component';
import { MatIconModule } from '@angular/material/icon';
import { MoviesPageComponent } from './movies-page/movies-page.component';
import { MovieDetailPageComponent } from './movie-detail-page/movie-detail-page.component';
import { RouterModule, Routes} from "@angular/router";
import { HistoryPageComponent } from './history-page/history-page.component';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule} from "@angular/forms";
import {MatTooltipModule} from "@angular/material/tooltip";
import { RecommendationPageComponent } from './recommendation-page/recommendation-page.component';
import { NgProgressModule } from 'ngx-progressbar';
import { LoadingCubesComponent } from './loading-cubes/loading-cubes.component';


const routes: Routes = [
  {path: 'movies', component: MoviesPageComponent, data: { hideMenu: false }},
  {path: '', redirectTo: 'movies', pathMatch: 'full', data: { hideMenu: false }},
  {path: 'details/:name', component: MovieDetailPageComponent, data: { hideMenu: false }},
  {path: 'history', component: HistoryPageComponent, data: { hideMenu: false }},
  {path: 'recommendation', component: RecommendationPageComponent, data: { hideMenu: false }},
];

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    MoviesPageComponent,
    MovieDetailPageComponent,
    HistoryPageComponent,
    RecommendationPageComponent,
    LoadingCubesComponent,
  ],
    imports: [
        RouterModule.forRoot(routes),
        BrowserModule,
        MatIconModule,
        HttpClientModule,
        FormsModule,
        MatTooltipModule,
        NgProgressModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
