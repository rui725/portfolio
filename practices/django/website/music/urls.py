from django.conf.urls import url
from . import views

app_name = 'music'

urlpatterns = [
    url(r'^$', views.IndexView.as_view(), name='index'),  # homepage for specific app

    url(r'(?P<pk>[0-9]+)/$', views.DetailView.as_view(), name='detail'),
  #  url(r'(?P<album_id>[0-9]+)/favourite/$', views.favourite , name='favourite'), not used in generic view

   #/music/album/add album
    url(r'album/add/$', views.AlbumCreate.as_view(), name='album-add'),

    # update view album
    url(r'album/(?P<pk>[0-9]+)/edit/$', views.AlbumUpdate.as_view(), name='album-update'),

    # delete  view album
    url(r'album/(?P<pk>[0-9]+)/delete/$', views.AlbumDelete.as_view(), name='album-delete'),

    # add song
       url(r'song/add/$', views.SongCreate.as_view(), name='song-add'),

        # update view album
    url(r'song/(?P<pk>[0-9]+)/edit/$', views.SongUpdate.as_view(), name='song-update'),

    # delete  view album
    url(r'song/(?P<pk>[0-9]+)/delete/$', views.SongDelete.as_view(), name='song-delete'),

]
