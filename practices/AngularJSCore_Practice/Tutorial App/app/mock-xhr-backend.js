System.register(['@angular/http', 'rxjs/Observable'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var http_1, Observable_1;
    var MockXHRBackend;
    return {
        setters:[
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (Observable_1_1) {
                Observable_1 = Observable_1_1;
            }],
        execute: function() {
            MockXHRBackend = class MockXHRBackend {
                constructor() {
                    this._mediaItems = [
                        {
                            id: 1,
                            name: "Firebug",
                            medium: "Series",
                            category: "Science Fiction",
                            year: 2010,
                            watchedOn: 1294166565384,
                            isFavorite: false
                        },
                        {
                            id: 2,
                            name: "The Small Tall",
                            medium: "Movies",
                            category: "Comedy",
                            year: 2015,
                            watchedOn: null,
                            isFavorite: true
                        }, {
                            id: 3,
                            name: "The Redemption",
                            medium: "Movies",
                            category: "Action",
                            year: 2016,
                            watchedOn: null,
                            isFavorite: false
                        }, {
                            id: 4,
                            name: "Hoopers",
                            medium: "Series",
                            category: "Drama",
                            year: null,
                            watchedOn: null,
                            isFavorite: true
                        }, {
                            id: 5,
                            name: "Happy Joe: Cheery Road",
                            medium: "Movies",
                            category: "Action",
                            year: 2015,
                            watchedOn: 1457166565384,
                            isFavorite: false
                        }
                    ];
                }
                createConnection(request) {
                    var response = new Observable_1.Observable((responseObserver) => {
                        var responseData;
                        var responseOptions;
                        switch (request.method) {
                            case http_1.RequestMethod.Get:
                                if (request.url.indexOf('mediaitems?medium=') >= 0 || request.url === 'mediaitems') {
                                    var medium;
                                    if (request.url.indexOf('?') >= 0) {
                                        medium = request.url.split('=')[1];
                                        if (medium === 'undefined')
                                            medium = '';
                                    }
                                    var mediaItems;
                                    if (medium) {
                                        mediaItems = this._mediaItems.filter(mediaItem => mediaItem.medium === medium);
                                    }
                                    else {
                                        mediaItems = this._mediaItems;
                                    }
                                    responseOptions = new http_1.ResponseOptions({
                                        body: { mediaItems: JSON.parse(JSON.stringify(mediaItems)) },
                                        status: 200
                                    });
                                }
                                else {
                                    var id = parseInt(request.url.split('/')[1]);
                                    mediaItems = this._mediaItems.filter(mediaItem => mediaItem.id === id);
                                    responseOptions = new http_1.ResponseOptions({
                                        body: JSON.parse(JSON.stringify(mediaItems[0])),
                                        status: 200
                                    });
                                }
                                break;
                            case http_1.RequestMethod.Post:
                                var mediaItem = JSON.parse(request.text().toString());
                                mediaItem.id = this._getNewId();
                                this._mediaItems.push(mediaItem);
                                responseOptions = new http_1.ResponseOptions({ status: 201 });
                                break;
                            case http_1.RequestMethod.Delete:
                                var id = parseInt(request.url.split('/')[1]);
                                this._deleteMediaItem(id);
                                responseOptions = new http_1.ResponseOptions({ status: 200 });
                        }
                        var responseObject = new http_1.Response(responseOptions);
                        responseObserver.next(responseObject);
                        responseObserver.complete();
                        return () => { };
                    });
                    return { response };
                }
                _deleteMediaItem(id) {
                    var mediaItem = this._mediaItems.find(mediaItem => mediaItem.id === id);
                    var index = this._mediaItems.indexOf(mediaItem);
                    if (index >= 0) {
                        this._mediaItems.splice(index, 1);
                    }
                }
                _getNewId() {
                    if (this._mediaItems.length > 0) {
                        return Math.max.apply(Math, this._mediaItems.map(mediaItem => mediaItem.id)) + 1;
                    }
                }
            };
            exports_1("MockXHRBackend", MockXHRBackend);
        }
    }
});
//# sourceMappingURL=mock-xhr-backend.js.map