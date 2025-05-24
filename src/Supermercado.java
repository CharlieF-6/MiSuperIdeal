import java.util.ArrayList;
import java.util.List;

public class Supermercado {
    private String nombre;
    private List<Producto> productos;
    private double sumaCalificacionesSuper;
    private int cantidadCalificacionesSuper;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";

    public Supermercado() {
    }

    public Supermercado(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
        this.sumaCalificacionesSuper = 0;
        this.cantidadCalificacionesSuper = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public List<Producto> buscarProductosPorNombre(String nombreProducto) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                encontrados.add(producto);
            }
        }
        return encontrados;
    }

    public Producto buscarProducto(String nombreProducto) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                return producto;
            }
        }
        return null;
    }

    public boolean eliminarProducto(String nombre) {
        return productos.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
    }

    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en este supermercado.");
        } else {
            for (Producto p : productos) {
                System.out.println(p);
            }
        }
    }

    public void calificarSupermercado(double calif) {
        if (calif < 0 || calif > 5) {
            System.out.println("Calificación inválida. Debe estar entre 0 y 5.");
            return;
        }

        sumaCalificacionesSuper += calif;
        cantidadCalificacionesSuper++;


        String color;
        if (calif >= 4.0) {
            color = ANSI_GREEN;
        } else if (calif >= 3.0) {
            color = ANSI_YELLOW;
        } else {
            color = ANSI_RED;
        }

        int estrellas = (int) Math.round(calif);
        String estrellasStr = "★".repeat(Math.max(0, estrellas)) +
                "☆".repeat(Math.max(0, 5 - estrellas));

        System.out.println(color + "Supermercado " + nombre + " calificado con: " +
                String.format("%.1f", calif) + " " + estrellasStr + ANSI_RESET);
    }

    public double getPromedioCalificacionSupermercado() {
        return cantidadCalificacionesSuper == 0 ? 0 : sumaCalificacionesSuper / cantidadCalificacionesSuper;
    }


}
