import {Component, OnInit} from '@angular/core';
import {Deal} from './model/deal';
import {DisplayMode} from './enum/display.mode';
import {HttpService} from "./service/http.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  title = 'to-do-list';
  displayMode = DisplayMode.ALL;
  deals = [];
  inputValue;
  count = 0;

  constructor(private  httpService: HttpService) {}

  ngOnInit(): void {
    this.httpService.getDeals().subscribe(
      deals => {
        this.deals = deals._embedded.deals as Deal[];
        this.count =this.deals.length;
      });
  }

  removeDeal(item) {
    const index = this.deals.indexOf(item, 0);
    if (index !== -1) {
      console.log(this.deals[index].id);
      if ((this.deals[index].id) != null) {
        this.httpService.deleteDeal(this.deals[index].id).subscribe();
      }
      this.deals.splice(index, 1);
      if (!item.completed) {
        this.count--;
      }
    }

  }

  displayDeal(deal) {
    switch (this.displayMode) {
      case DisplayMode.ALL:
        return true;
      case DisplayMode.COMPLETED:
        return deal.completed;
      case DisplayMode.ACTIVE:
        return !deal.completed;
    }
  }

  setAll() {
    this.displayMode = DisplayMode.ALL;
  }

  setActive() {
    this.displayMode = DisplayMode.ACTIVE;
  }

  setCompleted() {
    this.displayMode = DisplayMode.COMPLETED;
  }

  removeAllCompleted() {
    this.deals =
      this.deals.filter(value => value.completed === false);
    this.httpService.clearAllCompleted().subscribe();
  }

  addDeal() {
    this.deals.push(new Deal(undefined, this.inputValue, false));
    this.inputValue = null;
  }

  countCheckerBox(deal) {
    if (deal.completed === false) {
      this.count--;
    } else {
      this.count++;
    }
  }

  saveDeals() {
    this.httpService.saveDeals(this.deals).subscribe(deals => {
      this.deals = deals._embedded.deals as Deal[];
    })

  }

}
