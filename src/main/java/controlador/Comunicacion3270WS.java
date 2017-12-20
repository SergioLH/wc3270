package main.java.controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 *
 * @author apg29
 */
public class Comunicacion3270WS implements Comunicacion3270Emulador {

    protected Process emulator; // proceso del ws3270.exe
    protected InputStream lectura; // entrada de datos
    protected PrintWriter teclado; // salida de datos
    protected String ws3270exe = "C:/Program Files/wc3270/ws3270.exe";
    protected final String ENTER = "ENTER"; // tecla enter
    private final String FUNCTION_KEY = "PF(%d)"; // tecla F3
    protected static final String OK = "OK";
    protected static final String ASCII = "ascii";
    protected static final String MORE = "More...";
    protected static final String CADENA_CONEXION = "connect %s:%s";

    private static Comunicacion3270WS instancia = null;

    protected Comunicacion3270WS() {
        try {
            this.emulator = Runtime.getRuntime().exec(ws3270exe);
            lectura = this.emulator.getInputStream();
            teclado = new PrintWriter(new OutputStreamWriter(this.emulator.getOutputStream()));
        } catch (FileNotFoundException ef) {
            System.err.println("Error, ejecutable ws3270.exe no encontrado");
            System.exit(1);
        } catch (IOException ex) {
            System.err.println("Error, no se pudo conectar con ws3270.exe");
            System.exit(1);
        }
    }

    public static Comunicacion3270WS getInstancia() {
        if (instancia == null) {
            instancia = new Comunicacion3270WS();
        }
        return instancia;
    }

    @Override
    public StringBuilder leerPantalla() {
        StringBuilder cadena = new StringBuilder();
        try {
            while (lectura.available() == 0); //Espera a que se llene el buffer
            while (lectura.available() > 0) {
                cadena.append((char) lectura.read());
            }
        } catch (IOException ex) {
            cadena = null;
        } finally {
            return cadena;
        }
    }

    @Override
    public void enter() {
        escribirLinea(ENTER);
    }
    
    @Override
    public void ascii() {
        escribirLineaNoEsperaOK(ASCII);
    }
    
    @Override
    public boolean hayMasTexto() {
        ascii();
        String cadenaAux = leerPantalla().toString();
        return cadenaAux.contains(MORE);
    }

    @Override
    public void teclaFuncion(int teclaF) {
        String funcion = String.format(FUNCTION_KEY, teclaF);
        escribirLineaNoEsperaOK(funcion);
    }

    @Override
    public void escribirCadena(String cadena) {
        escribirLinea("string " + cadena);
    }

    @Override
    public void escribirLinea(String cadena) {
        do {
            cadena += "\n";
            this.teclado.write(cadena);
            this.teclado.flush();
        } while (leerPantalla().toString().contains(OK));
        //Espera una señal de OK o de MORE...
    }
    
    @Override
    public void escribirLineaNoEsperaOK(String cadena) {
        cadena += "\n";
        this.teclado.write(cadena);
        this.teclado.flush();
    }

    @Override
    public boolean buscarCadena(String cadena) {
        ascii();
        return leerPantalla().toString().contains(cadena);
    }

    @Override
    public void conectar(String ip, String puerto) throws RuntimeException {
        String cadenaConexion = String.format(CADENA_CONEXION, ip, puerto);
        escribirLinea(cadenaConexion);
        enter();
    }

}
