package model;

import java.util.List;

public class Carrera {
    private final String nombre;
    private final int codigo;
    private final int semestres;
    private final List<Estudiante> estudiantes;

    public Carrera(String nombre, int codigo, int semestres, List<Estudiante> estudiantes) {
        validarParametros(nombre, codigo, semestres);

        this.nombre = nombre;
        this.codigo = codigo;
        this.semestres = semestres;
        this.estudiantes = estudiantes;
    }

    private void validarParametros(String nombre, int codigo, int semestres) {
        if (nombre == null || nombre.length() < 2) {
            throw new IllegalArgumentException("Nombre no válido");
        }
        if (codigo < 1000 || codigo > 9999) {
            throw new IllegalArgumentException("Código no válido");
        }
        if (semestres < 4 || semestres > 20) {
            throw new IllegalArgumentException("Cantidad de semestres no válida");
        }
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if (estudianteExistente(estudiante.getMatricula())) throw new RuntimeException("Estudiante Existente");

        estudiantes.add(estudiante);
    }

    public boolean estudianteExistente(String matricula) {
        return estudiantes.stream()
                .anyMatch(estudiante -> estudiante.getMatricula().equals(matricula));
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getSemestres() {
        return semestres;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }
}
