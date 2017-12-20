package Exceptions;

public class TaskNuberUsedException extends Exception{
    public TaskNuberUsedException() {
        super("Task number repeated");
    }
}
