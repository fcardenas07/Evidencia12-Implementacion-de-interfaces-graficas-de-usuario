package gui;

import model.Carrera;
import model.Universidad;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.*;

public class VentanaRegistrarEstudiante extends Ventana {
    private final Universidad universidad;
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JButton botonRegistrar, botonCancelar;
    private JTextField campoRut;
    private JTextField campoMatricula;
    private JComboBox listaCarreras;

    public VentanaRegistrarEstudiante(Universidad universidad) {
        super("Registro de Estudiante", 500, 520);
        this.universidad = universidad;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
        generarCampoNombre();
        generarCampoApellido();
        generarCampoRut();
        generarCampoMatricula();
        generarListaCarreras();
        generarBotonRegistrar();
        generarBotonCancelar();
    }

    private void generarEncabezado() {
        String textoCabecera = "Registro de Estudiante";
        super.generarJLabelEncabezado(textoCabecera, 190, 10, 200, 50);
    }

    private void generarListaCarreras() {
        super.generarJLabel("Carrera:", 20, 250, 100, 20);
        String[] arreglo = getArregloCarreras();
        this.listaCarreras = super.generarListaDesplegable(arreglo, 200, 250, 250, 20);
        this.add(this.listaCarreras);
    }

    private String[] getArregloCarreras() {
        return universidad.getCarreras().stream()
                .map(Carrera::getNombre)
                .toArray(String[]::new);
    }

    private void generarBotonRegistrar() {
        String textoBoton = "Registrar Estudiante";
        botonRegistrar = super.generarBoton(textoBoton, 75, 400, 180, 25);
        this.add(botonRegistrar);
        botonRegistrar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 180, 25);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private void generarCampoNombre() {
        String textoNombre = "Nombre:";
        super.generarJLabel(textoNombre, 20, 50, 150, 20);
        campoNombre = super.generarJTextField(200, 50, 250, 20);
        this.add(campoNombre);
    }

    private void generarCampoApellido() {
        String textoApellido = "Apellido:";
        super.generarJLabel(textoApellido, 20, 100, 150, 20);
        campoApellido = super.generarJTextField(200, 100, 250, 20);
        this.add(campoApellido);
    }

    private void generarCampoRut() {
        super.generarJLabel("Rut (Sin puntos ni guión):", 20, 150, 200, 20);
        this.campoRut = super.generarJTextField(200, 150, 250, 20);
        this.add(this.campoRut);
    }

    private void generarCampoMatricula() {
        super.generarJLabel("Matrícula:", 20, 200, 200, 20);
        campoMatricula = super.generarJTextField(200, 200, 250, 20);
        this.add(campoMatricula);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrar) {
            registrarEstudiante();
        }
        if (e.getSource() == botonCancelar) {
            new VentanaMenuBienvenida(universidad);
            this.dispose();
        }
    }

    private void registrarEstudiante() {
        try {
            universidad.agregarEstudiante(campoNombre.getText(),
                    campoApellido.getText(),
                    campoRut.getText(),
                    campoMatricula.getText(),
                    (String) listaCarreras.getSelectedItem());
        } catch (RuntimeException e) {
            showMessageDialog(this, e.getMessage(), "Mensaje de error", ERROR_MESSAGE);
            return;
        }
        showMessageDialog(this,
                "Estudiante registrado correctamente",
                "Mensaje de confirmación",
                INFORMATION_MESSAGE);
        new VentanaMenuBienvenida(universidad);
        this.dispose();
    }
}
