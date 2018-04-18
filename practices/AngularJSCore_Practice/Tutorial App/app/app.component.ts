import { Component } from '@angular/core';

@Component({
    selector: 'mw-app',
    templateUrl: 'app/app.component.html', // '' inline template. `` multiple line 
    styleUrls: [`app/app.component.css`]
})
export class AppComponent{
    onMediaItemDelete(mediaItem){
        console.log("deleted");
    }
    firstMediaItem = {
        id: 1,
        name: "Firebug",
        medium: "Series",
        category: "Science Fiction",
        year: 2010,
        watchedOn:  129416656384,
        isFavorite: false
    };
}
