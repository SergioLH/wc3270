package main.java.controlador;

import Exceptions.UserIdInUseException;

public class ComunicacionMusicSP {

    private Comunicacion3270Emulador comunicacion;
    private static String ip = "155.210.68.153";
    private static String puerto = "423";

    private static ComunicacionMusicSP instancia = null;

    protected ComunicacionMusicSP(Comunicacion3270Emulador comunicacion) {
        this.comunicacion = comunicacion;
    }

    public static ComunicacionMusicSP getInstancia(Comunicacion3270Emulador comunicacion) {
        if (instancia == null) {
            instancia = new ComunicacionMusicSP(comunicacion);
        }
        return instancia;
    }

    public void conectar() {
        comunicacion.conectar(ip, puerto);
    }

    public boolean login(String usuario, String contraseña) throws Exception {
        //Escribe el nombre de usuario
        //Si no es válido pulsa F3 y Enter para limpiar campos
        //y devolverá FALSE
        comunicacion.escribirCadena(usuario);
        comunicacion.enter();
        if (comunicacion.buscarCadena("Userid is not authorized")) {
            comunicacion.teclaFuncion(3);
            comunicacion.enter();
            return false;
        }
        //Escribe la contraseña
        //Si no es válida pulsa F3 y Enter para limpiar campos
        //y devolverá FALSE
        comunicacion.escribirCadena(contraseña);
        comunicacion.enter();
        if (comunicacion.buscarCadena("Password incorrect!")) {
            comunicacion.teclaFuncion(3);
            comunicacion.enter();
            return false;
        }
        //Si ya hay un usuario con el mismo ID connectado
        //Lanza una excepción
        if (comunicacion.buscarCadena("Userid is in use.")) {
            throw new UserIdInUseException();
        }
        //Si la contraseña es válida devolverá TRUE
        //y ejecutará el programa
        if (comunicacion.buscarCadena("Press ENTER to continue...")) {
            comunicacion.enter();
            comenzarPrograma();
            return true;
        }
        return false;
    }

    public void comenzarPrograma() throws Exception {
        comunicacion.escribirCadena("tasks2.job");
        comunicacion.enter();
    }

    public void logout() {
        comunicacion.teclaFuncion(3);
        comunicacion.escribirCadena("off");
        comunicacion.enter();
    }

    public String verPantalla() {
        comunicacion.ascii();
        return comunicacion.leerPantalla().toString();
    }
}
