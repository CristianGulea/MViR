import { Injectable, Output, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LoadingCubesService {

  @Output() eventEmitter: EventEmitter<any> = new EventEmitter();
  isLoading: boolean = false;

  setValue(isLoading: boolean): void {
    this.eventEmitter.emit(isLoading);
    this.isLoading = isLoading;
  }

  getValue(): any {
    return this.eventEmitter;
  }

  getIsLoadingNow(): boolean{
    return this.isLoading;
  }
}
