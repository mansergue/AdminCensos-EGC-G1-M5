"""
This file demonstrates writing tests using the unittest module. These will pass
when you run "manage.py test".

Replace this with more appropriate tests for your application.
"""

from django.test import TestCase
from principal.models import *

class SimpleTest(TestCase):
    def test_basic_addition(self):
        """
        Tests that 1 + 1 always equals 2.
        """
        self.assertEqual(1 + 1, 2)

    def test_census(self):
        """
        Test that the Census is correctly created and saved in DB
        """
	ca = Ca.objects.create(id=5454, name="Canarias")
        census = Census.objects.create(id=2222, title="Gonz", postalCode=41300, ca=ca)
        self.assertEqual(census.id, 2222)
