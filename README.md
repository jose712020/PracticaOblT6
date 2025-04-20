# **Indíce**
  

0.  **Instalación.**

-  **MENÚ PRINCIPAL.**

1. **QUIENES SOMOS**

2. **FUNCIONAMIENTO DEL MENÚ PRINCIPAL.**

3. **MENÚ CLIENTE.**

- **CONSULTAR EL CATÁLOGO DE PRODUCTOS**

- **REALIZAR UN PEDIDO**
  
- **VER MIS PEDIDOS**

- **VER MIS DATOS PERSONALES**

- **MODIFICAR MIS DATOS PERSONALES**

- **SALIR**

4. **MENÚ TRABAJADOR.**

- **CONSULTAR LOS PEDIDOS QUE TENGO ASIGNADOS**

- **MODIFICAR EL ESTADO DE UN PEDIDO**

- **CONSULTAR EL CATÁLOGO DE PRODUCTOS**

- **MODIFICAR UN PRODUCTO**

- **VER EL HISTÓRICO DE PRODUCTOS TERMINADOS**

- **VER MI PERFIL**

- **MODIFICAR MIS DATOS PERSONALES**

- **CERRAR SESIÓN**

5. **MENÚ DE ADMINISTRADOR.**

- **VER TODO EL CATALOGO**

- **EDITAR UN PRODUCTO DEL CATALOGO**

- **VER EL RESUMEN DE TODOS LOS CLIENTES**

- **VER EL RESUMEN DE TODOS LOS PEDIDOS**

- **VER EL RESUMEN DE TODOS LOS TRABAJADORES**

- **VER ESTADÍSTICAS DE LA APP**

- **CAMBIAR EL ESTADO DE UN PEDIDO**

- **DAR DE ALTA UN TRABAJADOR**

- **DAR DE BAJA UN TRABAJADOR**

- **ASIGNAR UN PEDIDO A UN TRABAJADOR**
  
- **SALIR**

6. **CAMBIOS Y FUNCIONALIDADES NUEVAS**


