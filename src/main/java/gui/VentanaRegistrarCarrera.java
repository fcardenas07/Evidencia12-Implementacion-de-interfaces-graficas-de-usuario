package gui;

import model.Universidad;

import javax.swing.*;
import javax.swing.text.InternationalFormatter;
import java.awt.event.ActionEvent;

import static java.lang.Integer.parseInt;

public class VentanaRegistrarCarrera extends Ventana {
    private final Universidad universidad;
    private JTextField campoCodigo;
    private JTextField campoNombre;
    private JTextField campoSemestres;
    private JButton botonRegistrar;
    private JButton botonCancelar;

    public VentanaRegistrarCarrera(Universidad universidad) {
        super("Registro de Carrera", 500, 520);
        this.universidad = universidad;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarEncabezado();
        generarBotonCancelar();
        generarBotonRegistrar();
        generarCampoNombre();
        generarCampoCodigo();
        generarCampoSemestres();
    }

    private void generarEncabezado() {
        String textoCabecera = "Registro de Carrera Universitaria";
        super.generarJLabelEncabezado(textoCabecera, 100, 10, 350, 50);
    }

    private void generarBotonRegistrar() {
        String textoBoton = "Registrar Carrera";
        botonRegistrar = super.generarBoton(textoBoton, 75, 400, 150, 20);
        this.add(botonRegistrar);
        botonRegistrar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 150, 20);
        this.add(botonCancelar);
        botonCancelar.addActionListener(this);
    }

    private void generarCampoNombre() {
        String textoNombre = "Nombre Carrera:";
        super.generarJLabel(textoNombre, 20, 50, 150, 20);
        campoNombre = super.generarJTextField(200, 50, 250, 20);
        this.add(campoNombre);
    }

    private void generarCampoCodigo() {
        String textoCodigo = "Código Carrera:";
        super.generarJLabel(textoCodigo, 20, 100, 150, 20);
        InternationalFormatter formato = super.generarFormato(1000, 9999);
        campoCodigo = super.generarJFormattedTextField(formato, 200, 100, 250, 20);
        this.add(campoCodigo);
    }

    private void generarCampoSemestres() {
        String textoSemestres = "Cantidad de Semestres:";
        super.generarJLabel(textoSemestres, 20, 150, 150, 20);
        InternationalFormatter formato = super.generarFormato(4, 20);
        campoSemestres = super.generarJFormattedTextField(formato, 200, 150, 250, 20);
        this.add(campoSemestres);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrar) {
            registrarCarrera();
        }
        if (e.getSource() == botonCancelar) {
            new VentanaMenuBienvenida(universidad);
            this.dispose();
        }
    }

    private void registrarCarrera() {
        try {
            universidad.agregarCarrera(
                    campoNombre.getText().toUpperCase(),
                    parseInt(campoCodigo.getText()),
                    parseInt(campoSemestres.getText()));
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "Carrera registrada correctamente");
        new VentanaMenuBienvenida(universidad);
        this.dispose();
    }
}
