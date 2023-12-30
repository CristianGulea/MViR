import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HistoryService} from "../service/history-service";
import {Rating} from "../model/Rating";
import {LoadingCubesService} from "../service/loading-cubes-service";
import {MainPageService} from "../service/main-page-service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-history-page',
  templateUrl: './history-page.component.html',
  styleUrls: ['./history-page.component.css']
})
export class HistoryPageComponent implements OnInit {

  private historyData:Rating[] = [];
  private historyDataSelected: number = 0;
  private historyIds: number[] = [];
  @ViewChild('checkbox') checkBox: HTMLInputElement | undefined;
  @ViewChild('scrollBarHistory') scrollBarHistory: ElementRef | undefined;
  private pageNumber: number = 0;
  private ws: WebSocket | undefined;

  constructor(private routes: Router, private mainPageService: MainPageService, private historyService: HistoryService, public loadingCubesService: LoadingCubesService) {

  }

  ngOnInit(): void {
    if (localStorage.getItem("mvirCode") == undefined){
      this.routes.navigate(['movies']);
    }
    this.refreshComponent();
    this.ws = this.initWS();
  }

  ngOnDestroy(){
    /*if (this.ws != undefined){
      alert("here close")
      this.ws.close();
    }*/
  }

  private initWS(){
    let ws = new WebSocket("ws://localhost:8080/socket");

    ws.onopen = function() {
      console.log("WebSocket connection is open and ready to send messages.");

      const htmlElementCode: HTMLElement | null = document.getElementById("mvirCode");
      if (htmlElementCode?.innerText !== ""){
        let code = htmlElementCode?.innerText;
        if (code != null){
          ws.send(code);
        }
      }
    };

    ws.onmessage = (event: { data: string }) => {
      console.log("Received message from server: " + event.data);
      this.refreshComponent();
    };

    return ws;
  }

  private refreshComponent(){
    this.loadingCubesService.setValue(true);
    let finishCount: number = 0;
    this.historyIds = [];
    let codeMvir = localStorage.getItem("mvirCode");
    if (codeMvir != undefined) {
      this.historyService.getHistoryDetails(codeMvir, this.pageNumber).subscribe(historyData => {
        this.historyData = historyData;
        finishCount += 1;
        if (finishCount === 1){
          this.loadingCubesService.setValue(false);
          this.mainPageService.changeHistoryColour();
        }
      });
    }
    this.historyDataSelected = 0;
  }

  getHistoryData(){
    return this.historyData;
  }

  getHistoryDataSelected(): number{
    return this.historyDataSelected
  }

  setHistoryDataSelected(number: number){
    this.historyDataSelected = number;
  }

  incrementHistoryDataSelected(){
    this.historyDataSelected = this.historyDataSelected + 1;
  }

  decrementHistoryDataSelected(){
    this.historyDataSelected = this.historyDataSelected - 1;
  }


  handleCheckBox(data: Rating, event: Event) {
    if (event != null && event.currentTarget != null) {
      // @ts-ignore
      if (event.currentTarget.checked){
        this.incrementHistoryDataSelected();
        this.historyIds.push(data.id);
        return;
      }
      this.historyIds =  this.historyIds.filter((num) => num !== data.id);
      this.decrementHistoryDataSelected();
    }
  }

  scrollDown() {
    if (this.scrollBarHistory) {
      const scrollMovie = this.scrollBarHistory.nativeElement;
      scrollMovie.scrollBy(0, 20);
    }
  }


  scrollUp() {
    if (this.scrollBarHistory) {
      const scrollMovie = this.scrollBarHistory.nativeElement;
      scrollMovie.scrollTop -= 20;
    }
  }

  deleteSelectedHistory() {
    this.historyService.deleteData(this.historyIds).subscribe(() => {
      this.refreshComponent();
    });
  }


}
