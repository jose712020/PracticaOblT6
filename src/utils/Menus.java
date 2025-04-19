package utils;

import controlador.Controlador;
import models.Admin;
import models.Cliente;
import models.Trabajador;

import java.util.Scanner;

public class Menus {
    public static final Scanner S = new Scanner(System.in);

    //Menu del administrador
    public static void menuAdministrador(Controlador controlador, Admin admin) {
        if (controlador.ultimoInicioSesion(admin.getId()) != null) {
            System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║\t\tUsted inició sesión por última vez el " + controlador.ultimoInicioSesion(admin.getId()) + "\t\t ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        }
        System.out.print("""
                1.- Ver todo el catálogo
                2.- Editar un producto
                3.- Ver un resumen de todos los Clientes
                4.- Ver un resumen de todos los Pedidos
                5.- Ver un resumen de todos los Trabajadores
                6.- Ver las estadísticas de la aplicación
                7.- Cambiar el estado de un pedido
                8.- Dar de alta un trabajador
                9.- Dar de baja un trabajador
                10.- Asignar un pedido a un trabajador
                11.- Muestra configuración del programa
                12.- Envía listado de pedidos por correo
                13.- Copia de seguridad
                14.- Salir
                Introduce una opción:""");
    }

    // Menu del cliente
    public static void menuCliente(Controlador controlador, Cliente cliente) {
        int pedidosPendientes = controlador.numPedidosPendientesCliente(cliente.getId());
        System.out.println("FERNANSHOP");
        System.out.print("Bienvenido " + cliente.getNombre());
        System.out.println(". Actualmente tiene " + pedidosPendientes + ((pedidosPendientes == 1) ?
                " pedido pendiente" : " pedidos pendientes") + " de entrega");

        if (controlador.ultimoInicioSesion(cliente.getId()) != null) {
            System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║\t\tUsted inició sesión por última vez el " + controlador.ultimoInicioSesion(cliente.getId()) + "\t\t ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        }

        System.out.print("""
                1.- Consultar el catálogo de productos
                2.- Realizar un pedido
                3.- Ver mis pedidos
                4.- Ver mis datos personales
                5.- Modificar mis datos personales
                6.- Salir
                Introduce una opción:""");
    }

    //Menu del trabajador
    public static void menuTrabajador(Controlador controlador, Trabajador trabajador) {
        System.out.println("Bienvenido " + trabajador.getNombre() + ". Tienes " + trabajador.numPedidosPendientes() +
                (trabajador.numPedidosPendientes() == 1 ? " pedido" : " pedidos") + " que gestionar.");
        if (controlador.ultimoInicioSesion(trabajador.getId()) != null) {
            System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║\t\tUsted inició sesión por última vez el " + controlador.ultimoInicioSesion(trabajador.getId()) + "\t\t ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        }
        System.out.print("""
                1.- Consultar los pedidos que tengo asignados
                2.- Modificar el estado de un pedido
                3.- Consultar el catálogo de productos
                4.- Modificar un producto
                5.- Ver el histórico de pedidos terminados
                6.- Ver mi perfil
                7.- Modificar mis datos personales
                8.- Salir
                Introduce una opción:""");
    }

    // Logo del menú principal
    public static void portada() {
        System.out.println("""
                
                ███████╗███████╗██████╗ ███╗   ██╗ █████╗ ███╗   ██╗███████╗██╗  ██╗ ██████╗ ██████╗\s
                ██╔════╝██╔════╝██╔══██╗████╗  ██║██╔══██╗████╗  ██║██╔════╝██║  ██║██╔═══██╗██╔══██╗
                █████╗  █████╗  ██████╔╝██╔██╗ ██║███████║██╔██╗ ██║███████╗███████║██║   ██║██████╔╝
                ██╔══╝  ██╔══╝  ██╔══██╗██║╚██╗██║██╔══██║██║╚██╗██║╚════██║██╔══██║██║   ██║██╔═══╝\s
                ██║     ███████╗██║  ██║██║ ╚████║██║  ██║██║ ╚████║███████║██║  ██║╚██████╔╝██║    \s
                ╚═╝     ╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚═╝    \s
                                                                                                    \s
                """);
    }
}
