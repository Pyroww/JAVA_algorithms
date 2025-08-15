package br.com.comercio;

import br.com.comercio.gui.MainFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Garante que a interface gráfica seja criada e atualizada na thread correta (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}