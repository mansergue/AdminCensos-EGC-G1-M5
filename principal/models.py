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
    

