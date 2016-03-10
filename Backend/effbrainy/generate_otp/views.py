from register.models import Register
from generate_otp.models import Generate_otp
from generate_otp.serializers import Generate_otpSerializer
from rest_framework import generics
# from ticket.permissions import IsOwnerOrReadOnly
# from rest_framework import permissions
from django.shortcuts import get_object_or_404
import random
from random import randint


from django.http import JsonResponse

class Generate_otpList(generics.ListCreateAPIView):
 queryset = Generate_otp.objects.all()
 serializer_class = Generate_otpSerializer

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

class CustomListView(generics.ListAPIView):
    #paginate_by = 2
    def get(self, request, *args, **kwargs):
      from django.http import JsonResponse

      access_token = request.GET.get('access_token')
      # access_token=''

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

      import datetime
      now = datetime.datetime.now()
      now_plus_60 = now + datetime.timedelta(minutes = 60)

      user=Register.objects.get(token_generated=access_token)

      if(Generate_otp.objects.filter(user_id=user.pk).exists()):
       Generate_otp.objects.filter(user_id=user.pk).delete() 

      otp_generated=str(random.randint(100000, 999999))
      objects=Generate_otp.objects.create(user_id=user.pk,otp=otp_generated,validity=now_plus_60)

      from pprint import pprint
      import requests
      from django.conf import settings

      #from settings import sid, token
      sid = 'bitjini'
      token = '85dbbbc18dfaf078290eeee3c185ac6dfd8a208f'

      def send_message(sid, token, sms_from, sms_to, sms_body):
          return requests.post('https://twilix.exotel.in/v1/Accounts/{sid}/Sms/send.json'.format(sid=sid),
          auth=(sid, token),
          data={
              'From': sms_from,
              'To': sms_to,
              'Body': sms_body
          })


        #if __name__ == '__main__':
        # 'From' doesn't matter; For transactional, this will be replaced with your SenderId;
        # For promotional, this will be ignored by the SMS gateway
        # Incase you are wondering who Dr. Rajasekhar is http://en.wikipedia.org/wiki/Dr._Rajasekhar_(actor)
      r = send_message(sid, token,
          sms_from='09243422233',  # sms_from='8808891988',
          sms_to=user.phone, # sms_to='9052161119',
          sms_body='Hi '+user.phone+', your number '+otp_generated+' is now turned asOTP.')
      print r.status_code
      pprint(r.json())

      msg='Hi '+user.phone+', your number '+otp_generated+' is now turned asOTP.'

      from django.core.mail import send_mail
      send_mail('SAVMYTIME: ',msg, 'poojapauskar22@gmail.com', [user.email], fail_silently=False)



      details=[]
      details.append({
              'status':'200',
              'objects':list(Generate_otp.objects.filter(user_id=user.pk).values('otp','user_id','validity','created'))
        })


      return JsonResponse(details,safe=False)
  	  
class Delete_otp(generics.ListAPIView):
    #paginate_by = 2
    def get(self, request, *args, **kwargs):
      from django.http import JsonResponse

      access_token = request.GET.get('access_token')
      # access_token='123456789'

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
      
      user=Register.objects.get(token_generated=access_token)
      Generate_otp.objects.filter(user_id=user.pk).delete()



      details=[]
      details.append({
              'status':'200',
              'message':'OTP deleted'
        })


      return JsonResponse(details,safe=False)
  	        
  	  
  	  


       

