package data;

import models.Producto;

import java.io.Serializable;
import java.util.ArrayList;

public class DataProductos implements Serializable {

    public static ArrayList<Producto> getProductosMock(){
        ArrayList<Producto> productos = new ArrayList<>();
        // Crear productos con datos reales
        productos.add(new Producto(23432,"Samsung", "Galaxy S23", "Smartphone con pantalla AMOLED de 6.1 pulgadas", 899.99f, 9));
        productos.add(new Producto(345345,"Apple", "MacBook Pro 16", "Portátil de alto rendimiento con chip M2 Pro", 2499.00f, 10));
        productos.add(new Producto(5643,"Sony", "WH-1000XM5", "Auriculares inalámbricos con cancelación de ruido", 349.99f, 8));
        productos.add(new Producto(45632,"LG", "OLED55C3", "Televisor OLED 4K de 55 pulgadas", 1399.00f, 9));
        productos.add(new Producto(12434,"Dyson", "V15 Detect", "Aspiradora inalámbrica de alta potencia", 799.99f, 7));
        productos.add(new Producto(8330,"Philips", "Hue Starter Kit", "Kit de luces inteligentes con puente y bombillas", 199.99f, 8));
        productos.add(new Producto(67233,"Nike", "Air Max 270", "Zapatillas deportivas para correr", 129.99f, 8));
        productos.add(new Producto(92371,"Adidas", "Ultraboost 22", "Zapatillas de running con amortiguación avanzada", 179.99f, 9));
        productos.add(new Producto(45742,"Canon", "EOS R6", "Cámara sin espejo de alta resolución", 2499.00f, 9));
        productos.add(new Producto(93639,"Bose", "SoundLink Revolve+", "Altavoz Bluetooth portátil con sonido 360°", 299.99f, 8));
        productos.add(new Producto(56323,"Levi's", "501 Original", "Vaqueros clásicos de corte recto", 89.99f, 7));
        productos.add(new Producto(56341,"IKEA", "MALM", "Cama con almacenamiento incorporado", 399.00f, 7));
        productos.add(new Producto(456333,"Logitech", "MX Master 3", "Ratón inalámbrico de alta precisión para profesionales", 99.99f, 9));
        productos.add(new Producto(89073,"Microsoft", "Surface Pro 9", "Tablet convertible en portátil con pantalla táctil", 1199.00f, 10));
        productos.add(new Producto(60903,"Amazon", "Echo Dot 5ª Gen", "Altavoz inteligente con Alexa integrado", 49.99f, 8));
        productos.add(new Producto(94856,"Sony", "PlayStation 5", "Consola de videojuegos de última generación", 499.99f, 10));
        productos.add(new Producto(93723,"Nintendo", "Switch OLED", "Consola híbrida con pantalla OLED", 349.99f, 9));
        productos.add(new Producto(83263,"KitchenAid", "Artisan 5KSM150", "Batidora de pie para repostería", 499.99f, 8));
        productos.add(new Producto(45459,"HP", "Spectre x360", "Portátil 2 en 1 con pantalla táctil 4K", 1599.00f, 9));
        productos.add(new Producto(14566,"Asus", "ROG Zephyrus G14", "Portátil gaming con procesador Ryzen 9", 1799.99f, 9));
        productos.add(new Producto(98475,"Samsung", "Galaxy Watch 6", "Reloj inteligente con monitor de salud", 299.99f, 8));
        productos.add(new Producto(78648,"Garmin", "Fenix 7", "Reloj multideporte con GPS avanzado", 699.99f, 9));
        productos.add(new Producto(84635,"Xiaomi", "Mi Electric Scooter 4 Pro", "Patinete eléctrico con gran autonomía", 849.99f, 8));
        productos.add(new Producto(56343,"Whirlpool", "W Collection W7", "Horno empotrado multifunción con autolimpieza", 1299.00f, 9));
        productos.add(new Producto(34545,"Bosch", "Serie 6 SMS68TI01E", "Lavavajillas eficiente y silencioso", 799.00f, 8));
        productos.add(new Producto(84675,"DeWalt", "DCD796D2-QW", "Taladro percutor inalámbrico compacto", 199.99f, 8));
        productos.add(new Producto(97463,"Fitbit", "Charge 6", "Pulsera de actividad física con seguimiento avanzado", 129.99f, 8));
        return productos;
    }


}
