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

class Generate_otp(models.Model):
 created = models.DateTimeField(auto_now_add=True)
 user_id = models.CharField(max_length=100, blank=True,default='')
 otp = models.CharField(max_length=100, blank=True,default='')
 validity = models.CharField(max_length=100, blank=True,default='')
    
 class Meta:
  ordering = ('created',)

