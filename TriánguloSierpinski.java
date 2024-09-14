package Triangulo_Sierpinski;
//Se cambio la clase Applet por JFrame y JPanel
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TriánguloSierpinski extends JPanel {

    double xp1, yp1, xp2, yp2;
    double sin60 = Math.sin(Math.PI / 3);
    int nivel_de_recursividad = 6;

    // Se inicializan las variables pero ahora desde el constructor
    public TriánguloSierpinski() {
        xp1 = 300;
        yp1 = 300;
        xp2 = 10;
        yp2 = 300;
    }
       
    //Aqui ahora organizo el dibujo dentro de un JPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintRecursivo(g, nivel_de_recursividad, xp1, yp1, xp2, yp2);
    }

    private void paintRecursivo(Graphics g, int i, double xp12, 
        double yp12, double xp22, double yp22) {
        double dx = (xp22 - xp12) / 2;
        double dy = (yp22 - yp12) / 2;
        double xp32 = xp12 + dx - 2 * dy * sin60;
        double yp32 = yp12 + dy + 2 * dx * sin60;

        if (i <= 0) {
            // Se dibuja con drawline el triángulo desde el nivel abajo
            g.drawLine((int) xp12, (int) yp12, (int) xp22, (int) yp22);
            g.drawLine((int) xp22, (int) yp22, (int) xp32, (int) yp32);
            g.drawLine((int) xp32, (int) yp32, (int) xp12, (int) yp12);
        } else {
            // Calculos de puntos y se dibuja con recursividad
            double dx1 = (xp22 + xp12) / 2;
            double dy1 = (yp22 + yp12) / 2;
            paintRecursivo(g, i - 1, xp12, yp12, dx1, dy1);
            paintRecursivo(g, i - 1, dx1, dy1, xp22, yp22);
            paintRecursivo(g, i - 1, (xp12 + xp32) / 2, (yp12 + yp32) / 2,
                    (xp32 + xp22) / 2, (yp32 + yp22) / 2);
        }
    }
//Se abre el panel o ventana donde estara el triangulo y sus dimensiones
    public static void main(String[] args) {
        JFrame frame = new JFrame("Triángulo de Sierpinski");
        TriánguloSierpinski panel = new TriánguloSierpinski();
        frame.add(panel);
        frame.setSize(350, 350);
        frame.setVisible(true);
    }
}
