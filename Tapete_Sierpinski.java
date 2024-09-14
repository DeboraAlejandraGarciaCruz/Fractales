package Tapete_Sierpinski;
//Se cambia la clase Applet por JFrame y JPanel
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tapete_Sierpinski extends JPanel {

    private int nivel_recurs = 5;

    public Tapete_Sierpinski() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = Math.min(getWidth(), getHeight());
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;
        paintRecursivo(g, x, y, size, nivel_recurs);
    }

    // Aqui tenemos el metodo recursivo para dibujar el tapete
    private void paintRecursivo(Graphics g, int x, int y, int size, int nivel) {
        if (nivel == 0) {
            g.fillRect(x, y, size, size);
        } else {
            // Para los subcuadrados los divide en 3 
            int newSize = size / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                       //Para dejar en blanco el cuadrado del centrito
                        g.setColor(Color.WHITE);
                        g.fillRect(x + newSize, y + newSize, newSize, newSize);
                        g.setColor(Color.BLACK);
                    } else {
                        // dibuja los 8 subcuadrados
                        paintRecursivo(g, x + i * newSize, y + j * newSize, newSize, nivel - 1);
                    }
                }
            }
        }
    }
//Se crea la ventana y sus dimensiones donde estara el tapete
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tapete de Sierpinski");
        Tapete_Sierpinski panel = new Tapete_Sierpinski();
        frame.add(panel);
        frame.setSize(650, 650);
        frame.setVisible(true);
    }
}