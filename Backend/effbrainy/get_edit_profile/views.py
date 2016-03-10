from register.models import Register
from get_edit_profile.serializers import Get_edit_profileSerializer
from rest_framework import generics
# from ticket.permissions import IsOwnerOrReadOnly
# from rest_framework import permissions
from django.shortcuts import get_object_or_404

class Get_edit_profileList(generics.ListCreateAPIView):
 queryset = Register.objects.all()
 serializer_class = Get_edit_profileSerializer
 # permission_classes = (permissions.IsAuthenticatedOrReadOnly,)

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
  access_token = request.GET.get('access_token')
  # access_token = '123456789'
  if(Register.objects.filter(token_generated=access_token).exists()):
    pass
  else:
    error=[]
    error.append({
            'status':'404',
            'message':'access token not valid'
      })
    return JsonResponse(error[0],safe=False)



  import sys
  print >> sys.stderr, access_token

  # vz_id= Register.objects.filter(token_generated=access_token).values('vz_id')
  # print >> sys.stderr, vz_id
  
  
  profile=Register.objects.filter(token_generated=access_token).values('token_generated','username','password', 'name', 'email', 'phone','city_id','address','is_admin','created')[0],  
  





  
  return JsonResponse(profile[0],safe=False)


       

