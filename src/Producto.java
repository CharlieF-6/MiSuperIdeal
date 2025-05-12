public class Producto {
    private String nombre;
    private String marca;
    private double precio;
    private int unidades;
    private String calidad;
    private double calificacion;

    public Producto(String nombre, String marca, double precio, int unidades, String calidad) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.unidades = unidades;
        this.calidad = calidad;
        this.calificacion = 0.0;
    }

    public String getNombre() {
        return nombre;
    }

    public void calificar(double c) {
        this.calificacion = c;
    }

    @Override
    public String toString() {
        return "Producto: " + nombre +
                "\nMarca: " + marca +
                "\nPrecio: " + precio +
                "\nUnidades: " + unidades +
                "\nCalidad: " + calidad +
                "\nCalificación: " + calificacion +
                "\n---------------------------";
    }
}