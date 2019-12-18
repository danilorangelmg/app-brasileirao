
##### README version beta 1.0
# PROJECT ARCHITECTURE

### General Development

- This App has developed in Kotlin
- The Model View ViewModel Pattern(MVVM) is used.
- The project is divided modules, being they features(Dynamic feature), repositories, networkService, app (default) and domain.
- Retrofit and coroutines are used to make requests
- For coordination of the flow of screens is used Android Navigation
- Injection dependency is done with the Koin Framework

### Tools and Technologies
    - Koin -> Dependency injection
    - LiveData -> ViewModel Observable
    - Retrofit -> Network(Rest) requests
    - Coroutine -> Asynchronous execute
    - Dynamic Feature Module -> Features modules onDemand
    - Kotlin -> Language

### Modules

- :app -> Coordinates the features
- :soccer(feature) -> Dynamic Feature(onDemand)
- :domain -> Save and coordinates features models
- :networkService -> Network(Rest) engine, coordinates retrofit calls
- :repository: interface and coordinates service calls from features
- :databaseService ->  Database Engine(Not Implement)
