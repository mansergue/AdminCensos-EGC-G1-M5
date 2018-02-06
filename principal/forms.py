from models import Census,UserAccount,UserAccountPerCensus,Role
from django.forms import ModelForm

class CensusForm(ModelForm):
    class Meta:
        model = Census
        fields = ['title','postalCode','ca']
class UserAccount(ModelForm):
    class Meta:
        model = UserAccount
        fields = ['username','password','email','role']
class UserAccountPerCensusForm(ModelForm):
    class Meta:
        model = UserAccountPerCensus
        fields = ['census','user_account']
class Role(ModelForm):
    class Meta:
        model = Role
        fields = ['name']
