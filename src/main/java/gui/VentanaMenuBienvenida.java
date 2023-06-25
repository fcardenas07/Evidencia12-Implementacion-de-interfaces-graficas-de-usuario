package gui;

import model.Universidad;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaMenuBienvenida extends Ventana {
    private final Universidad universidad;
    private JButton botonRegistrarCarrera;
    private JButton botonSalida;
    private JButton botonRegistrarEstudiante;
    private JButton botonBuscarEstudiante;

    public VentanaMenuBienvenida(Universidad universidad) {
        super("Menu Intranet", 500, 520);
        this.universidad = universidad;
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonRegistrarCarrera();
        generarBotonRegistrarEstudiante();
        generarBotonBuscarEstudiante();
        generarBotonSalir();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Intranet 2.0";
        super.generarJLabelEncabezado(textoBienvenida, 20, 30, 500, 30);
    }

    private void generarBotonRegistrarCarrera() {
        String textoBoton = "Registrar Carrera";
        botonRegistrarCarrera = super.generarBoton(textoBoton, 175, 100, 200, 40);
        this.add(botonRegistrarCarrera);
        botonRegistrarCarrera.addActionListener(this);
    }

    private void generarBotonSalir() {
        String textoBoton = "Salir";
        botonSalida = super.generarBoton(textoBoton, 175, 420, 200, 40);
        this.add(botonSalida);
        botonSalida.addActionListener(this);
    }

    private void generarBotonRegistrarEstudiante() {
        String textoBoton = "Registrar Estudiante";
        botonRegistrarEstudiante = super.generarBoton(textoBoton, 175, 180, 200, 40);
        this.add(botonRegistrarEstudiante);
        botonRegistrarEstudiante.addActionListener(this);
    }

    private void generarBotonBuscarEstudiante() {
        String textoBoton = "Buscar Estudiante";
        botonBuscarEstudiante = super.generarBoton(textoBoton, 175, 260, 200, 40);
        this.add(botonBuscarEstudiante);
        botonBuscarEstudiante.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonRegistrarCarrera) {
            new VentanaRegistrarCarrera(universidad);
            this.dispose();
        }
        if (event.getSource() == botonRegistrarEstudiante) {
            new VentanaRegistrarEstudiante(universidad);
            this.dispose();
        }
        if (event.getSource() == botonBuscarEstudiante) {
            new VentanaBusquedaEstudiante(universidad);
            this.dispose();
        }
        if (event.getSource() == botonSalida) {
            this.dispose();
            System.exit(0);
        }
    }
}
