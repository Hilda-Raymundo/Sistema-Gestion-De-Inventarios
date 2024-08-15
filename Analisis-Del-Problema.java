//Análisis del Problema
'
1.	¿Cuáles son las entradas? (Las entradas se encuentran marcadas)
    Para la seguridad:
        o	Ingresar nombre y contraseña (para saber cuál es su rol y lo que puede hacer)
        o	Roles: Administrador, vendedor, encargado de bodega
Para el Menú Principal: 
•	El usuario debe seleccionar qué es lo que quiere hacer (Gestionar productos, Controlar existencias, Pedidos de compra, Informes y estadísticas)
Para Gestionar Productos:
o	Dentro de “Gestionar productos”, el usuario debe seleccionar dónde quiere ir:
	Categorías de productos: 
•	El usuario puede seleccionar: Agregar nueva categoría, para lo que necesita ingresar Nombre y Descripción de la categoría 
•	El usuario puede seleccionar: Modificar una categoría existente, para lo que necesita ingresar Nombre y Descripción de la categoría 
•	El usuario puede seleccionar: Eliminar una categoría existente, para lo que necesita ingresar Nombre de la categoría 
	Características de productos:
•	El usuario puede seleccionar: Agregar nueva característica, para lo que necesita ingresar Nombre y Descripción(opcional) de la característica 
•	El usuario puede seleccionar: Modificar una característica existente, para lo que necesita ingresar Nombre y Descripción de la característica 
•	El usuario puede seleccionar: Eliminar una característica existente, para lo que necesita ingresar Nombre de la característica
	Especificaciones de productos:
•	El usuario puede seleccionar: Agregar nueva especificación, para lo que necesita ingresar Nombre, Descripción(opcional), tipo de dato (texto, número, fecha, etc.) de la especificación 
•	El usuario puede seleccionar: Modificar una especificación existente, para lo que necesita ingresar Nombre, Descripción y tipo de dato de la especificación
•	El usuario puede seleccionar: Eliminar una especificación existente, para lo que necesita ingresar Nombre de la especificación
	Crear un nuevo producto:
•	Para crear un nuevo producto el usuario debe ingresar: nombre, categoría, característica, especificación, valor para cada característica, valor por cada especificación
•	Para modificar un producto existente el usuario debe ingresar: categoría, característica, especificación (Para seleccionar el producto debe ingresar su nombre) 
	Alta de productos:
•	Para dar alta a un producto el usuario debe ingresar nombre, seleccionar la categoría, también puede ingresar: descripción del producto, características del producto, precio de venta, cantidad inicial de stock
	Baja de productos:
•	En esta sección solo se selecciona el nombre del producto, no se ingresa nada manualmente.
	Modificación de productos:
•	El usuario selecciona el nombre del producto de una lista de todos los productos. El usuario ingresa los nuevos datos: nombre, categoría, característica, especificación, valor para cada característica, valor por cada especificación
      Para Controlar Existencias:
o	Entrada de productos:
	Ingresar código de producto, cantidad que se desea ingresar.
o	Salida de productos:
	Ingresar código de producto, cantidad que se desea vender, fecha de venta.
o	Historial
	Selecciona un producto de la lista de productos.
      Para Pedidos de Compra:
o	Creación de pedido:
	Selecciona el proveedor, los productos. Ingresa la cantidad de cada producto.
o	Gestión de pedidos:
	Ingresa/selecciona proveedor, fecha, estado o producto. (Solo para buscar/filtrar)
	Selecciona un pedido para ver sus detalles.
	Si desea modificar, ingresar el estado del pedido.
o	Recepción de pedido:
	Selecciona un pedido de la lista de pedidos pendientes.
	Ingresar la cantidad de productos recibidos de cada producto solicitado.
             Para Informes y estadísticas:
o	Generar informes de inventario actual
	Filtrar informes por categoría de producto.
	Ordenar filtrado por diferentes criterios (nombre, cantidad, valor total del stock)
o	Generar informes de movimientos de stock
	Seleccionar producto, filtrar por fecha.
