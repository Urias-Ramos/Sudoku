package com.sudoku.game.ui;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Resources;

/**
 * Esta clase representa un JFrame, aqui se agrega el canvas a la ventana y
 * se cargan los datos de la aplicación.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class MainFrame extends JFrame {
	private JPanel panelPrincipal;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase.
	 * Aqui se crea un JPanel donde se le agregara el canvas, posteriormente se agregara al
	 * JFrame y tambien se cargaran los datos de la apliacion.
	 * 
	 */
	public MainFrame() {
		panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		//se agrega el canvas al jpanel
		panelPrincipal.add(GameManifest.SCENARIO);
		
		this.add(panelPrincipal, BorderLayout.CENTER);
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				//Al ajustar el tamaño de la ventana, este metodo se ejecuta y se obtiene el ancho y alto modificado.
				GameManifest.ANCHO_PANTALLA_COMPLETA = getSize().width;
				GameManifest.ALTO_PANTALLA_COMPLETA = getSize().height;
			}
		});
		
		//se cargan los archivos de la aplicacion
		Resources.loadProfile();
		GameManifest.changeTheme();
		GameManifest.IDIOME.changeLanguage(GameManifest.PROFILE.getLanguageIndex());
	}
}