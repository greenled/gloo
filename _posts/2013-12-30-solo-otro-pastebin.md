---
layout: post
title: Sólo otro pastebin
base_url: "../../../../../"
excerpt: Hace unos meses, en algún momento de septiembre, un colega de una lista de correo cubana me habló de la necesidad de crear un pastebin para apoyar otra recién creada lista de correo de desarrolladores. En ese momento ni siquiera sabía qué era un pastebin ni lo útil que puede resultar...
---

30 de diciembre de 2013

Hola, mi nombre es Juan Carlos. Estudio Ingeniería Informática en la [Universidad de Camagüey](http://www.reduc.edu.cu), Cuba, hace tres años y medio. Mi mayor hobby es la programación, junto a la lectura, las series anime y el rock.

Hace unos meses, en algún momento de septiembre, un colega de una lista de correo cubana me habló de la necesidad de crear un pastebin para apoyar otra recién creada lista de correo de desarrolladores. En ese momento ni siquiera sabía qué era un pastebin ni lo útil que puede resultar.

Después de buscar un poco en Wikipedia y otro tanto en Google supe que se trataba de una aplicación que permite a los desarrolladores pegar un trozo de código fuente en una página y acceder luego a él a través de una dirección URL. Este tipo de aplicaciones es especialmente útil cuando tratas de pedir ayuda en un IRC u otro servicio de mensajería instantánea y necesitas mostrar tu código. En esos casos, en lugar de enviarlo por el chat, usas un pastebin, y compartes la dirección URL con otros desarrolladores, que a su vez pueden hacerles correcciones y hacértelas llegar por la misma vía.

La idea de hacer mi propio pastebin me gustó y empecé a trabajar. Al cabo de un mes y algo ya tenía una versión estable. Estaba diseñado para generar poco tráfico de red, dado que las conexiones interprovinciales en las redes cubanas pueden llegar a ser bastante lentas. Sin embargo hubo un problema con el hosting y mi pastebin no se utilizó. Fue realmente frustrante, pero de alguna forma estaba contento porque había aprendido una cuantas cosas nuevas en ese tiempo.

Ya en diciembre leyendo el blog [humanOS](http://humanos.uci.cu), al que entro con frecuencia, encontré una interesante noticia sobre la convocatoria a un concurso: el Concurso Universitario Cubano de Software Libre. Para participar había que hacer software, hardware o documentación que utilizara una licencia libre. Era la primera vez en mi carrera que me enteraba de un concurso así, por lo que decidí que no perdería la oportunidad de participar dado que soy un entusiata del software libre. Inmediatamente me vino a la mente el pastebin.

Ni siquiera había pensado en qué licencia tendría, y eso fue lo primero que tuve que decidir. Después de revisar algunas como la Apache 2, la BSD, la GPL, la LGPL y la AGPL con mis casi nulos conocimientos sobre leyes me decidí por la AGPL. Esta licencia, creada por la Free Software Foundation, cumple con las cuatro libertades del software y está diseñada especialmente para software que ofrezca servicios a través de una red de computadoras. 

Hasta ahora había trabajado con la versión 1.2.4 de [Play!](http://www.playframework.com), un popular framework web para Java, pero desde el curso anterior estaba esperando la ocasión para trabajar con la versión 2 que incluye un montón de caracteríasticas nuevas. Como la versión 1.2.4 tenía ya varios años de antigüedad decidí implementar el pastebin desde cero con la nueva versión, pues uno de los puntos medidos en el concurso era la actualidad de las tecnologías utilizadas.

Era necesario mantener la carga de la red igual o menor que con la versión anterior del programa, por eso pensé que sería útil agregarle APIs que permitieran utilizarlo desde algún cliente para así ahorrar el ancho de banda.

Fue así como comenzó la historia de Gloo, mi primer programa libre. Pienso continuar su desarrollo aún después de terminado el concurso con la esperanza de que llegue a ser útil a alguien. Esa es mi forma de demostrar respeto a otros desarrolladores que de forma desinteresada comparten cada día su código. En lo que va de diciembre me han ocurrido cosas increíbles relacionadas con Gloo (y estoy seguro de que me ocurrirán muchas más) que próximamente contaré en este blog.
