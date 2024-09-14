import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

class CopoNieve extends JPanel {

    // Coordenadas del triangulo basico
    double xp1 = 200, yp1 = 50;   
    double xp2 = 50, yp2 = 300;   
    double xp3 = 350, yp3 = 300;  
    double sin60 = Math.sin(Math.PI / 3);

    int nivel_recurs = 5;

    // Método para dibujar el copo de nieve
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Aqui se dibujan sus lados
        paintRecursivo(g, nivel_recurs, xp1, yp1, xp2, yp2); 
        paintRecursivo(g, nivel_recurs, xp2, yp2, xp3, yp3); 
        paintRecursivo(g, nivel_recurs, xp3, yp3, xp1, yp1); 
    }
 
    private void paintRecursivo(Graphics g, int i, double xp12, double yp12, double xp22, double yp22) {
        double dx = (xp22 - xp12) / 3.0;
        double dy = (yp22 - yp12) / 3.0;

        // Cálculamos un punto intermedio nuevo para dibujar otro triángulo
        double xx = xp12 + 1.5 * dx - dy * sin60;
        double yy = yp12 + 1.5 * dy + dx * sin60;

        if (i <= 0) {
            g.drawLine((int) xp12, (int) yp12, (int) xp22, (int) yp22);
        } else {
            // Recursivas para los nuevos triangulos
            paintRecursivo(g, i - 1, xp12, yp12, xp12 + dx, yp12 + dy);  
            paintRecursivo(g, i - 1, xp12 + dx, yp12 + dy, xx, yy);      
            paintRecursivo(g, i - 1, xx, yy, xp22 - dx, yp22 - dy);     
            paintRecursivo(g, i - 1, xp22 - dx, yp22 - dy, xp22, yp22);  
        }
    }

    // Método para crear un nuevo frame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Copo de Nieve de Koch__");
        CopoNieve panel = new CopoNieve();
        frame.setSize(400, 450);
        frame.add(panel); 
        frame.setVisible(true);
    }
}