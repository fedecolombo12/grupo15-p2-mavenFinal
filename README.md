<h1 align="center"> Grupo 15 - P2 - Maven Final </h1>
<h6 align="center"> Federico Colombo - Yabel Neves </h6>


<p align="center">
  <img src="UMLObligatorio.png" alt="UML Actualizado">
</p>

## <p><span style="color: skyblue;"> Índice </span></p>
* [Actualización de UML](#actualizacion-uml)
* [Procesos de Carga](#descripción)
* [Estructura](#estructura)


## <p><span style="color: skyblue;"> Actualización UML </span></p>
<p> Se agrega a la entidad 'User' los atributos: isVerified, del tipo boolean y userFavourites, del tipo int. </p>
<p> Se agrega a la entidad 'Tweet' el atributo: date, del tipo String. </p>

## <p><span style="color: skyblue;"> Procesos de Carga </span></p>

### Lectura de Drivers
<p align="justify"> 
    Empezamos definiendo la ruta del archivo de conductores en la variable driversFile. Luego utilizamos un bloque 
try-with-resources para abrir y leer el archivo de forma segura, creamos un BufferedReader para leer el archivo y se 
inicia un ciclo while para leer cada línea del archivo.
En cada iteración, se agrega la línea leída (en minúsculas) a la lista enlazada driversLinkedList, cuando el ciclo 
finaliza el bloque try-with-resources cierra automáticamente el BufferedReader. Si ocurre un problema, se tira la 
Excepción IOException. </p>

### Lectura CSV
<p align="justify"> 
    Es un proceso similar al descripto anteriormente, definimos la ruta del archivo CSV en la variable csvFile,
utilizamos un bloque try-with-resources (abrir y leer de forma segura) y creamos un BufferedReader para leer el archivo.
Saltamos la primera fila del archivo que contiene los nombres de las columnas utilizando csvParser.iterator().next(), para comenzar a 
iterar sobre cada fila del archivo utilizando un bucle 'for' y el objeto CSVRecord proporcionado por el CSVParser.
Los valores de cada columna del registro son extraídos y se almacenan en un arreglo 'values'.</p>

#### Procedimiento de Creacion de Tweet, User y HashTag
<p align="justify"> 
La clase ReadCSV comienza con la creación de dos listas enlazadas: userList (User) y tweetList (Tweet)

### Tweet
El método readCSV se inció creando un Tweet al que se le asignan los valores utilizando los índices en el arreglo values y 
una lista enlazada 'hashTagTweet' para almacenar los hashtags del tweet. Se obtiene la columna de hashtags y se verifica si está vacía:
- Si no lo está, el texto se divide en hashtags utilizando 'split()', se crea un 'HashTag' para cada hashtag, que se agregan 'hashTagTweet' y 
finalmente se agrega el Tweet a 'tweetList'.

### User
Se crea User y se asignan los valores utilizando el mismo mecanismo que en Tweet. Se verifica si la 'userList' contiene al usuario:
- Si es así, se obtiene la referencia al usuario existente y se agrega el tweet a su lista de tweets.
- Si no existe, se agrega el tweet a la lista de tweets del usuario y el usuario a la lista userList. 

### Exceptions 
Se capturan y se ignoran las excepciones generales (usando un bloque try-catch vacío) para evitar que loserrores de formato en los valores del 
archivo interrumpan el proceso de carga. Si ocurre alguna excepción de lectura del archivo, se lanza una excepción personalizada FileNotValidException 
con un mensaje de error adecuado.

Memoria: 4.42%
</p>


## Estructura

### Función 1

<p align="justify"> 
En este método se utilizan varios tipos de TADs para realizar el cálculo del 'top10' más activos en cuanto a tweets dependiendo de un año y mes específicos.

- TAD 'TablaHash': se almacena y cuenta el número de ocurrencias de cada piloto en los tweets. Cada piloto es almacenado como una clave y 
el número de ocurrencias se guarda como el valor asociado a esa clave. Este TAD ofrece un acceso rápido a los elementos y es eficiente para 
la búsqueda y actualización de valores.

- TAD 'ListaEnlazada': Se almacenan los pilotos en orden descendente según su número de ocurrencias, se realiza mediante la implementación de un Heap máximo 'MyHeapImpl'
que se basa en una lista enlazada. La lista enlazada permite una inserción eficiente de elementos y un acceso secuencial para obtener los diez pilotos más activos.

La combinación de los TADs utilizados, permite realizar cálculos eficientes para obtener los diez pilotos más activos. 
La 'TablaHash' agiliza la recopilación de ocurrencias y la 'ListaEnlazada' facilita la extracción de los máximos valores para obtener los pilotos más activos. 

Tiempo de ejecución aprox: 0seg.</p>


### Función 2


<p align="justify"> 
Se utiliza un TAD 'Heap' para obtener eficientemente los 15 usuarios con más tweets. El heap garantiza una inserción y extracción eficientes, 
y se inicializa con el tamaño de la lista de usuarios para un mejor rendimiento. Almacenando los usuarios en el 'Heap', se pueden obtener 
rápidamente los usuarios con más tweets. Además, la flexibilidad y eficiencia del 'Heap' proporciona beneficios para futuras modificaciones y requisitos del sistema.

Tiempo de ejecución aprox: 32seg.
Memoria: 21.65% </p>

### Función 3

<p align="justify"> 
Utilizamos TAD 'ListaEnlazada', debido a su eficiencia para agregar y eliminar elementos (importante porque no se sabe cantidad de hashtags), 
para almacenar los hashtags diferentes encontrados en los tweets de un día específico. La lista proporciona también flexibilidad en el tamaño, 
ya que no es necesario especificar un tamaño máximo de antemano y acceso a los elementos en un orden secuencial, lo cual es útil para verificar 
la presencia de un hashtag antes de agregarlo. 

Tiempo de ejecución aprox: 96seg.
Memoria: 33.82% </p>

### Función 4

<p align="justify">
Utilizamos una TAD 'Hash' para contar eficientemente los hashtags más utilizados en tweets dentro de un rango de fecha específico, lo que proporciona un 
acceso rápido a los hashtags y reduce la complejidad. El uso de la 'TablaHash' permite un acceso eficiente a los elementos mediante una función de hash, 
mejorando la eficiencia en la búsqueda y actualización de los hashtags. La capacidad inicial se establece en 50, lo que proporciona un buen rendimiento 
en términos de factor de carga y evita reasignaciones innecesarias de memoria. Se utilizan bucles while anidados para recorrer los tweets y los hashtags 
de cada tweet de manera flexible y procesar los datos relevantes.
OBS: Se agrega función auxiliar notF1 para filtrar los hashtags no deseados, en este caso, aquellos iguales a la palabra prohibida "f1". 

Tiempo de ejecución aprox: 0seg. </p>

### Función 5

<p align="justify">
Para la función se utiliza el TAD 'Heap' que como ya mencionamos permite realizar operaciones de inserción, extracción y búsqueda eficientes del elemento máximo o mínimo. 
La eficiencia del 'Heap' se debe a que las operaciones de inserción y extracción se realizan en tiempo logarítmico (O(log n)), donde n es el tamaño, 
garantizando un rendimiento mejor para grandes cantidades de usuarios.

Se elige un tamaño inicial que va a ser el tamaño de 'userList' lo que evita reasignaciones innecesarias de memoria y mejora el rendimiento.
Los usuarios con más favoritos se almacenan en un arreglo de tamaño 7 llamado 'top7', lo que permite un acceso rápido y garantiza un orden específico, pudiendo
así obtener rápidamente los usuarios con más favoritos sin tener que ordenar toda la lista de usuarios. 

Tiempo de ejecución aprox: 28seg.
Memoria: 20.07% </p>

### Función 6

<p align="justify"> 
Se usa tipo de datos Scanner para leer la entrada del usuario desde la consola.
Utilizamos un bucle 'while' ya que permite tener un mayor control sobre el índice de iteración, 
en este caso representado por la variable i. Se inicializa la variable i dentro del 'while', de modo que no 
se realiza una inicialización redundante antes de entrar al bucle, lo que deriva en un ahorro de algunos ciclos de ejecución 
y genera una mejor eficiencia del código.

Se usa 'toLowerCase()' en las comparaciones de cadenas. Al convertir tanto el contenido del tweet como la palabra ingresada 
por el usuario a minúsculas, se logra una búsqueda de texto insensible entre mayúsculas y minúsculas, lo que permite encontrar coincidencias.
La 'ListaEnlazada' es adecuada para este tipo de búsqueda, ya que se puede recorrer secuencialmente cada elemento de la lista.

Tiempo de ejecución aprox: 65seg.
Memoria: 19.68% </p>


### Funciones Agregadas

<p align="justify"> 
En los métodos que se solicita al usuario ingresar fechas por consola, se crearon dos métodos auxiliares los cuales son: 
'verificarFechaEnRango' y 'verificarFechaEnRangoYM' en los cuales se chequea que la 
fecha ingresada se encuentre en el rango establecido por la letra (entre Julio de 2021 y Agosto de 2022), 
lanzando una exception, 'WrongDate' en caso de que no se cumpla.
</p>
