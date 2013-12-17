# APIs de Gloo

En este documento encontrarás información sobre cómo utilizar las APIs de Gloo. Esta información te resultará útil si deseas crear un cliente de escritorio para esta aplicación. Este documento está dirigido a desarrolladores, si buscas información sobre cómo instalar Gloo lee el documento LEEME.

Gloo tiene tres APIs:

- Texto plano
- Xml
- Json

Cada una ofrece dos servicios básicos:

- Guardar texto
- Acceder a un texto guardado

Las siguientes secciones explican cómo utilizar cada API.

> Para los ejemplos utilicé curl, pero puedes usar algún equivalente si lo prefieres.

## API de texto plano

Esta es la más sencilla de todas. Entendiendo cómo funciona te será más fácil entender las demás.

### Guardar texto

- **URL**: /api/text/
- **Método**: POST
- **Formato de los datos**: text/plain
- **Datos**: texto a guardar
- **Respuesta**: identificador del texto guardado

#### Ejemplo

```bash
$ curl
	--header "Content-type: text/plain"
	--request POST
	--data 'Probando el API de texto'
	http://localhost:9000/api/text/
$ c523f5df-854e-4cb9-b93c-0fa2aaf7341d
```

### Acceder a un texto guardado

- **URL**: /api/text/[key]
- **Parámetros**: identificador del texto guardado
- **Método**: GET
- **Formato de los datos**: text/plain
- **Respuesta**: contenido del texto guardado

#### Ejemplo

```bash
$ curl
	--header "Content-type: text/plain"
	http://localhost:9000/api/text/c523f5df-854e-4cb9-b93c-0fa2aaf7341d
$ Probando el API de texto
```

## API Xml

Esta funciona de forma similar a la anterior, sólo que espera el texto en formato Xml.

### Guardar texto

- **URL**: /api/xml/
- **Método**: POST
- **Formato de los datos**: text/xml
- **Datos**: texto a guardar encerrado en una etiqueta "content"
- **Respuesta**: identificador del texto guardado encerrado en una etiqueta "key"

#### Ejemplo

```bash
$ curl
	--header "Content-type: text/xml"
	--request POST
	--data '<content>Probando el API Xml</content>'
	http://localhost:9000/api/text/
$ <key>f45e2e67-9d1e-4f3b-8240-d0fd37aca902</key>
```

### Acceder a un texto guardado

- **URL**: /api/xml/[key]
- **Parámetros**: identificador del texto guardado
- **Método**: GET
- **Formato de los datos**: text/xml
- **Respuesta**: contenido del texto guardado encerrado en una etiqueta "content"

#### Ejemplo

```bash
$ curl
	--header "Content-type: text/xml"
	http://localhost:9000/api/xml/f45e2e67-9d1e-4f3b-8240-d0fd37aca902
$ <content>Probando el API Xml</content>
```

## API Json

Esta funciona de forma similar a la anterior, sólo que espera el texto en formato Json.

### Guardar texto

- **URL**: /api/json/
- **Método**: POST
- **Formato de los datos**: application/json
- **Datos**: objeto Json con un atributo "content" con  valor igual al texto a guardar
- **Respuesta**: objeto Json con atributo "key" con valor igual al identificador del texto guardado

#### Ejemplo

```bash
$ curl
	--header "Content-type: application/json"
	--request POST
	--data '{"content":"Probando el API Json"}'
	http://localhost:9000/api/json/
$ {"key":"1eca3423-9566-4815-b7ca-0ceb48f5a56b"}
```

### Acceder a un texto guardado

- **URL**: /api/json/[key]
- **Parámetros**: identificador del texto guardado
- **Método**: GET
- **Formato de los datos**: application/json
- **Respuesta**: contenido del texto guardado encerrado en una etiqueta "content"

#### Ejemplo

```bash
$ curl
	--header "Content-type: application/json"
	http://localhost:9000/api/json/1eca3423-9566-4815-b7ca-0ceb48f5a56b
$ {"content":"Probando el API Json"}
```
