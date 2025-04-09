package persistencia;

import controlador.Controlador;
import models.*;
import utils.Utils;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

public class Persistencia {

    public static final String RUTA_P = "config/config.properties";

    // Metodo que lee el producto de los catalogos
    public static ArrayList<Producto> leeCatalogo() {
        ArrayList<Producto> productos = new ArrayList<>();
        File carpetaProductos = new File(leeRutaProductos());

        if (!carpetaProductos.exists()) carpetaProductos.mkdirs();
        if (carpetaProductos.list() == null) return productos;

        for (String archivo : carpetaProductos.list()) {
            try {
                Producto producto;
                FileInputStream fis = new FileInputStream(carpetaProductos + "\\" + archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                producto = (Producto) ois.readObject();
                productos.add(producto);
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException e) {
                return productos;
            }
        }
        return productos;
    }

    // Metodo que guarda los producto de los catalogos en disco
    public static void guardaCatalogoEnDisco(ArrayList<Producto> catalogo) {
        File carpetaProductos = new File(leeRutaProductos());

        if (!carpetaProductos.exists()) carpetaProductos.mkdirs();

        for (Producto producto : catalogo) {
            guardaProductoEnDisco(producto);
        }
    }

    // Metodo que lee los administradores
    public static ArrayList<Admin> leeAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        File carpetaAdmins = new File(leeRutaAdmins());

        if (!carpetaAdmins.exists()) carpetaAdmins.mkdirs();
        if (carpetaAdmins.list() == null) return admins;

        for (String archivo : carpetaAdmins.list()) {
            try {
                Admin admin;
                FileInputStream fis = new FileInputStream(carpetaAdmins + "\\" + archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                admin = (Admin) ois.readObject();
                admins.add(admin);
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException e) {
                return admins;
            }
        }
        return admins;
    }

    // Metodo que guarda los administradores en disco
    public static void guardaAdminsEnDisco(ArrayList<Admin> admins) {
        File carpetaAdmins = new File(leeRutaAdmins());

        if (!carpetaAdmins.exists()) carpetaAdmins.mkdirs();

        for (Admin admin : admins) {
            try {
                FileOutputStream fos = new FileOutputStream(carpetaAdmins + "\\" + admin.getId() + ".admin");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(admin);
                oos.close();
                fos.close();
            } catch (IOException e) {
                return;
            }
        }
    }

    // Metodo que busca si se ha iniciado los datos de prueba
    public static boolean datosPrueba() {
        boolean banderaClientes = buscaClientePrueba(), banderaTrabajadores = buscaTrabajadoresPrueba(), bandera = false;

        if (banderaClientes && banderaTrabajadores) bandera = true;
        return bandera;
    }

    // Funcion que busca el ID del trabajador de prueba
    private static boolean buscaTrabajadoresPrueba() {
        File carpetaTrabajadores = new File(leeRutaTrabajadores());

        if (!carpetaTrabajadores.exists()) carpetaTrabajadores.mkdirs();
        boolean bandera = false;

        for (String archivo : carpetaTrabajadores.list()) {
            try {
                Trabajador trabajador;
                FileInputStream fis = new FileInputStream(carpetaTrabajadores + "\\" + archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                trabajador = (Trabajador) ois.readObject();
                if (trabajador.getId() == 100000) return true;
            } catch (IOException | ClassNotFoundException e) {
                bandera = false;
            }
        }
        return bandera;
    }

    // Funcion que busca el ID del cliente de prueba
    private static boolean buscaClientePrueba() {
        File carpetaClientes = new File(leeRutaClientes());

        if (!carpetaClientes.exists()) carpetaClientes.mkdirs();
        boolean bandera = false;

        for (String archivo : carpetaClientes.list()) {
            try {
                Cliente cliente;
                FileInputStream fis = new FileInputStream(carpetaClientes + "\\" + archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                cliente = (Cliente) ois.readObject();
                ois.close();
                fis.close();
                if (cliente.getId() == 99999) return true;
            } catch (IOException | ClassNotFoundException e) {
                bandera = false;
            }
        }
        return bandera;
    }

    // Metodo que lee los clientes en disco
    public static ArrayList<Cliente> leeClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        File carpetaClientes = new File(leeRutaClientes());

        if (!carpetaClientes.exists()) carpetaClientes.mkdirs();
        if (carpetaClientes.list() == null) return clientes;

        for (String archivo : carpetaClientes.list()) {
            try {
                Cliente cliente;
                FileInputStream fis = new FileInputStream(carpetaClientes + "\\" + archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                cliente = (Cliente) ois.readObject();
                clientes.add(cliente);
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException e) {
                return clientes;
            }
        }
        return clientes;
    }

    // Metodo que lee los clientes en trabajadores
    public static ArrayList<Trabajador> leeTrabajadores() {
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        File carpetaTrabajadores = new File(leeRutaTrabajadores());

        if (!carpetaTrabajadores.exists()) carpetaTrabajadores.mkdirs();
        if (carpetaTrabajadores.list() == null) return trabajadores;

        for (String archivo : carpetaTrabajadores.list()) {
            try {
                Trabajador trabajador;
                FileInputStream fis = new FileInputStream(carpetaTrabajadores + "\\" + archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                trabajador = (Trabajador) ois.readObject();
                trabajadores.add(trabajador);
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException e) {
                return trabajadores;
            }
        }
        return trabajadores;
    }

    // Metodo que guarda los clientes en disco
    public static void guardaClienteEnDisco(Cliente cliente) {
        File carpetaClientes = new File(leeRutaClientes());

        if (!carpetaClientes.exists()) carpetaClientes.mkdirs();

        try {
            FileOutputStream fos = new FileOutputStream(carpetaClientes + "\\" + cliente.getId() + ".cliente");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cliente);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo que guarda los trabajadores en disco
    public static void guardaTrabajadorEnDisco(Trabajador trabajador) {
        File carpetaTrabajador = new File(leeRutaTrabajadores());

        if (!carpetaTrabajador.exists()) carpetaTrabajador.mkdirs();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(carpetaTrabajador + "\\" + trabajador.getId() + ".trabajador");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(trabajador);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo que guarda los productos en disco
    public static void guardaProductoEnDisco(Producto producto) {
        File carpetaProductos = new File(leeRutaProductos());

        if (!carpetaProductos.exists()) carpetaProductos.mkdirs();
        try {
            FileOutputStream fos = new FileOutputStream(carpetaProductos + "\\" + producto.getId() + ".producto");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(producto);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo que lee la ruta de los productos
    public static String leeRutaProductos() {
        Properties prop = new Properties();

        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            return prop.getProperty("RUTA_PRODUCTOS", "data/productos");
        } catch (IOException e) {
            return "";
        }
    }

    // Metodo que lee la ruta de los administradores
    private static String leeRutaAdmins() {
        Properties prop = new Properties();

        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            return prop.getProperty("RUTA_ADMINISTRADORES", "data/usuarios/administradores");
        } catch (IOException e) {
            return "";
        }
    }

    // Metodo que lee la ruta de los trabajadores
    public static String leeRutaTrabajadores() {
        Properties prop = new Properties();

        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            return prop.getProperty("RUTA_TRABAJADORES", "data/usuarios/trabajadores");
        } catch (IOException e) {
            return "";
        }
    }

    // Metodo que lee la ruta de los clientes
    public static String leeRutaClientes() {
        Properties prop = new Properties();

        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            return prop.getProperty("RUTA_CLIENTES", "data/usuarios/clientes");
        } catch (IOException e) {
            return "";
        }
    }

    // Metodo que lee la ruta de los logs
    private static String leeRutaLogs() {
        Properties prop = new Properties();
        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            return prop.getProperty("RUTA_LOGS", "data/logs");
        } catch (IOException e) {
            return "";
        }
    }

    // Metodo que lee la ruta de los backups
    private static String leeRutaBackup() {
        Properties prop = new Properties();

        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            return prop.getProperty("RUTA_BACKUP", "data/backup");
        } catch (IOException e) {
            return "";
        }
    }

    // Metodo que guarda los inicio de sesion un log
    public static void guardaActividadInicioSesion(Object user) {
        File carpetaLog = new File(leeRutaLogs());

        if (!carpetaLog.exists()) carpetaLog.mkdirs();

        if (user instanceof Admin) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaLog + "\\" + "actividad.logs", true));
                bw.write("Inicio de sesión;" + (((Admin) user).getNombre()) + ";Administrador;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                bw.close();
            } catch (IOException e) {
                return;
            }
        }

