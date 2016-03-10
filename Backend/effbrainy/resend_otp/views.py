from register.models import Register
from resend_otp.serializers import Resend_otpSerializer
from rest_framework import generics
# from send_again.permissions import IsOwnerOrReadOnly
# from rest_framework import permissions


class Resend_otpList(generics.ListCreateAPIView):
 queryset = Register.objects.all()
 serializer_class = Resend_otpSerializer
 # permission_classes = (permissions.IsAuthenticatedOrReadOnly,)



