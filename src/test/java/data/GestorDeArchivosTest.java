package data;

import model.Carrera;
import model.Estudiante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorDeArchivosTest {
    private String rutaEstudiantes;
    private String rutaCarreras;

    @BeforeEach
    void setUp() {
        rutaEstudiantes = "src/test/resources/estudiantes.txt";
        rutaCarreras = "src/test/resources/carreras.txt";
    }

    @Test
    void testLeerEstudiantes() {
        List<Estudiante> estudiantes = GestorDeArchivos.leerEstudiantes(rutaEstudiantes);

        Estudiante estudiante = estudiantes.get(estudiantes.size() - 1);

        assertAll("Lectura de estudiante",
                () -> assertEquals(20, estudiantes.size()),
                () -> assertEquals("Otto", estudiante.getNombre()),
                () -> assertEquals("Reyes", estudiante.getApellido()),
                () -> assertEquals("87151816", estudiante.getRut()),
                () -> assertEquals("8715181624", estudiante.getMatricula()),
                () -> assertEquals("ICI", estudiante.getNombreCarrera())
        );
    }

    @Test
    void testLeerCarreras() {
        List<Carrera> carreras = GestorDeArchivos.leerCarreras(rutaCarreras);

        Carrera carrera = carreras.get(carreras.size() - 1);

        assertAll("Lectura de carreras",
                () -> assertEquals(3, carreras.size()),
                () -> assertEquals("ICI-I", carrera.getNombre()),
                () -> assertEquals(3092, carrera.getCodigo()),
                () -> assertEquals(12, carrera.getSemestres())
        );
    }

    @Test
    void testAgregarEstudiante() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");

        Estudiante estudiante = new Estudiante("Juan",
                "Perez",
                "12345678-9",
                "12345678923",
                "ICI");

        GestorDeArchivos.agregarEstudiante(estudiante, tempFile.toString());

        List<String> lines = Files.readAllLines(tempFile);
        String actual = lines.get(0);

        assertEquals(1, lines.size());
        assertEquals("Juan,Perez,12345678-9,12345678923,ICI", actual);
    }

    @Test
    void testAgregarCarrera() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");

        Carrera carrera = new Carrera("ICI-B", 3098, 12, new ArrayList<>());

        GestorDeArchivos.agregarCarrera(carrera, tempFile.toString());

        List<String> lines = Files.readAllLines(tempFile);
        String actual = lines.get(0);

        assertEquals(1, lines.size());
        assertEquals("ICI-B,3098,12", actual);
    }
}
