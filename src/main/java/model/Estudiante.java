package model;

public class Estudiante {
    private final String nombre;
    private final String apellido;
    private final String rut;
    private final String matricula;
    private final String nombreCarrera;

    public Estudiante(String nombre, String apellido, String rut, String matricula, String nombreCarrera) {
        validarParametros(nombre, apellido, rut, matricula);

        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.matricula = matricula;
        this.nombreCarrera = nombreCarrera;
    }

    private void validarParametros(String nombre, String apellido, String rut, String matricula) {
        if (nombre == null || nombre.length() < 2) {
            throw new IllegalArgumentException("Nombre no válido");
        }
        if (apellido == null || apellido.length() < 2) {
            throw new IllegalArgumentException("Apellido no válido");
        }
        if (rut == null || rut.isBlank()) {
            throw new IllegalArgumentException("Rut no válido");
        }
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula no válida");
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRut() {
        return rut;
    }

    public boolean nombresIguales(String nombreEstudiante) {
        return nombre.toUpperCase().equals(nombreEstudiante.toUpperCase().strip());
    }
}
