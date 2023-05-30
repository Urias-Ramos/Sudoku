package com.sudoku.game.core.generator;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;

/**
 * Esta clase representa una celda de Notas del sudoku.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 203-03-19
 *
 */
public class Notes implements UpdateAndPainting {
	private String value;
	
	private int dimension;
	
	private int x, y;
	private Rectangle rectangle;
	
	private Font font;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Aqui se crean e inicializan los objetos de la clase con los parametros enviados.
	 * 
	 * @param value valor que tendra esta celda de nota.
	 * @param dimension dimension fija que tendra la nota.
	 */
	public Notes(String value, int dimension) {
		setValue(value);
		
		setDimension(dimension);
		
		x = 0;
		y = 0;
		
		rectangle = new Rectangle(0, 0, getDimension(), getDimension());
		
		font = new Font("Arial", Font.BOLD, 12);
	}
	
	/**
	 * Devuelve el valor de la tecla.
	 * 
	 * @return valor de la tecla.
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Establece el valor que tendra la tecla.
	 * 
	 * @param value nuevo valor de la tecla.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Devuelve la dimension actual de la nota.
	 * 
	 * @return la demension actual.
	 */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * Establece una nueva dimension para la celda de nota.
	 * 
	 * @param dimension nuevo valor para el atributo.
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	/**
	 * Establece las coordenadas donde se pintaran los objetos.
	 * 
	 * @param x el nuevo valor de la coordenada x.
	 * @param y el nuevo valor de la coordenada y.
	 */
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void upgrade() {
		rectangle.x = x;
		rectangle.y = y;
	}

	@Override
	public void paint(Graphics g) {
		Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORCELLBACKGROUND);
		Painter.bordeRectangulo(g, rectangle, GameManifest.COLORLINE);
		
		Painter.setFont(g, font);
		
		x = (int) (rectangle.getCenterX() - (Painter.getAnchoString(g, value) / 2));
		y = (int) (rectangle.getCenterY() + (Painter.getAltoString(g, value) / 2));
		
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
	}
}