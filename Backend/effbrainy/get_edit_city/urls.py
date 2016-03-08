from django.conf.urls import include
from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns
from get_edit_city import views
from get_edit_city.views import get_queryset


urlpatterns = [
    url(r'^get_edit_city/update/$', views.Get_edit_cityList.as_view()),
    url(r'^get_edit_city/$', get_queryset),
    
    
    
	
]

urlpatterns = format_suffix_patterns(urlpatterns)

urlpatterns += [
    url(r'^api-auth/', include('rest_framework.urls',
                               namespace='rest_framework')),
]