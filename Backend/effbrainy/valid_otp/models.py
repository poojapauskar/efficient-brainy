from django.db import models


from django.core.validators import RegexValidator


class Valid_otp(models.Model):
    otp = models.CharField(max_length=100, blank=True,default='')
    