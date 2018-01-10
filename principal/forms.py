from models import Census
from django.forms import ModelForm

class CensusForm(ModelForm):
    class Meta:
        model = Census
        fields = ['title','postalCode','ca']
class UserAccount(modelForm):
    class Meta:
        model = UserAccount
        fields = ['username','password','email','role']
class UserAccountPerCensus(modelForm):
    class Meta:
        model = UserAccountPerCensus
        fields = ['census','user_account']
