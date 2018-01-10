from django.db import models
from django.template.defaultfilters import default

class Ca(models.Model):
    id = models.IntegerField(primary_key=True, unique=True, null=False)
    name = models.CharField(max_length=100, null=False, blank=False)
    class Meta:
        db_table = 'ca'
    def __unicode__(self):
        return self.name

class Census(models.Model):
    id = models.IntegerField(unique=True, primary_key=True, null=False)
    title = models.CharField(max_length=100, null=False)
    postalCode = models.IntegerField(null=True, blank=True, db_column='postalCode')
    ca = models.ForeignKey(Ca)
    class Meta:
        db_table = 'census'
    def __unicode__(self):
        return self.title

class User(models.Model):
    id = models.IntegerField(primary_key=True)	
    name = models.CharField(max_length=100L)
    surname = models.CharField(max_length=200L)
    genre = models.CharField(max_length=1L)
    edad = models.DateField()
    dni = models.CharField(max_length=9L)
    ca = models.ForeignKey(Ca)
    user_account = models.ForeignKey('UserAccount')
    class Meta:
        db_table = 'user'

class UserAccount(models.Model):
    id = models.IntegerField(primary_key=True)
    username = models.CharField(max_length=50L, unique=True)
    password = models.CharField(max_length=50L)
    email = models.CharField(max_length=100L)
    role = models.ForeignKey(Role)
    class Meta:
        db_table = 'user_account'

class UserAccountPerCensus(models.Model):
    id = models.IntegerField(primary_key=True)
    census = models.ForeignKey(Census)
    user_account = models.ForeignKey(UserAccount)
    class Meta:
        db_table = 'user_account_per_census'
    

