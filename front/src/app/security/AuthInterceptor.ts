import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {TokenStorageService} from "../service/token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {

  private HEADER: string = "Authorization";

  constructor(
    private tokenStorage: TokenStorageService
  ) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.tokenStorage.getToken();

    if (token != null) {
      authReq = req.clone({
        headers: req.headers.set(this.HEADER, 'Bearer ' + token)
      });
    }

    return next.handle(authReq);
  }

}
