package controlador;

import comunicaciones.Comunicaciones;
import data.DataProductos;
import models.*;
import persistencia.Persistencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Controlador implements Serializable {
    // Atributos
    private ArrayList<Cliente> clientes;
    private ArrayList<Trabajador> trabajadores;
    private ArrayList<Admin> admins;
    private ArrayList<Producto> catalogo;

    //Constructor
    public Controlador() {
        clientes = Persistencia.leeClientes();
        trabajadores = Persistencia.leeTrabajadores();

        admins = Persistencia.leeAdmins();
        if (admins.isEmpty()) {
            mockAdmin();
            Persistencia.guardaAdminsEnDisco(admins);
        }

        catalogo = Persistencia.leeCatalogo();
        if (catalogo.isEmpty()) {
            mockCatalogo();
            Persistencia.guardaCatalogoEnDisco(catalogo);
        }
    }

    // Mock que rellena los catalogos
    private void mockCatalogo() {
        catalogo = DataProductos.getProductosMock();
    }

    // Mock que va a crear un admin
    private void mockAdmin() {
        admins.add(new Admin(generaIdAdmin(), "root", "root", "root@root"));
    }

    // Mock que va a decidir el usuario si iniciarlo o no
    public void mock(boolean iniciaMockTeclado) {
        if (iniciaMockTeclado) {
            Cliente c1 = new Cliente(99999, "garfieldkeka@gmail.com", "cliente", "cliente",
                    "Pueblo Paleta", "Madrid", "Avd Perdido", 11223344);
            c1.setToken(generaToken(c1));
            c1.setValid(true);
            clientes.add(c1);
            Persistencia.guardaClienteEnDisco(c1);

            Trabajador t1 = new Trabajador(100000, "trabajador", "trabajador", "joseluissanchez0406@gmail.com", 55443322);
            trabajadores.add(t1);
            Persistencia.guardaTrabajadorEnDisco(t1);
        }
    }

    // Getters y Setters
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    public ArrayList<Producto> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(ArrayList<Producto> catalogo) {
        this.catalogo = catalogo;
    }

    // Otros metodos

    // Metodo que sirve para registrarnos, introduciremos el correo y la contraseña del usuario, devolvera quien coincida
    public Object login(String email, String clave) {
        for (Admin admin : admins) {
            if (admin.login(email, clave)) {
                Persistencia.guardaActividadInicioSesion(admin);
                return admin;
            }
        }

        for (Trabajador trabajador : trabajadores) {
            if (trabajador.login(email, clave)) {
                Persistencia.guardaActividadInicioSesion(trabajador);
                return trabajador;
            }
        }

        for (Cliente cliente : clientes) {
            if (cliente.login(email, clave)) {
                Persistencia.guardaActividadInicioSesion(cliente);
                return cliente;
            }
        }
        return null;
    }


    // Metodo que añade un producto al carrito le pasamos una copia
    public boolean addProductoCarrito(Cliente cliente, int idProducto) {
        Producto temp = buscaProductoById(idProducto);
        if (temp == null) return false;
        Producto copia = new Producto(temp.getId(), temp.getMarca(), temp.getModelo(), temp.getDescripcion(), temp.getPrecio(), temp.getRelevancia());
        cliente.addProductoCarro(copia);
        Persistencia.guardaClienteEnDisco(cliente);
        return true;
    }

    // Metodo que busca un producto por ID
    public Producto buscaProductoById(int id) {
        for (Producto producto : catalogo) {
            if (producto.getId() == id) return producto;
        }
        return null;
    }

    // Metodo para confirmar cualquier pedido de cliente
    public boolean confirmaPedidoCliente(int idCliente) {
        Cliente temp = buscaClienteById(idCliente);

        if (temp.getCarro().isEmpty()) return false;

        ArrayList<Producto> copiaCarro = new ArrayList<>();
        copiaCarro.addAll(temp.getCarro());

        Pedido pedidoTemp = new Pedido(generaIdPedido(), LocalDate.now(), LocalDate.now().plusDays(5),
                "Pedido creado", copiaCarro);

        temp.addPedido(pedidoTemp);
        temp.vaciaCarro();
        Persistencia.guardaClienteEnDisco(temp);

        Trabajador trabajadorTemp = buscaTrabajadorCandidatoParaAsignar();

        if (trabajadorTemp != null) {
            if (asignaPedido(pedidoTemp.getId(), trabajadorTemp.getId())) {
                PedidoClienteDataClass dataTemp = null;

                for (PedidoClienteDataClass p : getPedidosAsignadosTrabajador(trabajadorTemp.getId())) {
                    if (p.getIdPedido() == pedidoTemp.getId()) dataTemp = p;
                }

                Persistencia.guardaTrabajadorEnDisco(trabajadorTemp);
                Comunicaciones.enviaCorreoPedidoAsignacion(trabajadorTemp.getEmail(), "ASIGNACIÓN DE PEDIDOS", dataTemp);
                Comunicaciones.enviaMensajeTelegramTrabajador(trabajadorTemp.getNombre() + " se te ha asignado el pedido: " + pedidoTemp.getId());
            }
        }
        return true;
    }

    // Metodo que busca un trabajador candidato para asignar un pedido
    public Trabajador buscaTrabajadorCandidatoParaAsignar() {
        int numPedidosMinimo = Integer.MAX_VALUE;
        Trabajador candidato = null;


        for (Trabajador t : trabajadores) {
            if (t.numPedidosPendientes() < numPedidosMinimo) {
                numPedidosMinimo = t.numPedidosPendientes();
                candidato = t;
            }
        }

        if (trabajadores.size() > 1) if (hayEmpateTrabajadoresCandidatos(candidato)) return null;
        return candidato;
    }

    // Metodo que mira si hay empate en los pedidos pendientes de los trabajadores
    public boolean hayEmpateTrabajadoresCandidatos(Trabajador candidato) {
        for (Trabajador t : trabajadores) {
            if (t.getId() != candidato.getId())
                if (t.getPedidosPendientes().size() == candidato.getPedidosPendientes().size()) return true;
        }
        return false;
    }

    // Metodo que busca un cliente por su id, y lo devuelve
    public Cliente buscaClienteById(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == idCliente) return cliente;
        }
        return null;
    }

    // Metodo que busca un admin por su id, y lo devuelve
    public Admin buscaAdminById(int idAdmin) {
        for (Admin admin : admins) {
            if (admin.getId() == idAdmin) return admin;
        }
        return null;
    }

    // Metodo que busca productos por su marca, devolvemos un Array
    public ArrayList<Producto> buscaProductosByMarca(String marca) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : catalogo) {
            if (producto.getMarca().toLowerCase().contains(marca.toLowerCase())) productosEncontrados.add(producto);
        }
        return productosEncontrados;
    }

    // Metodo que busca productos por su modelo, devolvemos un Array
    public ArrayList<Producto> buscaProductosByModelo(String modelo) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : catalogo) {
            if (producto.getModelo().toLowerCase().contains(modelo.toLowerCase())) productosEncontrados.add(producto);
        }
        return productosEncontrados;
    }

    // Metodo que busca productos por su descripcion, devolvemos un Array
    public ArrayList<Producto> buscaProductosByDescripcion(String descripcion) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : catalogo) {
            if (producto.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
                productosEncontrados.add(producto);
        }
        return productosEncontrados;
    }

    // Metodo que busca productos por su termino, devolvemos un Array
    public ArrayList<Producto> buscaProductosByTermino(String termino) {
        ArrayList<Producto> productos = new ArrayList<>();

        productos.addAll(buscaProductosByMarca(termino));
        productos.addAll(buscaProductosByModelo(termino));
        productos.addAll(buscaProductosByDescripcion(termino));

        return productos;
    }

    // Metodo que busca productos por su precio, devolvemos un Array
    public ArrayList<Producto> buscaProductosByPrecio(float precioMin, float precioMax) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : catalogo) {
            if (producto.getPrecio() >= precioMin && producto.getPrecio() <= precioMax)
                productosEncontrados.add(producto);
        }
        return productosEncontrados;
    }

    // Metodo que edita un producto y si lo ha hecho bien retorna true
    public boolean editarProducto(Producto p) {
        for (Producto producto : catalogo) {
            if (producto.getId() == p.getId()) {
                producto.setMarca(p.getMarca());
                producto.setModelo(p.getModelo());
                producto.setDescripcion(p.getDescripcion());
                producto.setPrecio(p.getPrecio());
                Persistencia.guardaProductoEnDisco(producto);
                return true;
            }
        }
        return false;
    }

    // Metodo que devuelve todos los pedidos que haya
    public ArrayList<Pedido> getTodosPedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        for (Cliente c : clientes) {
            if (!c.getPedidos().isEmpty()) pedidos.addAll(c.getPedidos());
        }

        return pedidos;
    }

    // Metodo que cuenta los pedidos totales
    public int numPedidosTotales() {
        int cont = 0;

        for (Cliente c : clientes) {
            if (c.getPedidos() != null) cont += c.getPedidos().size();
        }
        return cont;
    }

    // Metodo que busca un pedido por su ID
    public Pedido buscaPedidoById(int idPedido) {
        for (Pedido p : getTodosPedidos()) {
            if (p.getId() == idPedido) return p;
        }
        return null;
    }

    // Metodo que cambia el estado de un pedido
    public boolean cambiaEstadoPedido(int idPedido, int nuevoEstado) {
        Pedido pedido = buscaPedidoById(idPedido);
        if (pedido == null) return false;
        boolean bandera = pedido.cambiaEstado(nuevoEstado);

        if (bandera) Persistencia.guardaActividadActualizaPedido(pedido);

        return bandera;
    }

    // Funcion que saca un cliente de un pedido
    private Cliente sacaClienteDeUnPedido(int idPedido) {
        for (Cliente c : clientes) {
            if (c != null && !c.getPedidos().isEmpty()) {
                for (Pedido p : c.getPedidos()) {
                    if (p.getId() == idPedido) return c;
                }
            }
        }
        return null;
    }

    // Funcion que le manda un correo a un trabajador
    private void enviaCorreoPedidoModificadoTrabajador(Pedido pedido) {
        if (!trabajadores.isEmpty()) {
            for (Trabajador t : trabajadores) {
                for (PedidoClienteDataClass p : getPedidosAsignadosTrabajador(t.getId())) {
                    if (pedido.getId() == p.getIdPedido())
                        Comunicaciones.enviaCorreoCambiaEstadoPedidoTrabajador(t.getEmail(), "PEDIDO MODIFICADO", p);
                }
            }
        }
    }

    // Metodo que añade un trabajador a trabajadores
    public boolean nuevoTrabajador(String email, String clave, String nombre, int movil) {
        Trabajador trabajador = new Trabajador(generaIdTrabajador(), nombre, clave, email, movil);
        boolean bandera = trabajadores.add(trabajador);

        if (bandera) Persistencia.guardaTrabajadorEnDisco(trabajador);

        ArrayList<Pedido> pedidosSinAsignar = pedidosSinTrabajador();
        Trabajador candidato = buscaTrabajadorCandidatoParaAsignar();

        if (!pedidosSinAsignar.isEmpty() && candidato != null) {
            for (Pedido p : pedidosSinAsignar) {
                asignaPedido(p.getId(), candidato.getId());
            }

            for (Pedido pedido : getTodosPedidos()) {
                for (PedidoClienteDataClass pDataClass : getPedidosAsignadosTrabajador(candidato.getId())) {
                    if (pedido.getId() == pDataClass.getIdPedido())
                        Comunicaciones.enviaCorreoPedidoAsignacion(candidato.getEmail(), "ASIGNACIÓN DE PEDIDOS", pDataClass);
                }
            }
        }

        return bandera;
    }

    // Metodo que añade un cliente a clientes
    public boolean nuevoCliente(String email, String clave, String nombre, String localidad, String provincia, String direccion, int movil) {
        Cliente c = new Cliente(generaIdCliente(), email, clave, nombre, localidad, provincia, direccion, movil);
        Persistencia.guardaClienteEnDisco(c);
        return clientes.add(c);
    }

    // Metodo que busca un trabajador en un pedido asignado
    public Trabajador buscaTrabajadorAsignadoAPedido(int idPedido) {
        if (trabajadores.isEmpty()) return null;

        for (Trabajador t : trabajadores) {
            for (Pedido p : t.getPedidosAsignados()) {
                if (p.getId() == idPedido) return t;
            }
        }

        return null;
    }

    // Metodo que devuelve los pedidos que no tienen el trabajador
    public ArrayList<Pedido> pedidosSinTrabajador() {
        ArrayList<Pedido> pedidos = new ArrayList<>();

        if (!trabajadores.isEmpty()) {
            for (Cliente c : clientes) {
                for (Pedido p : c.getPedidos()) {
                    if (buscaTrabajadorAsignadoAPedido(p.getId()) == null) pedidos.add(p);
                }
            }
        } else {
            for (Cliente c : clientes) {
                pedidos.addAll(c.getPedidos());
            }
        }


        return pedidos;
    }

    // Metodo que muestra el numero de pedidos sin el trabajador
    public int numPedidosSinTrabajador() {
        return pedidosSinTrabajador().size();
    }

    // Metodo que asigna un pedido a un trabajador
    public boolean asignaPedido(int idPedido, int idTrabajador) {
        Pedido pedidoTemp = buscaPedidoById(idPedido);
        Trabajador trabajadorTemp = buscaTrabajadorById(idTrabajador);

        if (pedidoTemp == null) return false;
        if (trabajadorTemp == null) return false;

        boolean bandera = trabajadorTemp.asignaPedido(pedidoTemp);

        if (bandera) {
            Cliente cliente = sacaClienteDeUnPedido(pedidoTemp.getId());
            if (cliente != null) Persistencia.guardaActividadNuevoPedido(cliente.getId(), trabajadorTemp.getId());
            Persistencia.guardaTrabajadorEnDisco(trabajadorTemp);
        }

        return bandera;
    }

    // Metodo que devuelve los pedidos asignados del trabajador
    public ArrayList<PedidoClienteDataClass> getPedidosAsignadosTrabajador(int idTrabajador) {
        ArrayList<PedidoClienteDataClass> pedidosAsignadosT = new ArrayList<>();

        Trabajador temp = buscaTrabajadorById(idTrabajador);

        //Bucle que mira los pedidos completados de los trabajadores
        for (Pedido pT : temp.getPedidosPendientes()) {
            // Bucle del cliente
            for (Cliente c : clientes) {
                // Bucle que mira los pedidos de los clientes
                for (Pedido pA : c.getPedidos()) {
                    if (pA.getId() == pT.getId()) {
                        pedidosAsignadosT.add(new PedidoClienteDataClass(c.getId(), c.getEmail(), c.getNombre(), c.getLocalidad(),
                                c.getProvincia(), c.getDireccion(), c.getMovil(), pA.getId(), pA.getFechaPedido(), pA.getFechaEntregaEstimada(),
                                pA.getEstado(), pA.getComentario(), pA.getProductos()));

                    }
                }
            }
        }
        Collections.sort(pedidosAsignadosT);
        return pedidosAsignadosT;
    }

    // Metodo que busca un trabajador por su id, y lo devuelve
    public Trabajador buscaTrabajadorById(int idTrabajador) {
        for (Trabajador t : trabajadores) {
            if (t.getId() == idTrabajador) return t;
        }

        return null;
    }

    // Metodo que busca un pedido asignado en el trabajador
    public Pedido buscaPedidoAsignadoTrabajador(int idTrabajador, int idPedido) {
        // Miramos en los trabajadores a ver si coincide la id que nos pasan del trabajador con algun trabajador
        for (Trabajador t : trabajadores) {
            if (t.getId() == idTrabajador) {
                // Si coincide hacemos un bucle para comprobar sus pedidos
                for (Pedido p : t.getPedidosAsignados()) {
                    // Si la id de un pedido coincide lo devolvemos
                    if (p.getId() == idPedido) return p;
                }
            }
        }
        return null;
    }

    // Metodo que devuelve un array de pedidos completados
    public ArrayList<PedidoClienteDataClass> getPedidosCompletadosTrabajador(int idTrabajador) {
        ArrayList<PedidoClienteDataClass> pedidosCompletadosT = new ArrayList<>();

        Trabajador temp = buscaTrabajadorById(idTrabajador);
        if (temp == null) return null;
        if (temp.getPedidosAsignados().isEmpty()) return pedidosCompletadosT;

        //Bucle que mira los pedidos completados de los trabajadores
        for (Pedido pT : temp.getPedidosCompletados()) {
            // Bucle del cliente
            for (Cliente c : clientes) {
                // Bucle que mira los pedidos de los clientes
                for (Pedido pC : c.getPedidos()) {
                    if (pC.getId() == pT.getId()) {
                        pedidosCompletadosT.add(new PedidoClienteDataClass(c.getId(), c.getEmail(), c.getNombre(), c.getLocalidad(),
                                c.getProvincia(), c.getDireccion(), c.getMovil(), pC.getId(), pC.getFechaPedido(), pC.getFechaEntregaEstimada(),
                                pC.getEstado(), pC.getComentario(), pC.getProductos()));
                    }
                }
            }
        }


        Collections.sort(pedidosCompletadosT);
        return pedidosCompletadosT;
    }

    // Metodo que pasa los pedidos asignados y completados al trabajador
    public ArrayList<PedidoClienteDataClass> getPedidosAsignadosYCompletados(int idTrabajador) {
        ArrayList<PedidoClienteDataClass> pedidos = new ArrayList<>();

        Trabajador temp = buscaTrabajadorById(idTrabajador);

        pedidos.addAll(getPedidosAsignadosTrabajador(temp.getId()));
        pedidos.addAll(getPedidosCompletadosTrabajador(temp.getId()));
        return pedidos;
    }

    // Metodo que genera una id aleatoria para el cliente entre el 1 y 99999
    // El ID del cliente de prueba sera 99999
    private int generaIdCliente() {
        boolean repetido = false;
        int id;
        do {
            // Generamos la id
            id = (int) (Math.random() * 99999);
            // Hacemos un bucle de clientes para comprobar sus id
            for (Cliente c : clientes) {
                if (c.getId() == id) {
                    // Si la id se repite salimos del bucle for
                    repetido = true;
                    break;
                }
            }
        } while (repetido);

        return id;
    }

    // Metodo que genera una id aleatoria para el producto entre el 500000 y 599999
    private int generaIdProducto() {
        return (int) (Math.random() * 100000) + 500000;
    }

    // Metodo que genera una id aleatoria para el pedido entre el 600001 y 699999
    private int generaIdPedido() {
        boolean repetido = false;
        int id;
        do {
            // Generamos la id
            id = (int) (Math.random() * 99999) + 600001;
            // Hacemos un bucle de clientes para comprobar sus pedidos
            for (Cliente c : clientes) {
                // Bucle que comprueba los pedidos de cada cliente y si la id coincide
                for (int i = 0; i < c.getPedidos().size(); i++) {
                    if (c.getPedidos().get(i).getId() == id) {
                        repetido = true;
                        break;
                    }
                }
            }
        } while (repetido);
        return id;
    }

    // Metodo que genera una id aleatoria para el admin entre el 200001 y 299999
    private int generaIdAdmin() {
        boolean repetido = false;
        int id;
        do {
            // Generamos la id
            id = (int) (Math.random() * 99999) + 200001;
            for (Admin a : admins) {
                if (a.getId() == id) {
                    repetido = true;
                    break;
                }
            }
        } while (repetido);
        return id;
    }

    // Metodo que genera una id aleatoria para el admin entre el 100001 y 199999
    // El ID del trabajador de prueba sera 100000
    private int generaIdTrabajador() {
        boolean repetido = false;
        int id;
        do {
            // Generamos la id
            id = (int) (Math.random() * 99999) + 100001;
            // Hacemos un bucle de clientes para comprobar sus id
            for (Trabajador t : trabajadores) {
                if (t.getId() == id) {
                    // Si la id se repite salimos del bucle for
                    repetido = true;
                    break;
                }
            }
        } while (repetido);
        return id;
    }

    // Metodo que le pasan un correo, y comprueba si coincide con el correo de un admin, trabajador o cliente
    public boolean compruebaCorreos(String correoTeclado) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(correoTeclado)) return true;
        }

        for (Trabajador trabajador : trabajadores) {
            if (trabajador.getEmail().equals(correoTeclado)) return true;
        }

        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(correoTeclado)) return true;
        }

        return false;
    }

    // Metodo que genera un Token aleatorio al cliente
    public String generaToken(Cliente c) {
        String token = "JM-" + (int) (Math.random() * 99999999);
        c.setToken(token);
        c.setValid(false);
        Persistencia.guardaClienteEnDisco(c);
        Comunicaciones.enviaCorreoToken(c.getEmail(), "¡Hola! Bienvenido a FERNANSHOP " + c.getNombre() + ", " +
                "tu token de verificación de la cuenta es", "TU CÓDIGO DE VERIFICACIÓN DE CUENTA", token, c.getNombre());
        return token;
    }

    // Metodo que comprueba el token de los clientes
    public boolean compruebaToken(Cliente c, String tokenTeclado) {
        // Miramos si el usuario pasado coincide con algun cliente
        if (c.getToken().equals(tokenTeclado)) {
            c.setValid(true);
            Persistencia.guardaClienteEnDisco(c);
            return true;
        } else c.setValid(false);

        return false;
    }

    // Metodo que cancela un pedido de un cliente
    public boolean cancelaPedidoCliente(int idCliente) {
        Cliente cliente = buscaClienteById(idCliente);

        if (cliente != null) {
            cliente.vaciaCarro();
            Persistencia.guardaClienteEnDisco(cliente);
            return true;
        }
        return false;
    }

    // Metodo que devuelve el numero de pedidos pendientes (estado: en preparacion, enviado o creado)
    public int numPedidosPendientes() {
        int cont = 0;
        for (Trabajador t : trabajadores) {
            if (!t.getPedidosPendientes().isEmpty()) cont += t.getPedidosPendientes().size();
        }
        return cont;
    }

    // Metodo que devuelve el numero de pedidos pendientes (estado: entregado o cancelado)
    public int numPedidosCompletadosCancelados() {
        int cont = 0;
        for (Trabajador t : trabajadores) {
            if (!t.getPedidosCompletados().isEmpty()) cont += t.getPedidosCompletados().size();
        }
        return cont;
    }

    // Metodo que cambia el comentario de un pedido
    public boolean cambiaComentarioPedido(int idPedido, String comentarioTeclado) {
        Pedido pedido = buscaPedidoById(idPedido);
        if (pedido == null) return false;
        pedido.addComentario(comentarioTeclado);
        return true;
    }

    // Metodo que muestra el numero de pedidos pendientes de un cliente
    public int numPedidosPendientesCliente(int idCliente) {
        Cliente cliente = buscaClienteById(idCliente);

        if (cliente == null) return -1;

        int cont = 0;
        for (Pedido p : cliente.getPedidos()) {
            if (p.getEstado() == 0 || p.getEstado() == 1 || p.getEstado() == 2) cont++;
        }

        return cont;
    }

    // Metodo que cambia los datos personales del cliente
    public static boolean modificaDatosPersonalesCliente(String nombreTeclado, String contraTeclado, String correoTeclado, String localidadTeclado, String provinciaTeclado, String direccionTeclado, int telefonoTeclado, Cliente cliente) {
        cliente.setNombre(nombreTeclado);
        cliente.setClave(contraTeclado);
        cliente.setEmail(correoTeclado);
        if (!localidadTeclado.equalsIgnoreCase("no")) cliente.setLocalidad(localidadTeclado);
        if (!provinciaTeclado.equalsIgnoreCase("no")) cliente.setLocalidad(provinciaTeclado);
        if (!direccionTeclado.equalsIgnoreCase("no")) cliente.setLocalidad(direccionTeclado);
        if (telefonoTeclado != -1) cliente.setMovil(telefonoTeclado);
        Persistencia.guardaClienteEnDisco(cliente);
        return true;
    }

    // Metodo que cambia los datos personales del trabajador
    public static boolean modificaDatosPersonalesTrabajador(String nombreTeclado, String contraTeclado, String correoTeclado, int telefonoTeclado, Trabajador trabajador) {
        trabajador.setNombre(nombreTeclado);
        trabajador.setPass(contraTeclado);
        trabajador.setEmail(correoTeclado);
        if (telefonoTeclado != -1) trabajador.setMovil(telefonoTeclado);
        Persistencia.guardaTrabajadorEnDisco(trabajador);
        return true;
    }

    // Metodo que borra un trabajador
    // TODO meter Persistencia que borra un trabajador
    public boolean borraTrabajador(Trabajador temp) {
        return trabajadores.remove(temp);
    }

    // Metodo que busca si se ha iniciado los datos de prueba
    public boolean buscaDatosPrueba() {
        return Persistencia.datosPrueba();
    }

    // Metodo que quita un producto del carro del cliente
    public boolean quitaProductoCarroCliente(Cliente cliente, int idProducto) {
        boolean borrado = cliente.quitaProducto(idProducto);

        if (borrado) Persistencia.guardaClienteEnDisco(cliente);

        return borrado;
    }

    // Metodo que devuelve los pedidos que esten Entregados
    public ArrayList<Pedido> devuelvePedidosEntregados() {
        ArrayList<Pedido> pedidosEntregados = new ArrayList<>();

        for (Cliente c : clientes) {
            if (c != null && !c.getPedidos().isEmpty()) {
                for (Pedido p : c.getPedidos()) {
                    if (p.getEstado() == 3) pedidosEntregados.add(p);
                }
            }
        }

        Collections.sort(pedidosEntregados);
        return pedidosEntregados;
    }

    // Metodo que devuelve los pedidos que esten Cancelados
    public ArrayList<Pedido> devuelvePedidosCancelados() {
        ArrayList<Pedido> pedidosCancelados = new ArrayList<>();

        for (Cliente c : clientes) {
            if (c != null && !c.getPedidos().isEmpty()) {
                for (Pedido p : c.getPedidos()) {
                    if (p.getEstado() == 4) pedidosCancelados.add(p);
                }
            }
        }

        Collections.sort(pedidosCancelados);
        return pedidosCancelados;
    }

    // Metodo que devuelve los pedidos que esten Pendientes (Creado, En preparación, Enviado)
    public ArrayList<Pedido> devuelvePedidosPendientes() {
        ArrayList<Pedido> pedidosPendientes = new ArrayList<>();

        for (Cliente c : clientes) {
            if (c != null && !c.getPedidos().isEmpty()) {
                for (Pedido p : c.getPedidos()) {
                    if (p.getEstado() == 0 || p.getEstado() == 1 || p.getEstado() == 2) pedidosPendientes.add(p);
                }
            }
        }

        Collections.sort(pedidosPendientes);
        return pedidosPendientes;
    }

    // Metodo que devuelve los pedidos cancelados de un cliente en especifico
    public ArrayList<Pedido> verPedidosCancelados(int idCliente) {
        ArrayList<Pedido> pedidosCancelados = new ArrayList<>();
        Cliente temp = buscaClienteById(idCliente);

        if (temp == null) return pedidosCancelados;
        if (temp.getPedidos().isEmpty()) return pedidosCancelados;

        for (Pedido p : temp.getPedidos()) {
            if (p.getEstado() == 4) pedidosCancelados.add(p);
        }

        Collections.sort(pedidosCancelados);
        return pedidosCancelados;
    }

    // Metodo que devuelve los pedidos pendientes (Creado, En preparación, Enviado) de un cliente en especifico
    public ArrayList<Pedido> verPedidosPendientes(int idCliente) {
        ArrayList<Pedido> pedidosPendientes = new ArrayList<>();
        Cliente temp = buscaClienteById(idCliente);

        if (temp == null) return pedidosPendientes;
        if (temp.getPedidos().isEmpty()) return pedidosPendientes;

        for (Pedido p : temp.getPedidos()) {
            if (p.getEstado() == 0 || p.getEstado() == 1 || p.getEstado() == 2) pedidosPendientes.add(p);
        }

        Collections.sort(pedidosPendientes);
        return pedidosPendientes;
    }

    // Metodo que cambia la fehca de entrega de un pedido
    public boolean cambiaFechaEntregaPedido(int idPedido, LocalDate nuevaFecha) {
        Pedido pedido = buscaPedidoById(idPedido);
        if (pedido == null) return false;
        return pedido.cambiaFechaEntrega(nuevaFecha);
    }

    // Metodo que envia un correo de que se ha modificado un pedido para el cliente
    public void enviaCorreoPedidoModificadoCliente(int idPedido) {
        Pedido pedido = buscaPedidoById(idPedido);
        if (pedido != null) {
            Cliente c = sacaClienteDeUnPedido(pedido.getId());
            if (c != null) {
                Comunicaciones.enviaCorreoPedidoEstadoCliente(c.getEmail(), "PEDIDO MODIFICADO", pedido);
                Persistencia.guardaClienteEnDisco(c);
                enviaCorreoPedidoModificadoTrabajador(pedido);
            }
        }
    }

    // Metodo que llama a un metodo de la Persistencia para guardarlo en el log
    public void guardaCierreSesion(Object user) {
        Persistencia.guardaActividadCierreSesion(user);
    }

    // Metodo que muestra el ultimo inicio de sesion del usuario
    public String ultimoInicioSesion(int idUsuario) {
        return Persistencia.ultimoInicioSesion(idUsuario);
    }

    // Metodo que te comprueba si tienes
    public boolean accesoInvitado() {
        return Persistencia.accesoInvitado();
    }

    // Metodo que devuelve un arraylist de String del properties linea a linea
    public ArrayList<String> configuracionPrograma() {
        return Persistencia.configuracionPrograma();
    }

    // Metodo que crea una copia de seguridad en la ruta
    public boolean creaBackup(String rutaBackup) {
        return Persistencia.creaBackup(rutaBackup, this);
    }

    // Metodo que crea una copia de seguridad en la ruta por defecto
    public boolean creaBackup() {
        return Persistencia.creaBackup(this);
    }

    // Metodo que recupera una copia de seguridad en la ruta que nos pasen
    public boolean recuperaBackup(String rutaBackup) {
        Controlador recuperado = Persistencia.recuperaBackup(rutaBackup);
        if (recuperado == null) return false;
        clientes = recuperado.clientes;
        trabajadores = recuperado.trabajadores;
        admins = recuperado.admins;
        catalogo = recuperado.catalogo;
        return true;
    }


    // Metodo que recupera una copia de seguridad en la ruta por defecto
    public boolean recuperaBackup() {
        Controlador recuperado = Persistencia.recuperaBackup();
        if (recuperado == null) return false;
        clientes = recuperado.clientes;
        trabajadores = recuperado.trabajadores;
        admins = recuperado.admins;
        catalogo = recuperado.catalogo;
        return true;
    }
}