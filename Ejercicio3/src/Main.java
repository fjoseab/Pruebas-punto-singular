
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Variables de condicion
        boolean condicion = false;
        boolean condicionEdad = false;
        boolean encontrado = false;
        //Variables de lectura
        Scanner lectura;
        byte opcion;
        String usuario;
        String contraseña;
        String nombre;
        byte edad;
        //Variable de almacenamiento de datos temporal
        String datos[][];

        //Instacia de la clase Archivo y Usuario
        Archivo a = new Archivo();
        Usuario u;

        //Ciclo para menu
        while (!condicion) {
            //Carga de los usuarios que ya se almacenaron en el archivo txt
            datos = a.cargarArchivo();
            //Opciones de menu
            System.out.println("Seleccione opcion: ");
            System.out.println("1) Login");
            System.out.println("2) Registro");
            System.out.println("3) Salir");
            lectura = new Scanner(System.in);
            try {
                //Lectura de la opcion del menu
                opcion = lectura.nextByte();
                //Opcion de login
                if (opcion == 1) {
                    //Se comprueba que ya esistan usuarios registrados
                    if (datos == null) {
                        System.out.println("Antes debe registrarse: ");
                    } else {
                        //Lectura de usuario y contraseña
                        System.out.println("Ingrese usuario: ");
                        lectura = new Scanner(System.in);
                        usuario = lectura.next();
                        System.out.println("Ingrese contraseña: ");
                        lectura = new Scanner(System.in);
                        contraseña = lectura.next();
                        //Busqueda del usuario y contraseña en los datos guardados
                        for (int i = 0; i < datos.length; i++) {
                            //Comparacion de los datos guardados con los ingresados
                            if (datos[i][0].equals(usuario) && datos[i][0].equals(contraseña)) {
                                //Verificacion de si es su primer login
                                if (datos[i][4].equals("false")) {
                                    //Lectura de nombre
                                    System.out.println("Ingrese nombre: ");
                                    lectura = new Scanner(System.in);
                                    nombre = lectura.next();
                                    //Guardado de nombre de forma temporal
                                    datos[i][2] = nombre;
                                    //Ciclo para verificar que la edad ingresada es valida
                                    while (!condicionEdad) {
                                        try {
                                            //Lectura de edad
                                            System.out.println("Ingrese edad: ");
                                            lectura = new Scanner(System.in);
                                            edad = lectura.nextByte();
                                            //Verificacion de edad
                                            if (edad >= 0 && edad <= 125) {
                                                //Guardado de edad y cambio para indicar que ya se realizo el primer login de forma temporal
                                                datos[i][3] = String.valueOf(edad);
                                                datos[i][4] = "true";
                                                condicionEdad = true;
                                                a.borrar();
                                                //Guardado de datos de forma permanente
                                                for (int j = 0; j < datos.length; j++) {
                                                    u = new Usuario(datos[j][0], datos[j][1], datos[j][2], (datos[j][3]), datos[j][4]);
                                                    a.escribirArchivo(u);
                                                }
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Lo que ingreso no es la edad : ");
                                        }
                                    }
                                    condicionEdad = false;
                                    System.out.println("Bienvenido " + datos[i][2].toUpperCase());
                                } else {
                                    System.out.println("Bienvenido " + datos[i][2].toUpperCase());
                                }
                            }
                        }
                    }
                }
                //Opcion de registro
                if (opcion == 2) {
                    //Lectura de usuario                    
                    System.out.println("Ingrese usuario: ");
                    lectura = new Scanner(System.in);
                    usuario = lectura.next();
                    //Verificacion de que no existe ningun usuario
                    if (datos == null) {
                        //Lectura de contraseña
                        System.out.println("Ingrese contraseña: ");
                        lectura = new Scanner(System.in);
                        contraseña = lectura.next();
                        //Guardado del usuario en un txt
                        System.out.println("Usuario registrado: ");
                        u = new Usuario(usuario, contraseña, null, "0", "false");
                        a.escribirArchivo(u);
                    } else {
                        //Existen usuarios y se busca si alguno coincide con el que se quiere registrar
                        //En caso de encontrarse se notifica
                        for (int i = 0; i < datos.length; i++) {
                            if (datos[i][0].equals(usuario)) {
                                System.out.println("Error el usuario ya esta registrado: ");
                                encontrado = true;
                            }
                        }
                        //El usuario no se encontro
                        if (encontrado == false) {
                            //Lectura de contraseña
                            System.out.println("Ingrese contraseña: ");
                            lectura = new Scanner(System.in);
                            contraseña = lectura.next();
                            //Guardado del usuario en un txt
                            System.out.println("Usuario registrado: ");
                            u = new Usuario(usuario, contraseña, null, "0", "false");
                            a.escribirArchivo(u);
                        }
                    }
                }
                //Opcion salir
                if (opcion == 3) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error opcion invalida");
            }
        }
    }

}

