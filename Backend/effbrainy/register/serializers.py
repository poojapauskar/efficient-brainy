from rest_framework import serializers
from register.models import Register, LANGUAGE_CHOICES, STYLE_CHOICES
import random
from random import randint
import json
import time

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
        if Register.objects.filter(username=validated_data.get('username')).exists():
          return validated_data
        else:
          objects=Register.objects.create(is_admin='0',token_generated=validated_data.get('token_generated'),name=validated_data.get('name'),username=validated_data.get('username'),password=validated_data.get('password'),email=validated_data.get('email'),phone=validated_data.get('phone'),city_id=validated_data.get('city_id'),address=validated_data.get('address'))
        
        # print >> sys.stderr, objects
        return objects



