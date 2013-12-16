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
	--data 'probando el API de texto'
	http://localhost:9000/api/text/
$ c523f5df-854e-4cb9-b93c-0fa2aaf7341d
```

### Acceder a un texto guardado

- **URL**: /api/text/<key>
- **Parámetros**: identificador del texto guardado
- **Método**: GET
- **Formato de los datos**: text/plain
- **Respuesta**: contenido del texto guardado

#### Ejemplo

```bash
$ curl
	--header "Content-type: text/plain"
	http://localhost:9000/api/text/c523f5df-854e-4cb9-b93c-0fa2aaf7341d
$ probando el API de texto
```
