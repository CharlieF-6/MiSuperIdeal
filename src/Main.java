import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Bienvenido. Ingrese su nombre: ");
        String nombreUsuario = teclado.nextLine();
        Usuario usuario = new Usuario(nombreUsuario);

        int opcion;
        do {
            System.out.println("\n\u001B[1;38;5;141m ***** Menú de opciones *****\u001B[0m");
            System.out.println("\n\u001B[38;5;111m1. Agregar supermercado\u001B[0m");
            System.out.println("\u001B[38;5;180m2. Agregar producto\u001B[0m");
            System.out.println("\u001B[38;5;111m3. Calificar producto\u001B[0m");
            System.out.println("\u001B[38;5;145m4. Calificar supermercado\u001B[0m");
            System.out.println("\u001B[38;5;180m5. Ver supermercados\u001B[0m");
            System.out.println("\u001B[37m6. Ver productos\u001B[0m");
            System.out.println("\u001B[38;5;180m7. Buscar producto en todos los supermercados\u001B[0m");
            System.out.println("\u001B[38;5;145m8. Eliminar producto\u001B[0m");
            System.out.println("\u001B[38;5;111m9. Eliminar supermercado\u001B[0m");
            System.out.println("\u001B[37m10. Salir\u001B[0m");

            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            Supermercado encontrado;
            String nombreSuper1;
            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del supermercado: ");
                    nombreSuper1 = teclado.nextLine();
                    usuario.agregarSupermercado(new Supermercado(nombreSuper1));
                    String verdeSuave = "\u001B[32m";
                    String reset = "\u001B[0m";
                    System.out.println(verdeSuave + "Supermercado registrado exitosamente." + reset);
                }


                case 2 -> {
                    System.out.print("Supermercado al que desea agregar producto: ");
                    nombreSuper1 = teclado.nextLine();
                    encontrado = usuario.buscarSupermercado(nombreSuper1);

                    if (encontrado != null) {
                        String nomProd;
                        do {
                            System.out.print("Nombre del producto: ");
                            nomProd = teclado.nextLine();
                        } while (!nomProd.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"));

                        String marca;
                        do {
                            System.out.print("Marca: ");
                            marca = teclado.nextLine();
                        } while (!marca.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"));

                        double precio = -1;
                        while (precio <= 0) {
                            System.out.print("Precio: ");
                            try {
                                precio = Double.parseDouble(teclado.nextLine());
                            } catch (NumberFormatException e) {
                                precio = -1;
                            }
                        }

                        int unidades = -1;
                        while (unidades <= 0) {
                            System.out.print("Unidades: ");
                            try {
                                unidades = Integer.parseInt(teclado.nextLine());
                            } catch (NumberFormatException e) {
                                unidades = -1;
                            }
                        }

                        encontrado.agregarProducto(new Producto(nomProd, marca, precio, unidades));
                        String verdeSuave = "\u001B[32m";
                        String reset = "\u001B[0m";
                        System.out.println(verdeSuave + "Producto agregado correctamente." + reset);
                    } else {
                        System.out.println("Supermercado no encontrado.");
                    }
                }

                case 3 -> {
                    System.out.print("Nombre del supermercado: ");
                    nombreSuper1 = teclado.nextLine();
                    encontrado = usuario.buscarSupermercado(nombreSuper1);
                    if (encontrado != null) {
                        System.out.print("Nombre del producto a calificar: ");
                        String nombreProd = teclado.nextLine();
                        Producto prod = encontrado.buscarProducto(nombreProd);

                        if (prod != null) {
                            double calif = -1;
                            while (calif < 0 || calif > 5) {
                                System.out.print("Ingrese calificación (0-5): ");
                                try {
                                    calif = Double.parseDouble(teclado.nextLine());
                                } catch (NumberFormatException e) {
                                    calif = -1;
                                }
                            }
                            prod.calificar(calif);
                            String amarilloSuave = "\u001B[38;5;222m";
                            String reset = "\u001B[0m";
                            System.out.println(amarilloSuave + prod.obtenerEstrellasColoreadas() + reset);

                        } else {
                            String amarilloSuave = "\u001B[38;5;222m";
                            String reset = "\u001B[0m";
                            System.out.println(amarilloSuave + "Producto no encontrado." + reset);
                        }
                    } else {
                        String amarilloSuave = "\u001B[38;5;222m";
                        String reset = "\u001B[0m";
                        System.out.println(amarilloSuave + "Supermercado no encontrado." + reset);
                    }
                }


                case 4 -> {
                    System.out.print("Nombre del supermercado a calificar: ");
                    String nombreSuper = teclado.nextLine();
                    Supermercado superEncon = usuario.buscarSupermercado(nombreSuper);

                    if (superEncon != null) {
                        double calif = -1;
                        while (calif < 0 || calif > 5) {
                            System.out.print("Ingrese calificación para el supermercado (0-5): ");
                            try {
                                calif = Double.parseDouble(teclado.nextLine());
                            } catch (NumberFormatException e) {
                                calif = -1;
                            }
                        }

                        superEncon.calificarSupermercado(calif);
                    } else {
                        String verdeSuave = "\u001B[38;5;120m";
                        String reset = "\u001B[0m";
                        System.out.println(verdeSuave + "Supermercado no encontrado." + reset);
                    }
                }


                case 5 -> {
                    List<Supermercado> supermercados = usuario.getSupermercados();
                    if (supermercados.isEmpty()) {
                        System.out.println("No hay supermercados registrados.");
                    } else {
                        for (Supermercado s : supermercados) {
                            double calif = s.getPromedioCalificacionSupermercado();
                            String color = "";
                            if (calif >= 4.0) {
                                color = "\u001B[38;5;120m";
                            } else if (calif >= 3.0) {
                                color = "\u001B[38;5;227m";
                            } else if (calif > 0) {
                                color = "\u001B[38;5;131m";
                            }
                            int estrellas = (int) Math.round(calif);
                            StringBuilder estrellasStr = new StringBuilder();
                            estrellasStr.append("★".repeat(Math.max(0, estrellas)));
                            System.out.println("Supermercado: " + s.getNombre());
                            if (calif > 0) {
                                System.out.println(color + "Calificación promedio: " + String.format("%.2f", calif) + " " + estrellasStr + "\u001B[0m");
                            } else {
                                System.out.println("Este supermercado aún no ha sido calificado.");
                            }
                            System.out.println();
                        }
                    }
                }

                case 6 -> {
                    System.out.print("Nombre del supermercado: ");
                    nombreSuper1 = teclado.nextLine();
                    encontrado = usuario.buscarSupermercado(nombreSuper1);
                    if (encontrado != null) encontrado.mostrarProductos();
                    else System.out.println("Supermercado no encontrado.");
                }


                case 7 -> buscarProductoEnTodosLosSupermercados(usuario, teclado);


                case 8 -> {
                    System.out.print("Nombre del supermercado: ");
                    nombreSuper1 = teclado.nextLine();
                    encontrado = usuario.buscarSupermercado(nombreSuper1);
                    if (encontrado != null) {
                        System.out.print("Nombre del producto a eliminar: ");
                        String nombreProd = teclado.nextLine();
                        boolean eliminado = encontrado.eliminarProducto(nombreProd);
                        if (eliminado)
                            System.out.println("\u001B[33mProducto eliminado correctamente.\u001B[0m");
                        else
                            System.out.println("\u001B[31mProducto no encontrado.\u001B[0m");
                    } else {
                        System.out.println("\u001B[31mSupermercado no encontrado.\u001B[0m");
                    }
                }

                case 9 -> {
                    System.out.print("Nombre del supermercado a eliminar: ");
                    nombreSuper1 = teclado.nextLine();
                    boolean eliminado = usuario.eliminarSupermercado(nombreSuper1);
                    if (eliminado)
                        System.out.println("\u001B[33mSupermercado eliminado correctamente.\u001B[0m");
                    else
                        System.out.println("\u001B[31mSupermercado no encontrado.\u001B[0m");
                }

                case 10 ->
                        System.out.println("Gracias por usar MiSuperIdeal.");
                 default -> System.out.println("Opción inválida.");
             }
         } while (opcion != 10);

         teclado.close();
    }



    private static void buscarProductoEnTodosLosSupermercados(Usuario us

                                                              uario, Scanner teclado) {
        System.out.print("Ingrese el nombre del producto que desea buscar en todos los supermercados: ");
        String nombreProducto = teclado.nextLine();
        boolean encontrado = false;

        for (Supermercado superm : usuario.getSupermercados()) {
            List<Producto> productosEncontrados = superm.buscarProductosPorNombre(nombreProducto);
            if (!productosEncontrados.isEmpty()) {
                for (Producto producto : productosEncontrados) {
                    System.out.println("En el supermercado: " + superm.getNombre());
                    System.out.println("Producto: " + producto.getNombre());
                    System.out.println("Marca: " + producto.getMarca());
                    System.out.println("Precio: " + producto.getPrecio());
                    System.out.println("Unidades disponibles: " + producto.getUnidades());

                    double calif = producto.getPromedioCalificacion();
                    String color = "";
                    if (calif >= 4.0) {
                        color = "\u001B[32m";
                    } else if (calif >= 3.0) {
                        color = "\u001B[33m";
                    } else if (calif > 0) {
                        color = "\u001B[31m";
                    }

                    int estrellas = (int) Math.round(calif);
                    StringBuilder estrellasStr = new StringBuilder();
                    estrellasStr.append("★".repeat(Math.max(0, estrellas)));

                    if (calif > 0) {
                        System.out.println(color + "Calificación promedio: " + String.format("%.2f", calif) + " " + estrellasStr + "\u001B[0m");
                    } else {
                        System.out.println("Este producto aún no ha sido calificado.");
                    }

                    System.out.println();
                }
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado en ninguno de los supermercados.");
        }
    }
}