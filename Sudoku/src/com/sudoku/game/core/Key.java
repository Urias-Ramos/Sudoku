package com.sudoku.game.core;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.sudoku.game.core.ControlPanel.TYPEKEYCONTROL;
import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;

/**
 * Esta clase representa una tecla numerica del tablero de sudoku.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Key implements UpdateAndPainting {
	private String value;
	private int count;
	
	private boolean selected;
	
	private int x, y;
	private Rectangle rectangle;
	private Rectangle auxrectangle;
	
	private Font font, fontCount;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Crea e inicializa los atributos con los parametros solicitados.
	 * 
	 * @param value texto que tendra la tecla.
	 * @param dimension dimension que tendra la tecla.
	 */
	public Key(String value, int dimension) {
		setValue(value);
		setCount(9);
		setSelected(false);
		
		x = 0;
		y = 0;
		
		rectangle = new Rectangle(x, y, dimension * 2, dimension);
		auxrectangle = new Rectangle(x, y, dimension * 2, dimension);
		
		font = new Font("Arial", Font.PLAIN, 18);
		fontCount = new Font("Arial", Font.BOLD, 12);
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
	 * @param value nuevo valor de la tecla
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Devuelve la cantidad que falta por usar dicha tecla.
	 * 
	 * @return total de usos que le queda a la tecla.
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Establece el total de usos que le falta a la tecla.
	 * 
	 * @param count nuevo valor para el uso de la tecla.
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Incrementa el uso de la tecla.
	 */
	public void incrementCounter() {
		setCount((getCount() + 1));
	}
	
	/**
	 * Decrementa el uso de la tecla.
	 */
	public void decrementCounter() {
		setCount((getCount() - 1));
	}
	
	/**
	 * Devuelve un valor booleano si la tecla esta o no seleccionada.
	 * 
	 * @return esta seleccionado la tecla.
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Establece con un nuevo valor el atributo selected.
	 * 
	 * @param selected nuevo valor.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Devuelve la dimension de la tecla.
	 * 
	 * @return dimension de la tecla.
	 */
	public int getDimension() {
		return rectangle.width;
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
	
	/**
	 * Verifica si la tecla fue pulsada
	 */
	private void upgradeKey() {
		if((GameManifest.RATON.getRectanguloPosicion().intersects(rectangle))||(GameManifest.RATON.getRectanguloPosicion().intersects(auxrectangle))) {
			if(GameManifest.RATON.isClicIzquierdo()) {
				GameManifest.RATON.setClicIzquierdo(false);
				GameManifest.NUMEROSELECT = getValue();
				setSelected(true);
				GameManifest.SCENARIO.getGameScenario().getControlPanel().setTypeKeyControl(TYPEKEYCONTROL.NUMERIC);
			}
		}
	}
	
	@Override
	public void upgrade() {
		rectangle.x = x;
		rectangle.y = y;
		
		auxrectangle.x = x;
		auxrectangle.y = y + rectangle.height;
		
		if(!GameManifest.PAUSED) {
			upgradeKey();
		}
	}

	@Override
	public void paint(Graphics g) {
		Painter.setFont(g, font);
		
		if(isSelected()) {
			Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORNUMBERSELECTED);
		}
		else {
			Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORREGIONBACKGROUND);
		}
		
		x = (int) rectangle.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
		y = (int) rectangle.getCenterY() + (Painter.getAltoString(g, getValue()) / 4);
		
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
		
		x = (int) auxrectangle.getCenterX() - (Painter.getAnchoString(g, ""+getCount()) / 2);
		y = (int) auxrectangle.getCenterY() + (Painter.getAltoString(g, ""+getCount()) / 4);
		
		Painter.setFont(g, fontCount);
		Painter.string(g, ""+getCount(), GameManifest.COLORCELLFOREGROUND, x, y);
		
		Painter.bordeRectangulo(g, rectangle.x, rectangle.y, rectangle.width, rectangle.height * 2, GameManifest.COLORLINE);
	}
}