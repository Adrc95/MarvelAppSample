# MarvelAppSample
Este proyecto es una aplicación que permite mostrar la información de los personajes de Marvel utilizando la API abierta de Marvel.

## Funcionalidades
- Permite buscar el personaje por nombre
- Descripción del personaje (En caso de estar disponible)
- Lista de personajes favoritos (Almacenado en Base de Datos Local)
    - Borra un personaje de la lista de personaajes  al deslizar el elemento de derecha a izquierda
- Modo Claro/Oscuro
  
## Configuración del proyecto

Para ejecutar este proyecto, necesitas las claves de la API de Marvel

Entra en este [enlace](https://developer.marvel.com/) y selecciona "Obtener una clave" para obtener la clave.

Establecer las claves de API de Marvel en el archivo `local.properties` añade lo siguiente:

```
PUBLIC_KEY = <Clave pública de la api de Marvel>
PRIVATE_KEY = <Clave privada de la api de Marvel>
```

