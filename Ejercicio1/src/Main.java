
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Entrada de datos
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Cuantos numeros quiere ingresar?");
            int numeros[] = new int[sc.nextInt()];

            System.out.println("Cargar los Valores :");
            for (int i = 0; i < numeros.length; i++) {
                numeros[i] = sc.nextInt();
            }
            System.out.println("Numero: ");
            int numEncontrar = sc.nextInt();

            System.out.println("Las combinaciones son: ");
            sumar(numeros, 0, "", 0, numEncontrar);
        } catch (Exception e) {
            System.out.println("Error ");
        }

    }

    //Metodo recursivo para sacar la combinacion de numeros
    static void sumar(int datos[], int posicion, String combinacion, int suma, int numEncontrar) {
        //Comparacion de que ya se recorrio todo el arreglo de numeros
        if (posicion < datos.length) {
            suma = datos[posicion];
            combinacion = String.valueOf(datos[posicion]);
            for (int i = 1; i < datos.length; i++) {
                suma += datos[i];
                //Comparaciones para saber si la combinacion da el numero buscado
                if (suma == numEncontrar) {
                    combinacion += " " + datos[i];
                    System.out.println(" " + combinacion);
                }
                if (suma < numEncontrar) {
                    combinacion += " " + datos[i];
                }
                if (suma > numEncontrar) {
                    suma -= datos[i];
                }
            }
            //Limpiesa de variables
            combinacion = "";
            suma = 0;
            //LLamada recursiva
            sumar(datos, posicion + 1, combinacion, suma, numEncontrar);
        }
    }
}
