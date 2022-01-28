# Mobile test

# Clean-MVVM

Con aplicación Android se prentede mostrar la arquitectura para el desarolloro de un proyecto desde
cero, se trata de una aplicación vista detalle usando la Api de marvel con principios de clean
architecture y MVVM. Para el desarollo de esta se ha usado las siguientes tecnologias: Hilt, Kotlin,
Coroutines, Kotlin Flow, Room, Retrofit, Paging 3 library

# Modules

Para conseguir este objetivo se han creado los siguientes modulos:

- app: contiene los fragments y activities de la aplicación
- common-utils:
- data:
- database: esta capa implementa la bbdd local de nuestro telefono, por el momento solo se cachean
  la información especifica de un usuario
- domain:
- services: aqui se encuentran los DTO's y las llamadas al servicio
- string-manager: la idea es que puedan hacerce traducciones en un futuro, pues tenemos un único
  modulo donde actualizar los textos

This Android app has the following modules:

