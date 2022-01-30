# MobileTestApp

## Android App with Clean Architecture-MVVM

The goal of this Android application is to show an architecture for the development of a project
from scratch, it consists of a view/detail application using the marvel Api with principles of clean
architecture and MVVM. The following technologies have been used for the development:

- Hilt
- Kotlin
- Coroutines
- Kotlin Flow
- Room
- Retrofit
- Navigation component
- Data binding
- Paging 3 librarys
- Testing with junit, mockk and turbine

## Modules

The modules contained in our application are listed below:

| Modules | Description |
| ------ | ----- |
| App | Layer presentation  |
| Common-utils | Common classes and shared utilities with different modules  |
| Data | Repositories implementation, mappers |
| Database | ROOM, entities and share preferences (future) |
| Domain | Here should be any usecase, model or repository interface |
| Services | Remote module; contains the DTOs, the retrofit services interfaces and the remote data sources. |
| String-manager | Contains the texts of the application, the idea is that translations can be managed from this module. |

<p align="center">
  <img src="https://github.com/denniscayo/android_project_test/blob/develop/assets/Modules.png">
</p>

## Presentation

We use data **Data binding** that it was introduced in Google I/O 2015, the Data Binding library
helps write declarative layouts and minimize the glue code necessary to bind application logic and
layouts.

## Modules Domain Data Services

The simplest way to explain the communication and function of each module is to illustrate it with a
diagram.

<p align="center">
  <img src="https://github.com/denniscayo/android_project_test/blob/develop/assets/Pattern.png">
</p>

## Database

The Room persistence library provides an abstraction layer over SQLite to allow fluent database
access while harnessing the full power of SQLite.

In our application we use this database to store the information of a specific character, the first
time the user accesses the detail view, a call will be made to the service to obtain the information
and then it will be stored. So that if the same character is accessed again, the local information
will be retrieved.

## CharacterRepositoryImpl

This repository is important as it allows us to choose the source of data to obtain the information.
The first time a call will be made to the service from the remote data source and the information
will be stored in the local database, and then the information will be retrieved from the local data
source.

<p align="center">
  <img src="https://github.com/denniscayo/android_project_test/blob/develop/assets/CharacterRepositoryImpl.png">
</p>

## ViewModel

Each view model injects different use cases, and can trigger events or states based on the response
of the these.

Our viewModel uses two sealed class to modelate screen behavior

- **State:** Screen state: Idle, Loading, Data, Error. States are persistent in time **StateFlow**
- **Event:** An event is something that is not persistent in time, occurs and dissapears **Channel**
- **StateFlow** always emits the last value **Persistent**
- **Channel** emits only once **Volatile**

## State View

- **Loading:** When the screen starts
- **Error:** After loading there is no data
- **EmptyView:** There is no data to be drawn
- **Success:** Data is available to be drawn

## Testing

For testing our app we have used the following tools:

- **jUnit:** Basic test library, contains the minimum annotations and asserts necessary to run unit
  tests
- **Coroutines:** To test functions that are implemented in coroutines scopes.
- **MockK:** Library to mock the classes or the results of the necessary functions for the unit test
- **Turbine:** To simplify flow functions test
- **Fixture:** to generate real objects with random values

<p align="center">
  <img src="https://github.com/denniscayo/android_project_test/blob/develop/assets/Testing.png">
</p>

## View of the application

<p align="center">
  <img src="https://github.com/denniscayo/android_project_test/blob/master/assets/CapturaList.gif" width="250">
  <img src="https://github.com/denniscayo/android_project_test/blob/master/assets/CapturaDetail.gif" width="250">
  <img src="https://github.com/denniscayo/android_project_test/blob/develop/assets/CapturaAbout.png" width="250">
</p>

**Dennis Cayo**
