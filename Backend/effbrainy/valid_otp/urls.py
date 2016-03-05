from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from valid_otp import views

urlpatterns = [
    url(r'^valid_otp/$', views.Valid_otpList.as_view()),
]

urlpatterns = format_suffix_patterns(urlpatterns)