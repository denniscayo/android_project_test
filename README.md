# Mobile test
# Clean-MVVM

This Android application uses marvel Api with clean architecture principles and MVVM.
I also use Hilt, Kotlin, Coroutines, Kotlin Flow, Room, Retrofit, Paging 3 library.

# Modules

This Android app has the following modules:

- app: presentation layer
- common-utils: module that contains common utilities
- data: implement domain repositories and has mappers for DTO/Models
- database: ROOM database, dao and entities
- domain: business rules
- services: a rest module with DTO's and retrofit services