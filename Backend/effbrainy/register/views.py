from register.models import Register
from register.serializers import RegisterSerializer
from rest_framework import generics
# from register.permissions import IsOwnerOrReadOnly
# from rest_framework import permissions


class RegisterList(generics.ListCreateAPIView):
 queryset = Register.objects.all()
 serializer_class = RegisterSerializer
 # permission_classes = (permissions.IsAuthenticatedOrReadOnly,)



class RegisterDetail(generics.RetrieveUpdateDestroyAPIView):
 queryset = Register.objects.all()
 serializer_class = RegisterSerializer
 # permission_classes = (permissions.IsAuthenticatedOrReadOnly,
 #                      IsOwnerOrReadOnly,)






