import java.util.Scanner;

public class ConversorMonedaMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        APICliente apiCliente = new APICliente();
        int opcion;

        do {
            System.out.println("Bienvenido/a al Conversor de Moneda =)");
            System.out.println("1) Dólar  => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar  => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar  => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.print("Elija una opción válida: ");

            opcion = scanner.nextInt();

            if (opcion == 7) {
                System.out.println("Saliendo del programa...");
                break;
            }

            System.out.print("Ingrese la cantidad a convertir: ");
            double cantidad = scanner.nextDouble();

            try {
                double resultado = 0.0;

                switch (opcion) {
                    case 1:
                        resultado = apiCliente.convertirMoneda("USD", "ARS", cantidad); // Dólar a Peso argentino
                        break;
                    case 2:
                        resultado = apiCliente.convertirMoneda("ARS", "USD", cantidad); // Peso argentino a Dólar
                        break;
                    case 3:
                        resultado = apiCliente.convertirMoneda("USD", "BRL", cantidad); // Dólar a Real brasileño
                        break;
                    case 4:
                        resultado = apiCliente.convertirMoneda("BRL", "USD", cantidad); // Real brasileño a Dólar
                        break;
                    case 5:
                        resultado = apiCliente.convertirMoneda("USD", "COP", cantidad); // Dólar a Peso colombiano
                        break;
                    case 6:
                        resultado = apiCliente.convertirMoneda("COP", "USD", cantidad); // Peso colombiano a Dólar
                        break;
                    default:
                        System.out.println("Por favor, elija una opción válida.");
                        continue;
                }

                System.out.printf("Resultado: %.2f\n", resultado);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            System.out.println();
        } while (true);

        scanner.close();
        System.out.println("¡Gracias por usar el Conversor de Moneda!");
    }
}
