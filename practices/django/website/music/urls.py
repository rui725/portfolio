from django.conf.urls import url
from . import views

urlpatterns=[
    url(r'^$',views.index, name='index'),#homepage for specific app

    url(r'(?P<album_id>[0-9]+)/',views.detail,name='detail')

]
