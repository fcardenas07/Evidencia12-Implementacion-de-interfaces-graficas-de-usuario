package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CarreraTest {
    private Carrera carrera;
    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        carrera = new Carrera("ICI", 3059, 12, new ArrayList<>());
        estudiante = new Estudiante("nombre",
                "apellido",
                "123456789",
                "12345678923",
                "ICI");
        carrera.agregarEstudiante(estudiante);
    }

    @Test
    void agregarEstudianteNoExistente() {
        Estudiante nuevoEstudiante = new Estudiante("nombre",
                "apellido",
                "987654321",
                "98765432123",
                "ICI");

        carrera.agregarEstudiante(nuevoEstudiante);

        int size = carrera.getEstudiantes().size();
        String matricula = carrera.getEstudiantes().get(1).getMatricula();

        assertEquals(2, size);
        assertEquals("98765432123", matricula);
    }

    @Test
    void agregarEstudianteExistente() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> carrera.agregarEstudiante(estudiante));

        assertEquals("Estudiante Existente", exception.getMessage());
    }

    @Test
    void estudianteExistente() {
        boolean resultado = carrera.estudianteExistente("12345678923");

        assertTrue(resultado);
    }

    @Test
    void estudianteNoExistente() {
        boolean resultado = carrera.estudianteExistente("11111111123");

        assertFalse(resultado);
    }
}
