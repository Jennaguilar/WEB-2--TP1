## Consumo de API Externa

Este proyecto se integra con la API pública de DummyJSON utilizando `RestClient`. 

`RestClient` es un cliente HTTP síncrono introducido en Spring Framework 6.1. Funciona de manera bloqueante, lo que significa que envía y recibe solicitudes HTTP y espera a que cada una se complete antes de pasar a la siguiente instrucción. Su diseño fluido e intuitivo facilita la interconexión con servicios web externos, permitiendo obtener, procesar y mapear los datos JSON de origen directamente a nuestros Data Transfer Objects (DTOs) de manera eficiente.