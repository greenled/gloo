# Gloo - Un pastebin realmente sencillo

Gloo es un pastebin hecho con el objetivo de generar un tráfico de red mínimo. Para lograrlo, ofrece una interfaz gráfica liviana y varias APIs que permiten acceder al servicio sin necesidad de una interfaz gráfica.

## Funciones

Además de las funciones básicas de cualquier pastebin (guardar texto y asociarlo a una URL a través de una interfaz gráfica), Gloo agrega las siguientes (*por ahora*):

- **API Xml**
- **API Json**
- **API texto plano**

## Feedback

- **Twitter**: Si quieres mantenerte informado del progreso del desarrollo de Gloo puedes seguirme en [Twitter](http://twitter.com/greenled2013).
- **Dreamcatchers**: Si estás en Cuba, puedes aportar nuevas ideas a través de la red social [Dreamcatchers](http://dreamcatchers.reduc.edu.cu/perfil/greenLED)

## Cómo colaborar

Hay varias formas en las que puedes ser útil en este proyecto, estas son algunas:

**Probar la aplicación en tu entrono de trabajo particular**: puede parecer trivial, pero esto ayudaría a detectar y corregir problemas de compatibilidad con navegadores, máquinas virtuales, servidores frontend, y muchos otros que no imagino aún.

**Sugerir nuevas ideas**: ¿Tienes una idea genial para Gloo? Contáctame, quizá pronto esté trabajando en ella.

**Escribir código**: No tienes que ser un veterano en Java para implementar nuevas funcionalidades, corregir bugs, etc. Sería útil que antes de pensar en escribir código para Gloo tuvieras una noción de cómo funciona [Play 2](http://www.playframework.org): su forma de hacer las cosas se aparta un poco de las "Buenas Prácticas" para programar en Java. Varias formas de contribuir con código pueden ser:

	- **Clientes para las APIs**: Esto es algo que pienso hacer cuando Gloo alcance un grado de terminación aceptable, pero tú pudieras hacerlo antes. Personalmente, desearía que existieran clientes integrados en forma de plugins con los principales IDEs, como Eclipse, NetBeans, VisualStudio, y otros para la línea de comandos de los sistemas operativos.

	- **Nuevas APIs**: Inicialmente incluí sólo tres APIs porque me parecieron las más utilizadas. Sin embargo es posible que te sea mejor utilizar una distinta a las tres existentes y desees implementarla. Seguramente otros que también las utilicen agradecerán tu aporte.

	- **Casos de prueba**: Una parte importante del código son los casos de prueba. Diseñarlos e implementarlos correctamente, y darles mantenimiento sería de gran ayuda para mantener Gloo libre de errores.

**Escribir documentación**: Es importante tener una buena documentación que indique cómo sacarle el máximo provecho a Gloo. Los usuarios y desarrolladores la agradecerán.

**Traducir la interfaz a otros idiomas**: Para que Gloo sea fácil de utilizar para la mayorías de las personas su interfaz debe estar disponible en varios idiomas.

**Apoyar el depliegue**: Normalmente utilizo una conexión a Internet extremadamante lenta (a veces más que navegar me ahogo :)). Apreciaría muchísimo que me ayudaras a poner las versiones estables de Gloo (ya compiladas) en lugares desde los que los usuarios finales puedan descargarlas, como Source Forge.

Si te decides a colaborar deja una idea en [Dreamcatchers](http://dreamcatchers.reduc.edu.cu/perfil/greenLED) o escríbeme a juan.mejias [arroba] reduc.edu.cu

## Gloo en Heroku

Actualmente estoy trabajando en cómo poner Gloo en [Heroku](http://www.heroku.com) para que puedas verlo funcionando antes de instalarlo.

## Instalación

Puedes utilizar Gloo de dos formas: como desarrollador o como usuario final. La primera es a partir del código fuente, la segunda a partir de un paquete de instalación. Si solamente deseas utiizar Gloo ve directo al paso 5.

### 1 - Instalar Java Development Kit

Primeramente necesitas instalar el Java Development Kit:

```bash
$ apt-get install openjdk-7-jdk
$ java -version
```

> Actualmente estoy desarrollando Gloo con el JDK 7, sin embargo debería funcionar sin problemas con la versión 6.

### 2 - Instalar Play framework

Una vez tengas Java debes instalar el framework con que se desarrolla Gloo: [Play 2](http://www.playframework.org/download). Puedes descargarlo desde http://www.playframework.org/download. Dentro del paquete descargado encontrarás las instrucciones para su instalación.

> Actualmente estoy desarrollando Gloo con la versión 2.1.0 de Play.

### 3 - Personalización (opcional)

Recuerda que Gloo es Software Libre, y una de sus cuatro libertades te permite modificarlo y ajustarlo a tus necesidades.

La forma más fácil de hacerlo es cambiando las hojas de estilos (CSS). Los CSS de Gloo están escritos en LESS, un lenguaje que al compilarlo se obtiene como resultado CSS. Son fácilmene identificables por su extensión `.less` y se encuentran en el directorio `app/assets/stylesheets`. Una de las ventajas de utilizar LESS en lugar de CSS desde un principio es que durante el desarrollo de la aplicación Play auto compila los `.less` y si se le pide también los *minifica*.

Otra forma de personalizar Gloo es modificando sus archivos de vistas, que están en `app/views` y con extensión `.scala.html`. Estas vistas están compuestas por código Html y código Scala. Si has tenido experiencias previas con motores de plantillas Html encontrarás familiar la sintaxis. Te resultará útil leer la [documentación de Play acerca del motor de plantillas]()

Una vez modifiques los `.less` o los `.scala.html` deberás compilar la aplicación.

### 4 - Compilar la aplicación

El próximo paso es compilar Gloo. Para eso abre una terminal en el directorio raíz de la aplicación y ejecuta lo siguiente:

```bash
$ play dist
```
El comando anterior creará un nuevo directorio "dist" con el archivo gloo-1.0-SNAPSHOT.zip dentro. Este archivo contiene la aplicación compilada y lista para ejecutar.

### 5 - Ejecutar la aplicación

Una vez tengas el archivo con la aplicación compilada descomprímelo en un directorio en el que tengas permiso de lectura **y de escritura**:

```bash
$ unzip gloo2-1.0-SNAPSHOT.zip
```

Luego entra en el directorio de la aplicación descomprimida, asegúrate de que el archivo start tiene permiso de ejecución, y ejecútalo:

```
$ cd gloo2-1.0-SNAPSHOT
$ chmod +x start
$ ./start
```

Esto iniciará el servidor de la aplicación. Una vez iniciado podrás utilizar la aplicación accediendo a [localhost](http://localhost:9000)
