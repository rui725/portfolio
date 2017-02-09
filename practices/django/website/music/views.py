# GENERIC VIEWS#
from django.views import generic
from .models import Album, Song
from django.views.generic.edit import CreateView,DeleteView, UpdateView
from django.core.urlresolvers import reverse_lazy

class IndexView(generic.ListView):
    template_name = 'music/index.html'
    context_object_name = 'all_albums'

    def get_queryset(self):
        return Album.objects.all()

class DetailView(generic.DetailView):
    model = Album
    template_name = 'music/detail.html'


class AlbumCreate(CreateView):
    model = Album
    fields = ['artist', 'album_title','genre','album_logo']

class AlbumUpdate(UpdateView):
    model = Album
    fields = ['artist', 'album_title','genre','album_logo']

class AlbumDelete(DeleteView):
    model = Album
    success_url = reverse_lazy('music:index')

class SongCreate(CreateView):
    model = Song
    fields = ['album','audio_file','song_title','is_favourite']

class SongUpdate(UpdateView):
    model = Song
    fields = ['album','audio_file','song_title','is_favourite']

class SongDelete(DeleteView):
    model = Song
    success_url = reverse_lazy('music:detail')



















# LONG WAY #
from django.http import Http404
#--from django.shortcuts import render
from django.http import HttpResponse
# from django.template import loader #stuff for template
#--from django.shortcuts import render, get_object_or_404
#--from .models import Album, Song
#import pdb
# Create your views here.

#def index(request):
    #all_album = Album.objects.all()
    # stuff manual html code for HttpResponse
    # html = '<center><ul>'
    # for s in all_album:
    #    url = '/music/'+str(s.id)
    #    html += '<li><a href="'+url+'">'+ s.album_title+"</a></li>"
    # html+="</ul></center>"
    # return HttpResponse(html)

    # using Template loader note FOLDER MUST NAME Templates
    # template = loader.get_template('music/index.html')
    # context = {
    #    'all_album': all_album
    # }
    # return HttpResponse(template.render(context,request))

    # shortcute using render
    #context = {'all_album': all_album}
    #return render(request, 'music/index.html', context)


#def detail(request, album_id):
    # long way
    # try:
    #    album = Album.objects.get(pk=album_id)
    # except Album.DoesNotExist:
    #    raise Http404('Album does not exist')

    # shortway
    #album = get_object_or_404(Album, pk=album_id)
    #return render(request, 'music/detail.html', {'all_album': album})


#def favourite(request, album_id):
    #album = get_object_or_404(Album, pk=album_id)
    #try:
    #   selected_song = album.song_set.get(pk=request.POST['song'])
    #except Exception:
    #  return render(request,'music/detail.html',{'all_album':album, 'err_message':'Song Not Found'})
    #else:
    #     selected_song.is_favourite=True
    #     selected_song.save()
    #return render(request,'music/detail.html',{'all_album':album})
