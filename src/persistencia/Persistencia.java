package persistencia;

import models.Admin;
import models.Cliente;
import models.Producto;
import models.Trabajador;

import java.io.*;
import java.util.ArrayList;

public class Persistencia {
    // Metodo que lee el producto de los catalogos
    public static ArrayList<Producto> leeCatalogo() {
        ArrayList<Producto> productos = new ArrayList<>();
        File carpetaProductos = new File("data/productos");

        if (!carpetaProductos.exists()) carpetaProductos.mkdirs();

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
        File carpetaProductos = new File("data/productos");

        if (!carpetaProductos.exists()) carpetaProductos.mkdirs();

        for (Producto producto : catalogo) {
            guardaProductoEnDisco(producto);
        }
    }

    // Metodo que lee los administradores
    public static ArrayList<Admin> leeAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        File carpetaAdmins = new File("data/usuarios/administradores");

        if (!carpetaAdmins.exists()) carpetaAdmins.mkdirs();

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
        File carpetaAdmins = new File("data/usuarios/administradores");

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
        File carpetaTrabajadores = new File("data/usuarios/trabajadores");

        if (!carpetaTrabajadores.exists()) carpetaTrabajadores.mkdirs();

        for (String archivo : carpetaTrabajadores.list()) {
            try {
                Trabajador trabajador;
                FileInputStream fis = new FileInputStream(carpetaTrabajadores + "\\" + archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                trabajador = (Trabajador) ois.readObject();
                if (trabajador.getId() == 100000) return true;
            } catch (IOException | ClassNotFoundException e) {
                return false;
            }
        }
        return false;
    }

    // Funcion que busca el ID del cliente de prueba
    private static boolean buscaClientePrueba() {
        File carpetaClientes = new File("data/usuarios/clientes");

        if (!carpetaClientes.exists()) carpetaClientes.mkdirs();

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
                return false;
            }
        }
        return false;
    }

    // Metodo que lee los clientes en disco
    public static ArrayList<Cliente> leeClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        File carpetaClientes = new File("data/usuarios/clientes");

        if (!carpetaClientes.exists()) carpetaClientes.mkdirs();

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
        File carpetaTrabajadores = new File("data/usuarios/trabajadores");

        if (!carpetaTrabajadores.exists()) carpetaTrabajadores.mkdirs();

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
    public static void guardaClientesEnDisco(Cliente cliente) {
        File carpetaClientes = new File("data/usuarios/clientes");

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
        File carpetaTrabajador = new File("data/usuarios/trabajadores");

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

    public static void guardaProductoEnDisco(Producto producto) {
        File carpetaProductos = new File("data/productos");

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
}
