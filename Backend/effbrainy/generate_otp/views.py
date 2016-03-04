from register.models import Register
from generate_otp.serializers import Generate_otpSerializer
from rest_framework import generics
# from ticket.permissions import IsOwnerOrReadOnly
# from rest_framework import permissions
from django.shortcuts import get_object_or_404
import random
from random import randint


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

class CustomListView(ListView):
    #paginate_by = 2
    def get(self, request, *args, **kwargs):
      from django.http import JsonResponse

      access_token = request.GET.get('access_token')

      import sys
      print >> sys.stderr, access_token

      if(Register.objects.filter(token_generated=access_token).exists()):
        pass
      else:
        error=[]
	    error.append({
	            'status':'404',
	            'message':'access token not valid'
	      })
	    return JsonResponse(error[0],safe=False)

	  user_id=Register.objects.filter(token_generated=access_token).values_list('pk')
	  otp_generated=str(random.randint(100000, 999999))
	        
	  objects=Generate_otp.objects.create(user_id=user_id,otp=otp_generated,validity='')
	  return JsonResponse(objects,safe=False)


       

