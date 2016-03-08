from rest_framework import serializers
import random
from random import randint

from register.models import Register, LANGUAGE_CHOICES, STYLE_CHOICES
from generate_otp.models import Generate_otp, LANGUAGE_CHOICES, STYLE_CHOICES
from django.http import HttpResponse


class Resend_otpSerializer(serializers.ModelSerializer):
    class Meta:
        model = Register
        fields = ('phone',)
    

    def create(self, validated_data):
        """
        Create and return a new `Snippet` instance, given the validated data.
        """
        otp_generated=str(random.randint(100000, 999999))
        #details=validated_data
        #valid=0
        import datetime
        now = datetime.datetime.now()
        now_plus_60 = now + datetime.timedelta(minutes = 60)

        obj=Register.objects.get(phone=validated_data.get('phone'))
        Generate_otp.objects.filter(user_id=obj.pk).update(otp=otp_generated,validity=now_plus_60,created=now)

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
            sms_to=validated_data.get('phone'), # sms_to='9052161119',
            sms_body='Hi '+validated_data.get('phone')+', your number '+otp_generated+' is now turned asOTP.')
        print r.status_code
        pprint(r.json())



        msg='Hi '+validated_data.get('phone')+', your number '+otp_generated+' is now turned asOTP.'

        from django.core.mail import send_mail
        send_mail('SAVMYTIME: ',msg, 'poojapauskar22@gmail.com', [obj.email], fail_silently=False)


        
 


        return validated_data

