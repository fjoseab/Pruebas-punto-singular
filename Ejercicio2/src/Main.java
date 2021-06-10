
import java.util.Scanner;

public class Main {

    //Metodo Main
    public static void main(String[] args) {
        // LLamada de los metodos para solicitar PIN y calcular PIN
        PIN pin = new PIN();
        pin.adivinarPIN(pin.solicitudNumero(), (short) 0);
    }
}

//Clase PIN
class PIN {

    //Metodo para la lectura de PIN
    short solicitudNumero() {
        // Variables 
        boolean condicion = false;
        short pin = 0;
        byte digitos = 0;
        Scanner lectura;
        //Lectura
        while (!condicion) {
            try {
                System.out.print("Ingrese PIN: ");
                lectura = new Scanner(System.in);
                pin = lectura.nextShort();
                //Longitud de PIN
                digitos = (byte) (Math.log10(pin) + 1);
                //Condicion de longitud de PIN
                if (digitos == 4) {
                    condicion = true;
                } else {
                    //Mensaje de que no se cumple la longitud 
                    System.out.println("El PIN no cumple con la longitud de caracteres");
                }
            } catch (Exception e) {
                //Mensaje de que el PIN no es numerico
                System.out.println("Error el PIN debe ser numerico");
            }
        }
        return pin;
    }

    //Metodo adivinar PIN
    public void adivinarPIN(short pin, short numPINCalculado) {
        //Variable de pin calculado con anterioridad
        short numeroPIN = numPINCalculado;
        //Comparacion para saber si el PIN es el correcto
        if (pin != numeroPIN) {
            //LLamada de si mismo del metodo para calcular el PIN
            adivinarPIN(pin, (short) (numPINCalculado + 1));
        } else {
            System.out.println("El PIN es: " + numeroPIN);
        }

    }
}
