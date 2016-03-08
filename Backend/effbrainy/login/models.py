from django.db import models


from django.core.validators import RegexValidator


class Login(models.Model):
    username = models.CharField(max_length=100, blank=True,default='')
    password = models.CharField(max_length=100, blank=True,default='')
    
    # class Meta:
    #     ordering = ('created',)