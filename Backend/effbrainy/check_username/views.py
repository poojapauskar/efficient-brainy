from register.models import Register
from login.serializers import LoginSerializer
from rest_framework import generics

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

class Check_usernameList(generics.ListCreateAPIView):
 def get(self, request, *args, **kwargs):
  username=request.META.get('HTTP_USERNAME')
  details=[]

  if(Register.objects.filter(username=request.META.get('HTTP_USERNAME')).exists()):
   details.append(
                  {
                   'status':400,
                   'message':'Username already exists',
                  }
                 )
  else:
   details.append(
                  {
                   'status':200,
                   'message':'Valid username',
                  }
                 )  

  import sys
  from django.http import JsonResponse
  return JsonResponse(details[0],safe=False)



