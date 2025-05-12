import java.util.*;

public class Main {
    private static String nombreSuper;
    private static Supermercado encontrado;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();

        System.out.println("Bienvenido a MiSuperIdeal");
        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = teclado.nextLine();


        Usuario usuario = new Usuario(nombre);
        usuarios.add(usuario);

        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar supermercado");
            System.out.println("2. Agregar producto");
            System.out.println("3. Ver productos");
            System.out.println("4. Calificar producto");
            System.out.println("5. Ver supermercados");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del supermercado: ");
                    String nombreSuper = teclado.nextLine();
                    Supermercado nuevoSuper = new Supermercado(nombreSuper);
                    usuario.agregarSupermercado(nuevoSuper);
                }

                case 2 ->{
                    System.out.print("Supermercado al que desea agregar producto: ");
                    nombreSuper = teclado.nextLine();
                    Supermercado encontrado = usuario.buscarSupermercado(nombreSuper);

                    if (encontrado != null) {
                        System.out.print("Nombre del producto: ");
                        String nomProd = teclado.nextLine();
                        System.out.print("Marca: ");
                        String marca = teclado.nextLine();
                        System.out.print("Precio: ");
                        double precio = teclado.nextDouble();
                        System.out.print("Unidades: ");
                        int unidades = teclado.nextInt();
                        teclado.nextLine();
                        System.out.print("Calidad (Alta, Media, Baja): ");
                        String calidad = teclado.nextLine();

                        Producto nuevoProd = new Producto(nomProd, marca, precio, unidades, calidad);
                        encontrado.agregarProducto(nuevoProd);
                        System.out.println("Producto agregado correctamente.");
                    } else {
                        System.out.println("Supermercado no encontrado.");
                    }
                }


                case 3-> {
                    System.out.print("Nombre del supermercado: ");
                    nombreSuper = teclado.nextLine();
                    encontrado = usuario.buscarSupermercado(nombreSuper);

                    if (encontrado != null) {
                        encontrado.mostrarProductos();
                    } else {
                        System.out.println("Supermercado no encontrado.");
                    }
                }

                case 4-> {
                    System.out.print("Nombre del supermercado: ");
                    nombreSuper = teclado.nextLine();
                    encontrado = usuario.buscarSupermercado(nombreSuper);

                    if (encontrado != null) {
                        System.out.print("Nombre del producto a calificar: ");
                        String nombreProd = teclado.nextLine();
                        Producto prod = encontrado.buscarProducto(nombreProd);

                        if (prod != null) {
                            System.out.print("Ingrese calificación (0-5): ");
                            double calif = teclado.nextDouble();
                            prod.calificar(calif);
                            System.out.println("Producto calificado.");
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                    } else {
                        System.out.println("Supermercado no encontrado.");
                    }
                }

                case 5->{
                    usuario.verSupermercados();
                }


                case 6->{
                    System.out.println("Gracias por usar MiSuperIdeal.");
                }

                default->{
                    System.out.println("Opción inválida.");
                }
            }

        } while (opcion != 6);

        teclado.close();
    }
}