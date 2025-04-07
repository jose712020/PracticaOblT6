package models;

import controlador.Controlador;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {
    //Atributos
    private int id;
    private String email;
    private String clave;
    private String nombre;
    private String localidad;
    private String provincia;
    private String direccion;
    private int movil;
    private String token;
    private boolean isValid;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Producto> carro;

    //Constructor
    public Cliente(int id, String email, String clave, String nombre, String localidad, String provincia, String direccion, int movil) {
        this.id = id;
        this.email = email;
        this.clave = clave;
        this.nombre = nombre;
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.movil = movil;
        pedidos = new ArrayList<>();
        carro = new ArrayList<>();
    }


    //Getter y Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Producto> getCarro() {
        return carro;
    }

    public void setCarro(ArrayList<Producto> carro) {
        this.carro = carro;
    }

    //Otros metodos
    @Override
    public String toString() {
        return "Nombre: " + nombre + '\n' +
                "Email: " + email + '\n' +
                "Localidad: " + localidad + '\n' +
                "Provincia: " + provincia + '\n' +
                "Dirección: " + direccion + '\n' +
                "Número de Teléfono: " + movil + '\n';
    }

    public boolean login(String email, String pass) {
        return email.equals(this.email) && pass.equals(this.clave);
    }

    // Metodo que añade un producto en el carrito
    public void addProductoCarro(Producto p) {
        carro.add(p);
    }

    // Metodo que quita un producto en el carro
    public boolean quitaProducto(int idProducto) {
        for (Producto p : carro) {
            if (p.getId() == idProducto) return carro.remove(p);
        }
        return false;
    }

    // Metodo que comprueba la longitud de un producto
    public int numProductosCarro() {
        return carro.size();
    }

    // Metodo que vacia un carro
    public void vaciaCarro() {
        carro.clear();
    }

    // Metodo que añade un pedido
    public void addPedido(Pedido p) {
        pedidos.add(p);
    }

    // Metodo que suma el precio de los productos sin IVA
    public float precioCarroSinIva(int IVA) {
        float precio = 0;

        for (Producto p : carro) {
            precio += p.getPrecio();
        }
        return precio;
    }

    // Suma de los productos con su respectivo IVA
    public float precioIVACarro(int IVA) {
        float precio = 0;

        for (Producto p : carro) {
            precio += p.getPrecio() * (IVA / 100f);
        }
        return precio;
    }

    // Metodo que suma el precio del carro sin IVA
    public float precioCarroConIVA(int IVA) {
        return precioCarroSinIva(IVA) + precioIVACarro(IVA);
    }

    // Metodo que comprueba si existe un producto en el carrito
    public boolean existeProductoCarro(int idProducto) {
        for (Producto p : carro) {
            if (p.getId() == idProducto) return true;
        }
        return false;
    }
}
