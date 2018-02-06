from django.conf.urls import patterns, include, url

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    url(r'^$', 'principal.views.inicio', name='inicio'),
    url(r'^nuevocenso/','principal.views.nuevo_censo'),
    url(r'^modificarcenso/','principal.views.modificar_censo'),
    url(r'^eliminarcenso/','principal.views.eliminar_censo'),
    url(r'^eliminartodoscensos/','principal.views.eliminar_censos'),
    url(r'^censos/','principal.views.censos'),
)
