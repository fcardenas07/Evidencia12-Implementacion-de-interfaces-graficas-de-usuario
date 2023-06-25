package data;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import model.Carrera;
import model.Estudiante;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;

public class GestorDeArchivos {
    public static List<Estudiante> leerEstudiantes(String ruta) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            return reader.readAll().stream()
                    .map(record -> new Estudiante(record[0], record[1], record[2], record[3], record[4]))
                    .collect(toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Carrera> leerCarreras(String ruta) {
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            return reader.readAll().stream()
                    .map(record -> new Carrera(record[0], parseInt(record[1]), parseInt(record[2]), new ArrayList<>()))
                    .collect(toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void agregarEstudiante(Estudiante estudiante, String ruta) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(ruta, true))) {
            String[] record = {estudiante.getNombre(),
                    estudiante.getApellido(),
                    estudiante.getRut(),
                    estudiante.getMatricula(),
                    estudiante.getNombreCarrera()};
            writer.writeNext(record, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void agregarCarrera(Carrera carrera, String ruta) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(ruta, true))) {
            String[] record = {carrera.getNombre(), valueOf(carrera.getCodigo()), valueOf(carrera.getSemestres())};
            writer.writeNext(record, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