## **0. INSTALACIÓN**
-Lo primero que vamos ha hacer es instalar el archivo .jar del programa desde Google Drive [aquí](https://drive.google.com/file/d/17L9t4K1SB4Jgq2fdjiYfEImc2iACYx0X/view?usp=sharing)

-Para poder usar nuestro programa tenemos que instalar la versión mas reciente del JDK (Java Development Kit) [click aquí para descargar](https://download.oracle.com/java/24/latest/jdk-24_windows-x64_bin.exe). Durante la instalación tenemos que asegurarnos de que esté instalado en **"C:\Program Files\Java"**, después tenemos que entrar en el **"Panel de Control"** (para entrar podemos meternos en "Configuración" y buscamos panel del control)

![image](https://github.com/user-attachments/assets/142e6ab5-3d75-49d5-b6e7-56d8e87320cd)


(También se puede dandole a la tecla Windows y escribir "Panel de Control")
![image](https://github.com/user-attachments/assets/c73cd836-d74e-4ed9-a4c7-757474680b84)

Al abrir el Panel de Control le daremos al botón de buscar donde escribiremos **"Variables"** y después pincharemos en el **"Editar las variables de entorno del sistema"**
![image](https://github.com/user-attachments/assets/989b2490-4316-4cd5-b08b-59a434ca3b67)

Se nos abrirá la siguiente pestaña y le daremos a **"Variables de entorno..."**

![image](https://github.com/user-attachments/assets/45a4f94e-5035-42a8-ba7b-67b8031e67b7)


-En la sección Variables del sistema busque la variable de entorno **PATH** y selecciónela. Haga clic en Editar. Si no existe la variable de entorno PATH haga clic en Nuevo.

-En la ventana **Editar la variable del sistema** (o **Nueva variable del sistema**), debe especificar el valor de la variable de entorno PATH. Haga clic en **Aceptar**. Cierre todas las demás ventanas haciendo clic en **Aceptar**.

-Para la comprobación le daremos a la letra Windows + R y se nos abrirá la pestaña **Ejecutar** y escribiremos el comando **cmd** y le daremos al botón de **Aceptar.**

![image](https://github.com/user-attachments/assets/6a6f6ac9-57ee-4f29-bdd5-757a992775b8)

-Para ver que todo es correcto escribimos el comando **"javac -version"**, si todo sale bien nos saldrá la versión 24
![image](https://github.com/user-attachments/assets/bf22037e-f064-4747-96fa-6eca49319485)

-Para ejecutar nuestro programa nos iremos al enlace de GitHub [aquí](https://github.com/jose712020/PracticaOblT6). Después le daremos a **"<> Code"** donde se desplegará varias opciones, y le daremos a **"Download ZIP"**

![image](https://github.com/user-attachments/assets/54504807-d4e3-4821-b3d5-0eeb366a2622)

-Al descargarlo, extraemos la carpeta en la ubicación donde más te convenga, al tenerla extraida nos meteros en la carpeta extraida y nos saldrá lo siguiente. Antes de ejecutar el **"PracticaOblT6.bat"**, tendremos que haber añadido el archivo jar
del programa al principio de este punto. 

![image](https://github.com/user-attachments/assets/0ab1ef01-8eb5-4b0b-b79c-c379527cb5fb)
![image](https://github.com/user-attachments/assets/92105ad5-7bd8-4a71-980d-dc738885ca10)

Una vez descargado, añadiremos al archivo a la carpeta del proyecto donde este el ejecutable .bat y lo ejecutaremos haciendo doble click o dandole click derecho al ratón y dandole a la opción "Abrir":

![image](https://github.com/user-attachments/assets/71c3d4be-36da-480c-a8aa-be80a73ee51f)

![image](https://github.com/user-attachments/assets/4bcec1fd-e4e8-498e-90ef-b546b73af513)


(En caso de que nos salga un pantallazo azul de Windows le daremos a donde ponga más información y le daremos a ejectucar de todas maneras)

![image](https://github.com/user-attachments/assets/bc565971-c5b4-408f-b63d-56adfaf14d26)

![image](https://github.com/user-attachments/assets/3a1bebc8-85ec-49d0-babc-ca5c12cf593d)

### **MENÚ PRINCIPAL**

- Finalmente tendremos nuestro programa en funcionamiento:

Principalmente, el programa nada mas iniciar nos avisará si queremos usar datos de prueba en el software, se deberá elegir la opción S o N, aunque no necesariamente en mayúsculas:

![image](https://github.com/user-attachments/assets/5ac49b8c-79d2-48a2-a84e-c72132efa6d6)

Si le damos a la opción "N" pasará directamente al menú principal:

**(IMAGEN NUEVA AQUÍ)**

Si le damos al opción "S" nos dirá los datos de prueba para el programa:

![image](https://github.com/user-attachments/assets/684ea8de-e064-41ce-a837-e9e5f8fd9eab)

Y después volverá al menu principal, mostrado anteriormente

## 1. **QUIENES SOMOS**

Somos un programa que gestionará una tienda online.

- Gestionaremos **tres perfiles** donde tendremos el perfil usuario **"Cliente"**, un perfil **"Trabajador"**, y un perfil **"Administrador"**.

## 2. **FUNCIONAMIENTO DEL MENÚ PRINCIPAL.**

Está es la entrada del programa donde tendremos 3 opciones en nuestro menú principal, donde la **opción 1** será el **Ver el catalogo**, donde se podrá ver todos los productos de nuestra tienda de 5 en 5 sin iniciar sesión:

![image](https://github.com/user-attachments/assets/186172c9-3995-4647-ba41-70675b21e85e)
![image](https://github.com/user-attachments/assets/54a3b803-166d-44cc-af45-c4df81ae4e46)

- La **opción 2** será **Registrarse**, donde se registrarán los clientes:

![image](https://github.com/user-attachments/assets/8779fdad-6fea-499d-add3-89319401c756)

- La **opción 3** será **Iniciar sesión**, donde podrán iniciar sesión cualquiera de los 3 perfiles, después dependerá si introduce sus datos correctos:

![image](https://github.com/user-attachments/assets/cd3ab1c7-eaa8-4cd1-a319-dfa782de2d41)

## 3. **MENÚ CLIENTE**

- Para acceder tendremos que registrarnos primero en el menú principal en **"Registrarse"**, una vez hecho eso habremos registrado un cliente. Nos avisa primero si estamos realmente seguros de registrarnos, en el caso de que nos hayamos equivocado:

![image](https://github.com/user-attachments/assets/105bdfae-24bb-4bd2-a7e7-1224ab083b3b)

Si no es el caso y le damos "S" comenzarán las instrucciones de inicio de sesión:
![image](https://github.com/user-attachments/assets/fb43f3f1-ac88-4871-803b-56d181cdcb82)

- Una vez registrados, antes nos pedirá un token para validar la sesión de nuestra cuenta al correo que hemos introducido:

![image](https://github.com/user-attachments/assets/d53f7471-ec99-44c0-9001-68b2e4ad214b)

Si el token introducido es correcto nos dejará entrar:

![image](https://github.com/user-attachments/assets/10308bb6-985a-494e-b4e9-f60b9f333421)

![image](https://github.com/user-attachments/assets/77f89bf8-5858-4271-9c81-aa43388bca20)

## **CONSULTAR EL CATÁLOGO DE PRODUCTOS**

- Esta opción nos permitirá ver todos los productos de nuestra tienda sin inicio de sesión. Se desplegará un menú de distintos tipos de métodos de búsqueda:

![image](https://github.com/user-attachments/assets/e72cec3d-600c-4736-8a94-94f1814a8fde)

Si pulsamos la opción ver todo el catñalogo, saldrá todo el catálogo tal y como en la primera opción del menu principal:

![image](https://github.com/user-attachments/assets/8796b5d1-0ea7-45e5-9835-751e51113ff9)

Las opciones siguientes son busquedas concretas por marca, modelo, descripción, término y precio

**Por marca**

![image](https://github.com/user-attachments/assets/277de2fa-7937-403f-a604-b3faa11199dc)

**Por modelo**

![image](https://github.com/user-attachments/assets/c6506ac1-19bf-4b81-8599-5254f52ce97a)

**Por descripción**

![image](https://github.com/user-attachments/assets/ad49681a-acb6-427a-b838-a10a0e30f17a)

**Por término**

![image](https://github.com/user-attachments/assets/4063b098-4a1d-44f5-865e-1d1ac3706417)

**Por precio**

![image](https://github.com/user-attachments/assets/2946be4c-1b59-4618-8bc4-ecae12e764cb)

- Por último la opción de salir, donde se vuelve al menú principal:

![image](https://github.com/user-attachments/assets/b1c01aec-6107-473c-a204-1b6d490124b6)

## **REALIZAR UN PEDIDO**

- Realizaremos un pedido con los productos de la tienda, meteremos el producto del catálogo para añadirlo a la cesta (el tamaño de la cesta no tiene límite, aunque no se pueden poner productos duplicados). Para ello tenemos el siguiente menú:

![image](https://github.com/user-attachments/assets/3b41dd7a-3109-4793-bc28-793c0f9df51c)

**Inserta un producto en el carro**

Los productos se insertan uno a uno con un menú resumido de todos los productos del catálogo y se insertan poniendo el número que los define la principio de cada uno

![image](https://github.com/user-attachments/assets/3770a711-53f1-4832-a8a3-ac287ba8d197)

**Ver el carro**

Mostrar los productos totales disponibles en el carro y el precio total de todo el carro, salen todos los datos destacados del producto y la cantidad de cada uno entre paréntesis:

![image](https://github.com/user-attachments/assets/d103683d-92bd-45ca-98b0-c88ee261d8b0)

**Eliminar un producto del carro**

Nos saldrán los productos del carrito de forma enumerada

![image](https://github.com/user-attachments/assets/2b0d6f11-8f4a-4c76-88bf-62210f0ff920)

Elegiremos el producto a eliminar mediante el número de enumeración, y se eliminará con éxito

![image](https://github.com/user-attachments/assets/9cce9c9b-4569-4899-9bf4-8ab4158ff337)

Podemos ver que ha funcionado: 

![image](https://github.com/user-attachments/assets/8ce0555a-9b82-4214-a4e3-2c825d1904c7)

**Confirmar pedido**

Nos saldra una ventana como esta:

![image](https://github.com/user-attachments/assets/461f5874-b201-4769-96d7-99e0ee0d6488)

Si le damos a "N" cancelará la opción de realizar pedido y volvera de nuevo al menú principal de realizar pedido

![image](https://github.com/user-attachments/assets/dd3d6845-b6b1-4138-8064-9599eca3a695)

Si le damos a "S" el envió se realizará con éxito según los productos que tengamos en el carrito y nos enviará un correo con todos los productos que hayamos pedido

![image](https://github.com/user-attachments/assets/a0509f28-03b9-4475-96bc-b31b1bca3016)

![image](https://github.com/user-attachments/assets/f274ceb2-d0c8-418f-9f3b-c67e0a221b9b)

**Cancelar pedido**

Nos saldrá un mensaje como este:

![image](https://github.com/user-attachments/assets/382834dd-ccc5-4d64-a23e-e08388418fe6)

Si entroducimos "N", volveremos al menú de realización del pedido y nuestros productos seguirán en el carrito:

![image](https://github.com/user-attachments/assets/77af2306-aaff-4da7-b5d4-774c852ec61f)

![image](https://github.com/user-attachments/assets/0ba57bb5-b1e0-4f1d-ab1a-5c11951ded69)

Si le damos a "S" se cancelará el pedido con éxito y se borrarán todos nuestros productos introducidos en el carrito

![image](https://github.com/user-attachments/assets/66db6c85-6bee-4097-8a96-6d3469cb1218)

Podemos ver por esta captura que ha funcionado:

![image](https://github.com/user-attachments/assets/2ff912a4-f207-4261-82c1-18a50ec1a5b8)

**Salir**

Con esta opción volveremos al menú principal del cliente

![image](https://github.com/user-attachments/assets/3d1b0ecb-5db0-4646-8109-79a32bf7304a)

## **VER MIS PEDIDOS**

- Podremos ver los pedidos que hayamos realizado.

![image](https://github.com/user-attachments/assets/cc93243e-3149-41f9-8c62-89a392ff519a)

(En caso de que no hayamos pedido nada)

![image](https://github.com/user-attachments/assets/0d1d3bc2-8a4a-4e8d-b828-06f9735d5e89)

## **VER MIS DATOS PERSONALES**

- Muestra los datos personales del cliente, introducidos durante el registro

![image](https://github.com/user-attachments/assets/0fdfeaa4-01cb-4aa0-baf4-b6e5b39c3f0a)

## **MODIFICAR MIS DATOS PERSONALES**

- Cambiar los datos personales del cliente

![image](https://github.com/user-attachments/assets/baee6d19-6c3b-49ed-b41e-de90bb043d97)

- Nos volverá a pedir un token tanto si hemos puesto un correo nuevo o no:

![image](https://github.com/user-attachments/assets/3f0a535f-df67-47c8-aba1-7705c575a17b)

![image](https://github.com/user-attachments/assets/264e4383-4d61-41e0-ab20-5dacb91721c2)

![image](https://github.com/user-attachments/assets/8240fe09-ad16-42ed-8367-b115cc3827e1)

- Si vemos de nuevo los datos, vemos que ha funcionado

![image](https://github.com/user-attachments/assets/237329d4-9fa3-45df-b190-a11657f8ff79)

## **SALIR**

- Salir del menú de cliente, cerrar sesión y volver al menú principal

![image](https://github.com/user-attachments/assets/bb036f4a-b5cf-4e91-aaa8-91e7a6d9ba8f)

## 4. **MENÚ TRABAJADOR.**

El trabajador deberá ser creado por el administrador previamente, pero lo veremos en el apartado de administrador después

- El trabajador se registrará a traves de la opción de iniciar sesión, igual que el cliente:

![image](https://github.com/user-attachments/assets/dda8cf11-7b24-4642-9fc0-2d731cb61d49)

- Una vez la sesión iniciada, tendremos las siguiente opciones:

![image](https://github.com/user-attachments/assets/8ce35689-1ec0-41a8-90c8-af7968b862dd)

## **CONSULTAR LOS PEDIDOS QUE TENGO ASIGNADOS**

- Aquí el trabajador mira los pedidos que le han asignado automáticamente o mediante la administración. Para demostrar su funcionamiento, haremos que nuestro cliente de prueba haga un pedido y haremos que se asigne automáticamente o mediante el administrador (se explicará después en el apartado de administrador)

Una vez el pedido realizado y asignado automáticamente o mediante el administrador, volveremos a iniciar sesión en el trabajador cuyo pedido ha sido asignado y le daremos a la opción 1, nos saldrán todos los pedidos asignados, aunque en este caso solo haya uno:

![image](https://github.com/user-attachments/assets/3183cd40-57cd-409c-8971-ab69f2fff32c)
(En el caso de que hayan pedidos)

![image](https://github.com/user-attachments/assets/6ebc9908-2605-477d-98b0-921d7cc25dce)
(En el caso de no exista ningún pedido)

## **MODIFICAR EL ESTADO DE UN PEDIDO**

- Saldrán los pedidos de forma enumerada y tendremos que poner el número de enumeración que nos aparece:

![image](https://github.com/user-attachments/assets/25c3bd84-3b9d-41b9-9eba-bc156fc63d3c)

**Modifica el estado**

Nos preguntará si queremos modificar el estado del pedido, si le decimos "n" pasará a la siguiente pregunta todo el rato:

![image](https://github.com/user-attachments/assets/74758657-5b34-45bc-bdfb-967d238b5cda)

- Si le decimos "s" a la primera pregunta, saldrá un menú para elegir el nuevo estado:

![image](https://github.com/user-attachments/assets/20253ca4-b9a3-4b34-823d-192bb4bca5b6)

- Elegimos un nuevo estado para el pedido:

![image](https://github.com/user-attachments/assets/80620efe-a8c6-41f2-a6dc-594a5ed63373)

**Añade comentario**

Saldrá después de añadir el estado nuevo, primero nos preguntará si queremos añadir el comentario o no

![image](https://github.com/user-attachments/assets/5397f4d9-f07e-4e15-aeb6-81a65436e8bf)

- Si le decimos "s", saldrá un apartado para poner el comentario al pedido seleccionado:

 ![image](https://github.com/user-attachments/assets/2c2fd0cb-ee32-4883-bbed-94106492d51e)

 **Modificar fecha de entrega**

Saldrá después de añadir el comentario, primero nos preguntará si queremos modificar la fecha o no

![image](https://github.com/user-attachments/assets/3c14a570-55d2-4204-818a-aa2c5dd32aca)

- Si le decimos "s", empezarán a salir solicitudes por teclado del día, el mes y el año de entrega:

![image](https://github.com/user-attachments/assets/cd229d40-ae48-4d3b-8d2e-73d23ca7f86f)

Una vez terminados todos los cambios en el pedido, le enviarán un correo al cliente que realizó el pedido con los cambios que, podemos ver que son los correctos:

![image](https://github.com/user-attachments/assets/2bdfd628-d573-4e9a-91b6-9ed1e9cc65c4)

Si volvemos a la cuenta del cliente, podemos ver con esta captura que ha funcionado:

![image](https://github.com/user-attachments/assets/d6b018d8-4bd7-4cab-baac-10f65e20ed0a)

## **CONSULTAR EL CATÁLOGO DE PRODUCTOS**

Si pulsamos esta opción, saldrá todo el catálogo tal y como en la primera opción del menu principal:

![image](https://github.com/user-attachments/assets/8796b5d1-0ea7-45e5-9835-751e51113ff9)

## **MODIFICAR UN PRODUCTO**

- Por seguridad, se pedirá la ID del producto para poder modificarlo:

![image](https://github.com/user-attachments/assets/e98b0fa5-9de9-4a4e-abb9-144e6e2bd951)

- Vamos a probar con este producto:

![image](https://github.com/user-attachments/assets/81f0eb78-5dda-459d-be34-a6c86b5f79ff)

- Ponemos los datos nuevos:

![image](https://github.com/user-attachments/assets/0178bdfe-2fbf-4243-b371-adc87c6780b5)

- Vemos con esta captura que funciona:

![image](https://github.com/user-attachments/assets/f3108129-004a-4b2f-8bdc-133e9b1be11b)

## **VER EL HISTÓRICO DE PEDIDOS TERMINADOS**

- Aquí podremos ver un resumen de todos los pedidos que han sido terminados de cambiar estado y comentarios

![image](https://github.com/user-attachments/assets/a9a31f3e-4ddf-4e38-9053-e0cb7fd8271a)

## **VER MI PERFIL**

- Vemos los datos personales del trabajador

![image](https://github.com/user-attachments/assets/a9163b68-773d-4f2c-9859-ce62f584f354)

## **MODIFICAR MIS DATOS PERSONALES**

- Aquí modificamos los datos personales del trabajador

![image](https://github.com/user-attachments/assets/d7770594-14a6-40c6-8ea0-e0e3a2f88948)

![image](https://github.com/user-attachments/assets/53fb3bd3-7cd6-4688-833c-36a6ed8f0582)
(Vemos que ha funcionado)

## **SALIR**

- Cerramos sesión y volvemos al menu principal

![image](https://github.com/user-attachments/assets/6b39aeaf-6311-41cc-8474-3a3da6b12342)

## 5. **MENÚ ADMINISTRADOR.**

Para acceder al administrador lo haremos desde iniciar sesión en el menu principal añadiendo sus datos:

![image](https://github.com/user-attachments/assets/98c8c791-21e2-44eb-9f13-69af65675809)

El administrador es el gestor de nivel más alto de todo el programa, podemos verlo solo con el menú del mismo:

![image](https://github.com/user-attachments/assets/2f0204e6-eed7-455b-b60c-dfc5abc6f10e)

- Podemos ver las estadísticas de la APP de forma general y global

## **VER TODO EL CATALOGO**

- En esta opción podemos ver todos los productos tal y como en la primera opción del menu principal:

![image](https://github.com/user-attachments/assets/8796b5d1-0ea7-45e5-9835-751e51113ff9)

## **EDITAR UN PRODUCTO**

- Se editarán todos los datos de un producto

- Por seguridad, se pedirá la ID del producto para poder modificarlo:

![image](https://github.com/user-attachments/assets/484f1041-601c-46dd-a831-e0df4c97d0be)

- Vamos a probar con este producto, como demostración:

![image](https://github.com/user-attachments/assets/b4634044-7b9e-462e-b429-40b422537687)

- Introducimos los datos nuevos:

![image](https://github.com/user-attachments/assets/5a0802ba-f9bb-40fc-ad53-fac86cec9995)

- Vemos que ha funcionado:

![image](https://github.com/user-attachments/assets/8832dde9-6367-43e2-9cd8-3e9237204343)

## **VER UN RESUMEN DE TODOS LOS CLIENTES**

- Nos muestran los datos de todos los clientes:

![image](https://github.com/user-attachments/assets/1ab23dc6-2595-42d7-8037-fe6d89d1631b)

- En el caso de que no haya:

![image](https://github.com/user-attachments/assets/315563d2-c635-403e-a940-921932aea035)

## **VER UN RESUMEN DE TODOS LOS PEDIDOS**

- Nos muestran los datos de todos los pedidos:

![image](https://github.com/user-attachments/assets/17d7dc48-e981-4892-8296-102d4bfe0d06)

- En el caso de que no haya:

![image](https://github.com/user-attachments/assets/ef2e9a60-197d-4017-808a-085761b6fc19)

## **VER UN RESUMEN DE TODOS LOS TRABAJADORES**

- Nos muestran los datos de todos los trabajadores:

![image](https://github.com/user-attachments/assets/0e92c080-c4d3-4212-b776-eeda17bb5418)

- En el caso de que no haya:

![image](https://github.com/user-attachments/assets/bac4c1e4-290a-4a9e-8d71-9f9b12764409)

## **VER LAS ESTADÍSTICAS DE LA APP**

- Simplemente nos muestra las estadísticas de la aplicación que nos aparece al principio del menú de administrador:

![image](https://github.com/user-attachments/assets/e62b8f47-dbb3-41fb-8cd6-2d9a65cece14)

## **CAMBIAR EL ESTADO DE UN PEDIDO**

- Mediante esta opción cambiaremos el estado de un pedido realizado por un cliente, el administrador puede cambiar cualquier estado de cualquier pedido en todo el programa

En el caso de que no haya ninguno saldrá este mensaje:

![image](https://github.com/user-attachments/assets/36084e24-6c66-45d3-b40e-79d9d76dd57d)

**TODO**{
Vamos a crear un pedido rápido a traves de un cliente para demostrar su funcionalidad:

![image](https://github.com/user-attachments/assets/cf80369a-44fc-470a-b7fa-5479af2778f8)

**Modifica el estado**

- Saldrán los pedidos de forma enumerada y tendremos que poner el número de enumeración que nos aparece:

![image](https://github.com/user-attachments/assets/ce0a3168-e4cc-43a7-aabe-06d7f2347b82)

- Saldrá un menú para elegir el nuevo estado y elegimos un nuevo estado para el pedido:

![image](https://github.com/user-attachments/assets/d9bb1ede-7131-4f18-a83a-0c67cc6663c2)

**Añade comentario**

- Añadir un comentario de forma opcional al pedido asignado

![image](https://github.com/user-attachments/assets/de6d39f4-2516-4073-848f-7b802864a61b)

- Podemos ver con esta captura que ha funcionado:

![image](https://github.com/user-attachments/assets/32010f59-3313-45f2-98d1-8f72553c8ca2)
}
## **DAR DE ALTA UN TRABAJADOR**

- Aquí crearemos los trabajadores para que gestión los diversos productos de los clientes

Vamos a crear uno con estos datos:

![image](https://github.com/user-attachments/assets/7c275815-9b5f-42b9-bce5-296f0c5287dc)

- Vemos que se ha creado si vemos todos los trabajadores:

![image](https://github.com/user-attachments/assets/7f8c21ac-a4ae-4895-8e96-17fd4c604383)

- Iniciamos sesión y vemos que funciona:

![image](https://github.com/user-attachments/assets/02c7fb2a-9375-406a-a50c-f57cd695ab13)

![image](https://github.com/user-attachments/assets/53cc1679-b27d-4077-a0b9-ee72df9614d2)

## **DAR DE BAJA UN TRABAJADOR**

- Aquí daremos de baja a los trabajadores

- En el caso de que algún trabajador tenga pedidos pendientes, nos dará un error debido a que no se pueden eliminar trabajadores con pedidos asignados:

![image](https://github.com/user-attachments/assets/bfadb655-c2b1-40f8-b3d0-d1737578fd4f)

- Si no tiene pedidos asignados:

![image](https://github.com/user-attachments/assets/02034dde-37f2-428e-9fb3-8fd8206ad66c)

- Vemos que ha funcionado:

![image](https://github.com/user-attachments/assets/938610e6-4767-4f78-8a20-59d110f750be)

## **ASIGNAR UN PEDIDO A UN TRABAJADOR**

- Asignar un pedido realizado por un cliente a un trabajador:

Para probar, vamos ha hacer que uno de nuestros clientes haga varios pedidos

- Con los pedidos realizados, vamos a asignar uno de los pedidos:

![image](https://github.com/user-attachments/assets/6dfba0d8-fd57-48b7-90bb-3aa18e717890)

- Nos saldrá un menú de selección de los trabajadores creados por el administrador:

![image](https://github.com/user-attachments/assets/5c5eae3c-b68f-428f-a8b5-74b76c62140d)

- Elegimos a uno de ellos como gestionador del producto seleccionado anteriormente:

![image](https://github.com/user-attachments/assets/0f985348-a1f1-4674-806d-cd8d80fb59f6)

- Vemos que ha funcionado:

![image](https://github.com/user-attachments/assets/6beecb40-72ad-4470-bc86-c5ab5f3c5ff1)

![image](https://github.com/user-attachments/assets/266dc196-0505-4ef9-ad90-49e949ccc186)

## **MUESTRA CONFIGURACIÓN DEL PROGRAMA**

Forma parte de las funcionalidades nuevas del programa, muestra la configuración del programa donde se guardan los datos en disco y si se permite el modo invitado o no:

![image](https://github.com/user-attachments/assets/d24e43cf-29c6-4bf1-97f7-958edb926274)

## **ENVIA LISTADO DE PEDIDOS POR CORREO**

Forma parte de la funcionalidades nuevas del programa, envia al correo electrónico introducido por teclado un excel con los datos de todos los pedidos y la cantidad de productos de cada uno:

![image](https://github.com/user-attachments/assets/ef584ab7-3704-4f1f-8ba9-48b462fac8d0)

![image](https://github.com/user-attachments/assets/ed011ffd-c42a-4f9f-94b1-42c532a3838c)


## **SALIR**

- Cerramos sesión y volvemos al menu principal:

![image](https://github.com/user-attachments/assets/c17b5a4a-3427-49e8-9293-ed7fdec0e835)

## 6. **CAMBIOS Y FUNCIONALIDADES NUEVAS**

## **IMPLANTACIÓN DE CORREOS ELECTRÓNICOS Y MENSAJES DE TELEGRAM**

Una de las funcionalidades más importantes es la implantación de correos electrónicos en el software, ocurrira en diferentes casos:
- **Durante el registro para generar un token para activar la cuenta registrada ya sea de un cliente o un trabajador**
- **Cuando un trabajador o administrador modifique un pedido realizado**
- **Cuando el administrador asigna un pedido a un trabajador, este lo recibirán solo los trabajadores**
- **Cuando se realice un pedido nuevo por un cliente**

Y para el telegram ocurrirá cuando:
- **Cuando el administrador asigna un pedido a un trabajador, este lo recibirán solo los trabajadores**

## **SEGURIDAD AL REGISTRARTE**

Nos hemos fijado que cualquier cosa que metieras en el correo funcionaba, así que hemos mejorado la implementación de correos:

![image](https://github.com/user-attachments/assets/c1168e46-937b-4a4c-b54c-797cba3fe36a)

Lo mismo hemos aplicado con los números de teléfono, etc...

![image](https://github.com/user-attachments/assets/0bbec500-5662-4629-9414-96f32a8e7ce5)

## **CORREO DE REGISTRO (TOKEN) Y SEGURIDAD**

Una vez nos hayamos registrado, miraremos nuestra bandeja del correo y veremos que nos ha llegado un correo con una combinación de numeros y dos caracteres:

![image](https://github.com/user-attachments/assets/68843b59-f7f5-4002-b213-05f7a8cf267b)

Se necesitará una validación de dicho código que nos ha llegado a nuestro correo electrónico para poder iniciar sesión:

![image](https://github.com/user-attachments/assets/5b4c4605-c311-471a-8519-708455910024)

Si introducimos un token incorrecto, nos lo hará saber y nos llevará de vuelta al inicio de nuestro software:

![image](https://github.com/user-attachments/assets/2d0ecabd-03be-45fc-b923-c2bc0fcc11da)

Sin embargo si ponemos el token que nos aparece en el correo ya nos dejará acceder:

![image](https://github.com/user-attachments/assets/01eddb00-aea5-41b9-9b77-6813d50f06a5)

**MODIFICACIÓN DE DATOS**

Por seguridad cuando modifiquemos nuestros datos, se nos enviará un nuevo token al correo:

![image](https://github.com/user-attachments/assets/e8b595d6-f448-462e-a1cb-e2cca87ba11b)
![image](https://github.com/user-attachments/assets/edd03910-4a82-4691-af16-ac00d28f16a3)
![image](https://github.com/user-attachments/assets/e2085025-20d2-4f78-b4dc-44b59e0cf668)

Y volveremos a tener la petición del token, en caso de introducirlo mal, se nos cerrará la sesión y volveremos al programa principal

Si lo introducimos bien volveremos a entrar:

![image](https://github.com/user-attachments/assets/cfc58bdc-9c96-41ed-b5b2-1a3dedc28758)
![image](https://github.com/user-attachments/assets/ddef2f44-c819-4059-bc2c-88df302c6e73)

Además con los datos modificados:

![image](https://github.com/user-attachments/assets/e6ad30f4-8414-41ea-90e5-6489cbd77aa9)

**REALIZACIÓN DE PEDIDO**

Ahora, cuando un cliente realice un pedido, se le enviará un correo con los datos del pedido, además del precio con IVA final:

![image](https://github.com/user-attachments/assets/e04eab69-166d-418e-bf46-c4fb6bf15acd)

![image](https://github.com/user-attachments/assets/7c30cb94-fdc3-4fdf-9c76-ce35198a1eec)
![image](https://github.com/user-attachments/assets/6af8a697-8408-4ef3-9ec6-4e7686abf3fe)

Lo mismo con el perfil de Trabajadores: 

![image](https://github.com/user-attachments/assets/c8322dfa-1797-4316-a884-b6122353853b)

Miraremos el correo:

![image](https://github.com/user-attachments/assets/050e53f6-8549-4f68-bde0-88d96d3b105a)

![image](https://github.com/user-attachments/assets/c5428cdb-91ab-4bca-a28b-4537e9b5751e)


Y una vez introducido el token válido entraremos al menú con nuestro perfil actualizado:

![image](https://github.com/user-attachments/assets/0c9892cf-b6e6-4107-84f8-3464e74ac87b)

**CORREOS CON PEDIDOS ASIGNADOS AL TRABAJADOR**

Para comprobar este cambio vamos a realizar un pedido con uno de nuestros clientes:

![image](https://github.com/user-attachments/assets/f4d9c394-9e11-4b3e-84a6-7ea6b6a09a8f)

Mediante la asignación automática o por el trabajador se lo asignamos al trabajador. Ahora a nuestro trabajador llamado carlos le habrá llegado una notificación al correo y en el telegram de que se le ha asignado un pedido con sus detalles:

**-Correo:**

![image](https://github.com/user-attachments/assets/e0f2e23a-b496-4706-ac7e-647017414e57)

**Telegram:**

![image](https://github.com/user-attachments/assets/13778ac1-f89d-4f31-b11c-b47d031623d3)

- Podemos ver que es el mismo código:

![image](https://github.com/user-attachments/assets/2b3e423e-2cfd-48f3-8d3b-6631175ee1d1)


**MODIFICACIÓN DE UN PEDIDO**

-Cuando hagamos un cambio de un pedido se lo haremos saber al cliente, les llegará un correo con los detalles del cambio de pedido. 

-Vamos a modificar un pedido, hemos mejorado la selección de modificar un pedido:

![image](https://github.com/user-attachments/assets/999b00e7-abc3-4eac-9fb0-c1fd73ee061d)

Ahora vamos hacer modificaciones en el pedido, hemos mejorado muchos aspectos de la modificación de un pedido:

![image](https://github.com/user-attachments/assets/55669f2f-ea7e-4001-bc8d-3e791cfe69b0)

El cliente recibirá un correo con la modificación realizada:

![image](https://github.com/user-attachments/assets/cff8f2e9-71f9-4024-b127-345b8480a02d)

El correo ha sido actualizado correctamente con todos los detalles de los datos modificados del pedido.



