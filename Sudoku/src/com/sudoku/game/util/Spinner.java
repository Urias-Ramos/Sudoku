package com.sudoku.game.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;

/**
 * Esta clase representa un componente JSpinner.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Spinner implements UpdateAndPainting {
	private int x, y;
	private Rectangle screen;
	private Rectangle btnIncrease;
	private Rectangle btnDecrease;
	
	private BufferedImage iconIncrease, iconDecrease;
	
	private int min, max, value;
	
	/**
	 * Contructor de la clase.
	 * 
	 * Crea una instancia con los valores establecidos.
	 * 
	 * @param min rango minimo hasta donde podra disminuir su valor.
	 * @param max rango maximo hasta donde podra aumentar su valor.
	 * @param value valor por defecto.
	 * @param DimensionScreen dimension que tendra la pantalla.
	 * @param DimensionButton dimension que tendran los botones de aumentar y disminuir.
	 */
	public Spinner(int min, int max, int value, Dimension DimensionScreen,  Dimension DimensionButton) {
		setMin(min);
		setMax(max);
		setValue(value);
		
		x = 0;
		y = 0;
		
		screen = new Rectangle(x, y, DimensionScreen.width, DimensionScreen.height);
		btnIncrease = new Rectangle(x, y, DimensionButton.width, DimensionButton.height);
		btnDecrease = new Rectangle(x, y, DimensionButton.width, DimensionButton.height);
		
		iconIncrease = Resources.getImagen("icon/icon_increase.png");
		iconDecrease = Resources.getImagen("icon/icon_decrease.png");
	}
	
	/**
	 * Devuelve el rango minimo del Spinner.
	 * 
	 * @return valor actual del atributo min.
	 */
	public int getMin() {
		return min;
	}
	
	/**
	 * Establece el valor del rango minimo.
	 * 
	 * @param min nuevo valor del rango minimo.
	 */
	public void setMin(int min) {
		this.min = min;
	}
	
	/**
	 * Devuelve el rango maximo del Spinner.
	 * 
	 * @return valor actual del atributo max.
	 */
	public int getMax() {
		return max;
	}
	
	/**
	 * Establece el valor del rango maximo.
	 * 
	 * @param max nuevo valor del rango maximo.
	 */
	public void setMax(int max) {
		this.max = max;
	}
	
	/**
	 * Devuelve el valor del atributo value.
	 * 
	 * @return valor actual del atributo value.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Establece un nuevo valor para el atributo value.
	 * 
	 * @param value nuevo valor para el atributo value.
	 */
	public void setValue(int value) {
		this.value = value;
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
		screen.x = x;
		screen.y = y;
		
		btnIncrease.x = (screen.x + screen.width);
		btnIncrease.y = (screen.y);
		
		btnDecrease.x = (screen.x + screen.width);
		btnDecrease.y = (screen.y + btnDecrease.width);
		
		setValue(GameManifest.PROFILE.getErrorLimit());
		
		if(GameManifest.RATON.getRectanguloPosicion().intersects(btnIncrease)&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			
			if((getValue() + 1) <= max) {
				setValue(getValue() + 1);
			}
		}
		else if(GameManifest.RATON.getRectanguloPosicion().intersects(btnDecrease)&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			
			if((getValue() - 1) >= min) {
				setValue(getValue() - 1);
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		Painter.rellenoRectangulo(g, screen, GameManifest.COLORCELLBACKGROUND);
		Painter.bordeRectangulo(g, screen, GameManifest.COLORLINE);
		
		Painter.bordeRectangulo(g, btnIncrease, GameManifest.COLORLINE);
		Painter.bordeRectangulo(g, btnDecrease, GameManifest.COLORLINE);
		
		x = (int) (btnIncrease.getCenterX() - (btnIncrease.width / 4));
		y = (int) (btnIncrease.getCenterY() - (btnIncrease.height / 4));
		Painter.image(g, iconIncrease, x, y, btnIncrease.width / 2, btnIncrease.height / 2);
		
		x = (int) (btnDecrease.getCenterX() - (btnDecrease.width / 4));
		y = (int) (btnDecrease.getCenterY() - (btnDecrease.height / 4));
		Painter.image(g, iconDecrease, x, y, btnDecrease.width / 2, btnDecrease.height / 2);
		
		x = (int) (screen.getCenterX() - (Painter.getAnchoString(g, ""+getValue()) / 2));
		y = (int) (screen.getCenterY() + (Painter.getAltoString(g, ""+getValue()) / 4));
		Painter.string(g, ""+getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
	}
}