package comunicaciones;

import models.Pedido;
import models.PedidoClienteDataClass;
import persistencia.Persistencia;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
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
                                                                         body {
                                                                             font-family: 'Arial', sans-serif;
                                                                             background-color: #f0f2f5;
                                                                             margin: 0;
                                                                             padding: 0;
                                                                             display: flex;
                                                                             justify-content: center;
                                                                             align-items: center;
                                                                             min-height: 100vh;
                                                                         }
                    
                                                                         .container {
                                                                             max-width: 600px;
                                                                             background-color: #ffffff;
                                                                             padding: 30px 25px;
                                                                             border-radius: 12px;
                                                                             box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                                                                             text-align: center;
                                                                         }
                    
                                                                         .header {
                                                                             background: linear-gradient(90deg, #00b4d8, #48cae4);
                                                                             padding: 15px;
                                                                             border-radius: 10px 10px 0 0;
                                                                             color: white;
                                                                             font-size: 24px;
                                                                             font-weight: bold;
                                                                         }
                    
                                                                         .info {
                                                                             padding: 20px;
                                                                             background: #f7f9fb;
                                                                             border-radius: 8px;
                                                                             margin-top: 20px;
                                                                         }
                    
                                                                         .info p {
                                                                             font-size: 18px;
                                                                             color: #333;
                                                                             margin: 10px 0;
                                                                         }
                    
                                                                         .detalles {
                                                                             font-size: 22px;
                                                                             font-weight: bold;
                                                                             color: #ffffff;
                                                                             background-color: #0077b6;
                                                                             padding: 12px;
                                                                             border-radius: 6px;
                                                                             margin-top: 15px;
                                                                         }
                    
                                                                         .imagen {
                                                                             margin-top: 30px;
                                                                         }
                    
                                                                         .imagen img {
                                                                             width: 100px;
                                                                             border-radius: 10px;
                                                                         }
                    
                                                                         footer {
                                                                             background-color: #e9ecef;
                                                                             padding: 20px 10px;
                                                                             margin-top: 30px;
                                                                             border-radius: 0 0 12px 12px;
                                                                         }
                    
                                                                         footer h2 {
                                                                             color: #0077b6;
                                                                             font-size: 18px;
                                                                             margin: 5px 0;
                                                                         }
                    
                                                                         footer h3 {
                                                                             font-size: 16px;
                                                                             color: #495057;
                                                                             margin: 0;
                                                                         }
                                                                     </style>
                                                                 </head>
                                                                 <body>
                                                                     <div class="container">
                                                                         <div class="header">
                                                                             ¡HOLA, BIENVENIDO %s!
                                                                         </div>
                                                                         <div class="info">
                                                                             <p>%s</p>
                                                                             <div class="detalles">%s</div>
                                                                         </div>
                                                                         <div class="imagen">
                                                                             <img src="https://static.vecteezy.com/system/resources/previews/014/967/264/non_2x/welcome-sign-illustration-in-minimal-style-png.png" alt="Bienvenida">
                                                                         </div>
                                                                         <footer>
                                                                             <h2>GRACIAS POR USAR NUESTROS SERVICIOS</h2>
                                                                             <h3>&copy; FERNANSHOP2025</h3>
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
                                                                                       body {
                                                                                           font-family: 'Arial', sans-serif;
                                                                                           background-color: #f4f4f4;
                                                                                           margin: 0;
                                                                                           padding: 0;
                                                                                           display: flex;
                                                                                           justify-content: center;
                                                                                           align-items: center;
                                                                                           min-height: 100vh;
                                                                                       }
                                                                                       .container {
                                                                                           max-width: 600px;
                                                                                           background: #ffffff;
                                                                                           padding: 20px;
                                                                                           border-radius: 12px;
                                                                                           box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
                                                                                           text-align: center;
                                                                                       }
                                                                                       .header {
                                                                                           background: linear-gradient(90deg, #007BFF, #00C6FF);
                                                                                           padding: 15px;
                                                                                           border-radius: 12px 12px 0 0;
                                                                                           color: white;
                                                                                           font-size: 24px;
                                                                                           font-weight: bold;
                                                                                       }
                                                                                       .info {
                                                                                           padding: 20px;
                                                                                           background: #f9f9f9;
                                                                                           border-radius: 8px;
                                                                                           margin-top: 15px;
                                                                                       }
                                                                                       .info p {
                                                                                           font-size: 18px;
                                                                                           color: #333;
                                                                                           margin: 10px 0;
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
                                                                                       .imagen {
                                                                                           margin-top: 30px;
                                                                                       }
                                                                                       .imagen img {
                                                                                           width: 100px;
                                                                                           border-radius: 8px;
                                                                                       }
                                                                                       footer {
                                                                                           background: #e0e0e0;
                                                                                           padding: 15px;
                                                                                           margin-top: 25px;
                                                                                           border-radius: 0 0 12px 12px;
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
                                                                                           ¡HOLA!
                                                                                       </div>
                                                                                       <div class="info">
                                                                                           <p>¡Su pedido ha sido modificado!</p>
                                                                                           <p><strong>Detalles del pedido:</strong></p>
                                                                                           <div class="detalles">%s</div>
                                                                                       </div>
                                                                                       <div class="imagen">
                                                                                           <img src="https://static.vecteezy.com/system/resources/previews/014/769/501/non_2x/online-order-system-line-icon-vector.jpg" alt="Icono de pedido online">
                                                                                       </div>
                                                                                       <footer>
                                                                                           <h2>GRACIAS POR USAR NUESTROS SERVICIOS</h2>
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
                                                                  body {
                                                                      font-family: 'Arial', sans-serif;
                                                                      background-color: #f4f4f4;
                                                                      margin: 0;
                                                                      padding: 0;
                                                                      display: flex;
                                                                      justify-content: center;
                                                                      align-items: center;
                                                                      min-height: 100vh;
                                                                  }
                                                                  .container {
                                                                      max-width: 600px;
                                                                      background: #ffffff;
                                                                      padding: 20px;
                                                                      border-radius: 12px;
                                                                      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
                                                                      text-align: center;
                                                                  }
                                                                  .header {
                                                                      background: linear-gradient(90deg, #007BFF, #00C6FF);
                                                                      padding: 15px;
                                                                      border-radius: 12px 12px 0 0;
                                                                      color: white;
                                                                      font-size: 24px;
                                                                      font-weight: bold;
                                                                  }
                                                                  .info {
                                                                      padding: 20px;
                                                                      background: #f9f9f9;
                                                                      border-radius: 8px;
                                                                      margin-top: 15px;
                                                                  }
                                                                  .info p {
                                                                      font-size: 18px;
                                                                      color: #333;
                                                                      margin: 10px 0;
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
                                                                  .imagen {
                                                                      margin-top: 30px;
                                                                  }
                                                                  .imagen img {
                                                                      width: 100px;
                                                                      border-radius: 8px;
                                                                  }
                                                                  footer {
                                                                      background: #e0e0e0;
                                                                      padding: 15px;
                                                                      margin-top: 25px;
                                                                      border-radius: 0 0 12px 12px;
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
                                                                      ¡HOLA!
                                                                  </div>
                                                                  <div class="info">
                                                                      <p>¡Ha realizado un pedido nuevo!</p>
                                                                      <p><strong>Detalles del pedido:</strong></p>
                                                                      <div class="detalles">%s</div>
                                                                  </div>
                                                                  <div class="imagen">
                                                                      <img src="https://cdn-icons-png.flaticon.com/512/6463/6463788.png" alt="Nuevo pedido">
                                                                  </div>
                                                                  <footer>
                                                                      <h2>GRACIAS POR USAR NUESTROS SERVICIOS</h2>
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

    // Metodo que le asigna un pedido a un trabajador con los datos del cliente tmb
    public static void enviaCorreoCambiaEstadoPedidoTrabajador(String receptor, String asunto, PedidoClienteDataClass pedido) {
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
                                  <p>Se ha modificado un pedido asignado.</p>
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

    // Metodo que envia un excel al correo
    public static void enviarExcelGuardadoPorCorreo(String destino) {
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
            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            mensaje.setSubject("Listado de pedidos");

            // Cuerpo de texto
            MimeBodyPart cuerpoTexto = new MimeBodyPart();
            cuerpoTexto.setText("Hola,\n\nAdjunto te envío el archivo Excel con los pedidos.\n\nUn saludo.");

            // Adjuntar archivo
            MimeBodyPart adjunto = new MimeBodyPart();
            File archivo = new File(Persistencia.leeRutaDocumentosExcel() + "\\Pedidos.xlsx");
            if (!archivo.exists()) {
                throw new FileNotFoundException("No se encontró el archivo en la ruta: " +
                        Persistencia.leeRutaDocumentosExcel() + "\\Pedidos.xlsx");
            }
            adjunto.attachFile(archivo);

            // Ensamblar multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(cuerpoTexto);
            multipart.addBodyPart(adjunto);

            // Asignar contenido al mensaje
            mensaje.setContent(multipart);

            // Enviar
            Transport.send(mensaje);

        } catch (Exception e) {
            System.out.println("El correo introducido no es válido");
        }
    }

    // Metodo que envia el resumen del pedido en formato PDF
    public static void enviaCorreoResumen(String destino, Pedido pedidoTemp) {
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
            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            mensaje.setSubject("Resumen del pedido");

            // Cuerpo de texto
            MimeBodyPart cuerpoTexto = new MimeBodyPart();
            cuerpoTexto.setText("Hola,\n\nAdjunto te envío el resumen del pedido.\n\nUn saludo.");

            // Adjuntar archivo
            MimeBodyPart adjunto = new MimeBodyPart();
            File archivo = new File(Persistencia.leeRutaDocumentosPdf() + "\\" + pedidoTemp.getId() + ".pdf");
            if (!archivo.exists()) {
                throw new FileNotFoundException("No se encontró el archivo en la ruta: " +
                        Persistencia.leeRutaDocumentosPdf() + "\\" + pedidoTemp.getId() + ".pdf");
            }
            adjunto.attachFile(archivo);

            // Ensamblar multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(cuerpoTexto);
            multipart.addBodyPart(adjunto);

            // Asignar contenido al mensaje
            mensaje.setContent(multipart);

            // Enviar
            Transport.send(mensaje);

        } catch (Exception e) {
            System.out.println("El correo introducido no es válido");
        }
    }
}
