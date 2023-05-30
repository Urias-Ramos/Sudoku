package com.sudoku.game.core.graphicsengine;

import java.awt.Graphics;

/**
 * interfaz UpdateAndPainting.
 * 
 * Esta interfaz define los metodos necesarios para implementar la logica del video juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public interface UpdateAndPainting {
	
	/**
	 * Este metodo actualiza el estado de los elementos del juego.
	 * 
	 * Este metodo se ejecuta en cada ciclo de actualizacion del juego y se utiliza para
	 * actualizar el estado y la logica de los elementos del juego,
	 */
	public void upgrade();
	
	/**
	 * Este metodo pinta los elementos visuales del juego en pantalla.
	 * 
	 * Es metodo se ejecuta en cada ciclo de renderizado del juego y se utiliza para
	 * pintar los elementos visuales en pantalla, como el tablero, las regiones, el resaltado, etc.
	 * 
	 * @param g El objeto Graphics en el que se pntan los elemetos del juego.
	 */
	public void paint(Graphics g);
}