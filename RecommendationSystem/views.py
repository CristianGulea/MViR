from django.http import JsonResponse
from RecommendationServer.recommendation import getBest10FitForAUserWNewConnection, calculateMAEWNewConnection


def getBest10FitForAUserWNewConnectionView(request, userId):
    data = getBest10FitForAUserWNewConnection(userId)
    response_data = {
        'movies': data,
    }
    return JsonResponse(response_data)


def calculateMAEWNewConnectionView(request):
    data = calculateMAEWNewConnection()
    response_data = {
        'mae': data,
    }
    return JsonResponse(response_data)
