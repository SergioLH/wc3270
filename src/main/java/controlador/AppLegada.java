package main.java.controlador;

import Exceptions.TaskNuberUsedException;

import java.util.ArrayList;

/**
 * @author apg29
 */
public class AppLegada {

    private Comunicacion3270Emulador comunicacion;

    private static AppLegada instancia = null;

    protected AppLegada(Comunicacion3270Emulador comunicacion) {
        this.comunicacion = comunicacion;
    }

    public static AppLegada getInstancia(Comunicacion3270Emulador comunicacion) {
        if (instancia == null) {
            instancia = new AppLegada(comunicacion);
        }
        return instancia;
    }

    public void nuevoFicheroTareas() {
        comunicacion.escribirCadena("n");
        comunicacion.enter();
        comunicacion.escribirCadena("y");
        comunicacion.enter();
        comunicacion.enter();
    }

    public void cambiarMenuNuevaTarea() {
        comunicacion.escribirCadena("a");
        comunicacion.enter();
    }

    public void añadirTarea(String taskNumber, String name, String description,
                            String dd, String mm, String yy) throws Exception {
        comunicacion.escribirCadena(taskNumber);
        comunicacion.enter();
        comunicacion.ascii();
        if (!comunicacion.leerPantalla().toString().contains("TASK NUMBER REPEATED")) {
            comunicacion.escribirCadena(name);
            comunicacion.enter();
            comunicacion.escribirCadena(description);
            comunicacion.enter();
            comunicacion.escribirCadena(dd);
            comunicacion.enter();
            comunicacion.escribirCadena(mm);
            comunicacion.enter();
            comunicacion.escribirCadena(yy);
            comunicacion.enter();
            comunicacion.enter();
        } else {
            throw new TaskNuberUsedException();
        }
    }

    public void eliminarTarea(String taskNumber) {
        comunicacion.escribirCadena("r");
        comunicacion.enter();
        comunicacion.escribirCadena(taskNumber);
        comunicacion.enter();
        comunicacion.escribirCadena("y");
        comunicacion.enter();
        comunicacion.enter();
    }

    public void guardarTareas() {
        comunicacion.escribirCadena("s");
        comunicacion.enter();
        comunicacion.enter();
    }

    public ArrayList<Tarea> buscarTareas(String dd, String mm, String yy) {
        comunicacion.escribirCadena("t");
        comunicacion.enter();
        comunicacion.escribirCadena(dd);
        comunicacion.enter();
        comunicacion.escribirCadena(mm);
        comunicacion.enter();
        comunicacion.escribirCadena(yy);
        return leerTareas();
    }

    public ArrayList<Tarea> getTareas() {
        comunicacion.escribirCadena("l");
        return leerTareas();
    }

    private ArrayList<Tarea> leerTareas() {
        String numTask;
        String name;
        String description;
        String date;
        ArrayList<Tarea> tareas = new ArrayList<>();
        do {
            comunicacion.enter();
            comunicacion.ascii();
            String cadenaAux = comunicacion.leerPantalla().toString();
            String[] array = cadenaAux.split("data:");
            int n = array.length;
            n--;
            int i = 0;
            while (i <= n) {
                if (array[i].contains("TASK NUMBER")) {
                    numTask = array[i];
                    name = array[i + 1];
                    description = array[i + 2];
                    date = array[i + 3];

                    numTask = numTask.split(": ")[1];
                    name = name.split(": ")[1];
                    description = description.split(": ")[1];
                    date = date.split(": ")[1];

                    tareas.add(new Tarea(numTask, name, description, date));
                }
                i++;
            }
        } while (comunicacion.hayMasTexto());

        comunicacion.enter();
        return tareas;
    }

    public void logout() {
        guardarTareas();
        comunicacion.escribirCadena("e");
        comunicacion.enter();
        comunicacion.enter();
    }
}
