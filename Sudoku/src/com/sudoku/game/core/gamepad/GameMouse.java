package com.sudoku.game.core.gamepad;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.util.GameManifest;

/**
 * Esta clase es la encargada de controlar los eventos del mouse en el juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class GameMouse extends MouseAdapter implements UpdateAndPainting {
	private Point posicion;
	private boolean clicIzquierdo, clicDerecho;
	private Rectangle rectangle;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Inicializa el mouse con los valores definidos.
	 */
	public GameMouse() {
		clicIzquierdo = false;
		clicDerecho = false;
		
		posicion = new Point();
		rectangle = new Rectangle(0, 0, 1, 1);
	}
	
	/**
	 * Se obtiene la ubicacion del mouse y se actualiza la hitbox de este para poder utilizarlo en el juego.
	 */
	private void actualizarPosicionMouse() {
		Point posicionInicial = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(posicionInicial, GameManifest.SCENARIO);
		posicion.setLocation(posicionInicial.getX(), posicionInicial.getY());
		
		rectangle.x = posicion.x;
		rectangle.y = posicion.y;
	}
	
	/**
	 * Devuelve la posicion en el que se encuentra el mouse.
	 * 
	 * @return posicion en el que se encuentra el mouse.
	 */
	public Point getPosicion() {
		return posicion;
	}
	
	/**
	 * Establece la posicion del mouse.
	 * 
	 * @param posicion nuevo valor donde esta el mouse.
	 */
	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}
	
	/**
	 * Devuelve true o false si se presiono el clic izquierdo del mouse.
	 * 
	 * @return true si presiono el clic izquierdo, de lo contrario false.
	 */
	public boolean isClicIzquierdo() {
		return clicIzquierdo;
	}
	
	/**
	 * Establece un nuevo estado para el clic izquierdo-
	 * 
	 * @param clicIzquierdo nuevo valor del atributo.
	 */
	public void setClicIzquierdo(boolean clicIzquierdo) {
		this.clicIzquierdo = clicIzquierdo;
	}
	
	/**
	 * Devuelve true o false si se presiono el clic derecho del mouse.
	 * 
	 * @return true si presiono el clic derecho, de lo contrario false.
	 */
	public boolean isClicDerecho() {
		return clicDerecho;
	}
	
	/**
	 * Establece un nuevo estado para el clic derecho-
	 * 
	 * @param clicDerecho nuevo valor del atributo.
	 */
	public void setClicDerecho(boolean clicDerecho) {
		this.clicDerecho = clicDerecho;
	}
	
	/**
	 * Hitbox del mouse dentro del juego.
	 * 
	 * @return rectangle.
	 */
	public Rectangle getRectanguloPosicion() {
		return rectangle;
	}
	
	/**
	 * Se obtiene un evento si el mouse se presiona y se determina si fue un clic izquierdo o derecho.
	 */
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			clicIzquierdo = true;
		}
		else if(SwingUtilities.isRightMouseButton(e)) {
			clicDerecho = true;
		}
	}
	
	/**
	 * Se obtiene un evento si el mouse se solto y se determina si fue un clic izquierdo o derecho.
	 */
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			clicIzquierdo = false;
		}
		else if(SwingUtilities.isRightMouseButton(e)) {
			clicDerecho = false;
		}
	}

	@Override
	public void upgrade() {
		if(GameManifest.SCENARIO != null) {
			actualizarPosicionMouse();
		}
	}

	@Override
	public void paint(Graphics g) {
		
	}
}