from rest_framework import serializers
from register.models import Register
from generate_otp.models import Generate_otp
from login.models import Login

import random
from random import randint

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

from django.views import generic
from django.views.generic import ListView

from register.models import Register
from rest_framework import generics


class Valid_otpSerializer(serializers.ModelSerializer):
    class Meta:
        model = Generate_otp
        fields = ('otp',)

