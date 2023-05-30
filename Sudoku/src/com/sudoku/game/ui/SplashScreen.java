package com.sudoku.game.ui;

import java.awt.Font;
import java.awt.Graphics;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.core.graphicsengine.Scenario.Scene;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;

/**
 * Esta clase representa el SplashScreen del juego.
 * 
 * @author Urias Ramos
 * @since 1.0
 * @since 2023-03-19
 *
 */
public class SplashScreen implements UpdateAndPainting {
	private String cadena;
	private int x, y;
	
	private Font font;
	
	private long tiempo;
	
	/**
	 * Constructor de la clase
	 * 
	 * Aqui se crean los objetos y se hacen los calculos iniciales de este menu.
	 */
	public SplashScreen() {
		x = 0;
		y = 0;
		
		font = new Font("Century Gothic", Font.PLAIN, 42);
		tiempo = - 1;
	}

	@Override
	public void upgrade() {
		if(tiempo == -1) {
			tiempo = System.nanoTime();
		}
		
		if(GameManifest.isTiempoCumplidoMilisegundos(tiempo, 1500)) {
			GameManifest.SCENARIO.setEscenarioActual(Scene.STARTMENU);
		}
	}

	@Override
	public void paint(Graphics g) {
		Painter.setFont(g, font);
		
		cadena = "WITHOUT STUDIOS";
		x = GameManifest.CENTRO_PANTALLA_COMPLETA.x - (Painter.getAnchoString(g, cadena) / 2);
		y = GameManifest.CENTRO_PANTALLA_COMPLETA.y - (Painter.getAltoString(g, cadena) / 4);
		
		Painter.string(g, cadena, GameManifest.COLORCELLFOREGROUND, x, y);
	}
}