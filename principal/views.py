from principal.models import *
from principal.forms import *
from django.shortcuts import render_to_response, get_list_or_404
#Para autenticacion
from django.contrib.auth import authenticate
#Para usar Formularios
#from principal.forms import *
from django.contrib.auth.models import User
from django.http import HttpResponse, HttpResponseRedirect
from django.template import RequestContext

from datetime import datetime

URL_COOKIE="https://g1login.egc.duckdns.org/cookies/"

def inicio(request):
    session_id=request.COOKIES.get('session_id')
    if session_id is not None:
        response = urllib2.urlopen(URL_COOKIE+session_id).read()
        data = json.loads(response)
        if data['codigo'] == 1:
            censos = Census.objects.all()
    	    return render_to_response("listaCensos.html",{"censos":censos})
        else:
            return HttpResponseRedirect('https://g1login.egc.duckdns.org/login')
    else:
        return HttpResponseRedirect('https://g1login.egc.duckdns.org/login')


def nuevo_censo(request):
    if request.method=='POST':
        formulario = CensusForm(request.POST, request.FILES)
        if formulario.is_valid():
            formulario.save()
            return HttpResponseRedirect('/')
    else:
        formulario = CensusForm()
    return render_to_response('nuevocenso.html',{'formulario':formulario}, context_instance=RequestContext(request))

def modificar_censo(request):
    censoModificar = Census.objects.get()
    if request.method=='POST':
        formulario = CensusForm(request.POST, instance=censoModificar)
        if formulario.is_valid():
            formulario.save()
            return HttpResponseRedirect('/')
    else:
        formulario = CensusForm()
    return render_to_response('modificarcenso.html',{'formulario':formulario}, context_instance=RequestContext(request))

def eliminar_censo(request):
    Census.objects.get().delete()
    return render_to_response('eliminarcenso.html', context_instance=RequestContext(request))
def eliminar_censos(request):
    censos = Census.objects.all().delete()
    return render_to_response("eliminartodoscensos.html", context_instance=RequestContext(request))
  
def censos(request):
    censos = Census.objects.all()
    return render_to_response("listaCensos.html",{"censos":censos})

def censar(request):
    if request.method=='POST':
        formulario = UserAccountPerCensusForm(request.POST, request.FILES)
        if formulario.is_valid():
            formulario.save()
            return HttpResponseRedirect('/')
    else:
        formulario = UserAccountPerCensusForm()
    return render_to_response('censar.html',{'formulario':formulario}, context_instance=RequestContext(request))
