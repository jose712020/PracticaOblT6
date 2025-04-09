package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Trabajador implements Serializable {
    // Atributos
    private int id;
    private String nombre;
    private String pass;
    private String email;
    private int movil;
    private ArrayList<Pedido> pedidosAsignados;

    //Constructor
    public Trabajador(int id, String nombre, String pass, String email, int movil) {
        this.id = id;
        this.nombre = nombre;
        this.pass = pass;
        this.email = email;
        this.movil = movil;
        pedidosAsignados = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMovil() {
        return movil;
    }

    public void setMovil(int movil) {
        this.movil = movil;
    }

    public ArrayList<Pedido> getPedidosAsignados() {
        return pedidosAsignados;
    }

    public void setPedidosAsignados(ArrayList<Pedido> pedidosAsignados) {
        this.pedidosAsignados = pedidosAsignados;
    }

    // Otros metodos
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nEmail: " + email + "\nTeléfono móvil: " + movil + '\n';
    }

    // Metodo que devuelve si un trabajador se ha logueado bien
    public boolean login(String email, String pass) {
        return this.email.equals(email) && pass.equals(this.pass);
    }

    // Metodo que busca en los pedidos los pedidos pendientes
    public Pedido buscaPedidoAsignadoPendiente(int idPedido) {
        for (Pedido p : pedidosAsignados) {
            if (p.getId() == idPedido && (p.getEstado() == 3 || p.getEstado() == 4)) return p;
        }
        return null;
    }

    // Metodo que busca en los pedidos los pedidos completados
    public Pedido buscaPedidoAsignadoCompletado(int idPedido) {
        for (Pedido p : pedidosAsignados) {
            if (p.getId() == idPedido && (p.getEstado() == 3 || p.getEstado() == 4)) return p;
        }
        return null;
    }

    // Metodo que asigna un pedido a un trabajador
    public boolean asignaPedido(Pedido p) {
        return pedidosAsignados.add(p);
    }

    // Metodo que comprueba que el pedido este En preparacion o Enviado
    public ArrayList<Pedido> getPedidosPendientes() {
        ArrayList<Pedido> pedidosPendientes = new ArrayList<>();

        if (pedidosAsignados == null) return pedidosPendientes;

        for (Pedido p : pedidosAsignados) {
             if (p.getEstado() == 0 || p.getEstado() == 1) pedidosPendientes.add(p);
        }
        return pedidosPendientes;
    }

    // Metodo que comprueba que el pedido este Entregado o Cancelado
    public ArrayList<Pedido> getPedidosCompletados() {
        ArrayList<Pedido> pedidosCompletados = new ArrayList<>();

        if (pedidosAsignados == null) return pedidosCompletados;

        for (Pedido p : pedidosAsignados) {
            if (p.getEstado() == 2 || p.getEstado() == 3) pedidosCompletados.add(p);
        }
        return pedidosCompletados;
    }

    // Metodo que comprueba el numero de pedidos pendientes
    public int numPedidosPendientes() {
        return getPedidosPendientes().size();
    }

    // Metodo que comprueba el numero de pedidos completados
    public int numPedidosCompletados() {
        return getPedidosCompletados().size();
    }
}
