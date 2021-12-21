# The Movie DB

# TO BE DONE
 
 - Visualize video in movie/show detail for trailers and teasers
 - Improve the error handling, with personalized exceptions
 - App crashes in the search when there is no internet connection, Add a message to indicate there is no network
 - It crashes in the detail without internet too if the movie has not been visualized before, Add a message too
 - Improve the UX and UI, fonts, sizes, theme, skeleton
 - There is no UI testing, add it. Maybe with Expresso
 - Decrease the minSDK it is in the API 26, test if it works in previous versions

## Description

TteMovieDB is a simple aplication that consumes the services provided by the TheMovieDB open API

The project was developed in Android Native with Kotlin using:

- Clean Architecture with 4 layers

- [x] Presentation: A layer that interacts with the UI.
- [x] Domain: Contains the business logic of the app, models and usecases.
- [x] Data: Abstract definition of all the data sources.
- [x] Framework: Implements interaction with the Android SDK and provides concrete implementations for the data layer.

- Each module has its unit tests

![alt text](https://koenig-media.raywenderlich.com/uploads/2019/06/Android-Clean-Architecture.png)

- Model-View-ViewModel as pattern for the presentation layer

![alt text](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png?hl=es-419)

- Repository pattern for the data layer
- Coroutines with Flow for the background tasks
- Dagger Hilt for Dependency Injection
- Retrofit to consume the API Rest+
- Room to persist data locally

### Dependencies used

- [x] Kotlin v1.5.21
- [x] Dagger Hilt v2.40.5
- [x] Retrofit2 v2.9.0
- [x] Coroutines v1.5.0
- [x] Glide v4.12.0
- [x] Android Navigation v2.3.5
- [x] Safe Args v2.3.5
- [x] MockK v1.12.1

## Requirements

- [x] Minimum version: Android 8 - API level 26

### Made by Brayan Arias - stevenson.arias@gmail.com - www.linkedin.com/in/bsav157

 
