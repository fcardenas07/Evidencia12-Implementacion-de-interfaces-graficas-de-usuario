package gui;

import model.Carrera;
import model.Estudiante;
import model.Universidad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaBusquedaEstudiante extends Ventana {
    private final Universidad universidad;
    private JTextField campoNombre;
    private JButton botonBuscar;
    private JButton botonRegresar;
    private JComboBox listaCarreras;

    public VentanaBusquedaEstudiante(Universidad universidad) {
        super("Búsqueda de Estudiante", 500, 520);
        this.universidad = universidad;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarListaCarreras();
        generarCampoNombre();
        generarBotonBuscar();
        generarBotonRegresar();
    }

    private void generarListaCarreras() {
        super.generarJLabel("Carrera:", 20, 100, 100, 20);
        String[] arreglo = getArregloCarreras();
        listaCarreras = super.generarListaDesplegable(arreglo, 200, 100, 250, 20);
        this.add(listaCarreras);
    }

    private String[] getArregloCarreras() {
        return universidad.getCarreras().stream()
                .map(Carrera::getNombre)
                .toArray(String[]::new);
    }

    private void generarBotonBuscar() {
        String textoBoton = "Buscar Estudiante";
        botonBuscar = super.generarBoton(textoBoton, 75, 400, 180, 25);
        this.add(botonBuscar);
        botonBuscar.addActionListener(this);
    }

    private void generarBotonRegresar() {
        String textoBotonCancelar = "Regresar";
        botonRegresar = super.generarBoton(textoBotonCancelar, 275, 400, 180, 25);
        this.add(botonRegresar);
        botonRegresar.addActionListener(this);
    }

    private void generarCampoNombre() {
        String textoNombre = "Nombre Estudiante:";
        super.generarJLabel(textoNombre, 20, 50, 150, 20);
        campoNombre = super.generarJTextField(200, 50, 250, 20);
        this.add(campoNombre);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonBuscar) {
            String[] nombreColumnas = {"Nombre estudiante", "Matrícula", "Carrera", "Código Carrera"};
            new VentanaTabla(registrarEstudiante(), nombreColumnas);
        }
        if (e.getSource() == botonRegresar) {
            new VentanaMenuBienvenida(universidad);
            this.dispose();
        }
    }

    private String[][] registrarEstudiante() {
        List<Estudiante> estudiantes;
        String nombreCarrera = String.valueOf(listaCarreras.getSelectedItem());

        if (campoNombre.getText().isBlank()) {
            estudiantes = universidad.buscarEstudiante(nombreCarrera);
        } else {
            estudiantes = universidad.buscarEstudiante(nombreCarrera, campoNombre.getText());
        }

        return estudiantes.stream()
                .map(this::mapEstudiante)
                .toArray(String[][]::new);
    }

    private String[] mapEstudiante(Estudiante estudiante) {
        String nombreCarrera = estudiante.getNombreCarrera();
        return new String[]{estudiante.getNombre(),
                estudiante.getMatricula(),
                nombreCarrera,
                String.valueOf(universidad.buscarCarrera(nombreCarrera).getCodigo())
        };
    }
}
