from rest_framework import serializers
from register.models import Register, LANGUAGE_CHOICES, STYLE_CHOICES
from get_edit_profile.models import Get_edit_profile, LANGUAGE_CHOICES, STYLE_CHOICES
import random
from random import randint



class Get_edit_profileSerializer(serializers.ModelSerializer):
    class Meta:
        model = Register
        fields = ('token_generated','username','password', 'name', 'email', 'phone','city_id','address','is_admin','created')
    

    def create(self, validated_data): 
 	   

      # obj=Register.objects.get(vz_id=validated_data.get('vz_id'))



      Register.objects.filter(username=validated_data.get('username')).update(password=validated_data.get('password'),name=validated_data.get('name'),email=validated_data.get('email'),phone=validated_data.get('phone'),city_id=validated_data.get('city_id'),address=validated_data.get('address'))
      return validated_data