//Clase para la definicion de usuario
class Usuario {

    //Atributos 
    private String usuario;
    private String contraseña;
    private String nombre;
    private String edad;
    private String ingreso;

    //Constructor
    public Usuario(String usuario, String contraseña, String nombre, String edad, String ingreso) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.edad = edad;
        this.ingreso = ingreso;
    }

    //Get y Set de los atributos
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }
}

//Calse para la lectura y escritura del archivo
class Archivo {

    //Metodo para cargar el archivo
    public String[][] cargarArchivo() {
        //Variable donde se guardaran los datos
        String datos[][] = null;
        //Inicialización de variables para el acomodo de datos en la carga 
        int aux1 = 0;
        int tamaño = 0;
        //Inicialización de variables para la extracción de datos del archivo
        FileReader fileReader = null;
        FileReader fileReader2 = null;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReaderCarga = null;
        try {
            //Definición de la ruta del archivo  
            String ruta = "src/usuarios.txt";
            //Paso de la ruta del archivo para su lectura 
            fileReader = new FileReader(ruta);
            fileReader2 = new FileReader(ruta);
            //Lectura del archivo  
            bufferedReader = new BufferedReader(fileReader);
            bufferedReaderCarga = new BufferedReader(fileReader2);
            //Inicialización de variables para la lectura de los datos
            String linea;
            String[] longitud = null;
            //Ciclo para definir el largo y el ancho 
            while ((linea = bufferedReader.readLine()) != null) {
                tamaño++;
                longitud = linea.split("\\,");
            }
            //Definición de largo y ancho 
            datos = new String[tamaño][longitud.length];
            //Ciclo para carga de los datos 
            while ((linea = bufferedReaderCarga.readLine()) != null) {
                // Lee línea por línea, omitiendo los saltos de línea
                String[] seccion = linea.split("\\,");
                for (int i = 0; i < seccion.length; i++) {
                    datos[aux1][i] = seccion[i];
                }
                aux1++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("1" + e.getMessage());
        } catch (Exception e) {

        }
        return datos;
    }

    //Metodo para modificar el archivo
    public void escribirArchivo(Usuario u) {
        //Inicialización de variables para la carga de datos
        Writer out = null;
        try {
            //Apertura del archivo para guardar los datos
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/usuarios.txt", true), "UTF-8"));
            try {
                // Escritura linea a linea en el archivo
                out.write(u.getUsuario() + "," + u.getContraseña() + "," + u.getNombre() + "," + u.getEdad() + "," + u.getIngreso() + "\n");
            } catch (IOException ex) {
                System.out.println("Mensaje excepcion escritura: " + ex.getMessage());
            }
        } catch (UnsupportedEncodingException | FileNotFoundException ex2) {
            System.out.println("Mensaje error 2: " + ex2.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException ex3) {
                System.out.println("Mensaje error cierre fichero: " + ex3.getMessage());
            }
        }
    }

    //Metodo para borrar el archivo
    public void borrar() {
        Writer out = null;
        try {
            //Apertura del archivo para guardar los datos
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/usuarios.txt"), "UTF-8"));
            try {
                // Limpia el archivo
                out.write("");
            } catch (IOException ex) {
                System.out.println("Mensaje excepcion escritura: " + ex.getMessage());
            }
        } catch (UnsupportedEncodingException | FileNotFoundException ex2) {
            System.out.println("Mensaje error 2: " + ex2.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException ex3) {
                System.out.println("Mensaje error cierre fichero: " + ex3.getMessage());
            }
        }
    }
}