        if (user instanceof Trabajador) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaLog + "\\" + "actividad.logs", true));
                bw.write("Inicio de sesión;" + (((Trabajador) user).getNombre()) + ";Trabajador;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                bw.close();
            } catch (IOException e) {
                return;
            }
        }

        if (user instanceof Cliente) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaLog + "\\" + "actividad.logs", true));
                bw.write("Inicio de sesión;" + (((Cliente) user).getNombre()) + ";Cliente;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                bw.close();
            } catch (IOException e) {
                return;
            }
        }

    }

    // Metodo que guarda los cierres de sesion un log
    public static void guardaActividadCierreSesion(Object user) {
        File carpetaLog = new File(leeRutaLogs());

        if (!carpetaLog.exists()) carpetaLog.mkdirs();

        if (user instanceof Admin) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaLog + "\\" + "actividad.logs", true));
                bw.write("Cierre sesión;" + ((Admin) user).getNombre() + ";Administrador;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                escribirUltimoCierreSesion(((Admin) user).getId());
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (user instanceof Trabajador) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaLog + "\\" + "actividad.logs", true));
                bw.write("Cierre sesión;" + ((Trabajador) user).getNombre() + ";Trabajador;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                escribirUltimoCierreSesion(((Trabajador) user).getId());
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (user instanceof Cliente) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaLog + "\\" + "actividad.logs", true));
                bw.write("Cierre sesión;" + ((Cliente) user).getNombre() + ";Cliente;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                escribirUltimoCierreSesion(((Cliente) user).getId());
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // Metodo que escribe el ultimo inicio de sesion del usuario (escribe en el properties)
    private static void escribirUltimoCierreSesion(int idUsuario) {
        Properties prop = new Properties();
        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            prop.setProperty(String.valueOf(idUsuario), Utils.formateaFechaLog(LocalDateTime.now()));
            prop.store(new FileOutputStream(RUTA_P), "Ultimo inicio sesión de " + idUsuario + " a las " + Utils.formateaFecha(LocalDate.now()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo que guarda los nuevos pedidos en un log
    public static void guardaActividadNuevoPedido(int idCliente, int idTrabajador) {
        File carpetaLog = new File(leeRutaLogs());
        if (!carpetaLog.exists()) carpetaLog.mkdirs();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaLog + "\\" + "actividad.logs", true));
            bw.write("Nuevo pedido;" + idCliente + ";" + idTrabajador + ";" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo que guarda los actualiza pedido en un log
    public static void guardaActividadActualizaPedido(Pedido pedido) {
        File carpetaLog = new File(leeRutaLogs());

        if (!carpetaLog.exists()) carpetaLog.mkdirs();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaLog + "\\" + "actividad.logs", true));
            bw.write("Actualiza pedido;" + pedido.getId() + ";" + pedido.devuelveEstado(pedido.getEstado()) + ";"
                    + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo que devuelve el ultimo inicio de sesion del usuario (busca en el properties)
    public static String ultimoInicioSesion(int idUsuario) {
        Properties prop = new Properties();

        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            return prop.getProperty(String.valueOf(idUsuario));
        } catch (IOException e) {
            return "";
        }
    }

    // Metodo que devuelve si hay acceso de invitados
    public static boolean accesoInvitado() {
        Properties prop = new Properties();

        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            return Boolean.parseBoolean(prop.getProperty("ACCESO_INVITADO", String.valueOf(false)));
        } catch (IOException e) {
            return false;
        }
    }

    // Metodo que rellena un ArrayList de String con el contenido del properties
    public static ArrayList<String> configuracionPrograma() {
        ArrayList<String> configuracion = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(RUTA_P));
            String linea = "";
            while (linea != null) {
                linea = br.readLine();
                if (linea != null) configuracion.add(linea);
            }
        } catch (IOException e) {
            return configuracion;
        }
        return configuracion;
    }

    // Metodo que crea una copia de seguridad en la ruta que nos pasen
    public static boolean creaBackup(String rutaBackup, Controlador controlador) {
        File carpetaBackup = new File(rutaBackup);

        if (!carpetaBackup.exists()) carpetaBackup.mkdirs();

        try {
            FileOutputStream fos = new FileOutputStream(carpetaBackup + "\\" + "copiaSeguridad.backup");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(controlador);
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Metodo que crea una copia de seguridad en la ruta por defecto
    public static boolean creaBackup(Controlador controlador) {
        File carpetaBackup = new File(leeRutaBackup());

        if (!carpetaBackup.exists()) carpetaBackup.mkdirs();

        try {
            FileOutputStream fos = new FileOutputStream(carpetaBackup + "\\" + "copiaSeguridad.backup");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(controlador);
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Metodo que recupera una copia de seguridad en la ruta que nos pasen
    public static Controlador recuperaBackup(String rutaBackup) {
        Controlador recuperado = null;

        File carpetaCopia = new File(rutaBackup);

        if (!carpetaCopia.exists()) return recuperado;
        try {
            FileInputStream fis = new FileInputStream(rutaBackup + "\\" + "copiaSeguridad.backup");
            ObjectInputStream ois = new ObjectInputStream(fis);
            recuperado = (Controlador) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return recuperado;
    }

    // Metodo que recupera una copia de seguridad en la ruta por defecto
    public static Controlador recuperaBackup() {
        Controlador recuperado = null;
        File carpetaCopia = new File(leeRutaBackup());
        if (!carpetaCopia.exists()) return null;

        try {
            FileInputStream fis = new FileInputStream(carpetaCopia + "\\" + "copiaSeguridad.backup");
            ObjectInputStream ois = new ObjectInputStream(fis);
            recuperado = (Controlador) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return recuperado;
    }

    // Metodo que borra al trabajador del disco
    public static void borraTrabajador(int idTrabajador) {
        File carpetaTrabajadores = new File(leeRutaTrabajadores());

        if (carpetaTrabajadores.exists()) {
            for (String archivo : carpetaTrabajadores.list()) {
                int idArchivo = Integer.parseInt(archivo.substring(0, archivo.indexOf(".")));
                if (idArchivo == idTrabajador) {
                    File trabajador = new File(leeRutaTrabajadores() + "\\" + archivo);
                    trabajador.delete();
                }
            }
        }
    }

 /*   public static void enviarExcelGuardadoPorCorreo(String destino) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(user, pass);
            }
        });
        try {

            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(user));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
            mensaje.setSubject("Listado de pedidos");

            // Cuerpo de texto
            MimeBodyPart cuerpoTexto = new MimeBodyPart();
            cuerpoTexto.setText("Hola,\n\nAdjunto te envío el archivo Excel con los pedidos.\n\nUn saludo.");

            // Adjuntar archivo
            MimeBodyPart adjunto = new MimeBodyPart();
            File archivo = new File(Persistencia.leeRutaDocumentos() + "\\Pedidos.xlsx");
            if (!archivo.exists()) {
                throw new FileNotFoundException("No se encontró el archivo en la ruta: " +
                        Persistencia.leeRutaDocumentos() + "\\Pedidos.xlsx");
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
            throw new RuntimeException(e);
        }
    }*/
}
