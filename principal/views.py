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

listaCenso = []

def inicio(request):
    censos = Census.objects.all()
    return render_to_response("listaCensos.html",{"censos":censos})

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
