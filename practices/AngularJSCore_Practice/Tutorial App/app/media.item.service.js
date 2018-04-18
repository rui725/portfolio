System.register(['@angular/core', '@angular/http', 'rxjs/add/operator/map'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1;
    var MediaItemService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {}],
        execute: function() {
            MediaItemService = class MediaItemService {
                constructor(http) {
                    this.http = http;
                }
                get(medium) {
                    let searchParams = new http_1.URLSearchParams();
                    searchParams.append('medium', medium);
                    return this.http.get('mediaitems', { search: searchParams }).map(response => {
                        return response.json().mediaItems;
                    });
                }
                add(mediaItem) {
                    //this.mediaItems.push(mediaItem);
                    return this.http.post('mediaitems', mediaItem);
                }
                delete(mediaItem) {
                    /*let index = this.mediaItems.indexOf(mediaItem);
                    if(index >=0){
                        this.mediaItems.splice(index,1);
                    }*/
                    return this.http.delete(`mediaItems/${mediaItem.id}`);
                }
            };
            MediaItemService = __decorate([
                core_1.Injectable(), 
                __metadata('design:paramtypes', [http_1.Http])
            ], MediaItemService);
            exports_1("MediaItemService", MediaItemService);
        }
    }
});
//# sourceMappingURL=media.item.service.js.map