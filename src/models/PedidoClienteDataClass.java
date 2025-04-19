package models;

import utils.Utils;

import java.time.LocalDate;
import java.util.*;

public class PedidoClienteDataClass implements Comparable<PedidoClienteDataClass>{
    //Atributos Cliente
    private int idCliente;
    private String email;
    private String nombre;
    private String localidad;
    private String provincia;
    private String direccion;
    private int movil;
    //Atributos Pedido
    private int idPedido;
    private LocalDate fechaPedido;
    private LocalDate fechaEntregaEstimada;
    private int estado;
    private String comentario;
    private ArrayList<Producto> productos;

    // Constructor
    public PedidoClienteDataClass(int idCliente, String email, String nombre, String localidad, String provincia, String direccion, int movil, int idPedido, LocalDate fechaPedido, LocalDate fechaEntregaEstimada, int estado, String comentario, ArrayList<Producto> productos) {
        this.idCliente = idCliente;
        this.email = email;
        this.nombre = nombre;
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.movil = movil;
        this.idPedido = idPedido;
        this.fechaPedido = fechaPedido;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.estado = estado;
        this.comentario = comentario;
        this.productos = productos;
    }

    //Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getMovil() {
        return movil;
    }

    public void setMovil(int movil) {
        this.movil = movil;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    // Otros metodos
    @Override
    public String toString() {
        return " =============== " + idPedido + " =============== \n" +
                "Estado: " + devuelveEstado(estado) + "\n" +
                "Nombre: " + nombre + "\n" +
                "Dirección: " + direccion + "\n" +
                "Localidad: " + localidad + "\n" +
                "Provincia: " + provincia + "\n" +
                "Teléfono: " + movil + "\n" +
                "Fecha del pedido: " + Utils.formateaFecha(fechaPedido) + "\n" +
                "Fecha de entrega estimada: " + Utils.formateaFecha(fechaEntregaEstimada) + "\n" +
                "Comentarios del pedido: " + comentario + "\n" +
                "Detalles del pedido: \n" +
                pintaProductos(productos);
    }

    //Metodo que pinta los datos del pedido
    public String pintaPedidoCorreo() {
        String salida = "";
        salida += "\n\n";
        salida += "==========\tPedido " + idPedido + "\t===========<br>";
        salida += "Estado: " + devuelveEstado(estado) + "<br>";
        salida += "Nombre: " + nombre + "<br>";
        salida += "Dirección: " + direccion + "<br>";
        salida += "Localidad: " + localidad + "<br>";
        salida += "Provincia: " + provincia + "<br>";
        salida += "Teléfono: " + movil + "<br>";
        salida += "Fecha del pedido: " + Utils.formateaFecha(fechaPedido) + "<br>";
        salida += "Fecha de entrega estimada: " + Utils.formateaFecha(fechaEntregaEstimada) + "<br>";
        salida += "Comentario del pedido: " + comentario + "<br>";
        salida += "Detalles del pedido:<br>";
        for (Producto p : productos) {
            if (p != null) salida += p + "<br>";
        }
        salida += "<hr>";
        salida += "Total pedido: " + calculaTotalPedidoConIVA(Utils.IVA) + "€<br>";
        salida += "<br>";

        return salida;
    }

    //Deveuelve el precio sin IVA de un pedido concreto
    public float calculaTotalPedidoSinIVA() {
        float sumatorio = 0;
        for (Producto p : productos) {
            sumatorio += p.getPrecio();
        }
        return sumatorio;
    }

    //Devuelve el IVA del pedido concreto
    public float calculaIVAPedido(int IVA) {
        return calculaTotalPedidoSinIVA() * (float) (IVA / 100);
    }

    //Devuelve el precio con el IVA añadido
    public float calculaTotalPedidoConIVA(int IVA) {
        return calculaTotalPedidoSinIVA() + calculaIVAPedido(IVA);
    }

    // Funcion que devuelve el estado de un Pedido
    private String devuelveEstado(int estado) {
        return switch (estado) {
            case 0 -> "Creado";
            case 1 -> "En preparación";
            case 2 -> "Enviado";
            case 3 -> "Cancelado";
            default -> "";
        };

    }

    // Funcion que pinta productos
    private String pintaProductos(ArrayList<Producto> productos) {
        String resultado = "";
        Map<Integer, Integer> contador = new HashMap<>();

        for (Producto p : productos) {
            contador.put(p.getId(), contador.getOrDefault(p.getId(), 0) + 1);
        }

        Set<Integer> productosImpresos = new HashSet<>();
        for (Producto p : productos) {
            if (!productosImpresos.contains(p.getId())) {
                int cantidad = contador.get(p.getId());
                resultado += "\t- " + p.getMarca() + " - " + p.getModelo() + " (" + p.getPrecio() + ") (" + cantidad + ")";
                productosImpresos.add(p.getId());
            }
        }

        /*for (Producto p : productos) {
            resultado += p.toString() + "\n";
        }*/
        return resultado;
    }

    @Override
    public int compareTo(PedidoClienteDataClass p) {
        return this.fechaEntregaEstimada.compareTo(p.fechaEntregaEstimada);
    }
}