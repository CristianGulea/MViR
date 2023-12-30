from django.urls import path

from RecommendationServer.recommendation import calculateMAEWNewConnection
from templates.views import getBest10FitForAUserWNewConnectionView, calculateMAEWNewConnectionView

urlpatterns = [
    path('best-10-fit/<str:userId>/', getBest10FitForAUserWNewConnectionView, name='best-10-fit'),
    path('mae/', calculateMAEWNewConnectionView, name='calculate-mae'),
]
