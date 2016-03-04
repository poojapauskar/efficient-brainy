from city.models import City
from city.serializers import CitySerializer
from rest_framework import generics
# from register.permissions import IsOwnerOrReadOnly
# from rest_framework import permissions


class CityList(generics.ListCreateAPIView):
 queryset = City.objects.all()
 serializer_class = CitySerializer
 # permission_classes = (permissions.IsAuthenticatedOrReadOnly,)



class CityDetail(generics.RetrieveUpdateDestroyAPIView):
 queryset = City.objects.all()
 serializer_class = CitySerializer
 # permission_classes = (permissions.IsAuthenticatedOrReadOnly,
 #                      IsOwnerOrReadOnly,)






