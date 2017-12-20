package main.java.controlador;

public class Tarea {

    private final String numeroTarea;
    private final String nombre;
    private final String descripcion;
    private final String fecha;

    public Tarea(String numeroTarea, String nombre, String descripcion, String fecha) {
        this.numeroTarea = numeroTarea;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getNumeroTarea() {
        return numeroTarea;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String toString() {
        return "Num: " + numeroTarea + "Nombre: " + nombre + "Descripcion: " + descripcion + "fecha: " + fecha;
    }
}
