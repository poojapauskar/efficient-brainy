from register.models import Register
from generate_otp.models import Generate_otp
from valid_otp.models import Valid_otp
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

class Valid_otpList(generics.ListCreateAPIView):
 def get(self, request, *args, **kwargs):
  access_token = request.GET.get('access_token')
  otp=request.META.get('HTTP_OTP')
  details=[]

  user=Register.objects.get(token_generated=access_token)

  if(Generate_otp.objects.filter(user_id=user.pk).filter(otp=otp).exists()):
   obj1=Generate_otp.objects.get(otp=otp)

   import datetime
   from datetime import timedelta
   c= (datetime.datetime.now()-obj1.created.replace(tzinfo=None))>timedelta(seconds = 600)
   import sys
   print sys.stderr,c
   print sys.stderr,obj1.created.replace(tzinfo=None)
   print sys.stderr,datetime.datetime.now()

   if(c==True):
    details.append(
                  {
                   'status':200,
                   'message':'Valid OTP',
                  }
                 )
   else:
    details.append(
                  {
                   'status':401,
                   'message':'OTP Expired',
                  }
                 )
  else:
   details.append(
                  {
                   'status':400,
                   'message':'Invalid OTP',
                  }
                 )  

  import sys
  from django.http import JsonResponse
  return JsonResponse(details[0],safe=False)



