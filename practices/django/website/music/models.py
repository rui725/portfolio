from __future__ import unicode_literals
from django.shortcuts import reverse
from django.db import models

# Create your models here.
class Album(models.Model):
    artist = models.CharField(max_length=250)
    album_title = models.CharField(max_length=250)
    genre = models.CharField(max_length=100)
    album_logo = models.FileField()

    def get_absolute_url(self):
        return reverse('music:detail',kwargs={'pk':self.pk})

    def __str__(self):  # returns the title and artist when querying rather than the literal Album name
        return self.album_title + " - " + self.artist

class Song(models.Model):
    album = models.ForeignKey(Album,on_delete=models.CASCADE)
    audio_file = models.FileField(default="")
    song_title = models.CharField(max_length=250)
    is_favourite = models.BooleanField(default=False)

    def __str__(self):
        return self.song_title

    def get_absolute_url(self):
        return reverse('music:detail',kwargs={'pk':self.pk})