o	Generar informes de compra
	Seleccionar producto, filtrar por fecha.
o	Ver Gráficas y estadísticas(De los 3 puntos anteriores):
	Filtrar por producto, categoría de producto, proveedor o rango de fechas.
2.	¿Cuál es la salida esperada?

Para la seguridad: Registrar las actividades de los usuarios(nombre, fecha de acceso, salida del sistema, por cada vez que ingrese y salga)
Para Gestionar Productos:
o	Dentro de “Gestionar productos”:
	Categorías de productos: 
•	Lista de categorías existentes.
•	Guardar la nueva categoría.
•	Confirmación de eliminación.
•	Eliminar la categoría del archivo de texto y las asociaciones entre la categoría y los productos que pertenecían a ella.
	Características de productos:
•	Lista de características existentes.
•	Guardar la nueva característica.
•	Confirmación de eliminación.
•	Eliminar la característica del archivo de texto y las asociaciones entre la categoría y los productos que pertenecían a ella.
	Especificaciones de productos:
•	Lista de Especificaciones existentes.
•	Guardar la nueva especificación.
•	Confirmación de eliminación.
•	Eliminar la especificación del archivo de texto y las asociaciones entre la categoría y los productos que pertenecían a ella.
	Crear un nuevo producto:
•	Guardar producto.
•	Modificar producto.
	Alta de productos:
•	Generar un identificador único para cada producto creado.
•	Guardar el producto.
•	Mostrar un mensaje “Producto creado correctamente”
	Baja de productos:
•	Mostrar confirmación para eliminación.
•	Mostrar mensaje “Producto eliminado correctamente”
	Modificación de productos:
•	Mostrar datos actuales del producto.
•	Guardar cambios.
•	Mostrar mensaje “Producto modificado correctamente”
      Para Controlar Existencias:
o	Entrada de productos:
	Si el producto no existe, mostrar mensaje “Producto no registrado”
	Si el producto existe, mostrar mensaje “Ingrese la cantidad de productos”
	Actualizar cantidad de stock.
	Mostrar mensaje “Entrada de productos realizada correctamente”
o	Salida de productos:
	Si el producto no existe, el sistema debe mostrar un mensaje al usuario indicando que el producto no está registrado.
	Si el producto existe, el sistema debe solicitar al usuario la cantidad de productos que desea vender.
	Si la cantidad de productos es mayor que la cantidad de stock disponible, el sistema debe mostrar un mensaje al usuario indicando que no hay suficiente stock para realizar la venta.
	Si la cantidad de productos es menor o igual a la cantidad de stock disponible, el sistema debe actualizar la cantidad de stock del producto en el archivo de texto de acceso secuencial.
	El sistema debe mostrar un mensaje al usuario indicando que la venta se ha realizado correctamente
o	Alertas de stock bajo
	El sistema debe verificar el nivel de stock de cada producto periódicamente.
	Si el nivel de stock de un producto es menor o igual al punto de reorden, el sistema debe generar una alerta de stock bajo.
	La alerta de stock bajo debe ser mostrada al usuario por medio de una notificación.
	La alerta debe incluir el nombre del producto, el nivel de stock actual y el punto de reorden.
o	Historial
	Mostrar historial de movimientos de stock del producto seleccionado(fecha, hora, entrada o salida, cantidad de productos)
      Para Pedidos de Compra:
o	Creación de pedido:
	Calcular total de pedido.
	Confirmar pedido.
	Guardar pedido.
	Generar código para cada pedido.
	Mostrar mensaje “Pedido creado correctamente”
o	Gestión de pedidos:
	Mostrar todos los pedidos de compra creados.
	Filtrar según el criterio ingresado.
	Mostrar detalles de pedido (proveedor, fecha de creación, fecha de entrega estimada, productos solicitados, cantidad de producto, total del pedido.)
	Cancelar pedido si aún no ha sido enviado.
