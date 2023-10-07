# bpchallenge
Marcelo Martinez - marcelo.martinez1869@gmail.com

Explico un poco lo que se ha desarrollado

Se hizo un api desde cero la cual posee:

-Generacion de token JWT a traves de un endpoint de login.
En este punto queria aclarar que los datos de usuarios estan puestos en el aplication.properties.
Lo ideal seria en un ambiente real ponerlos en un secret y tener un secret manager para consumirlo directamente.


-4 endpoints GET los cuales para obtener REGIONES, PAISES, CIUDAD, y CLIMA para una determinada ciudad.

estos 4 endpoints GET deben utilizar un parametro en el HEADER para poder acceder llamado accessToken.
lo mismo que en la generacion del token, el url del api de accuweather + la key deberia ir en un secret

cada enpoint que es llamado guarda una serie de datos en base h2... los dos primeros guardan los dto completos y los ultimos dos se hizo una seleccion ya que era demasiada informacion y considero no tiene mucho sentido guardar todo sin ningun proposito ni contexto.
para acceder por consola http a la base se ingresa a
http://localhost:8080/h2-console/
user=sa
pass=password


tambien se armo una documentacion basica en swagger se accede mediante la url, se pueden probar los servicios desde ahi

http://localhost:8080/swagger-ui/

Tambien dejo una collection de postman (no configure para que rellene el token en todos los llamados, hay que pasarlo a mano)

para correrlo bastaria con bajarse el codigo y levantarlo como spring boot.
