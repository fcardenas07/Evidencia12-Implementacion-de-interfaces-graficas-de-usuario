package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UniversidadTest {

    private Universidad universidad;

    @BeforeEach
    void setUp() {
        universidad = new Universidad(
                "src/test/resources/carreras.txt",
                "src/test/resources/estudiantes.txt"
        );
    }

    @Test
    void creacionUniversidad() {
        List<Estudiante> estudiantes = universidad.getEstudiantes();
        List<Carrera> carreras = universidad.getCarreras();

        int estudianteSize = estudiantes.size();
        int carrerasSize = carreras.size();

        assertEquals(20, estudianteSize);
        assertEquals(3, carrerasSize);
    }

    @Test
    void buscarCarrera() {
        Carrera carrera = universidad.buscarCarrera(3095);

        assertAll("Buscar carrera",
                () -> assertEquals("ICME", carrera.getNombre()),
                () -> assertEquals(3095, carrera.getCodigo()),
                () -> assertEquals(12, carrera.getSemestres())
        );
    }

    @Test
    void buscarCarreraConCodigoNoExistente() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> universidad.buscarCarrera(1111));

        assertEquals("El codigo 1111 no existe", exception.getMessage());
    }

    @Test
    void buscarEstudiantePorCarrera() {
        List<Estudiante> estudiantes = universidad.buscarEstudiante("ICI");

        int size = estudiantes.size();
        boolean resultado = estudiantes.stream()
                .allMatch(estudiante -> estudiante.getNombreCarrera().equals("ICI"));

        assertEquals(6, size);
        assertTrue(resultado);
    }

    @Test
    void buscarEstudiantePorCarreraYNombreQueNoExiste() {
        List<Estudiante> estudiantes = universidad.buscarEstudiante("ICI", "Nombre");

        int size = estudiantes.size();

        assertEquals(0, size);
    }

    @Test
    void buscarEstudiantePorCarreraYNombreQueExiste() {
        List<Estudiante> estudiantes = universidad.buscarEstudiante("ICI", "Otto");

        int size = estudiantes.size();

        assertEquals(2, size);
    }
}