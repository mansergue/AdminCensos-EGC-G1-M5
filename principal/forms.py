from models import Census
from django.forms import ModelForm

class CensusForm(ModelForm):
    class Meta:
        model = Census
        fields = ['title','postalCode','ca']

