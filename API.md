# APIs de Gloo

En este documento encontrarás información útil acerca de las APIs de Gloo y cómo utilizarlas.

> Este documento está dirigido a desarrolladores, si buscas información sobre cómo instalar Gloo lee el documento README.

Gloo tiene tres APIs:

- Texto plano
- Xml
- Json

Cada una ofrece dos servicios básicos:

- Guardar texto
- Acceder a un texto guardado

Las siguientes secciones explican cómo utilizar cada API. Para los ejemplos utilicé curl, pero puedes usar algún equivalente si lo prefieres.

## API de texto plano

Esta es la más sencilla de todas. Entendiendo cómo funciona te será más fácil entender las demás.

### Guardar texto

- **URL**: /api/text/
- **Método HTTP**: POST
- **Content-type**: text/plain
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
- **Parámetros**:
	- **key**: identificador del texto guardado
- **Método HTTP**: GET
- **Content-type**: text/plain
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
- **Método HTTP**: POST
- **Content-type**: text/xml
- **Datos**: texto a guardar encerrado en una etiqueta "gloo"
- **Respuesta**: identificador del texto guardado encerrado en una etiqueta "gloo"

#### Ejemplo

```bash
$ curl
	--header "Content-type: text/xml"
	--request POST
	--data '<gloo>Probando el API Xml</gloo>'
	http://localhost:9000/api/text/
$ <gloo>f45e2e67-9d1e-4f3b-8240-d0fd37aca902</gloo>
```

### Acceder a un texto guardado

- **URL**: /api/xml/[key]
- **Parámetros**:
	- **key**: identificador del texto guardado
- **Método HTTP**: GET
- **Content-type**: text/xml
- **Respuesta**: contenido del texto guardado encerrado en una etiqueta "gloo"

#### Ejemplo

```bash
$ curl
	--header "Content-type: text/xml"
	http://localhost:9000/api/xml/f45e2e67-9d1e-4f3b-8240-d0fd37aca902
$ <gloo>Probando el API Xml</gloo>
```

## API Json

Esta funciona de forma similar a la anterior, sólo que espera el texto en formato Json.

### Guardar texto

- **URL**: /api/json/
- **Método HTTP**: POST
- **Content-type**: application/json
- **Datos**: objeto Json con un atributo "gloo" con  valor igual al texto a guardar
- **Respuesta**: objeto Json con atributo "gloo" con valor igual al identificador del texto guardado

#### Ejemplo

```bash
$ curl
	--header "Content-type: application/json"
	--request POST
	--data '{"gloo":"Probando el API Json"}'
	http://localhost:9000/api/json/
$ {"gloo":"1eca3423-9566-4815-b7ca-0ceb48f5a56b"}
```

### Acceder a un texto guardado

- **URL**: /api/json/[key]
- **Parámetros**:
	- **key**: identificador del texto guardado
- **Método HTTP**: GET
- **Content-type**: application/json
- **Respuesta**: objeto Json con atributo "gloo" con valor igual al contenido del texto guardado

#### Ejemplo

```bash
$ curl
	--header "Content-type: application/json"
	http://localhost:9000/api/json/1eca3423-9566-4815-b7ca-0ceb48f5a56b
$ {"gloo":"Probando el API Json"}
```
