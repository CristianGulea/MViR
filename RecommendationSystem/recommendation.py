#!/usr/bin/env python
# coding: utf-8

import psycopg2
from surprise import Dataset
from surprise import SVD
from surprise import Reader
import pandas as pd
from surprise.model_selection import cross_validate
from surprise.model_selection import train_test_split
from surprise import accuracy
from surprise import KNNBasic


def pred(userCode, movieIdE, algo):
    return algo.predict(userCode, movieIdE).est


def trainAlgoKNN(connectionDB):
    cur = connectionDB.cursor()

    cur.execute("SELECT * FROM ratingn")

    rows = cur.fetchall()

    users = []
    movies = []
    ratings = []

    for row in rows:
        idRating, hostname, movieId, rating, startDate, startTime, timeSpend, userId = row
        users.append(userId)
        movies.append(movieId)
        ratings.append(rating)

    cur.close()

    ratings_dict = {'userID': users, 'itemID': movies, 'rating': ratings}
    df = pd.DataFrame(ratings_dict)

    reader = Reader(rating_scale=(1, 5))
    data = Dataset.load_from_df(df[['userID', 'itemID', 'rating']], reader)

    trainSet, testSet = train_test_split(data, test_size=0.3, random_state=42)

    algo = KNNBasic()

    algo.fit(trainSet)

    return [algo, testSet]


def calculateMAE(algo, testSet):
    predictions = algo.test(testSet)
    return accuracy.mae(predictions)


def calculateMAEWNewConnection():
    connection = psycopg2.connect(
        host="localhost",
        database="movierecommender",
        user="postgres",
        password="cristian"
    )

    [algo, testSet] = trainAlgoKNN(connectionDB=connection)

    valueMae = calculateMAE(algo, testSet)

    connection.close()

    return valueMae


def getBest10FitForAUser(userId, connectionDB, algo):
    cur = connectionDB.cursor()
    cur.execute("SELECT DISTINCT movie_id FROM ratingn")
    rows = cur.fetchall()
    movieIds = []

    for row in rows:
        movie_id = row[0]
        movieIds.append(movie_id)

    cur.close()
    ratings_dict = {'movieID': movieIds}
    dfId = pd.DataFrame(ratings_dict)

    dfId['result'] = dfId['movieID'].apply(lambda x: pred(str(userId), x, algo))
    max_movies = dfId.nlargest(10, 'result')
    max_movies_sorted = max_movies.sort_values('result', ascending=False)
    return max_movies_sorted['movieID'].tolist()


def getBest10FitForAUserWNewConnection(userId):
    connection = psycopg2.connect(
        host="localhost",
        database="movierecommender",
        user="postgres",
        password="cristian"
    )

    [algo, testSet] = trainAlgoKNN(connectionDB=connection)

    moviesRanking = getBest10FitForAUser(userId=str(userId), connectionDB=connection, algo=algo)

    connection.close()

    return moviesRanking
