from django.conf.urls import include
from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from generate_otp import views
from generate_otp.views import get_queryset


urlpatterns = [
    url(r'^generate_otp/update/$', views.CustomListView.as_view()),
	
]

urlpatterns = format_suffix_patterns(urlpatterns)

urlpatterns += [
    url(r'^api-auth/', include('rest_framework.urls',
                               namespace='rest_framework')),
]