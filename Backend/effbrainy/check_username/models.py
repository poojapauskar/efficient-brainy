from django.db import models


from django.core.validators import RegexValidator


class Check_username(models.Model):
    username = models.CharField(max_length=100, blank=True,default='')
    