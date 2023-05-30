package com.sudoku.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.core.graphicsengine.Scenario.Scene;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;

/**
 * Esta clase representa el menu de victoria/derrota.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Winner implements UpdateAndPainting {
	private int x, y;
	private Rectangle rectangle, rectangleContenedor;
	private Rectangle btnNewGame, btnMainMenu;
	
	private String value;
	
	private Font font;
	
	private boolean winner;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Aqui se crean e inicializan los objetos de la clase.
	 */
	public Winner() {
		x = 0;
		y = 0;
		
		rectangle = new Rectangle(0, 0, 600, 400);
		rectangleContenedor = new Rectangle(0, 0, 300, 400);
		
		btnNewGame = new Rectangle(0, 0, 128, 48);
		btnMainMenu = new Rectangle(0, 0, 128, 48);
		
		font = new Font("Arial", Font.BOLD, 42);
	}
	
	/**
	 * Devuelve el valor actual del atributo winner.
	 * 
	 * @return devuelve el valor actual del atributo winner.
	 */
	public boolean isWinner() {
		return winner;
	}
	
	/**
	 * Establece el nuevo valor para el atributo winner.
	 * 
	 * @param winner nuevo valor para el atributo winner.
	 */
	public void setWinner(boolean winner) {
		this.winner = winner;
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
	 * Pinta un texto en las coordenadas dadas.
	 * 
	 * @param g contexto grafico del motor grafico.
	 * @param value texto a dibujar.
	 * @param x coordenada x donde se dibujara el texto.
	 * @param y coordenada y donde se dibujara el texto.
	 */
	private void paintValue(Graphics g, String value, int x, int y) {
		Painter.setFont(g, font.deriveFont(Font.BOLD, 16));
		Painter.string(g, value, GameManifest.COLORCELLFOREGROUND, x, y);
	}
	
	/**
	 * Cambia el menu actual por el menu inicio.
	 */
	public void back() {
		GameManifest.SCENARIO.setEscenarioActual(Scene.STARTMENU);
	}
	
	@Override
	public void upgrade() {
		rectangle.width = GameManifest.ANCHO_PANTALLA_COMPLETA;
		rectangle.height = GameManifest.ALTO_PANTALLA_COMPLETA;
		
		x = 0;
		y = 0;
		
		rectangle.x = x;
		rectangle.y = y;
		
		rectangleContenedor.x = (int) rectangle.getCenterX() - (rectangleContenedor.width / 2);
		rectangleContenedor.y = (int) rectangle.getCenterY() - (rectangleContenedor.height / 2);
		
		btnNewGame.x = (int) rectangle.getCenterX() - (btnNewGame.width / 2);
		btnNewGame.y = rectangleContenedor.y + rectangleContenedor.height - (btnNewGame.height * 2) - 20;
		
		btnMainMenu.x = (int) rectangle.getCenterX() - (btnMainMenu.width / 2);
		btnMainMenu.y = btnNewGame.y + (btnMainMenu.height + 10);
		
		if((GameManifest.RATON.getRectanguloPosicion().intersects(btnMainMenu))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			back();
		}
		else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnNewGame))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			GameManifest.SCENARIO.setLastScene(Scene.WINNERMENU);
			GameManifest.SCENARIO.setEscenarioActual(Scene.DIFFICULTYMENU);
		}
	}

	@Override
	public void paint(Graphics g) {
		Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORCELLBACKGROUND);
		
		if(isWinner()) {
			setValue(GameManifest.IDIOME.getProperty("6001"));
		}
		else {
			setValue(GameManifest.IDIOME.getProperty("6002"));
		}
		
		Painter.setFont(g, font);
		x = (int) rectangle.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
		y = (int) rectangleContenedor.y + (Painter.getAltoString(g, getValue() + 10));
		
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
		
		x = rectangleContenedor.x;
		y += Painter.getAltoString(g, getValue()) + 10;
		
		switch(GameManifest.SCENARIO.getGameScenario().getBoard().getDifficulty().getDificultyMode()) {
		case EASY:
			setValue(GameManifest.IDIOME.getProperty("3002"));
			break;
		case MEDIUM:
			setValue(GameManifest.IDIOME.getProperty("3003"));
			break;
		case HARD:
			setValue(GameManifest.IDIOME.getProperty("3004"));
			break;
		}
		
		paintValue(g, GameManifest.IDIOME.getProperty("6003"), x, y);
		Painter.bordeRectangulo(g, x, y + 10, 300, 1, GameManifest.COLORCELLFOREGROUND);
		paintValue(g, getValue(), (x + 300) - Painter.getAnchoString(g, getValue()), y);
		
		y += Painter.getAltoString(g, getValue()) + 10;
		setValue(""+GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().getValue());
		Painter.string(g, GameManifest.IDIOME.getProperty("6004"), GameManifest.COLORCELLFOREGROUND, x, y);
		paintValue(g, getValue(), (x + 300) - Painter.getAnchoString(g, getValue()), y);
		
		y += Painter.getAltoString(g, getValue()) + 40;
		setValue(""+GameManifest.SCOREACTUAL);
		paintValue(g, GameManifest.IDIOME.getProperty("6005"), x, y);
		Painter.bordeRectangulo(g, x, y + 10, 300, 1, GameManifest.COLORCELLFOREGROUND);
		paintValue(g, getValue(), (x + 300) - Painter.getAnchoString(g, getValue()), y);
		
		y += Painter.getAltoString(g, getValue()) + 10;
		setValue(""+GameManifest.ERRORCOUNT);
		paintValue(g, GameManifest.IDIOME.getProperty("6006"), x, y);
		Painter.bordeRectangulo(g, x, y + 10, 300, 1, GameManifest.COLORCELLFOREGROUND);
		paintValue(g, getValue(), (x + 300) - Painter.getAnchoString(g, getValue()), y);
		
		GameManifest.SCORE = (GameManifest.SCOREACTUAL - GameManifest.ERRORCOUNT);
		if(GameManifest.SCORE < 0) {
			GameManifest.SCORE = 0;
		}
		
		y += Painter.getAltoString(g, getValue()) + 10;
		setValue(""+GameManifest.SCORE);
		Painter.string(g, GameManifest.IDIOME.getProperty("6007"), GameManifest.COLORCELLFOREGROUND, x, y);
		paintValue(g, getValue(), (x + 300) - Painter.getAnchoString(g, getValue()), y);
		
		setValue(GameManifest.IDIOME.getProperty("5005"));
		Painter.rellenoRectangulo(g, btnNewGame, GameManifest.COLORCELESTE);
		x = (int) btnNewGame.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
		y = (int) btnNewGame.getCenterY() + (Painter.getAltoString(g, getValue()) / 4);
		Painter.string(g, getValue(), Color.WHITE, x, y);
		
		setValue(GameManifest.IDIOME.getProperty("5004"));
		Painter.rellenoRectangulo(g, btnMainMenu, GameManifest.COLORCELESTE);
		x = (int) btnMainMenu.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
		y = (int) btnMainMenu.getCenterY() + (Painter.getAltoString(g, getValue()) / 4);
		Painter.string(g, getValue(), Color.WHITE, x, y);
	}
}