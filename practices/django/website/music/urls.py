from django.conf.urls import url
from . import views
from django.contrib.auth import views as auth_views
app_name = 'music'

urlpatterns = [
    url(r'^$', views.IndexView.as_view(), name='index'),  # homepage for specific app
    #logout
    url(r'^logout/$', auth_views.logout,{'next_page': 'music:login'}, name='logout'),
    #login
    url(r'^login/$', auth_views.login, name='login'),

    url(r'^register/$', views.UserFormView.as_view(), name='register'),  # register

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
    url(r'song/(?P<pk>[0-9]+)/update/$', views.SongUpdate.as_view(), name='song-update'),

    # delete  view album
    url(r'song/(?P<pk>[0-9]+)/remove/$', views.SongDelete.as_view(), name='song-delete'),

]
