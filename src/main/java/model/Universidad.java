package model;

import data.GestorDeArchivos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class Universidad {
    private final List<Carrera> carreras;

    public Universidad(String rutaCarreras, String rutaEstudiantes) {
        this.carreras = GestorDeArchivos.leerCarreras(rutaCarreras);
        asignarEstudiantes(rutaEstudiantes);
    }

    private void asignarEstudiantes(String rutaEstudiantes) {
        List<Estudiante> estudiantes = GestorDeArchivos.leerEstudiantes(rutaEstudiantes);
        for (Carrera carrera : carreras) {
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getNombreCarrera().equals(carrera.getNombre())) {
                    carrera.agregarEstudiante(estudiante);
                }
            }
        }
    }

    public List<Estudiante> getEstudiantes() {
        return carreras.stream()
                .map(Carrera::getEstudiantes)
                .flatMap(Collection::stream)
                .toList();
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void agregarCarrera(String nombre, int codigo, int semestres) {
        if (carreraExistente(codigo)) throw new RuntimeException("Carrera Existente");

        Carrera carrera = new Carrera(nombre, codigo, semestres, new ArrayList<>());

        carreras.add(carrera);
        GestorDeArchivos.agregarCarrera(carrera, "src/main/resources/carreras.txt");
    }

    private boolean carreraExistente(int codigo) {
        return carreras.stream()
                .anyMatch(carrera -> carrera.getCodigo() == codigo);
    }

    public void agregarEstudiante(String nombre, String apellido, String rut, String matricula, String nombreCarrera) {
        Carrera carrera = buscarCarrera(nombreCarrera);

        Estudiante estudiante = new Estudiante(nombre, apellido, rut, matricula, nombreCarrera);

        carrera.agregarEstudiante(estudiante);
        GestorDeArchivos.agregarEstudiante(estudiante,"src/main/resources/estudiantes.txt");
    }

    public List<Estudiante> buscarEstudiante(String nombreCarrera) {
        return carreras.stream()
                .filter(carrera -> carrera.getNombre().equals(nombreCarrera))
                .findFirst()
                .map(Carrera::getEstudiantes)
                .orElse(emptyList());
    }

    public List<Estudiante> buscarEstudiante(String nombreCarrera, String nombreEstudiante) {
        List<Estudiante> estudiantes = carreras.stream()
                .filter(carrera -> carrera.getNombre().equals(nombreCarrera))
                .findFirst()
                .map(Carrera::getEstudiantes)
                .orElse(emptyList());

        return estudiantes.stream()
                .filter(estudiante -> estudiante.nombresIguales(nombreEstudiante))
                .collect(toList());
    }

    public Carrera buscarCarrera(int codigo) {
        return carreras.stream()
                .filter(carrera -> carrera.getCodigo() == codigo)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El codigo " + codigo + " no existe"));
    }

    public Carrera buscarCarrera(String nombre) {
        return carreras.stream()
                .filter(carrera -> carrera.getNombre().equals(nombre))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("La carrera" + nombre + " no existe"));
    }
}
