from rest_framework import serializers
from register.models import Register, LANGUAGE_CHOICES, STYLE_CHOICES
import random
from random import randint
import json
import time



class RegisterSerializer(serializers.ModelSerializer):

    class Meta:
        model = Register
        fields = ('pk','token_generated','username','password', 'name', 'email', 'phone','city_id','address','is_admin','created')
        #write_only_fields = ('firstame', 'lastname')

    def create(self, validated_data):
        """
        Create and return a new `Snippet` instance, given the validated data.
        """
        # city=City.objects.filter(id=validated_data.get('city_id')).values_list('city')
        objects=Register.objects.create(token_generated=validated_data.get('token_generated'),name=validated_data.get('name'),username=validated_data.get('username'),password=validated_data.get('password'),email=validated_data.get('email'),phone=validated_data.get('phone'),city_id=validated_data.get('city_id'),address=validated_data.get('address'))
        # print >> sys.stderr, objects
        return objects



    from register.models import Register
from register.serializers import RegisterSerializer
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser

#register = Register(firstname='a')
#register.save()

#register = Register(firstname='b')
#register.save()

#serializer = RegisterSerializer(register)
#serializer.data

#content = JSONRenderer().render(serializer.data)
#content

from django.contrib.auth.models import User

class UserSerializer(serializers.ModelSerializer):
    register = serializers.PrimaryKeyRelatedField(many=True, queryset=Register.objects.all())

    class Meta:
        model = User
        fields = ('id', 'username', 'register')

owner = serializers.ReadOnlyField(source='owner.username')

