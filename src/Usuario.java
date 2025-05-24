import java.util.*;

public class Usuario {
    private final ArrayList<Supermercado> supermercados;


    public Usuario(String nombreUsuario) {
        supermercados = new ArrayList<>();
    }


    public void agregarSupermercado(Supermercado s) {
        supermercados.add(s);
    }

    public boolean eliminarSupermercado(String nombre) {
        return supermercados.removeIf(s -> s.getNombre().equalsIgnoreCase(nombre));
    }

    public Supermercado buscarSupermercado(String nombre) {
        for (Supermercado s : supermercados) {
            if (s.getNombre().equalsIgnoreCase(nombre)) return s;
        }
        return null;
    }

    public ArrayList<Supermercado> getSupermercados() {
        return supermercados;
    }
}
