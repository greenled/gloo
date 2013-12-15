# Gloo - Un pastebin realmente sencillo con muchas funciones

Gloo es un pastebin hecho con el objetivo de generar un tráfico de red mínimo.

## Funciones

Además de las funciones básicas de cualquier pastebin (guardar texto y asociarlo a una URL), Gloo agrega las siguientes (*por ahora*):

- **Tres APIs** : Xml, Json y Texto plano

## Feedback

Si quieres mantenerte informado del progreso del desarrollo de Gloo deberías darte una vuelta por mi [[Twitter | http://twitter.com/greenled2013]].

## Gloo en Heroku

Actualmente estoy trabajando en cómo poner Gloo en [[Heroku | http://www.heroku.com ]] para que puedas verlo funcionando antes de instalarlo.

## Instalación

### 1 - Instalar Java Development Kit

Primeramente necesitas instalar el Java Development Kit:

```bash
$ apt-get install openjdk-7-jdk
$ java -version
```

> Actualmente estoy desarrollando Gloo con el JDK 7, sin embargo debería funcionar sin problema con la versión 6.

### 2 - Instalar Play framework

Una vez tengas Java debes instalar el framework con que se desarrolla Gloo: [[Play framework | http://www.playframework.org/download]]. Puedes descargarlo desde [http://www.playframework.org/download]. Dentro del paquete descargado encontrarás las instrucciones para su instalación.

> Actualmente estoy desarrollando Gloo con la versión 2.1.0 de Play.

### 3 - Compilar la aplicación

El próximo paso es compilar Gloo. Para eso abre una terminal en el directorio raíz de la aplicación y ejecuta lo siguiente:

```bash
$ play dist
```
El comando anterior creará un nuevo directorio "dist" con el archivo gloo-1.0-SNAPSHOT.zip dentro. Este archivo contiene la aplicación compilada y lista para ejecutar.

### 4 - Ejecutar la aplicación

Una vez tengas el archivo con la aplicación compilada descomprímelo en un directorio en el que tengas permiso de lectura **y de escritura**:

```bash
$ unzip gloo2-1.0-SNAPSHOT.zip
``` 

Luego entra en el directorio de la aplicación descomprimida, asegúrate de que el archivo start tiene permiso de ejecución, y ejecútalo:

```
$ cd gloo2-1.0-SNAPSHOT
$ chmod a+x start
$ ./start
```

Esto iniciará el servidor de la aplicación. Una vez iniciado podrás utilizar la aplicación accediendo a [http://localhost:9000]
