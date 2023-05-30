package com.sudoku.game.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.core.graphicsengine.Scenario.Scene;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;
import com.sudoku.game.util.Resources;

/**
 * Esta clase representa el menu creditos del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class CreditsMenu implements UpdateAndPainting {
	private int x, y;
	private Rectangle panelPrincipal;
	
	private BufferedImage iconBack;
	private Rectangle btnBack;
	
	private String value;
	private Font font;
	
	/**
	 * Constructor de la clase
	 * 
	 * Aqui se crean los objetos y se hacen los calculos iniciales de este menu.
	 */
	public CreditsMenu() {
		x = 0;
		y = 0;
		
		panelPrincipal = new Rectangle(x, y, 500, 300);
		
		iconBack = Resources.getImagen("icon/icon_atras.png");
		btnBack = new Rectangle(10, 10, 28, 28);
		
		font = new Font("Arial", Font.BOLD, 42);
	}
	
	/**
	 * Obtiene el cadena actual
	 * 
	 * @return la cadena actual
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Establece una nueva cadena.
	 * 
	 * @param value la nueva cadena a establecer
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Pinta el texto en pantalla.
	 * 
	 * @param g contexto grafico del motor grafico.
	 * @param value valor del texto a dibujar.
	 * @param margin margen que se le dara al texto sobre otros texto.
	 */
	private void paintLabel(Graphics g, String value, int margin) {
		setValue(value);
		x = (int) (panelPrincipal.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2));
		y = (int) (panelPrincipal.getCenterY() - (Painter.getAltoString(g, getValue()))) + margin;
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
	}
	
	/**
	 * Cambia el menu actual al menu inicio.
	 */
	public void back() {
		GameManifest.SCENARIO.setEscenarioActual(Scene.STARTMENU);
	}
	
	@Override
	public void upgrade() {
		x = (GameManifest.CENTRO_PANTALLA_COMPLETA.x - (panelPrincipal.width / 2));
		y = (GameManifest.CENTRO_PANTALLA_COMPLETA.y - (panelPrincipal.height / 2));
		
		panelPrincipal.x = x;
		panelPrincipal.y = y;
		
		if((GameManifest.RATON.getRectanguloPosicion().intersects(btnBack))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			back();
		}
	}

	@Override
	public void paint(Graphics g) {
		Painter.image(g, iconBack, btnBack);
		
		Painter.setFont(g, font);
		setValue(GameManifest.IDIOME.getProperty("1001"));
		x = (int) (panelPrincipal.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2));
		y = (int) (panelPrincipal.y);
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
		
		Painter.setFont(g, font.deriveFont(Font.BOLD, 14));
		paintLabel(g, GameManifest.IDIOME.getProperty("1002")+":", 0);
		paintLabel(g, "Urias Ramos", 20);
		paintLabel(g, GameManifest.IDIOME.getProperty("1003")+":", 80);
		paintLabel(g, GameManifest.VERSION, 100);
	}
}