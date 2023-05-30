package com.sudoku.game.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;

/**
 * Esta clase representa un Boton personalizado.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class ButtonSudoku implements UpdateAndPainting {
	private Rectangle rectangle;
	private int x, y;
	
	private int dimension;
	private String value;
	private boolean selected;
	
	private Font font;
	
	private int sizeImage;
	private BufferedImage imagen, mask;
	
	/**
	 * Constructor de la clase
	 * Aqui se crean los objetos e inicializan.
	 * 
	 * @param dimension tamaño fijo que tendra el boton.
	 * @param value texto que tendra el boton.
	 * @param imagen icono que tendra el boton.
	 */
	public ButtonSudoku(int dimension, String value, BufferedImage imagen) {
		this.dimension = dimension;
		this.value = value;
		this.imagen = imagen;
		setSelected(false);
		mask = null;
		
		rectangle = new Rectangle(0, 0, dimension, dimension);
		
		sizeImage = 28;
		
		font = new Font("Arial", Font.BOLD, 12);
	}
	
	/**
	 * Devuelve el atributo rectangle.
	 * 
	 * @return valor actual del objeto.
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	/**
	 * Devuelve el valor del atributo dimension.
	 * 
	 * @return la dimension del boton.
	 */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * Establece un nuevo valor para el atributo dimension.
	 * 
	 * @param dimension el nuevo valor para el atributo dimension.
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	/**
	 * Devuelve el valor actual del atributo sizeImage.
	 * 
	 * @return valor actual de sizeImage.
	 */
	public int getSizeImage() {
		return sizeImage;
	}
	
	/**
	 * Establece un nuevo valor para tañamo de la imagen.
	 * 
	 * @param sizeImage nuevo valor para el atributo sizeImage.
	 */
	public void setSizeImage(int sizeImage) {
		this.sizeImage = sizeImage;
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
	 * Este metodo devuelve true o false si esta seleccionado algun control.
	 * 
	 * @return devuelve booleano
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Actualiza solo los objetos del menu de ajustes generales.
	 * 
	 * @param auxY margen o espacio que hay entre los objetos.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Devuelve la imagen que representa el icono del boton.
	 * 
	 * @return el valor actual del atributo imagen.
	 */
	public BufferedImage getImagen() {
		return imagen;
	}
	
	/**
	 * Establece un nuevo valor para el atributo imagen.
	 * 
	 * @param imagen nuevo valor para el atributo imagen.
	 */
	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * Devuelve el valor actual del atributo mask.
	 * 
	 * @return valor actual del atributo mask.
	 */
	public BufferedImage getMask() {
		return mask;
	}
	
	/**
	 * Establece un nuevo valor para el atributo mask.
	 * 
	 * @param mask nuevo valor para el atributo mask.
	 */
	public void setMask(BufferedImage mask) {
		this.mask = mask;
	}
	
	/**
	 * Establece las coordenadas donde se pintaran los objetos.
	 * 
	 * @param x el nuevo valor de la coordenada x.
	 * @param y el nuevo valor de la coordenada y.
	 */
	public void setCoordenadas(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void upgrade() {
		rectangle.x = x;
		rectangle.y = y;
		
		rectangle.width = dimension;
		rectangle.width = dimension;
	}

	@Override
	public void paint(Graphics g) {
		if(mask == null) {
			if(isSelected()) {
				Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORNUMBERSELECTED);
			}
			else {
				Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORREGIONBACKGROUND);
			}
			Painter.bordeRectangulo(g, rectangle, GameManifest.COLORLINE);
		}
		else {
			Painter.image(g, mask, rectangle);
		}
		
		Painter.setFont(g, font);
		
		x = (int) rectangle.getCenterX() - (Painter.getAnchoString(g, value) / 2);
		y = (int) rectangle.getCenterY() + (Painter.getAltoString(g, value) / 2);
		
		Painter.image(g, imagen, (int) (rectangle.getCenterX() - (sizeImage / 2)), rectangle.y + 10, sizeImage, sizeImage);
		
		y = (rectangle.y + 10 + sizeImage + Painter.getAltoString(g, value));
		
		Painter.string(g, value, Color.WHITE, x, y);
	}
}