package view;

import controlador.Controlador;
import models.*;
import comunicaciones.Comunicaciones;
import utils.Menus;
import utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class main {
    public static final Scanner S = new Scanner(System.in);

    public static void main(String[] args) {
        Controlador controlador = new Controlador();

        if (!controlador.buscaDatosPrueba()) iniciaDatosPrueba(controlador);

        do {
            Menus.portada();

            System.out.println("            Bienvenidos a nuestra tienda online");
            System.out.println("============================================================");
            Object user = menuInicio(controlador);
            if (user != null) {
                menuUsuario(controlador, user);
            }
        } while (true);
    }

    private static Object menuInicio(Controlador controlador) {
        Object usuarioLogueado = null;
        int op = 0;
        boolean excepcion = false;
        do {
            do {
                excepcion = false;
                System.out.print("""
                        1. Ver el catálogo
                        2. Registrarse
                        3. Iniciar sesion
                        Marque su opción:""");
                try {
                    op = Integer.parseInt(S.nextLine());
                } catch (NumberFormatException e) {
                    excepcion = true;
                    System.out.println("Introduzca un valor numérico");
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                }
            } while (excepcion);

            switch (op) {
                case 1: // Ver el catálogo
                    verCatalogo(controlador);
                    break;
                case 2: // Registrase
                    registraCliente(controlador);
                    break;
                case 3: // Iniciar sesión
                    usuarioLogueado = iniciaSesion(controlador);
                    if (usuarioLogueado == null) System.out.println("No se ha encontrado ningún usuario...");
                    break;
                default: // Op incorrecta
                    System.out.println("Opción incorrecta...");
                    break;
            }
            Utils.pulsaContinuar();
            Utils.limpiarpantalla();
        } while (usuarioLogueado == null);
        return usuarioLogueado;
    }

    // Funcion que simula un mock
    private static void iniciaDatosPrueba(Controlador controlador) {
        boolean datosIniciados = false;

        System.out.println("¿Quieres iniciar el programa con datos de prueba? (S/N)");
        String iniciaMockTeclado = S.nextLine();

        if (iniciaMockTeclado.equalsIgnoreCase("s")) {
            datosIniciados = true;
            controlador.mock(datosIniciados);
            System.out.println("""
                    Iniciando usuarios de prueba...
                    Cliente:
                        Email: cliente@cliente
                        Nombre: cliente
                        Clave: cliente
                    
                    Trabajador:
                        Email: trabajador@trabajador
                        Nombre: trabajador
                        Clave: trabajador
                    """);
            Utils.pulsaContinuar();
            Utils.limpiarpantalla();
        } else Utils.limpiarpantalla();

    }

    private static Object iniciaSesion(Controlador controlador) {
        Object usuario = null;
        String op, email, pass;
        System.out.print("""
                ¿Cómo quieres loguearte?
                1. Cliente
                2. Trabajador
                3. Administrador
                Marque una opción:""");
        op = S.nextLine();

        switch (op) {
            case "1", "2", "3": //Cliente, Trabajador, Administrador
                System.out.print("Introduce tu email: ");
                email = S.nextLine();
                System.out.print("Introduce tu clave: ");
                pass = S.nextLine();

                usuario = controlador.login(email, pass);
                break;
            default:
                System.out.println("Opción incorrecta...");
                Utils.pulsaContinuar();
                Utils.limpiarpantalla();
                break;
        }
        return usuario;
    }

    private static void registraCliente(Controlador controlador) {
        System.out.println("¿Estás seguro que deseas registrarte? (S/N)");
        String respuesta = S.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            boolean bandera = false;

            String email = compruebaCorreo(controlador);
            System.out.print("Introduce la clave: ");
            String clave = S.nextLine();
            System.out.print("Introduce tu nombre: ");
            String nombre = S.nextLine();
            System.out.print("Introduce la localidad: ");
            String localidad = S.nextLine();
            System.out.print("Introduce la provincia: ");
            String provincia = S.nextLine();
            System.out.print("Introduce la dirección: ");
            String direccion = S.nextLine();

            int movil = 0;

            do {
                System.out.print("Introduce el número de teléfono: ");
                try {
                    movil = Integer.parseInt(S.nextLine());
                    bandera = true;
                } catch (NumberFormatException e) {
                    System.out.println("Debes introducer números...");
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                }
            } while (!bandera);

            if (controlador.nuevoCliente(email, clave, nombre, localidad, provincia, direccion, movil)) {
                Cliente cliente = null;
                for (Cliente c : controlador.getClientes()) {
                    if (c.getEmail().equals(email)) cliente = c;
                }

                String token = controlador.generaToken(cliente);

                System.out.println("Registrado correctamente...");
                Comunicaciones.enviaCorreoToken(email, "¡Hola! Bienvenido a FERNANSHOP " + nombre + ", " +
                        "tu token de verificación de la cuenta es", "TU CÓDIGO DE VERIFICACIÓN DE CUENTA", token, nombre);

            } else System.out.println("Ha ocurrido un error...");
        }
    }

    // Funcion en el que ves el catálogo
    private static void verCatalogoAdmin(Controlador controlador) {
        int cont = 0;
        for (Producto producto : controlador.getCatalogo()) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
            if (producto.getRelevancia() > 9)
                System.out.println("╠══════════════════════════════ PROMO ESPECIAL ════════════════════════════════╣");
            System.out.println("║\t- ID: " + producto.getId());
            System.out.println("║\t- Marca: " + producto.getMarca() + " - Modelo: " + producto.getModelo());
            System.out.println("║\t- Descripción: " + producto.getDescripcion());
            System.out.println("║\t- Precio: " + producto.getPrecio());
            cont++;
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝\n");
            if (cont == 5) {
                Utils.pulsaContinuar();
                cont = 0;
            }
        }
    }

    // Funcion en el que ves el catálogo
    private static void verCatalogo(Controlador controlador) {
        int cont = 0;
        for (Producto producto : controlador.getCatalogo()) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║\t- Marca: " + producto.getMarca() + " - Modelo: " + producto.getModelo());
            System.out.println("║\t- Descripción: " + producto.getDescripcion());
            System.out.println("║\t- Precio: " + producto.getPrecio());
            cont++;
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝\n");
            if (cont == 5) {
                Utils.pulsaContinuar();
                cont = 0;
            }
        }
    }

    // Funcion que nos llevara a un menu específico (Admin, Trabajador o Cliente)
    private static void menuUsuario(Controlador controlador, Object user) {
        menuAdmin(controlador, user);
        menuTrabajadores(controlador, user);
        menuClientes(controlador, user);
    }

    // Menu del cliente
    private static void menuClientes(Controlador controlador, Object user) {
        String op;
        for (Cliente cliente : controlador.getClientes()) {
            if (user.equals(cliente)) {

                compruebaToken(controlador, cliente);

                if (cliente.isValid()) {
                    do {
                        Menus.menuCliente(controlador, cliente);
                        op = S.nextLine();
                        switch (op) {
                            case "1"://Consultar el catálogo de productos
                                Utils.limpiarpantalla();
                                consultaCatalogo(controlador);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "2"://Realizar un pedido
                                Utils.limpiarpantalla();
                                realizaPedidoMenu(controlador, cliente);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "3"://Ver mis pedidos
                                Utils.limpiarpantalla();
                                verMisPedidosCliente(cliente);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "4"://Ver mis datos personales
                                Utils.limpiarpantalla();
                                pintaPerfilCliente(cliente);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "5"://Modificar mis datos personales
                                Utils.limpiarpantalla();
                                modificaDatosPersonalesCliente(controlador, cliente);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "6":// Salir
                                Utils.animacionFinSesion();
                                Utils.limpiarpantalla();
                                break;
                            default://Opción no existente
                                Utils.limpiarpantalla();
                                System.out.println("Opción incorrecta...");
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                        }
                    } while (cliente.isValid() && !op.equals("6"));
                }
            }
        } // Bucle de clientes
    }

    // Menu del trabajador
    private static void menuTrabajadores(Controlador controlador, Object user) {
        String op;
        for (Trabajador trabajador : controlador.getTrabajadores()) {
            if (user.equals(trabajador)) {
                compruebaToken(controlador, trabajador);
                if (trabajador.isValid()) {
                    do {
                        Menus.menuTrabajador(controlador, trabajador);
                        op = S.nextLine();
                        switch (op) {
                            case "1": //Consultar los pedidos que tengo asignados
                                Utils.limpiarpantalla();
                                consultaPedidoAsignados(controlador, trabajador);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "2": //Modificar el estado de un pedido
                                Utils.limpiarpantalla();
                                modificaPedido(controlador, trabajador);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "3": //Consultar el catálogo de productos
                                Utils.limpiarpantalla();
                                verCatalogo(controlador);
                                Utils.limpiarpantalla();
                                break;
                            case "4": //Modificar un producto
                                Utils.limpiarpantalla();
                                modificaProducto(controlador);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "5": //Ver el histórico de pedidos terminados
                                Utils.limpiarpantalla();
                                historicoPedidosTerminados(controlador, trabajador);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "6": //Ver mi perfil
                                Utils.limpiarpantalla();
                                pintaPerfilTrabajador(trabajador);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "7": //Modificar mis datos personales
                                Utils.limpiarpantalla();
                                modificaDatosPersonalesTrabajador(controlador, trabajador);
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                            case "8": //Salir
                                Utils.animacionFinSesion();
                                Utils.limpiarpantalla();
                                break;
                            default://Opción no existente
                                System.out.println("Opción incorrecta...");
                                Utils.pulsaContinuar();
                                Utils.limpiarpantalla();
                                break;
                        }
                    } while (trabajador.isValid() && !op.equals("8"));
                }
            }
        } // Bucle de trabajadores
    }

    // Menu del admin
    private static void menuAdmin(Controlador controlador, Object user) {
        String op;
        for (Admin admin : controlador.getAdmins()) {
            if (user.equals(admin)) {
                do {
                    estadisticasApp(controlador);
                    System.out.println();
                    Menus.menuAdministrador(controlador, admin);
                    op = S.nextLine();
                    switch (op) {
                        case "1": //Ver to el catálogo
                            Utils.limpiarpantalla();
                            verCatalogoAdmin(controlador);
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                        case "2": //Editar un producto
                            Utils.limpiarpantalla();
                            modificaProducto(controlador);
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                        case "3": //Ver un resumen de todos los Clientes
                            Utils.limpiarpantalla();
                            resumenClientes(controlador);
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                        case "4": //Ver un resumen de todos los Pedidos
                            Utils.limpiarpantalla();
                            resumenPedidosAdmin(controlador);
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                        case "5": // Ver un resumen de todos los Trabajadores
                            Utils.limpiarpantalla();
                            resumenTrabajadores(controlador);
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                        case "6": //Ver las estadísticas de la aplicación
                            Utils.limpiarpantalla();
                            estadisticasApp(controlador);
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                        case "7": //Cambiar el estado de un pedido
                            Utils.limpiarpantalla();
                            modificaPedido(controlador, admin);
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                        case "8": //Dar de alta un trabajador
                            Utils.limpiarpantalla();
                            altaTrabajador(controlador);
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                        case "9": //Dar de baja un trabajador
                            Utils.limpiarpantalla();
                            bajaTrabajador(controlador);
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                        case "10": //Asignar un pedido a un trabajador
                            Utils.limpiarpantalla();
                            asisgnaPedido(controlador, admin);
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                        case "11": //Salir
                            Utils.animacionFinSesion();
                            Utils.limpiarpantalla();
                            break;
                        default://Opción no existente
                            System.out.println("Opción incorrecta...");
                            Utils.pulsaContinuar();
                            Utils.limpiarpantalla();
                            break;
                    }
                } while (!op.equals("11"));
            }
        } // Bucle de admin
    }

    // Funcion que muestra el historial de pedidos terminados
    private static void historicoPedidosTerminados(Controlador controlador, Trabajador trabajador) {
        if (controlador.getPedidosCompletadosTrabajador(trabajador.getId()).isEmpty())
            System.out.println("No tienes ningún pedido...");
        else {
            ArrayList<PedidoClienteDataClass> pedidosTerminados = controlador.getPedidosCompletadosTrabajador(trabajador.getId());
            int cont = 1;

            Collections.sort(pedidosTerminados);
            System.out.println("""
                    ╔════════════════════════════════════════════════════╗
                    ║                 PEDIDOS TERMINADOS                 ║
                    ╚════════════════════════════════════════════════════╝""");
            for (PedidoClienteDataClass p : pedidosTerminados) {
                System.out.println(cont + ".- " + p);
            }
        }
    }

    // Funcion que hace que el administrador elija un pedido para asignar a un trabajador
    private static void asisgnaPedido(Controlador controlador, Admin admin) {
        ArrayList<Pedido> pedidosSinAsignar = controlador.pedidosSinTrabajador();

        if (pedidosSinAsignar.isEmpty())
            System.out.println("No se ha realizado ningún pedido o no hay pedidos para asignar...");
        else if (controlador.getTrabajadores().isEmpty()) System.out.println("No hay trabajadores...");
        else {
            Pedido pedidoTemp = null;
            Trabajador trabajadorTemp = null;
            int cont = 1;

            pintaPedidosSinAsignar(controlador, pedidosSinAsignar);

            System.out.print("Introduce el pedido que deseas asignar: ");
            String pedidoSeleccionado = S.nextLine();

            try {
                pedidoTemp = pedidosSinAsignar.get(Integer.parseInt(pedidoSeleccionado) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Error al elegir pedido...");
            }

            for (Trabajador t : controlador.getTrabajadores()) {
                pintaResumenTrabajador(cont, t);
                cont++;
            }

            System.out.print("Introduce el trabajador para asignar un pedido: ");
            String trabajadorSeleccionado = S.nextLine();
            try {
                trabajadorTemp = controlador.getTrabajadores().get(Integer.parseInt(trabajadorSeleccionado) - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Error al elegir trabajador...");
            }


            if (pedidoTemp == null || trabajadorTemp == null) System.out.println("No se han encontrado los datos...");
            else {
                if (controlador.asignaPedido(pedidoTemp.getId(), trabajadorTemp.getId())) {
                    System.out.println("Pedido asignado a " + trabajadorTemp.getNombre() + " con éxito...");
                    Comunicaciones.enviaMensajeTelegramTrabajador(trabajadorTemp.getNombre() + " se te ha asignado el pedido: " + pedidoTemp.getId());

                    PedidoClienteDataClass dataTemp = null;
                    for (Trabajador t : controlador.getTrabajadores()) {
                        for (PedidoClienteDataClass p : controlador.getPedidosAsignadosTrabajador(t.getId())) {
                            if (p.getIdPedido() == pedidoTemp.getId()) dataTemp = p;
                        }
                    }
                    Comunicaciones.enviaCorreoPedidoAsignacion(trabajadorTemp.getEmail(), "ASIGNACIÓN DE PEDIDOS", dataTemp);
                } else System.out.println("Ha ocurrido un error...");
            }

        }
    }

    // Funcion que pinta los pedidos sin asignar
    private static void pintaPedidosSinAsignar(Controlador controlador, ArrayList<Pedido> pedidosSinAsignar) {
        pintaPedidosSinData(controlador, pedidosSinAsignar);
    }

    // Funcion que consulta los pedidos asignados del trabajador
    private static void consultaPedidoAsignados(Controlador controlador, Trabajador trabajador) {
        if (trabajador.numPedidosPendientes() == 0) System.out.println("No tienes pedidos pendientes...");
        else {
            ArrayList<PedidoClienteDataClass> pedidosAsignados = controlador.getPedidosAsignadosTrabajador(trabajador.getId());
            int cont = 1;

            Collections.sort(pedidosAsignados);
            System.out.println("""
                    ╔════════════════════════════════════════════════════╗
                    ║                 PEDIDOS ASIGNADOS                  ║
                    ╚════════════════════════════════════════════════════╝""");
            for (PedidoClienteDataClass p : pedidosAsignados) {
                System.out.println(cont + ".- " + p);
                cont++;
            }
        }
    }

    // Funcion que muestra los pedidos que se han realizado
    private static void resumenPedidosAdmin(Controlador controlador) {
        if (controlador.getTodosPedidos().isEmpty()) System.out.println("No se han realizado pedidos...");
        else {
            ArrayList<Pedido> pedidosEntregados = new ArrayList<>();
            ArrayList<Pedido> pedidosCancelados = new ArrayList<>();
            ArrayList<Pedido> pedidosPendientes = new ArrayList<>();

            for (Cliente c : controlador.getClientes()) {
                for (Pedido p : c.getPedidos()) {
                    if (p.getEstado() == 3) pedidosEntregados.add(p);
                }
            }

            for (Cliente c : controlador.getClientes()) {
                for (Pedido p : c.getPedidos()) {
                    if (p.getEstado() == 4) pedidosCancelados.add(p);
                }
            }

            for (Cliente c : controlador.getClientes()) {
                for (Pedido p : c.getPedidos()) {
                    if (p.getEstado() == 0 || p.getEstado() == 1 || p.getEstado() == 2) pedidosPendientes.add(p);
                }
            }

            if (!pedidosEntregados.isEmpty()) {
                Collections.sort(pedidosEntregados);
                System.out.println("""
                        ╔════════════════════════════════════════════════════╗
                        ║                PEDIDOS ENTREGADOS                  ║
                        ╚════════════════════════════════════════════════════╝""");
                for (Pedido p : pedidosEntregados) {
                    System.out.println(p);
                }
            }
            if (!pedidosCancelados.isEmpty()) {
                Collections.sort(pedidosCancelados);
                System.out.println("""
                        ╔════════════════════════════════════════════════════╗ 
                        ║                PEDIDOS CANCELADOS                  ║
                        ╚════════════════════════════════════════════════════╝""");
                for (Pedido p : pedidosCancelados) {
                    System.out.println(p);
                }
            }
            if (!pedidosPendientes.isEmpty()) {
                Collections.sort(pedidosPendientes);
                System.out.println("""
                        ╔════════════════════════════════════════════════════╗ 
                        ║                PEDIDOS PENDIENTES                  ║
                        ╚════════════════════════════════════════════════════╝""");
                for (Pedido p : pedidosPendientes) {
                    System.out.println(p);
                }
            }
        }
    }

    // Submenu para realizar un pedido
    private static void realizaPedidoMenu(Controlador controlador, Cliente cliente) {
        String op;

        do {
            System.out.printf("""
                    Actualmente tiene %d productos en su carro.
                    1. Inserta un producto en el carro
                    2. Ver el carro
                    3. Eliminar un producto del carro
                    4. Confirmar el pedido
                    5. Cancelar el pedido
                    6. Salir
                    Introduce una opción:""", cliente.numProductosCarro());
            op = S.nextLine();

            switch (op) {
                case "1": //Inserta un producto en el carro
                    Utils.limpiarpantalla();
                    insertaProducto(controlador, cliente);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "2": //Ver el carro
                    Utils.limpiarpantalla();
                    verCarroCliente(cliente);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "3": //Eliminar un producto del carro
                    Utils.limpiarpantalla();
                    eliminaProducto(controlador, cliente);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "4": //Confirmar el pedido
                    Utils.limpiarpantalla();
                    confirmaPedido(controlador, cliente);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "5": //Cancelar el pedido
                    Utils.limpiarpantalla();
                    cancelaPedido(controlador, cliente);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "6": //Salir
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta...");
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
            }
        } while (!op.equals("6"));
    }

    // Funcion que cancela un pedido del cliente
    private static void cancelaPedido(Controlador controlador, Cliente cliente) {
        ArrayList<Pedido> pedidosRealizados = cliente.getPedidos();

        if (pedidosRealizados.isEmpty()) System.out.println("No se han realizado pedidos...");
        else {
            Pedido temp = seleccionaPedidoCliente(controlador, cliente);

            if (temp == null) System.out.println("No hay pedidos para cancelar...");
            else {
                System.out.println("¿Deseas cancelar el pedido? (S/N)");
                String cancelaPedido = S.nextLine();

                if (cancelaPedido.equalsIgnoreCase("s"))
                    if (controlador.cancelaPedidoCliente(cliente.getId(), temp.getId()))
                        System.out.println("El pedido se ha cancelado con éxito...");
            }
        }
    }

    // Funcion que selecciona un pedido en el cliente
    private static Pedido seleccionaPedidoCliente(Controlador controlador, Cliente cliente) {
        ArrayList<Pedido> pedidos = cliente.getPedidos();

        if (pedidos == null) return null;
        if (pedidos.isEmpty()) return null;

        ArrayList<Pedido> pedidosSinCancelado = new ArrayList<>();

        for (Pedido p : pedidos) {
            if (p.getEstado() != 4) pedidosSinCancelado.add(p);
        }

        if (pedidosSinCancelado.isEmpty()) return null;

        int cont = 1;

        System.out.print("""
                ╔════════════════════════════════════════════════════╗
                ║                  CANCELA PEDIDOS                   ║
                ╚════════════════════════════════════════════════════╝
                """);
        for (Pedido p : pedidos) {
            System.out.println("\t" + cont + ".- " + p);
            cont++;
        }

        System.out.print("Introduce el pedido: ");
        String pedidoSeleccionado = S.nextLine();

        Pedido pedidoElegido = null;
        try {
            pedidoElegido = pedidos.get(Integer.parseInt(pedidoSeleccionado) - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error al elegir pedido...");
        }

        if (pedidoElegido == null) return null;

        return controlador.buscaPedidoById(pedidoElegido.getId());
    }

    // Funcion que confirma un pedido del cliente
    private static void confirmaPedido(Controlador controlador, Cliente cliente) {
        if (cliente.numProductosCarro() == 0) System.out.println("No tienes productos en el carro...");
        else {
            System.out.printf("El total a pagar con IVA es: %.2f\n", cliente.precioCarroSinIva(Utils.IVA));
            System.out.println("¿Deseas confirmar el pedido? (S/N)");
            String confirmaPedido = S.nextLine();

            if (confirmaPedido.equalsIgnoreCase("s")) {
                if (controlador.confirmaPedidoCliente(cliente.getId()))
                    System.out.println("El pedido se ha realizado con éxito...");
                Comunicaciones.enviaCorreoPedidoCliente(cliente.getEmail(), "PEDIDO REALIZADO CON ÉXITO", cliente.getPedidos().getLast());
            } else System.out.println("La confirmación del pedido se ha cancelado...");
        }
    }

    // Funcion que elimina un producto del carrito
    private static void eliminaProducto(Controlador controlador, Cliente cliente) {
        if (cliente.getCarro().isEmpty()) System.out.println("No hay productos en el carrito...");
        else {
            Producto temp = null;
            int cont = 1;

            System.out.println("""
                    ╔════════════════════════════════════════════════════╗
                    ║                      CARRITO                       ║
                    ╚════════════════════════════════════════════════════╝""");
            for (Producto p : cliente.getCarro()) {
                System.out.println("\t" + cont + ".- " + p.getMarca() + " - " + p.getModelo() + " (" + p.getPrecio() + ")");
                cont++;
            }
            System.out.printf("Total con IVA: %.2f\n\n", cliente.precioCarroSinIva(Utils.IVA));


            System.out.print("Introduce el producto que quieres quitar: ");
            String productoSeleccionado = S.nextLine();

            try {
                temp = cliente.getCarro().get(Integer.parseInt(productoSeleccionado) - 1);
                temp = controlador.buscaProductoById(temp.getId());
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Error al elegir pedido...");
            }

            if (temp == null) System.out.println("No se ha encontrado ningún producto...");
            else {
                if (controlador.quitaProductoCarroCliente(cliente, temp.getId()))
                    System.out.println("El producto se ha eliminado del carrito...");
                else System.out.println("Ha ocurrido un error al añadir el producto al carrito...");
            }
        }
    }

    // Funcion que ve el carro de un cliente
    private static void verCarroCliente(Cliente cliente) {
        if (cliente.numProductosCarro() == 0) System.out.println("El carro está vacío...");
        else {
            System.out.println("""
                    ╔════════════════════════════════════════════════════╗
                    ║                      CARRITO                       ║
                    ╚════════════════════════════════════════════════════╝""");

            // TODO arreglar que no se repita un pedido
            for (Producto p : cliente.getCarro()) {
                int contProducto = 0;
                for (Producto productoRepetido : cliente.getCarro()) {
                    if (p.getId() == productoRepetido.getId()) contProducto++;
                }
                System.out.println("\t- " + p.getMarca() + " - " + p.getModelo() + " (" + p.getPrecio() + ") (" + (contProducto) + ")");
            }

            System.out.printf("Total con IVA: %.2f\n", cliente.precioCarroSinIva(Utils.IVA));
        }
    }

    // Funcion que inserta un producto en el carro
    private static void insertaProducto(Controlador controlador, Cliente cliente) {
        Producto temp = null;
        String productoSeleccionado;
        int cont = 1;

        for (Producto p : controlador.getCatalogo()) {
            System.out.println(cont + ".- " + p.getMarca() + " - " + p.getModelo() + " (" + p.getPrecio() + ")");
            cont++;
        }

        System.out.print("Introduce el número del producto: ");
        productoSeleccionado = S.nextLine();

        try {
            temp = controlador.getCatalogo().get(Integer.parseInt(productoSeleccionado) - 1);
            temp = controlador.buscaProductoById(temp.getId());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error al elegir pedido...");
        }

        if (temp != null && controlador.addProductoCarrito(cliente, temp.getId()))
            System.out.println("El producto se ha añadido al carrito correctamente...");
        else System.out.println("No se ha encontrado ningún producto...");
    }

    // Funcion que simula un submenu para buscar productos en el catálogo
    private static void consultaCatalogo(Controlador controlador) {
        String op;
        do {
            System.out.print("""
                    1. Ver todo el catálogo
                    2. Búsqueda por marca
                    3. Búsqueda por modelo
                    4. Búsqueda por descripción
                    5. Búsqueda por término
                    6. Búsqueda por precio
                    7. Salir
                    Introduce la opción que deseas:""");
            op = S.nextLine();

            switch (op) {
                case "1": //Ver to el catálogo
                    Utils.limpiarpantalla();
                    verCatalogo(controlador);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "2": //Búsqueda por marca
                    Utils.limpiarpantalla();
                    buscaProductosByMarca(controlador);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "3": //Búsqueda por modelo
                    Utils.limpiarpantalla();
                    buscaProductosByModelo(controlador);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "4": //Búsqueda por descripción
                    Utils.limpiarpantalla();
                    buscaProductosByDescripcion(controlador);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "5": //Búsqueda por término
                    Utils.limpiarpantalla();
                    buscaProductosByTermino(controlador);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "6": //Búsqueda por precio
                    Utils.limpiarpantalla();
                    buscaProductosByPrecio(controlador);
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
                case "7": //Salir
                    break;
                default:
                    System.out.println("Opción incorrecta...");
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                    break;
            }
        } while (!op.equals("7"));

    }

    // Funcion que busca los productos por precio
    private static void buscaProductosByPrecio(Controlador controlador) {
        float precioMin = Integer.MAX_VALUE, precioMax = Integer.MIN_VALUE;
        boolean continuar = false;

        do {
            System.out.print("Introduce el precio mínimo: ");
            try {
                precioMin = Float.parseFloat(S.nextLine());
                continuar = true;
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número...");
                Utils.pulsaContinuar();
                Utils.limpiarpantalla();
            }
        } while (!continuar);

        continuar = false;

        do {
            System.out.print("Introduce el precio máximo: ");
            try {
                precioMax = Float.parseFloat(S.nextLine());
                continuar = true;
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número...");
                Utils.pulsaContinuar();
                Utils.limpiarpantalla();
            }
        } while (!continuar);

        pintaProductos(controlador.buscaProductosByPrecio(precioMin, precioMax));
    }

    // Funcion que busca los productos por un término
    private static void buscaProductosByTermino(Controlador controlador) {
        System.out.print("Introduce la descripción: ");
        String terminoTeclado = S.nextLine();

        pintaProductos(controlador.buscaProductosByTermino(terminoTeclado));
    }

    // Funcion que busca los productos por su descripcion
    private static void buscaProductosByDescripcion(Controlador controlador) {
        System.out.print("Introduce la descripción: ");
        String descripcionTeclado = S.nextLine();

        pintaProductos(controlador.buscaProductosByDescripcion(descripcionTeclado));
    }

    // Funcion que busca los productos por su modelo
    private static void buscaProductosByModelo(Controlador controlador) {
        System.out.print("Introduce el nombre del modelo: ");
        String modeloTeclado = S.nextLine();

        pintaProductos(controlador.buscaProductosByModelo(modeloTeclado));
    }

    // Funcion que busca los productos por su marca
    private static void buscaProductosByMarca(Controlador controlador) {
        System.out.print("Introduce el nombre de la marca: ");
        String marcaTeclado = S.nextLine();

        pintaProductos(controlador.buscaProductosByMarca(marcaTeclado));
    }

    // Funcion que pinta un producto de los productos
    private static void pintaProductos(ArrayList<Producto> productos) {
        int cont = 0;
        for (Producto producto : productos) {
            System.out.println();
            System.out.println("============================================================================\n");
            if (producto.getRelevancia() > 9) System.out.println("************ Promo especial ************");
            System.out.println("- ID: " + producto.getId());
            System.out.println("- Marca: " + producto.getMarca() + " - Modelo: " + producto.getModelo());
            System.out.println("- Descripción: " + producto.getDescripcion());
            System.out.println("- Precio: " + producto.getPrecio());
            cont++;
            System.out.println("\n============================================================================\n");
            if (cont == 5) {
                Utils.pulsaContinuar();
                cont = 0;
            }
        }
    }

    //Función que pinta todos los pedidos realizados por un cliente concreto
    private static void verMisPedidosCliente(Cliente cliente) {
        if (cliente.getPedidos().isEmpty()) System.out.println("No has realizado ningún pedido...");
        else {
            ArrayList<Pedido> pedidosEntregados = new ArrayList<>();
            ArrayList<Pedido> pedidosCancelados = new ArrayList<>();
            ArrayList<Pedido> pedidosPendientes = new ArrayList<>();

            for (Pedido p : cliente.getPedidos()) {
                if (p.getEstado() == 3) pedidosEntregados.add(p);
            }

            for (Pedido p : cliente.getPedidos()) {
                if (p.getEstado() == 4) pedidosCancelados.add(p);
            }

            for (Pedido p : cliente.getPedidos()) {
                if (p.getEstado() == 0 || p.getEstado() == 1 || p.getEstado() == 2) pedidosPendientes.add(p);
            }
            if (!pedidosEntregados.isEmpty()) {
                Collections.sort(pedidosEntregados);
                System.out.println("""
                        ╔════════════════════════════════════════════════════╗
                        ║                PEDIDOS ENTREGADOS                  ║
                        ╚════════════════════════════════════════════════════╝""");
                for (Pedido p : pedidosEntregados) {
                    System.out.println(p);
                }
            }
            if (!pedidosCancelados.isEmpty()) {
                Collections.sort(pedidosCancelados);
                System.out.println("""
                        ╔════════════════════════════════════════════════════╗ 
                        ║                PEDIDOS CANCELADOS                  ║
                        ╚════════════════════════════════════════════════════╝""");
                for (Pedido p : pedidosCancelados) {
                    System.out.println(p);
                }
            }
            if (!pedidosPendientes.isEmpty()) {
                Collections.sort(pedidosPendientes);
                System.out.println("""
                        ╔════════════════════════════════════════════════════╗ 
                        ║                PEDIDOS PENDIENTES                  ║
                        ╚════════════════════════════════════════════════════╝""");
                for (Pedido p : pedidosPendientes) {
                    System.out.println(p);
                }
            }
        }
    }

    // Función que modifica los datos personales de un Cliente
    private static void modificaDatosPersonalesCliente(Controlador controlador, Cliente cliente) {
        int telefonoTeclado = -2;

        System.out.print("""
                MODIFICACIÓN DE DATOS:
                Introduce el nuevo nombre del cliente:\s""");
        String nombreTeclado = S.nextLine();
        System.out.print("Introduce la nueva clave del cliente: ");
        String contraTeclado = S.nextLine();
        String correoTeclado = compruebaCorreo(controlador);
        System.out.print("Introduzca la nueva localidad (Introduzca 'no' para dejar mismos datos): ");
        String localidadTeclado = S.nextLine();
        System.out.print("Introduzca la nueva provincia (Introduzca 'no' para dejar mismos datos): ");
        String provinciaTeclado = S.nextLine();
        System.out.print("Introduzca la nueva dirección (Introduzca 'no' para dejar mismos datos): ");
        String direccionTeclado = S.nextLine();
        do {
            System.out.print("Introduzca su nuevo teléfono (-1 para dejar mismos datos): ");
            try {
                telefonoTeclado = Integer.parseInt(S.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un valor numérico...");
                Utils.pulsaContinuar();
                Utils.limpiarpantalla();
            }
        } while (telefonoTeclado == -2);

        if (!Controlador.modificaDatosPersonalesCliente(nombreTeclado, contraTeclado, correoTeclado, localidadTeclado,
                provinciaTeclado, direccionTeclado, telefonoTeclado, cliente))
            System.out.println("Ha ocurrido un error...");
        else {
            //Generamos el token después de la modificación de datos
            String token = controlador.generaToken(cliente);
            // Le mandamos el correo con el token
            Comunicaciones.enviaCorreoToken(cliente.getEmail(), "¡Hola! Bienvenido a FERNANSHOP " + cliente.getNombre() + ", " +
                    "tu token de verificación de la cuenta es", "TU CÓDIGO DE VERIFICACIÓN DE CUENTA", token, cliente.getNombre());

            System.out.println("Tus datos han sido modificados...");
            Utils.pulsaContinuar();
            Utils.limpiarpantalla();
            // Hacemos que introduzca el token nuevo, ya que ha cambiado sus datos personales
            compruebaToken(controlador, cliente);
        }
    }

    private static void pintaPerfilCliente(Cliente c) {
        System.out.printf("""
                ╔═════════════════════════════════════════════════════════════════════╗
                ║                           PERFIL CLIENTE                            ║
                ╠═════════════════════════════════════════════════════════════════════╣
                ║ Nombre: %s
                ║ Email: %s
                ║ Localidad: %s
                ║ Provincia: %s
                ║ Dirección: %s
                ║ Número de Teléfono: %d
                ╚═════════════════════════════════════════════════════════════════════╝
                """, c.getNombre(), c.getEmail(), c.getLocalidad(), c.getProvincia(), c.getDireccion(), c.getMovil());
    }

    //Metodo de que comprueba el token de un usuario
    private static void compruebaToken(Controlador controlador, Object user) {
        //Trabajadores
        for (Trabajador trabajador : controlador.getTrabajadores()) {
            if (user == trabajador) {
                if (!trabajador.isValid()) {
                    System.out.print("Introduce tu token para registrarte: ");
                    String tokenTeclado = S.nextLine();
                    if (controlador.compruebaToken(trabajador, tokenTeclado))
                        System.out.println("Token correcto...");
                    else System.out.println("Token incorrecto...");
                }
            }
        }
        //Clientes
        for (Cliente cliente : controlador.getClientes()) {
            if (user == cliente) {
                if (!cliente.isValid()) {
                    System.out.print("Introduce tu token para registrarte: ");
                    String tokenTeclado = S.nextLine();
                    if (controlador.compruebaToken(cliente, tokenTeclado))
                        System.out.println("Token correcto...");
                    else System.out.println("Token incorrecto...");
                }
            }
        }
    }

    // Funcion que da a elegir como se quiere dar de baja al Trabajador, si mediante su ID o en un menú de selección
    private static void bajaTrabajador(Controlador controlador) {
        if (controlador.getTrabajadores().isEmpty()) System.out.println("No hay trabajadores para dar de baja...");
        else {
            Trabajador temp = eligeTrabajadorByMenu(controlador);

            if (temp == null) System.out.println("No se ha encontrado ningún trabajador...");
            else {
                System.out.println("El trabajador es: ");
                System.out.println(temp);

                System.out.print("¿Deseas eliminar al trabajador? (S/N): ");
                String respuesta = S.nextLine();

                if (respuesta.equalsIgnoreCase("N")) System.out.println("Cancelando baja...");
                else if (respuesta.equalsIgnoreCase("S")) {
                    if (temp.numPedidosPendientes() == 0 && controlador.borraTrabajador(temp)) {
                        System.out.println("Dado de baja correctamente...");

                        ArrayList<Pedido> pedidosSinAsignar = controlador.pedidosSinTrabajador();
                        Trabajador candidato = controlador.buscaTrabajadorCandidatoParaAsignar();

                        if (!pedidosSinAsignar.isEmpty() && candidato != null) {
                            for (Pedido p : pedidosSinAsignar) {
                                controlador.asignaPedido(p.getId(), candidato.getId());
                            }

                            for (Pedido pedido : controlador.getTodosPedidos()) {
                                for (PedidoClienteDataClass pDataClass : controlador.getPedidosAsignadosTrabajador(candidato.getId())) {
                                    if (pedido.getId() == pDataClass.getIdPedido())
                                        Comunicaciones.enviaCorreoPedidoAsignacion(candidato.getEmail(), "ASIGNACIÓN DE PEDIDOS", pDataClass);
                                }
                            }

                        }
                    } else System.out.println("No se ha podido borrar el trabajador...");
                } else System.out.println("Respuesta incorrecta...");
            }

        }
    }

    // Funcion que elimina un trabajador mediante un menú de selección
    private static Trabajador eligeTrabajadorByMenu(Controlador controlador) {
        int eligeTrabajador = -1, cont = 1;

        for (Trabajador t : controlador.getTrabajadores()) {
            System.out.println(cont + ".- ID: " + t.getId() + " ; Nombre: " + t.getNombre() + " ; Correo: " + t.getEmail() + " ; Móvil: " + t.getMovil());
            cont++;
        }

        System.out.print("Selecciona al trabajador: ");
        try {
            eligeTrabajador = Integer.parseInt(S.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debes introducir un número...");
        }
        if (eligeTrabajador > 0 && eligeTrabajador <= controlador.getTrabajadores().size())
            return controlador.getTrabajadores().get(eligeTrabajador - 1);

        return null;
    }

    // Funcion que muestra el resumen de los trabajadores al admin
    private static void resumenTrabajadores(Controlador controlador) {
        if (controlador.getTrabajadores().isEmpty()) System.out.println("No hay trabajadores dados de alta...");
        else {
            System.out.println("""
                    ╔═════════════════════════════════════════════════════════════════════════════════════════════╗
                    ║                                  RESUMEN TRABAJADORES                                       ║
                    ╠═════════════════════════════════════════════════════════════════════════════════════════════╣""");

            for (Trabajador t : controlador.getTrabajadores()) {
                System.out.println("║ ID: " + t.getId());
                System.out.println("║ Nombre: " + t.getNombre());
                System.out.println("║ Correo: " + t.getEmail());
                System.out.println("║ Móvil: " + t.getMovil());
                System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════════════╣");
            }
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════╝");
        }
    }

    // Funcion que pinta el resumen de un solo trabajador
    private static void pintaResumenTrabajador(int cont, Trabajador t) {
        System.out.println(cont + ".- ID: " + t.getId() + " .- " + t.getNombre() + "; Móvil: " + t.getMovil() + " - Correo: " + t.getEmail() + "\n");
    }

    // Funcion que muestra el resumen de los clientes al admin
    private static void resumenClientes(Controlador controlador) {
        if (controlador.getClientes().isEmpty()) System.out.println("No hay clientes registrados...");
        else {
            System.out.println("""
                    ╔═════════════════════════════════════════════════════════════════════════════════════════════╗
                    ║                                     RESUMEN CLIENTES                                        ║
                    ╠═════════════════════════════════════════════════════════════════════════════════════════════╣""");

            for (Cliente c : controlador.getClientes()) {
                System.out.println("║ ID: " + c.getId());
                System.out.println("║ Nombre: " + c.getNombre());
                System.out.println("║ Localidad: " + c.getLocalidad() + "(" + c.getProvincia() + ")");
                System.out.println("║ Correo: " + c.getEmail());
                System.out.println("║ Móvil: " + c.getMovil());
                System.out.println("╠═════════════════════════════════════════════════════════════════════════════════════════════╣");
            }
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════╝");
        }
    }

    // Funcion que muestra el número de clientes, trabajadores, pedidos, pedidos pendientes, pedidos completados o cancelados
    // y pedidos sin asignar
    private static void estadisticasApp(Controlador controlador) {
        String mensaje;
        if (controlador.numPedidosSinTrabajador() == 0) mensaje = "No hay pedidos para asignar.";
        else if (controlador.numPedidosSinTrabajador() == 1) {
            mensaje = "Tenemos " + controlador.numPedidosSinTrabajador() + " pedido sin asignar. Debe asignarlos a un trabajador.";
        } else
            mensaje = "Tenemos " + controlador.numPedidosSinTrabajador() + " pedidos sin asignar. Debe asignarlos a un trabajador.";

        System.out.println("Bienvenido Administrador. " + mensaje);
        System.out.printf(""" 
                        ╔════════════════════════════════════════════════════╗
                        ║               Estadísticas de la APP               ║
                        ╠════════════════════════════════════════════════════╣
                        ║ Número de clientes:%4d                            ║
                        ║ Número de trabajadores:%4d                        ║
                        ║ Número de pedidos:%4d                             ║
                        ║ Número de pedidos pendientes:%4d                  ║
                        ║ Número de pedidos completados o cancelados:%4d    ║
                        ║ Número de pedidos sin asignar:%4d                 ║
                        ╚════════════════════════════════════════════════════╝
                        """, controlador.getClientes().size(), controlador.getTrabajadores().size(),
                controlador.numPedidosTotales(), controlador.numPedidosPendientes(), controlador.numPedidosCompletadosCancelados(),
                controlador.pedidosSinTrabajador().size());
    }

    // Funcion que pide los datos para crear un nuevo trabajador
    private static void altaTrabajador(Controlador controlador) {
        boolean bandera = false;

        System.out.print("Introduce el nombre del nuevo trabajador: ");
        String nombreTeclado = S.nextLine();
        System.out.print("Introduce la clave del nuevo trabajador: ");
        String pass = S.nextLine();
        String email = compruebaCorreo(controlador);

        int movil = 0;
        do {
            System.out.print("Introduce el móvil del trabajador: ");
            try {
                movil = Integer.parseInt(S.nextLine());
                bandera = true;
            } catch (NumberFormatException e) {
                System.out.println("Debes introducer números...");
                Utils.pulsaContinuar();
                Utils.limpiarpantalla();
            }
        } while (!bandera);

        if (controlador.nuevoTrabajador(email, pass, nombreTeclado, movil)) {
            System.out.println("Trabajador dado de alta correctamente...");
            for (Trabajador t : controlador.getTrabajadores()) {
                if (t.getEmail().equals(email)) {
                    //Generamos el token después de la modificación de datos
                    String token = controlador.generaToken(t);
                    controlador.asignaTokenTrabajador(t, token);
                    // Le mandamos el correo con el token
                    Comunicaciones.enviaCorreoToken(t.getEmail(), "¡Hola! Bienvenido a FERNANSHOP " + t.getNombre()
                            + ", " + "tu token de verificación de la cuenta es", "TU CÓDIGO DE VERIFICACIÓN DE CUENTA", token, t.getNombre());
                }
            }
        } else System.out.println("Ha ocurrido un error...");
    }

    // Funcion que modifica los datos de un trabajador
    private static void modificaDatosPersonalesTrabajador(Controlador controlador, Trabajador trabajador) {
        int telefonoTeclado = -2;

        System.out.print("""
                MODIFICACIÓN DE DATOS:
                Introduce el nuevo nombre del trabajador:\s""");
        String nombreTeclado = S.nextLine();
        System.out.print("Introduce la nueva clave del trabajador: ");
        String contraTeclado = S.nextLine();
        String correoTeclado = compruebaCorreo(controlador);
        do {
            System.out.print("Introduzca su nuevo teléfono (-1 para dejar mismos datos): ");
            try {
                telefonoTeclado = Integer.parseInt(S.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un valor numérico...");
                Utils.pulsaContinuar();
                Utils.limpiarpantalla();
            }
        } while (telefonoTeclado == -2);

        if (!Controlador.modificaDatosPersonalesTrabajador(nombreTeclado, contraTeclado, correoTeclado, telefonoTeclado, trabajador))
            System.out.println("Ha ocurrido un error...");
        else {
            //Generamos el token después de la modificación de datos
            String token = controlador.generaToken(trabajador);
            // Le mandamos el correo con el token
            Comunicaciones.enviaCorreoToken(trabajador.getEmail(), "¡Hola! Bienvenido a FERNANSHOP " + trabajador.getNombre() + ", " +
                    "tu token de verificación de la cuenta es", "TU CÓDIGO DE VERIFICACIÓN DE CUENTA", token, trabajador.getNombre());

            System.out.println("Tus datos han sido modificados...");
            Utils.pulsaContinuar();
            Utils.limpiarpantalla();
            // Hacemos que introduzca el token nuevo, ya que ha cambiado sus datos personales
            compruebaToken(controlador, trabajador);
        }
    }

    // Funcion que crea un correo con sus validaciones
    private static String compruebaCorreo(Controlador controlador) {
        boolean correoDistinto = false;
        String correoTeclado;
        do {  //Bucle que comprobará que el correo nuevo no se repita con el de otra persona
            System.out.print("Introduzca el correo electrónico:");
            correoTeclado = S.nextLine();
            if (!controlador.compruebaCorreos(correoTeclado)) {
                correoDistinto = true;
                if (!correoTeclado.contains("@") || (!correoTeclado.contains(".com") && !correoTeclado.contains(".es"))) {
                    System.out.println("El correo esta mal introducido...");
                    correoDistinto = false;
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                }
            } else {
                System.out.println("Este correo ya está en uso, introduzca uno nuevo...");
                Utils.pulsaContinuar();
                Utils.limpiarpantalla();
            }
        } while (!correoDistinto);
        return correoTeclado;
    }

    // Funcion que pinta el perfil de un trabajador
    private static void pintaPerfilTrabajador(Trabajador t) {
        System.out.printf("""
                ╔═════════════════════════════════════════════════════════════════════╗
                ║                           PERFIL TRABAJADOR                         ║
                ╠═════════════════════════════════════════════════════════════════════╣
                ║ Nombre: %s
                ║ Email: %s
                ║ Número de Teléfono: %d
                ║ Número de Pedidos Asignados: %d
                ╚═════════════════════════════════════════════════════════════════════╝
                """, t.getNombre(), t.getEmail(), t.getMovil(), t.numPedidosPendientes());
    }

    // Funcion que modifica el estado de un pedido, añade un comentario al pedido o cambia fecha
    private static void modificaPedido(Controlador controlador, Object usuario) {
        Trabajador trabajador = buscaTrabajador(controlador, usuario);
        Admin admin = buscaAdmin(controlador, usuario);
        String op;

        System.out.print("""
                1. Modifica el estado
                2. Añade un comentario
                3. Cambiar fecha
                Marque una opción:""");
        op = S.nextLine();

        switch (op) {
            case "1": // Modifica el estado
                modificaEstadoPedido(controlador, trabajador, admin);
                break;
            case "2": //Añade un comentario
                aniadeComentarioPedido(controlador, trabajador, admin);
                break;
            case "3": //Cambiar fecha
                cambiaFechaPedido(controlador, trabajador, admin);
                break;
            default:
                System.out.println("Opción incorrecta...");
                break;
        }
    }

    // Funcion que cambia la fecha de un pedido
    private static void cambiaFechaPedido(Controlador controlador, Trabajador trabajador, Admin admin) {
        Pedido temp = null;
        if (trabajador != null) temp = seleccionaPedidoTrabajador(controlador, trabajador);
        if (admin != null) temp = seleccionaPedidoAdmin(controlador, admin);

        if (temp == null) System.out.println("No se ha encontrado ningún pedido...");
        else {
            LocalDate nuevaFecha = nuevaFecha();
            if (!temp.cambiaFechaEntrega(nuevaFecha)) System.out.println("Ha ocurrido un error...");
            else {
                System.out.println("Fecha actualizada correctamente");
                for (Cliente c : controlador.getClientes()) {
                    for (Pedido p : c.getPedidos()) {
                        if (p.getId() == temp.getId())
                            Comunicaciones.enviaCorreoPedidoEstadoCliente(c.getEmail(), "PEDIDO MODIFICADO", temp);
                    }
                }
                enviaCorreoPedidoModificidoTrabajador(controlador, temp);
            }
        }
    }

    // Funcion que devuelve una fecha
    private static LocalDate nuevaFecha() {
        int dia = -1, mes = -1, anio = -1;

        do {
            System.out.print("Introduce el día de la nueva fecha: ");
            try {
                dia = Integer.parseInt(S.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un valor numérico...");
                Utils.pulsaContinuar();
                Utils.limpiarpantalla();
            }
        } while (dia == -1);

        do {
            System.out.print("Introduce el mes de la nueva fecha: ");
            try {
                mes = Integer.parseInt(S.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un valor numérico...");
                Utils.pulsaContinuar();
                Utils.limpiarpantalla();
            }
        } while (mes == -1);

        do {
            System.out.print("Introduce el año de la nueva fecha: ");
            try {
                anio = Integer.parseInt(S.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un valor numérico...");
                Utils.pulsaContinuar();
                Utils.limpiarpantalla();
            }
        } while (anio == -1);

        return LocalDate.of(anio, mes, dia);
    }

    // Funcion que le envia al correo del trabajador la modificacion de un pedido
    private static void enviaCorreoPedidoModificidoTrabajador(Controlador controlador, Pedido temp) {
        if (!controlador.getTrabajadores().isEmpty()) {
            for (Trabajador t : controlador.getTrabajadores()) {
                for (PedidoClienteDataClass p : controlador.getPedidosAsignadosTrabajador(t.getId())) {
                    if (temp.getId() == p.getIdPedido())
                        Comunicaciones.enviaCorreoCambiaEstadoPedidoTrabajador(t.getEmail(), "PEDIDO MODIFICADO", p);
                }
            }
        }
    }

    // Funcion que busca al admin
    private static Admin buscaAdmin(Controlador controlador, Object usuario) {
        for (Admin a : controlador.getAdmins()) {
            if (usuario.equals(a)) return a;
        }
        return null;
    }

    // Funcion que busca un trabajador
    private static Trabajador buscaTrabajador(Controlador controlador, Object usuario) {
        for (Trabajador t : controlador.getTrabajadores()) {
            if (usuario.equals(t)) return t;
        }
        return null;
    }

    // Funcion que añade un comentario de un pedido
    private static void aniadeComentarioPedido(Controlador controlador, Trabajador trabajador, Admin admin) {
        Pedido temp = null;
        if (trabajador != null) temp = seleccionaPedidoTrabajador(controlador, trabajador);
        if (admin != null) temp = seleccionaPedidoAdmin(controlador, admin);

        if (temp == null) System.out.println("No se ha encontrado ningún pedido...");
        else {
            System.out.print("Introduce el comentario para el pedido: ");
            String comentarioTeclado = S.nextLine();

            // Le enviamos al cliente que su correo ha sido modificado
            if (controlador.cambiaComentarioPedido(temp.getId(), comentarioTeclado)) {
                System.out.println("Se ha añido un comentario al pedido correctamente...");
                Cliente cliente = null;
                for (Cliente c : controlador.getClientes()) {
                    for (Pedido p : c.getPedidos()) {
                        if (p.getId() == temp.getId()) cliente = c;
                    }
                }
                if (cliente != null)
                    Comunicaciones.enviaCorreoPedidoEstadoCliente(cliente.getEmail(), "PEDIDO MODIFICADO", temp);

                enviaCorreoPedidoModificidoTrabajador(controlador, temp);
            } else System.out.println("Ha ocurrido un error...");
        }

    }

    // Funcion que modifica el estado de un pedido
    private static void modificaEstadoPedido(Controlador controlador, Trabajador trabajador, Admin admin) {
        Pedido temp = null;

        if (trabajador != null) temp = seleccionaPedidoTrabajador(controlador, trabajador);
        if (admin != null) temp = seleccionaPedidoAdmin(controlador, admin);

        if (temp == null) System.out.println("No se ha encontrado ningún pedido...");
        else {
            int estadoTeclado = -1;
            boolean continuar = false;
            pintaPedidoUnico(temp);
            do {
                System.out.print("""
                        Selecciona el nuevo estado:
                        1. En preparación
                        2. Enviado
                        3. Entregado
                        4. Cancelado
                        Introduce el nuevo estado:""");
                try {
                    estadoTeclado = Integer.parseInt(S.nextLine());
                    continuar = true;
                } catch (NumberFormatException e) {
                    System.out.println("Debes introducir un número...");
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                }
            } while (!continuar);


            if (controlador.cambiaEstadoPedido(temp.getId(), estadoTeclado)) {
                System.out.println("El pedido se ha modificado con éxito...");
                // Le enviamos al cliente que su correo ha sido modificado
                Cliente cliente = null;
                for (Cliente c : controlador.getClientes()) {
                    for (Pedido p : c.getPedidos()) {
                        if (p.getId() == temp.getId()) cliente = c;
                    }
                }
                if (cliente != null)
                    Comunicaciones.enviaCorreoPedidoEstadoCliente(cliente.getEmail(), "PEDIDO MODIFICADO", temp);

                enviaCorreoPedidoModificidoTrabajador(controlador, temp);
            } else System.out.println("Ha ocurrido un error...");
        }

    }

    // Funcion que selecciona un pedido desde el administrador
    private static Pedido seleccionaPedidoAdmin(Controlador controlador, Admin admin) {
        ArrayList<Pedido> pedidos = controlador.getTodosPedidos();

        if (pedidos == null) return null;
        if (pedidos.isEmpty()) return null;

        pintaPedidosSinData(controlador, pedidos);

        System.out.print("Introduce el pedido: ");
        String pedidoSeleccionado = S.nextLine();

        Pedido pedidoElegido = null;
        try {
            pedidoElegido = pedidos.get(Integer.parseInt(pedidoSeleccionado) - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error al elegir pedido...");
        }

        if (pedidoElegido == null) return null;

        return controlador.buscaPedidoById(pedidoElegido.getId());
    }

    // Funcion que pinta un pedido sin data
    private static void pintaPedidosSinData(Controlador controlador, ArrayList<Pedido> pedidos) {
        int cont = 1;
        if (pedidos.isEmpty()) System.out.println("No tienes pedidos...");
        else {
            for (Pedido p : pedidos) {
                System.out.println(cont + ".- " + p);
                cont++;
                Utils.pulsaContinuar();
            }
        }
    }

    // Funcion que pinta un unico pedido
    private static void pintaPedidoUnico(Pedido temp) {
        System.out.println(temp);
    }

    // Función de menú de selección de un pedido
    private static Pedido seleccionaPedidoTrabajador(Controlador controlador, Trabajador trabajador) {
        ArrayList<PedidoClienteDataClass> pedidosData = controlador.getPedidosAsignadosYCompletados(trabajador.getId());
        int cont = 1;

        if (pedidosData == null) return null;
        if (pedidosData.isEmpty()) return null;

        for (PedidoClienteDataClass p : pedidosData) {
            System.out.println(cont + " .- " + p);
            cont++;
            Utils.pulsaContinuar();
        }

        System.out.print("Introduce el pedido: ");
        String pedidoSeleccionado = S.nextLine();

        PedidoClienteDataClass pedidoData = null;
        try {
            pedidoData = pedidosData.get(Integer.parseInt(pedidoSeleccionado) - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Error al elegir pedido...");
        }

        if (pedidoData == null) return null;
        Pedido pedidoTemp = controlador.buscaPedidoById(pedidoData.getIdPedido());

        if (pedidoTemp == null) return null;
        return pedidoTemp;
    }

    // Funcion que modifica un producto del catálogo
    public static void modificaProducto(Controlador controlador) {
        int id = -1;
        float precioTeclado = -2;
        boolean bandera = false;


        System.out.print("Selecciona la ID del producto: ");
        try {
            id = Integer.parseInt(S.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debes introducir un valor númerico...");
        }
        Producto producto = controlador.buscaProductoById(id);

        if (producto == null) System.out.println("No se ha encontrado ningún producto");
        else {
            System.out.println("Has elegido: " + producto.getDescripcion() + " - " + producto.getMarca() + " - " + producto.getModelo()
                    + " - " + producto.getPrecio() + "€");

            System.out.print("Introduzca una marca nueva (introduce 'no' para dejar el anterior): ");
            String marcaTeclado = S.nextLine();
            System.out.print("Introduzca un modelo nuevo (introduce 'no' para dejar el anterior): ");
            String modeloTeclado = S.nextLine();
            System.out.print("Introduzca una descripción nueva (introduce 'no' para dejar el anterior): ");
            String descripcionTeclado = S.nextLine();
            do {
                System.out.print("Introduzca un precio nuevo (introduce '-1' para dejar el anterior): ");
                try {
                    precioTeclado = Float.parseFloat(S.nextLine());
                    bandera = true;
                } catch (NumberFormatException e) {
                    System.out.println("Debes introducir un número");
                    Utils.pulsaContinuar();
                    Utils.limpiarpantalla();
                }
            } while (!bandera);

            Producto modificado = new Producto(producto.getId(),
                    ((!marcaTeclado.equalsIgnoreCase("no")) ? marcaTeclado : producto.getMarca()),
                    ((!modeloTeclado.equalsIgnoreCase("no")) ? modeloTeclado : producto.getModelo()),
                    ((!descripcionTeclado.equalsIgnoreCase("no")) ? descripcionTeclado : producto.getDescripcion()),
                    ((precioTeclado < 0) ? producto.getPrecio() : precioTeclado), producto.getRelevancia());

            if (controlador.editarProducto(modificado)) System.out.println("Producto modificado con éxito...");
            else System.out.println("Ha ocurrido un error...");
        }
    }
}