o	Recepción de pedido:
	Mostrar lista de pedidos pendientes.
	Si la cantidad de productos recibidos es mayor que la cantidad solicitada, el sistema debe mostrar un mensaje al usuario indicando que hay un exceso de productos.
	Si la cantidad de productos recibidos es menor que la cantidad solicitada, el sistema debe mostrar un mensaje al usuario indicando que faltan productos.
	Si la cantidad de productos recibidos es igual a la cantidad solicitada, el sistema debe actualizar el estado del pedido a "completado".
	El sistema debe actualizar la cantidad de stock de los productos recibidos.
	El sistema debe generar un registro de recepción de productos, incluyendo la fecha, la hora, el pedido de compra, los productos recibidos y la cantidad de cada producto.
             Para Informes y estadísticas:
o	Generar informes de inventario actual
	Mostrar el inventario actual de todos los productos(nombre, categoría, cantidad de stock disponible, valor total del stock(precio*cantidad) y punto de reorden)
	Mostrar informe según criterios filtrados.
	Exportar informe a un CSV
o	Generar informes de movimientos de stock
	Generar informe en base a un rango filtrado. (fecha, hora, operación realizada de entrada o salida, cant de productos y usuario que realizó la operación.)
	Exportar informe a un CSV
o	Generar informes de compra
	Generar informe de compras realizadas a un proveedor filtrado en un rango de fechas filtradas.(fecha de compra, número de pedido, nombre del producto, cantidad comprada, precio unitario y total)
	Exportar el informe a un CSV.
o	Ver Gráficas y estadísticas(De los 3 puntos anteriores):
	Mostrar gráficas y estadísticas de los 3 puntos anteriores.
	Mostrar “ filtrados por producto, categoría, producto, proveedor o rango de fechas.
	Mostrar detalles de la gráfica y estadística seleccionada.

3.	¿Qué método convierte las entradas en la salida esperada?
Para la seguridad: 
	Insertar datos en el archivo de texto secuencial.
Para Gestionar Productos:
o	Dentro de “Gestionar productos”:
	Categorías de productos: 
•	Buscar en el archivo de texto secuencial coincidencias con los datos ingresados.
•	Insertar en el archivo de texto secuencial.
•	Después de la confirmación del usuario, eliminar de el archivo de texto secuencial.
	Características de productos:
•	Buscar en el archivo de texto secuencial coincidencias con los datos ingresados.
•	Insertar en el archivo de texto secuencial.
•	Después de la confirmación del usuario, eliminar de el archivo de texto secuencial.
	Especificaciones de productos:
•	Buscar en el archivo de texto secuencial coincidencias con los datos ingresados.
•	Insertar en el archivo de texto secuencial.
•	Después de la confirmación del usuario, eliminar de el archivo de texto secuencial.
	Crear un nuevo producto:
•	Insertar en el archivo de texto secuencial.
•	Modificar en el archivo de texto secuencial.
	Alta de productos:
•	
	Baja de productos:
	Modificación de productos:
      Para Controlar Existencias:
o	Entrada de productos:
o	Salida de productos:
o	Alertas de stock bajo
o	Historial
      Para Pedidos de Compra:
o	Creación de pedido:
o	Gestión de pedidos:
o	Recepción de pedido:
Para Informes y estadísticas:
o	Generar informes de inventario actual
o	Generar informes de movimientos de stock
o	Generar informes de compra
o	Ver Gráficas y estadísticas(De los 3 puntos anteriores):.
4.	Requerimientos y/o restricciones adicionales
Para la seguridad: 
Para Gestionar Productos:
o	Dentro de “Gestionar productos”:
	Categorías de productos: 
	Características de productos:
	Especificaciones de productos:
	Crear un nuevo producto:
	Alta de productos:
	Baja de productos:
	Modificación de productos:
      Para Controlar Existencias:
o	Entrada de productos:
o	Salida de productos:
o	Alertas de stock bajo
o	Historial
      Para Pedidos de Compra:
o	Creación de pedido:
o	Gestión de pedidos:
o	Recepción de pedido:
             Para Informes y estadísticas:
o	Generar informes de inventario actual
o	Generar informes de movimientos de stock
o	Generar informes de compra
o	Ver Gráficas y estadísticas (De los 3 puntos anteriores):
'