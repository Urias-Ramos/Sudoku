package com.sudoku.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.core.graphicsengine.Scenario.Scene;
import com.sudoku.game.util.ButtonSudoku;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;
import com.sudoku.game.util.Resources;

/**
 * Esta clase representa el menu de pausa del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class PauseMenu implements UpdateAndPainting {
	private int x, y;
	private int margen;
	
	private Rectangle panelFrame, shadow;
	
	private BufferedImage iconPlay, iconRestart, iconExit;
	private BufferedImage iconButton, iconButtonLarge;
	
	private Rectangle panelOption;
	private ButtonSudoku[] btnOption;
	
	private String value;
	
	private Rectangle btnNewGame;
	private Font font;
	
	private int shadowOffset;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Aqui se crea e inicializa los objetos de la clase.
	 */
	public PauseMenu() {
		x = 0;
		y = 0;
		
		margen = 10;
		
		iconPlay = Resources.getImagen("icon/icon_continue.png");
		iconRestart = Resources.getImagen("icon/icon_restart.png");
		iconExit = Resources.getImagen("icon/icon_exit.png");
		
		iconButton = Resources.getImagen("icon/icon_button.png");
		iconButtonLarge = Resources.getImagen("icon/icon_button_large.png");
		
		panelFrame = new Rectangle(x, y, 350, 450);
		
		panelOption = new Rectangle(0, 0, 236, 154);
		btnOption = new ButtonSudoku[3];
		for(int i=0; i<btnOption.length; i++) {
			btnOption[i] = new ButtonSudoku(72, "Continuar", iconPlay);
			btnOption[i].setSizeImage(28);
			btnOption[i].setMask(iconButton);
		}
		btnOption[0].setImagen(iconRestart);
		btnOption[2].setImagen(iconExit);
		
		btnNewGame = new Rectangle(0, 0, 236, 72);
		
		font = new Font("Arial", Font.BOLD, 14);
	}
	
	/**
	 * Obtiene el valor actual del atributo value.
	 * 
	 * @return el valor actual
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Establece un nuevo valor para el atributo value.
	 * 
	 * @param value el nuevo valor para el atributo value.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Pinta los botones
	 * 
	 * @param g contexto grafico del motor grafico.
	 */
	private void paintButton(Graphics g) {		
		btnOption[0].paint(g);
		btnOption[1].paint(g);
		btnOption[2].paint(g);
		
		Painter.setFont(g, font.deriveFont(Font.BOLD, 22));
		setValue(GameManifest.IDIOME.getProperty("5005"));
		x = (int) btnNewGame.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
		y = (int) btnNewGame.getCenterY() + (Painter.getAltoString(g, getValue()) / 2);
		
		Painter.image(g, iconButtonLarge, btnNewGame);
		Painter.string(g, getValue(), Color.WHITE, x, y);
	}

	@Override
	public void upgrade() {
		if((GameManifest.RATON.getRectanguloPosicion().intersects(btnOption[0].getRectangle()))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			GameManifest.PAUSED = false;
			GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().stop();
			GameManifest.SCENARIO.getGameScenario().getBoard().restartSudoku();
		}
		else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnOption[1].getRectangle()))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			GameManifest.PAUSED = false;
			GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().resume();
		}
		else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnOption[2].getRectangle()))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().stop();
			GameManifest.SCENARIO.setEscenarioActual(Scene.STARTMENU);
		}
		else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnNewGame))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			GameManifest.PAUSED = false;
			GameManifest.SCENARIO.setLastScene(Scene.PLAYING);
			GameManifest.SCENARIO.setEscenarioActual(Scene.DIFFICULTYMENU);
		}
		
		panelFrame.x = (GameManifest.CENTRO_PANTALLA_COMPLETA.x) - (panelFrame.width / 2);
		panelFrame.y = (GameManifest.CENTRO_PANTALLA_COMPLETA.y - 50) - (panelFrame.height / 2);
		
		panelOption.x = (GameManifest.CENTRO_PANTALLA_COMPLETA.x) - (panelOption.width / 2);
		panelOption.y = (GameManifest.CENTRO_PANTALLA_COMPLETA.y) - (panelOption.height / 2);
		
		btnOption[0].setValue(GameManifest.IDIOME.getProperty("5002"));
		btnOption[1].setValue(GameManifest.IDIOME.getProperty("5003"));
		btnOption[2].setValue(GameManifest.IDIOME.getProperty("5004"));
		
		x = panelOption.x;
		y = panelOption.y;
		
		btnOption[0].setCoordenadas(x, y);
		
		x = panelOption.x + (margen + btnOption[1].getDimension());
		y = panelOption.y;
		
		btnOption[1].setCoordenadas(x, y);
		
		x = panelOption.x + ((margen + btnOption[1].getDimension()) * 2);
		y = panelOption.y;
		
		btnOption[2].setCoordenadas(x, y);
		
		btnNewGame.x = panelOption.x;
		btnNewGame.y = (panelOption.y + (panelOption.height)) - btnNewGame.height;
		
		btnOption[0].upgrade();
		btnOption[1].upgrade();
		btnOption[2].upgrade();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(panelFrame);
		
		shadowOffset = 10;
		shadow = new Rectangle(panelFrame.x + shadowOffset, panelFrame.y + shadowOffset, panelFrame.width, panelFrame.height);
		
		g2d.setColor(GameManifest.COLORLINE);
		g2d.fill(shadow);
		
		Painter.rellenoRectangulo(g, panelFrame, GameManifest.COLORCELLBACKGROUND);
		Painter.bordeRectangulo(g, panelFrame, GameManifest.COLORLINE);
		
		Painter.setFont(g, font.deriveFont(Font.BOLD, 72));
		
		setValue(GameManifest.IDIOME.getProperty("5001"));
		
		x = (int) panelOption.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
		y = panelOption.y - (Painter.getAltoString(g, getValue()) - (margen * 3));
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
		
		Painter.setFont(g, font);
		paintButton(g);
		g2d.dispose();
	}
}