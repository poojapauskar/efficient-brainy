from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from check_username import views

urlpatterns = [
    url(r'^check_username/$', views.Check_usernameList.as_view()),
]

urlpatterns = format_suffix_patterns(urlpatterns)