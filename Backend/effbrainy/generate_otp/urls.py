from django.conf.urls import include
from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from generate_otp import views



urlpatterns = [
    url(r'^generate_otp/$', views.CustomListView.as_view()),
    url(r'^generate_otp/list$', views.Generate_otpList.as_view()),
    url(r'^delete_otp/$', views.Delete_otp.as_view()),
	
]

urlpatterns = format_suffix_patterns(urlpatterns)

urlpatterns += [
    url(r'^api-auth/', include('rest_framework.urls',
                               namespace='rest_framework')),
]