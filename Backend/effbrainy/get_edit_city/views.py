from city.models import City
from get_edit_city.serializers import Get_edit_citySerializer
from rest_framework import generics
# from ticket.permissions import IsOwnerOrReadOnly
# from rest_framework import permissions
from django.shortcuts import get_object_or_404

class Get_edit_cityList(generics.ListCreateAPIView):
 def get(self, request, *args, **kwargs):
  City.objects.filter(id=request.META.get('HTTP_ID')).update(name=request.META.get('HTTP_NAME'),pin_code=request.META.get('HTTP_PIN'))
    #   return validated_data
  details=[]
  details.append(
                  {
                   'status':200,
                   'message':'Updated',
                  }
                 )  

  import sys
  from django.http import JsonResponse
  return JsonResponse(details[0],safe=False)

from django.http import JsonResponse

class StatusCode(object):
    OK = 200
    NOT_FOUND = 404
    # add more status code according to your need
import json
from django.http import HttpResponse
 
def JSONResponse(data = None, status = StatusCode.OK):
    if data is None:
        return HttpResponse(status)
    if data and type(data) is dict:
        return HttpResponse(json.dumps(data, indent = 4, encoding = 'utf-8', sort_keys = True), \
            mimetype = 'application/json', status = status)
    else:
        return HttpResponse(status = StatusCode.NOT_FOUND)

def get_queryset(request):
  from django.http import JsonResponse
  
  cities=list(City.objects.all().values('id','name','pin_code'))
  return JsonResponse(cities,safe=False)


       

