public class Producto {
    private String nombre;
    private String marca;
    private double precio;
    private int unidades;
    private double sumaCalificaciones;
    private int cantidadCalificaciones;

    public Producto() {
    }

    public Producto(String nombre, String marca, double precio, int unidades) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.unidades = unidades;
        this.sumaCalificaciones = 0;
        this.cantidadCalificaciones = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void calificar(double calificacion) {
        if (calificacion >= 0 && calificacion <= 5) {
            sumaCalificaciones += calificacion;
            cantidadCalificaciones++;
        } else {
            System.out.println("Calificación inválida, debe estar entre 0 y 5.");
        }
    }

    public double getPromedioCalificacion() {
        return cantidadCalificaciones == 0 ? 0 : sumaCalificaciones / cantidadCalificaciones;
    }

    public String obtenerEstrellasColoreadas() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_VERDE = "\u001B[32m";
        final String ANSI_AMARILLO = "\u001B[33m";
        final String ANSI_ROJO = "\u001B[31m";

        if (cantidadCalificaciones == 0) {
            return "☆☆☆☆☆ (Producto no calificado)";
        }

        double promedio = getPromedioCalificacion();
        int estrellas = (int) Math.round(promedio);
        String color;

        if (promedio >= 4.0) {
            color = ANSI_VERDE;
        } else if (promedio >= 3.0) {
            color = ANSI_AMARILLO;
        } else {
            color = ANSI_ROJO;
        }

        return color + "Producto calificado con: " +
                String.format("%.2f", promedio) + " " +
                "★".repeat(Math.max(0, estrellas)) +
                "☆".repeat(Math.max(0, 5 - estrellas)) +
                ANSI_RESET;
    }

    @Override
    public String toString() {
        return nombre + " (" + marca + ") - $" + precio + " - " + unidades + " unidades - " + obtenerEstrellasColoreadas();
    }
}
