from django.shortcuts import render
from django.shortcuts import get_object_or_404
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from .models import Stock
from .serializer import StockSerializer

# Create your views here.

#Stocks/FB
class StockList(APIView):
    #read stocks
    def get(self, request):
        stocks = Stock.objects.all()
        serializer = StockSerializer(stocks, many=True)
        return Response(serializer.data)

    #create stocks
    def post(self):
        pass