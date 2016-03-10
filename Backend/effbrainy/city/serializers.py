from rest_framework import serializers
from city.models import City, LANGUAGE_CHOICES, STYLE_CHOICES
import random
from random import randint
import json
import time



class CitySerializer(serializers.ModelSerializer):

    class Meta:
        model = City
        fields = ('pk','name','pin_code')
        #write_only_fields = ('firstame', 'lastname')

    def create(self, validated_data):
        """
        Create and return a new `Snippet` instance, given the validated data.
        """
        
        objects=City.objects.create(name=validated_data.get('name'),pin_code=validated_data.get('pin_code'))
        # print >> sys.stderr, objects
        return objects

