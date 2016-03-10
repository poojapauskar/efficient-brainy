from rest_framework import serializers
from city.models import City, LANGUAGE_CHOICES, STYLE_CHOICES
from get_edit_profile.models import Get_edit_profile, LANGUAGE_CHOICES, STYLE_CHOICES
import random
from random import randint



class Get_edit_citySerializer(serializers.ModelSerializer):
    class Meta:
        model = City
        fields = ('id','name','pin_code')
    

    # def create(self, validated_data): 
 	   

    #   # obj=Register.objects.get(vz_id=validated_data.get('vz_id'))



    #   City.objects.filter(id=self.kwargs['city_id']).update(name=validated_data.get('name'),pin_code=validated_data.get('pin_code'))
    #   return validated_data