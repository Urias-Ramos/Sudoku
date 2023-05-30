package com.sudoku.game.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Esta clase contiene metodos estaticos que permite una mayor flexibilidad
 * a la hora de pintar objetos como texto, imagen, Rectangle, etc.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Painter {
	
	public static int getAnchoString(Graphics g, String texto) {
		return g.getFontMetrics().stringWidth(texto);
	}
	
	public static int getAltoString(Graphics g, String texto) {
		return (int) g.getFontMetrics().getLineMetrics(texto, g).getHeight();
	}
	
	public static void bordeRectangulo(Graphics g, int x, int y, int ancho, int alto, Color color) {
		g.setColor(color);
		g.drawRect(x, y, ancho, alto);
	}
	
	public static void bordeRectangulo(Graphics g, int x, int y, int ancho, int alto) {
		g.drawRect(x, y, ancho, alto);
	}
	
	public static void bordeRectangulo(Graphics g, Rectangle rectangle, Color color) {
		g.setColor(color);
		g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}
	
	public static void bordeRoundRect(Graphics g, Rectangle rectangle, int arcWidth, int arcHeight, Color color) {
		g.setColor(color);
		g.drawRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, arcWidth, arcHeight);
	}
	
	public static void rellenoRectangulo(Graphics g, int x, int y, int ancho, int alto, Color color) {
		g.setColor(color);
		g.fillRect(x, y, ancho, alto);
	}
	
	public static void rellenoRectangulo(Graphics g, Rectangle rectangle, Color color) {
		g.setColor(color);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}
	
	public static void rellenoRoundRect(Graphics g, Rectangle rectangle, int arcWidth, int arcHeight, Color color) {
		g.setColor(color);
		g.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, arcWidth, arcHeight);
	}
	
	public static void string(Graphics g, String cadena, Color color, int x, int y) {
		g.setColor(color);
		g.drawString(cadena, x, y);
	}
	
	public static void setFont(Graphics g, Font font) {
		g.setFont(font);
	}
	
	public static void image(Graphics g, BufferedImage image, int x, int y,  int ancho, int alto) {
		g.drawImage(image, x, y, ancho, alto, null);
	}
	
	public static void image(Graphics g, BufferedImage image, Rectangle rectangle) {
		g.drawImage(image, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
	}
}