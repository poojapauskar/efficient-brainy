from django.db import models

# Create your models here.
from pygments.lexers import get_all_lexers
from pygments.styles import get_all_styles

from pygments.lexers import get_lexer_by_name
from pygments.formatters.html import HtmlFormatter
from pygments import highlight
from django.core.validators import RegexValidator

LEXERS = [item for item in get_all_lexers() if item[1]]
LANGUAGE_CHOICES = sorted([(item[1][0], item[0]) for item in LEXERS])
STYLE_CHOICES = sorted((item, item) for item in get_all_styles())

owner = models.ForeignKey('auth.User', related_name='register')
highlighted = models.TextField()

class Get_edit_profile(models.Model):
 created = models.DateTimeField(auto_now_add=True)
 username = models.CharField(max_length=100, blank=True,default='')
 password = models.CharField(max_length=100, blank=True,default='')
 name = models.CharField(max_length=100, blank=True,default='')
 email = models.EmailField(max_length=100, blank=True,default='')
 phone_regex = RegexValidator(regex=r'^\+?1?\d{12}$', message="Enter country code. Phone number must be entered in the format: '919999999'.")
 phone = models.CharField(max_length=12,validators=[phone_regex], blank=False) # validators should be a list
 city_id = models.CharField(blank=True,max_length=15,default='',editable=True)
 address = models.TextField(blank=True,default='')

 class Meta:
  ordering = ('created',)

