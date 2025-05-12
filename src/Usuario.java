import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private ArrayList<Supermercado> supermercados;

    public Usuario(String nombre) {
        this.nombre = nombre;
        supermercados = new ArrayList<>();
    }

    public void agregarSupermercado(Supermercado s) {
        supermercados.add(s);
    }

    public void verSupermercados() {
        System.out.println("Supermercados registrados:");
        for (Supermercado s : supermercados) {
            System.out.println("- " + s.getNombre());
        }
    }

    public Supermercado buscarSupermercado(String nombre) {
        for (Supermercado s : supermercados) {
            if (s.getNombre().equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }
}