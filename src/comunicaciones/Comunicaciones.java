package comunicaciones;

import models.Pedido;
import models.PedidoClienteDataClass;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;


public class Comunicaciones {
    // Metodo que le envia un mensaje al telegram del trabajador que se le ha asignado un pedido
    public static boolean enviaMensajeTelegramTrabajador(String mensaje) {
        String direccion; // URL de la API de mi bot en mi conversación
        String fijo = "https://api.telegram.org/bot7933251856:AAGX2oHNIFDQKXDq4PmQbst5v1zBQfddpZY/sendMessage?chat_id=1187949150&text=";
        direccion = fijo + mensaje; //Metemos el mensaje al final
        URL url;
        boolean dev;
        dev = false;
        try {
            url = new URL(direccion);  // Creando un objeto URL con la dirección de la API de mi bot
            URLConnection con = url.openConnection();  // Realizando la petición GET
            // Con esto, copiamos en un la respuesta HTTP, por si lo necesitamos
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            dev = true;  // Ha tenido éxito
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return dev;  // Devuelvo si ha tenido éxito o no
    }

    // Metodo que envía el token al correo del destinatario
    public static void enviaCorreoToken(String destino, String mensaje, String asunto, String token, String nombreUsuario) {
        //Guardamos la dirección que va a remitir el mensaje
        String emisor = "fernanshopjlmanule@gmail.com";
        String usuario = "fernanshopjlmanule@gmail.com";//Usuario para el logueo en el server de correo
        String clave = "sfkmqvpupcjjahcg";//Clave del usuario de correo
        //Host del servidor de correo
        String host = "smtp.gmail.com";
        //Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, clave);
            }
        });
        try {
            // Definir contenido en HTML con CSS
            String contenidoHTML = String.format("""
                    <!DOCTYPE html>
                                                <html>
                    
                                                <head>
                                                    <meta charset='utf-8'>
                                                    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
                                                    <title>CORREOFERNANSHOP</title>
                                                    <meta name='viewport' content='width=device-width, initial-scale=1'>
                                                    <style>
                                                        * {
                                                            background-color: darkslategrey;
                                                        }
                    
                                                        #container {
                                                            width: 600px;
                                                            margin: auto;
                                                            height: 497px;
                                                            background-color: cyan;
                                                        }
                    
                                                        #comienzo {
                                                            height: 50px;
                                                            font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                                                            width: 550px;
                                                            margin: auto;
                                                            text-align: center;
                                                            margin-top: 45px;
                                                            background-color: cyan;
                                                        }
                    
                                                        #comienzo h1 {
                                                            background-color: black;
                                                            color: white;
                                                        }
                    
                                                        #info {
                                                            height: 150px;
                                                            width: 300px;
                                                            margin: auto;
                                                            background-color: rgb(16, 124, 187);
                                                        }
                    
                                                        #info p {
                                                            font-weight: bold;
                                                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                                                            font-size: 20px;
                                                            color: black;
                                                            text-align: center;
                                                            margin: auto;
                                                            width: 300px;
                                                            background-color: white;
                                                        }
                    
                                                        img {
                                                            width: 100px;
                                                            background-color: cyan;
                                                        }
                    
                                                        #imagen {
                                                            width: 100px;
                                                            margin: auto;
                                                            margin-top: 60px;
                                                            background-color: cyan;
                                                        }
                    
                                                        #detalles {
                                                            font-weight: bold;
                                                            font-family: 'Times New Roman', Times, serif;
                                                            font-size: 45px;
                                                            color: white;
                                                            background-color: rgb(16, 124, 187);
                                                            text-align: center;
                                                            margin: auto;
                                                            width: 300px;
                                                            margin-top: 30px;
                                                        }
                    
                                                        footer{
                                                            background-color: rgb(223, 216, 216);
                                                        }
                    
                                                        footer h2 {
                                                            background-color: aliceblue;
                                                            font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
                                                            color: blue;
                                                            font-size: 20px;
                                                            text-align: center;
                                                        }
                    
                                                        footer h3 {
                                                            background-color: aliceblue;
                                                            font-size: 50px;
                                                            text-align: center;
                                                            font-family: sans-serif;
                                                        }
                                                    </style>
                                                </head>
                    
                                                <body>
                                                    <div id="container">
                                                        <div id="comienzo">
                                                            <h1>¡HOLA, BIENVENIDO %s!</h1>
                                                        </div>
                                                        <div id="info">
                                                            <p>%s</p>
                                                            <h2 id="detalles">%s</h2>
                                                        </div>
                                                        <div id="imagen">
                                                            <img src=https://static.vecteezy.com/system/resources/previews/014/967/264/non_2x/welcome-sign-illustration-in-minimal-style-png.png>
                                                        </div>
                                                        <footer>
                                                            <h2>GRACIAS POR USAR NUESTROS SERVICIOS</h2>
                                                            <h3>&copy;FERNANSHOP2025</h3>
                                                        </footer>
                                                    </div>
                                                </body>
                    
                                                </html>
                    """, nombreUsuario, mensaje, token);

            //Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);
            //En el mensaje, establecemos el emisor con los datos pasado sa la función
            message.setFrom(new InternetAddress(emisor));
            //En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            //Establecemos el Asunto
            message.setSubject(asunto);
            //Añadimos el contenido del mensaje
            //message.setText(mensaje); Si solo mandamos texto
            message.setContent(contenidoHTML, "text/html; charset=utf-8");
            //Intentamos mandar el mensaje
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("El correo introducido no es válido");
        }
    }

    // Metodo que le asigna un pedido a un trabajador con los datos del cliente tmb
    public static void enviaCorreoPedidoAsignacion(String receptor, String asunto, PedidoClienteDataClass pedido) {
        //Guardamos la dirección que va a remitir el mensaje
        String emisor = "fernanshopjlmanule@gmail.com";
        String usuario = "fernanshopjlmanule@gmail.com";//Usuario para el logueo en el server de correo
        String clave = "sfkmqvpupcjjahcg";//Clave del usuario de correo
        //Host del servidor de correo
        String host = "smtp.gmail.com";
        //Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, clave);
            }
        });
        try {
            // Definir contenido en HTML con CSS
            String contenidoHTML = String.format("""
                    <!DOCTYPE html>
                      <html>
                      <head>
                          <meta charset='utf-8'>
                          <meta http-equiv='X-UA-Compatible' content='IE=edge'>
                          <title>CORREOFERNANSHOP</title>
                          <meta name='viewport' content='width=device-width, initial-scale=1'>
                          <style>
                              body {
                                  font-family: 'Arial', sans-serif;
                                  background-color: #f4f4f4;
                                  margin: 0;
                                  padding: 0;
                                  display: flex;
                                  justify-content: center;
                                  align-items: center;
                                  height: 100vh;
                              }
                              .container {
                                  max-width: 600px;
                                  background: #ffffff;
                                  padding: 20px;
                                  border-radius: 10px;
                                  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                                  text-align: center;
                              }
                              .header {
                                  background: linear-gradient(90deg, #007BFF, #00C6FF);
                                  padding: 15px;
                                  border-radius: 10px 10px 0 0;
                                  color: white;
                                  font-size: 24px;
                                  font-weight: bold;
                              }
                              .info {
                                  padding: 20px;
                                  background: #f9f9f9;
                                  border-radius: 5px;
                                  margin-top: 15px;
                              }
                              .info p {
                                  font-size: 18px;
                                  color: #333;
                              }
                              .detalles {
                                  font-weight: bold;
                                  color: white;
                                  background: #007BFF;
                                  padding: 12px;
                                  border-radius: 5px;
                                  margin-top: 15px;
                                  font-size: 18px;
                              }
                              footer {
                                  background: #e0e0e0;
                                  padding: 15px;
                                  margin-top: 20px;
                                  border-radius: 0 0 10px 10px;
                              }
                              footer h2 {
                                  color: #007BFF;
                                  font-size: 20px;
                                  margin: 5px 0;
                              }
                              footer h3 {
                                  font-size: 16px;
                                  color: #555;
                              }
                          </style>
                      </head>
                      <body>
                          <div class="container">
                              <div class="header">
                                  ¡Hola!
                              </div>
                              <div class="info">
                                  <p>Se te ha asignado un nuevo pedido.</p>
                                  <p><strong>Detalles del pedido:</strong></p>
                                  <div class="detalles">%s</div>
                              </div>
                              <footer>
                                  <h2>Gracias por usar nuestros servicios</h2>
                                  <h3>&copy; FERNANSHOP 2025</h3>
                              </footer>
                          </div>
                      </body>
                      </html>
                    """, pedido.pintaPedidoCorreo());

            //Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);
            //En el mensaje, establecemos el emisor con los datos pasado sa la función
            message.setFrom(new InternetAddress(emisor));
            //En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
            //Establecemos el Asunto
            message.setSubject(asunto);
            //Añadimos el contenido del mensaje
            //message.setText(mensaje); Si solo mandamos texto
            message.setContent(contenidoHTML, "text/html; charset=utf-8");
            //Intentamos mandar el mensaje
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("El correo introducido no es válido");
        }

    }

    // Metodo que le envia un correo a un cliente y le indica que el pedido se ha modificado
    public static void enviaCorreoPedidoEstadoCliente(String receptor, String asunto, Pedido pedido) {
        //Guardamos la dirección que va a remitir el mensaje
        String emisor = "fernanshopjlmanule@gmail.com";
        String usuario = "fernanshopjlmanule@gmail.com";//Usuario para el logueo en el server de correo
        String clave = "sfkmqvpupcjjahcg";//Clave del usuario de correo
        //Host del servidor de correo
        String host = "smtp.gmail.com";
        //Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, clave);
            }
        });
        try {
            // Definir contenido en HTML con CSS
            String contenidoHTML = String.format("""
                        <!DOCTYPE html>
                                                       <html>
                                                       <head>
                                                           <meta charset='utf-8'>
                                                           <meta http-equiv='X-UA-Compatible' content='IE=edge'>
                                                           <title>CORREOFERNANSHOP</title>
                                                           <meta name='viewport' content='width=device-width, initial-scale=1'>
                                                           <style>
                                                               #container {
                                                                   background-color: cyan;
                                                               }
                                                             \s
                                                               * {
                                                                   background-color: darkslategrey;
                                                               }
                                           \s
                                                               #comienzo {
                                                                   height: 50px;
                                                                   font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                                                                   width: 550px;
                                                                   margin: auto;
                                                                   text-align: center;
                                                                   margin-top: 45px;
                                                                   background-color: cyan;
                                                               }
                                           \s
                                                               #comienzo h1 {
                                                                   background-color: black;
                                                                   color: white;
                                                               }
                                           \s
                                                               #info {
                                                                    height: fit-content;
                                                                   width: 500px;
                                                                   margin: auto;
                                                                   background-color: rgb(16, 124, 187);
                                                               }
                                           \s
                                                               #info p {
                                                                   font-weight: bold;
                                                                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                                                                   font-size: 20px;
                                                                   color: black;
                                                                   text-align: center;
                                                                   margin: auto;
                                                                   width: 500px;
                                                                   background-color: white;
                                                               }
                                           \s
                                                               img {
                                                                   width: 100px;
                                                                   background-color: cyan;
                                                               }
                                           \s
                                                               #imagen {
                                                                   width: 100px;
                                                                   margin: auto;
                                                                   margin-top: 60px;
                                                                   background-color: cyan;
                                                               }
                                           \s
                                                               #detalles {
                                                                   font-weight: bold;
                                                                   font-family: 'Times New Roman', Times, serif;
                                                                   color: white;
                                                                   background-color: rgb(16, 124, 187);
                                                                   text-align: center;
                                                                   margin: auto;
                                                                   width: 500px;
                                                                   margin-top: 30px;
                                                               }
                                           \s
                                                               footer {
                                                                   background-color: rgb(223, 216, 216);
                                                               }
                                           \s
                                                               footer h2 {
                                                                   background-color: aliceblue;
                                                                   font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
                                                                   color: blue;
                                                                   font-size: 20px;
                                                                   text-align: center;
                                                               }
                                           \s
                                                               footer h3 {
                                                                   background-color: aliceblue;
                                                                   font-size: 50px;
                                                                   text-align: center;
                                                                   font-family: sans-serif;
                                                               }
                                           \s
                                                               /*#container{
                                                                    background-color: black;
                                                                }
                                           \s
                                                                #comienzo{
                                                                    height: 70px;
                                                                    font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
                                                                    width: 550px;
                                                                    margin: auto;
                                                                    background-color: darkcyan;
                                                                    text-align: center;
                                                                }
                                           \s
                                                                #info h1{
                                                                    width: 115px;
                                                                    background-color: skyblue;
                                                                }
                                           \s
                                                                #info p{
                                                                    font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
                                                                    font-size: 30px;
                                                                    color: white;
                                                                    background-color: black;
                                                                    text-align: center;
                                                                    margin: auto;
                                                                    width: 550px;
                                                                }
                                           \s
                                                                #detalles{
                                                                    font-weight: bold;
                                                                    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
                                                                    color: black;
                                                                    background-color: white;
                                                                    text-align: center;
                                                                    margin: auto;
                                                                    width: 550px;
                                                                }
                                           \s
                                                                footer{
                                                                    background-color: darkcyan;
                                                                    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
                                                                    border: 2px black dashed;
                                                                    text-align: center;
                                                                    width: 550px;
                                                                    margin: auto;
                                                                }*/
                                                           </style>
                                                       </head>
                                                       <body>
                                                           <div id="container">
                                                                   <div id="comienzo">
                                                                       <h1>¡HOLA!</h1>
                                                                   </div>
                                                                   <div id="info">
                                                                       <p>¡Su pedido ha sido modificado!</p>
                                                                       <p>Detalles del pedido:</p>
                                                                       <h2 id="detalles">%s</h2>
                                                                   </div>
                                                                   <div id="imagen">
                                                                       <img src= "https://static.vecteezy.com/system/resources/previews/014/769/501/non_2x/online-order-system-line-icon-vector.jpg">
                                                                   </div>
                                                                   <footer>
                                                                       <h2>GRACIAS POR USAR NUESTROS SERVICIOS</h2>
                                                                       <h3>&copy;FERNANSHOP2025</h3>
                                                                   </footer>
                                                               </div>
                                                       </body>
                                                       </html>
                    """, pedido.pintaPedidoCorreo());

            //Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);
            //En el mensaje, establecemos el emisor con los datos pasado sa la función
            message.setFrom(new InternetAddress(emisor));
            //En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
            //Establecemos el Asunto
            message.setSubject(asunto);
            //Añadimos el contenido del mensaje
            //message.setText(mensaje); Si solo mandamos texto
            message.setContent(contenidoHTML, "text/html; charset=utf-8");
            //Intentamos mandar el mensaje
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("El correo introducido no es válido");
        }

    }

    // Metodo que le envia un correo a un cliente y le indica que el pedido se ha realizado
    public static void enviaCorreoPedidoCliente(String receptor, String asunto, Pedido pedido) {
        //Guardamos la dirección que va a remitir el mensaje
        String emisor = "fernanshopjlmanule@gmail.com";
        String usuario = "fernanshopjlmanule@gmail.com";//Usuario para el logueo en el server de correo
        String clave = "sfkmqvpupcjjahcg";//Clave del usuario de correo
        //Host del servidor de correo
        String host = "smtp.gmail.com";
        //Creamos nuestra variable de propiedades con los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, clave);
            }
        });
        try {
            // Definir contenido en HTML con CSS
            String contenidoHTML = String.format("""
                        <!DOCTYPE html>
                                                       <html>
                                                       <head>
                                                           <meta charset='utf-8'>
                                                           <meta http-equiv='X-UA-Compatible' content='IE=edge'>
                                                           <title>CORREOFERNANSHOP</title>
                                                           <meta name='viewport' content='width=device-width, initial-scale=1'>
                                                           <style>
                                                               #container {
                                                                   background-color: cyan;
                                                               }
                                                             \s
                                                               * {
                                                                   background-color: darkslategrey;
                                                               }
                                           \s
                                                               #comienzo {
                                                                   height: 50px;
                                                                   font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                                                                   width: 550px;
                                                                   margin: auto;
                                                                   text-align: center;
                                                                   margin-top: 45px;
                                                                   background-color: cyan;
                                                               }
                                           \s
                                                               #comienzo h1 {
                                                                   background-color: black;
                                                                   color: white;
                                                               }
                                           \s
                                                               #info {
                                                                   height: fit-content;
                                                                   width: 700px;
                                                                   margin: auto;
                                                                   background-color: rgb(16, 124, 187);
                                                               }
                                           \s
                                                               #info p {
                                                                   font-weight: bold;
                                                                   font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                                                                   font-size: 20px;
                                                                   color: black;
                                                                   text-align: center;
                                                                   margin: auto;
                                                                   width: 500px;
                                                                   background-color: white;
                                                               }
                                           \s
                                                               img {
                                                                   width: 100px;
                                                                   background-color: cyan;
                                                               }
                                           \s
                                                               #imagen {
                                                                   width: 100px;
                                                                   margin: auto;
                                                                   margin-top: 25px;
                    
                                                                   background-color: cyan;
                                                               }
                                           \s
                                                               #detalles {
                                                                   font-weight: bold;
                                                                   font-family: 'Times New Roman', Times, serif;
                                                                   color: white;
                                                                   background-color: rgb(16, 124, 187);
                                                                   text-align: center;
                                                                   margin: auto;
                                                                   width: 700px;
                                                                   height: fit-content;
                                                                   margin-top: 30px;
                                                               }
                                           \s
                                                               footer {
                                                                   background-color: rgb(223, 216, 216);
                                                               }
                                           \s
                                                               footer h2 {
                                                                   background-color: aliceblue;
                                                                   font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
                                                                   color: blue;
                                                                   font-size: 20px;
                                                                   text-align: center;
                                                               }
                                           \s
                                                               footer h3 {
                                                                   background-color: aliceblue;
                                                                   font-size: 50px;
                                                                   text-align: center;
                                                                   font-family: sans-serif;
                                                               }
                                                           </style>
                                                       </head>
                                                       <body>
                                                           <div id="container">
                                                                   <div id="comienzo">
                                                                       <h1>¡HOLA!</h1>
                                                                   </div>
                                                                   <div id="info">
                                                                       <p>¡Ha realizado un pedido nuevo!</p>
                                                                       <p>Detalles del pedido:</p>
                                                                       <h2 id="detalles">%s</h2>
                                                                   </div>
                                                                   <div id="imagen">
                                                                        <img src="https://cdn-icons-png.flaticon.com/512/6463/6463788.png">
                                                                   </div>
                                                                   <footer>
                                                                       <h2>GRACIAS POR USAR NUESTROS SERVICIOS</h2>
                                                                       <h3>&copy;FERNANSHOP2025</h3>
                                                                   </footer>
                                                               </div>
                                                       </body>
                                                       </html>
                    """, pedido.pintaPedidoCorreo());

            //Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);
            //En el mensaje, establecemos el emisor con los datos pasado sa la función
            message.setFrom(new InternetAddress(emisor));
            //En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
            //Establecemos el Asunto
            message.setSubject(asunto);
            //Añadimos el contenido del mensaje
            //message.setText(mensaje); Si solo mandamos texto
            message.setContent(contenidoHTML, "text/html; charset=utf-8");
            //Intentamos mandar el mensaje
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("El correo introducido no es válido");
        }

    }
}
