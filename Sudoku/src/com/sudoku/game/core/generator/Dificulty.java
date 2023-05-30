package com.sudoku.game.core.generator;

import com.sudoku.game.util.GameManifest;

/**
 * Esta clase se encarga de establecer la dificultad del juego, colocando n celdas vacias.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Dificulty {
	private DificultyMode mode;
	public enum DificultyMode {
		EASY,
		MEDIUM,
		HARD;
	}
	
	private int min, max;
	private int totalHide;
	
	/**
	 * Constructor de la clase
	 */
	public Dificulty() {
		min = 1;
		max = 9;
	}
	
	/**
	 * Establece la cantidad de celdas vacias del tablero de sudoku, 
	 * dado el nivel de dificultad establecido.
	 * 
	 * @param mode nivel de dicultad
	 */
	public void setDificulty(DificultyMode mode) {
		this.mode = mode;
		if(mode == null) {
			mode = DificultyMode.EASY;
		}
		
		switch(mode) {
		case EASY:
			min = 8;
			max = 54;
			break;
		case MEDIUM:
			min = 22;
			max = 64;
			break;
		case HARD:
			min = 64;
			max = 64;
			break;
			default:
				min = 1;
				max = 9;
		}
		
		//El total de celdas vacias sale en un rango de min-max
		totalHide = GameManifest.generarNumeroAleatorio(min, max);
	}
	
	/**
	 * Devuelve el Dificultad actual del sudoku.
	 * 
	 * @return Dificultad actual.
	 */
	public DificultyMode getDificultyMode() {
		return mode;
	}
	
	/**
	 * Devuelve un numero aleatorio.
	 * 
	 * @return numero aleatorio.
	 */
	public int getHide() {
		return GameManifest.generarNumeroAleatorio(1, 9);
	}
	
	/**
	 * Devuelve el total de celdas que deben estar vacias.
	 * 
	 * @return total de celdas vacias.
	 */
	public int getTotalHide() {
		return totalHide;
	}
}