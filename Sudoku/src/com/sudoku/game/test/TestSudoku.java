package com.sudoku.game.test;

import java.awt.Toolkit;
import javax.swing.JFrame;

import com.sudoku.game.ui.MainFrame;
import com.sudoku.game.util.GameManifest;

/**
 * Clase principal que inicia la ejecucion del programa.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class TestSudoku {
	
	public static void main(String[] args) {
		JFrame ventana = new MainFrame();
		ventana.setIconImage(Toolkit.getDefaultToolkit().getImage(ventana.getClass().getResource("/icon/icon_app.png")));
		ventana.setTitle("Sudoku");
		ventana.setUndecorated(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(950, 650);
		ventana.setLocationRelativeTo(null);
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventana.setVisible(true);
		
		//cuando se crea la ventana y esta visible, se inicia el hilo del motor grafico.
		GameManifest.ENGINE.startGame();
	}
}