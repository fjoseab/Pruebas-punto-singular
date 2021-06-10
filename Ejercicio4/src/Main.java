
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        //Lectura de dato
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese contenido");
        Object dato = reader.readLine();
        //Creacion del objeto generico
        Generico g = new Generico();
        g.generico(dato);
    }
}

// Calse que contiene el metodo para seber de que tipo es
class Generico {
    
    //Metodo que recibe como parametro un generico
    public void generico(Object dato) {
        //Variables
        String contenido = dato.toString();
        boolean condicionNumero = true;
        boolean palabra = true;
        boolean condicion = false;
        
        //Saber si es numero
        for (int i = 0; i < contenido.length(); i++) {
            if (contenido.charAt(i) == '0' || contenido.charAt(i) == '1'
                    || contenido.charAt(i) == '2' || contenido.charAt(i) == '3'
                    || contenido.charAt(i) == '4' || contenido.charAt(i) == '5'
                    || contenido.charAt(i) == '6' || contenido.charAt(i) == '7'
                    || contenido.charAt(i) == '8' || contenido.charAt(i) == '9') {
                palabra = false;

            } else {
                condicionNumero = false;
                palabra = true;
            }
        }
        if (condicionNumero) {
            System.out.println("Eres un numero");
        }
        //Saber si es algun tipo de lista o vector
        if (contenido.charAt(0) == '[' && contenido.charAt(contenido.length() - 1) == ']') {
            condicion = true;
            palabra = false;
        }
        if (contenido.charAt(0) == '{' && contenido.charAt(contenido.length() - 1) == '}') {
            condicion = true;
            palabra = false;
        }
        if (condicion) {
            System.out.println("Eres una lista");
        }
        //Saber si es otro tipo de dato
        if (contenido.equalsIgnoreCase("true") || contenido.equalsIgnoreCase("false")) {
            System.out.println("Eres de otro tipo");
            palabra = false;
        }
        //saber si es una palabra
        if(palabra) {
            System.out.println("Eres una palabra");
        }
    }
}
