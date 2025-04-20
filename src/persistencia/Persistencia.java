package persistencia;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import controlador.Controlador;
import models.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Utils;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

public class Persistencia {

    public static final String RUTA_P = "./config/config.properties";

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

        if (carpetaTrabajadores.list() == null) return false;

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

        if (carpetaClientes.list() == null) return false;

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
            return;
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
            return;
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
            return;
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
            return prop.getProperty("RUTA_CLIENTES", "data/DDusuarios/clientes");
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

    // Metodo que lee la ruta de los documentos de excel
    public static String leeRutaDocumentosExcel() {
        Properties prop = new Properties();

        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            return prop.getProperty("RUTA_EXCEL", "data/documentos/excel");
        } catch (IOException e) {
            return "";
        }
    }

    // Metodo que lee la ruta de los documentos pdf
    public static String leeRutaDocumentosPdf() {
        Properties prop = new Properties();

        try {
            prop.load(new BufferedReader(new FileReader(RUTA_P)));
            return prop.getProperty("RUTA_PDF", "data/documentos/pdf");
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
                return;
            }
        }

        if (user instanceof Trabajador) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaLog + "\\" + "actividad.logs", true));
                bw.write("Cierre sesión;" + ((Trabajador) user).getNombre() + ";Trabajador;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                escribirUltimoCierreSesion(((Trabajador) user).getId());
                bw.close();
            } catch (IOException e) {
                return;
            }
        }

        if (user instanceof Cliente) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(carpetaLog + "\\" + "actividad.logs", true));
                bw.write("Cierre sesión;" + ((Cliente) user).getNombre() + ";Cliente;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                escribirUltimoCierreSesion(((Cliente) user).getId());
                bw.close();
            } catch (IOException e) {
                return;
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
            return;
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
            return;
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
            return;
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
            br.close();
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
            return null;
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
            return null;
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

    // Metodo que adjunta todos los correos
    public static void adjuntaCorreos(ArrayList<Pedido> correosAdj) {
        File carpetaDocumentos = new File(leeRutaDocumentosExcel());

        if (!carpetaDocumentos.exists()) carpetaDocumentos.mkdirs();
        String nombreArchivo = "Pedidos.xlsx";
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("Hoja 1");
        String[] encabezados = {"ID", "Fecha Pedido", "Fecha Entrega", "Estado", "Comentario", "Nº de productos"};
        int indiceFila = 0;

        Row fila = hoja.createRow(indiceFila);
        for (int i = 0; i < encabezados.length; i++) {
            String encabezado = encabezados[i];
            Cell celda = fila.createCell(i);
            celda.setCellValue(encabezado);
        }

        indiceFila++;
        for (int i = 0; i < correosAdj.size(); i++) {
            fila = hoja.createRow(indiceFila);
            Pedido pedido = correosAdj.get(i);
            fila.createCell(0).setCellValue(pedido.getId());
            fila.createCell(1).setCellValue(Utils.formateaFecha(pedido.getFechaPedido()));
            fila.createCell(2).setCellValue(Utils.formateaFecha(pedido.getFechaEntregaEstimada()));
            fila.createCell(3).setCellValue(pedido.devuelveEstado(pedido.getEstado()));
            fila.createCell(4).setCellValue(pedido.getComentario());
            fila.createCell(5).setCellValue(pedido.getProductos().size());
            indiceFila++;
        }

        // Guardamos
        File directorioActual = new File(carpetaDocumentos + "\\" + nombreArchivo);
        try {
            FileOutputStream fos = new FileOutputStream(directorioActual);
            libro.write(fos);
            libro.close();
        } catch (IOException e) {
            return;
        }
    }

    // Metodo que guarda un resumen del PDF
    public static void guardaResumenPedido(Pedido pedidoTemp) {
        File carpetaDocumentos = new File(leeRutaDocumentosPdf());
        if (!carpetaDocumentos.exists()) carpetaDocumentos.mkdirs();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(carpetaDocumentos, pedidoTemp.getId() + ".pdf")));
            document.open();
            document.add(new Paragraph("======== PEDIDO " + pedidoTemp.getId() + " ========\n"));
            document.add(new Paragraph("Fecha de pedido: " + Utils.formateaFecha(pedidoTemp.getFechaPedido()) + "\n"));
            document.add(new Paragraph("Fecha de entrega: " + Utils.formateaFecha(pedidoTemp.getFechaEntregaEstimada()) + "\n"));
            document.add(new Paragraph("Estado: " + pedidoTemp.devuelveEstado(pedidoTemp.getEstado()) + "\n"));
            document.add(new Paragraph("Comentario: " + pedidoTemp.getComentario() + "\n"));
            document.add(new Paragraph("Productos: \n" + pedidoTemp.pintaProductos(pedidoTemp.getProductos()) + "\n"));
            document.add(new Paragraph("El total del pedido es: " + pedidoTemp.precioFinal() + "€"));
            document.close();
        } catch (IOException | DocumentException e) {
            return;
        }
    }

    // Metodo que borra clientes en disco
    public static void borraCliente(int idCliente) {
        File carpetaClientes = new File(leeRutaClientes());

        if (carpetaClientes.exists()) {
            for (String archivo : carpetaClientes.list()) {
                int idArchivo = Integer.parseInt(archivo.substring(0, archivo.indexOf(".")));
                if (idArchivo == idCliente) {
                    File cliente = new File(leeRutaClientes() + "\\" + archivo);
                    cliente.delete();
                }
            }
        }
    }

    // Metodo que borra productos en disco
    public static void borraProductoEnDisco(int idProducto) {
        File carpetaProductos = new File(leeRutaProductos());

        if (carpetaProductos.exists()) {
            for (String archivo : carpetaProductos.list()) {
                int idArchivo = Integer.parseInt(archivo.substring(0, archivo.indexOf(".")));
                if (idArchivo == idProducto) {
                    File producto = new File(leeRutaProductos() + "\\" + archivo);
                    producto.delete();
                }
            }
        }
    }
}
