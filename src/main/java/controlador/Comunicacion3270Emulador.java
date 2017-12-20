package main.java.controlador;

/**
 *
 * @author apg29
 */
public interface Comunicacion3270Emulador {
    
    StringBuilder leerPantalla();
    
    void enter();
    
    void ascii();
    
    boolean hayMasTexto();
    
    void teclaFuncion(int teclaF);
    
    void escribirCadena(String cadena);
    
    void escribirLinea(String cadena);
    
    void escribirLineaNoEsperaOK(String cadena);
    
    boolean buscarCadena(String cadena);

    //void esperaPantalla(String texto);
    
    void conectar(String ip, String puerto) throws RuntimeException;
    
}
