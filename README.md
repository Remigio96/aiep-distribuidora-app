# 📱 Proyecto App Distribuidora (Taller de Aplicaciones Móviles)

Este repositorio contiene el código fuente de un prototipo de aplicación **Android** desarrollado como parte de la **Actividad Sumativa de la Semana 6** del Taller de Aplicaciones Móviles de **AIEP**.

El proyecto se basa en un caso de estudio para una empresa de distribución de alimentos y demuestra la implementación de funcionalidades clave utilizando **servicios en la nube de Google Firebase**.

## 📃 CASO

```bash
Una empresa de distribución de alimentos ha incluido dentro de su servicio a
sus clientes, el servicio de despacho a domicilio, por compras sobre 50 mil
pesos, dentro de un radio de 20 km. Para compras entre 25.000 a 49.999,
entonces se cobrará una tarifa de $150 pesos por kilómetro recorrido. Y si el
total de compra es menor, se cobrará $300 pesos por kilómetro recorrido.

El dueño de la distribuidora ha encargado el desarrollo de una aplicación,
en donde un usuario pueda realizar compra de productos de la distribuidora,
y realice el cálculo automáticamente por despacho, dependiendo de las reglas
de negocio. Los usuarios pueden registrarse a la aplicación por medio de
cuentas Gmail.

Por parte de gestión de despacho, existen alimentos que no pueden perder la
cadena de frío (como carnes y mariscos congelados), por lo cual requiere
conocer la temperatura del congelador del camión, y en caso que supere el
límite, emitir una alarma en su dispositivo móvil.

El administrador del local posee una versión de Android Lollipop. Sin embargo,
la mayoría de los potenciales clientes utilizan Android Oreo.

```


---

## 🖋️ Tareas 

✅ Crea una pantalla Login, y utilizando la autenticación de Firebase, utiliza el medio de
acceso por medio de correo electrónico y contraseña. Si el usuario y clave coinciden
con los registrados en Firebase Authentication, entonces abrirá una nueva pestaña,
llamado MenuActivity.

✅ Si el usuario coincide con los datos, entonces almacena en RealTime Database, la
posición GPS de tu dispositivo.

✅ Configura dispositivo físico o virtual para instalación de la aplicación, e instala la
aplicación móvil en Android, comprobando que el método funciona correctamente.

✅ Crea y gestiona este proyecto en un único repositorio de github, actualizando el
cumplimiento de las historias de usuario, incentivando el trabajo en equipo, y uso de
herramientas colaborativas.

## 🚀 Funcionalidades Implementadas

El prototipo actual cuenta con las siguientes características:

### 🔑 Autenticación de Usuarios

* Creación de una pantalla de **Login** dedicada.
* **Registro** de nuevos usuarios mediante correo electrónico y contraseña.
* **Inicio de sesión** para usuarios existentes.
* **Persistencia de la sesión**: la aplicación recuerda al usuario y salta el login si ya ha iniciado sesión.
* **Botón para cerrar sesión** de forma segura.

### 📍 Captura y Almacenamiento de Ubicación GPS

* Solicitud de permisos de ubicación al usuario en tiempo de ejecución.
* Obtención de las **coordenadas GPS** (latitud y longitud) del dispositivo una vez que el usuario inicia sesión.
* Almacenamiento de las coordenadas en **Firebase Realtime Database**, asociadas al **ID único** de cada usuario.

---

## 🛠️ Tecnologías y Herramientas Utilizadas

* **Lenguaje:** Kotlin
* **Entorno de Desarrollo:** Android Studio
* **Base de Datos en la Nube:** Google Firebase
* **Firebase Authentication:** Para la gestión de usuarios
* **Firebase Realtime Database:** Para el almacenamiento de datos en tiempo real
* **APIs de Google:** Google Play Services Location (para obtener la ubicación del dispositivo)
* **Control de Versiones:** Git y GitHub
* **SDK Mínimo:** API 21 (Android 5.0 Lollipop), asegurando compatibilidad con los dispositivos del caso de estudio.

---

## 📋 Requisitos para la Ejecución

Para compilar y ejecutar este proyecto, se necesita:

* **Android Studio** (versión Iguana o superior recomendada).
* Un **dispositivo Android** o un **Emulador** configurado con API 21 o superior.
* Una **conexión a internet** para la sincronización con Firebase.
* Un archivo **google-services.json** válido de un proyecto de Firebase propio.

---

## ⚙️ Configuración del Proyecto

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/Remigio96/aiep-distribuidora-app.git
   ```

2. Abrir el proyecto en **Android Studio**.

3. Crear un proyecto en **Firebase Console**.

4. Añadir una aplicación **Android** a tu proyecto de Firebase con el nombre de paquete:

   ```
   cl.aiep.distribuidoraapp
   ```

5. Descargar el archivo `google-services.json` desde la configuración de tu proyecto en Firebase.

6. Copiar el archivo `google-services.json` descargado en la carpeta **app** del proyecto en Android Studio.

7. Habilitar los proveedores:

   * **Authentication (Email/Password)**
   * **Realtime Database (modo de prueba)**
     en la consola de Firebase.

8. Sincronizar el proyecto con los archivos de **Gradle**.

---

✅ Este README cumple con los requisitos de la tarea, documentando el trabajo de manera clara y profesional.
