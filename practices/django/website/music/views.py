from django.shortcuts import render
from django.http import HttpResponse
from .models import Album
# Create your views here.

def index(request):
    all_album = Album.objects.all()
    html = '<center><ul>'
    for s in all_album:
        url = '/music/'+str(s.id)
        html += '<li><a href="'+url+'">'+ s.album_title+"</a></li>"
    html+="</ul></center>"
    return HttpResponse(html)

def detail(request, album_id):
    return HttpResponse("<h2>Album ID: "+ album_id + "</h2>")