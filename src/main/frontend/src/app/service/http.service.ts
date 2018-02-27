import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";


@Injectable()
export class HttpService {

  url = "http://localhost:8080/";

  constructor(private http: HttpClient) {
  }

  public getDeals(): Observable<any> {
    return this.http.get(this.url + "deal");
  }

  public saveDeals(deals): Observable<any> {
    return this.http.post(this.url + "deal/save", deals);
  }

  public deleteDeal(id) {
    return this.http.delete("deal/" + id);
  }

  public clearAllCompleted() {
    return this.http.delete("deal/clear-all-completed");
  }
}
