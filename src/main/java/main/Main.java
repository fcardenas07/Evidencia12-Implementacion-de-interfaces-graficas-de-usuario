package main;

import gui.VentanaMenuBienvenida;
import model.Universidad;

public class Main {
    public static void main(String[] args) {
        Universidad universidad = new Universidad("src/main/resources/carreras.txt", "src/main/resources/estudiantes.txt");
        new VentanaMenuBienvenida(universidad);
    }
}
