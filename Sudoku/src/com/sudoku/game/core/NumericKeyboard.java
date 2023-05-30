package com.sudoku.game.core;

import java.awt.Graphics;
import java.util.LinkedList;

import com.sudoku.game.core.ControlPanel.TYPEKEYCONTROL;
import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.util.GameManifest;

/**
 * Esta clase Representa un teclado numerico.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class NumericKeyboard implements UpdateAndPainting {
	private int x, y;
	private int margen;
	
	private boolean select;
	
	private int dimension;
	
	private LinkedList<Key> keyList;
	
	private int indexX, indexY, max;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Crea un panel numerico con las teclas del 1-9.
	 */
	public NumericKeyboard() {
		margen = 5;
		
		keyList = new LinkedList<Key>();
		for(int i=0; i<9; i++) {
			keyList.add(new Key(""+(i+1), 32));
		}
	}
	
	/**
	 * Devuelve true si una tecla de panel numerico esta seleccionada.
	 * 
	 * @return si existe una tecla seleccionada en el panel numerico.
	 */
	public boolean isSelect() {
		return select;
	}
	
	/**
	 * Establece si existe o no una tecla seleccionada.
	 * 
	 * @param select valor del esatdo del panel numerico.
	 */
	public void setSelect(boolean select) {
		this.select = select;
	}
	
	/**
	 * Devuelve la dimension del teclado numerico.
	 * 
	 * @return dimension del panel numerico.
	 */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * Establece la dimension del panel numerico.
	 * 
	 * @param dimension nueva dimension para el panel numerico.
	 */
	public void setDimension(int dimension) {
		this.dimension = (dimension / 9) - (margen);
	}
	
	/**
	 * Devuelve la tecla con el indice especificado.
	 * 
	 * @param index indice de la tecla.
	 * @return la tecla especifica.
	 */
	public Key getKey(int index) {
		return keyList.get(index);
	}
	
	/**
	 * Establece la posicion donde se pintara el tablero.
	 * 
	 * @param x coordenada x deonde se pintara el tablero.
	 * @param y coordenada y deonde se pintara el tablero.
	 */
	public void setPosicionTablero(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void upgrade() {
		x = x + 10;
		y = y - ((32 * 2) * 3);
		
		if(keyList.size() > 0) {
			
			indexX = 0;
			indexY = 0;
			max = 3;
			for(Key key: keyList) {
				
				key.setCoordinates((x + ((key.getDimension() + margen) * indexX) + margen), (y + (((key.getDimension()) + margen) * indexY) + margen));
				key.upgrade();
				
				key.setSelected(false);
				if(key.getCount() > 0) {
					if(GameManifest.NUMEROSELECT.contentEquals(key.getValue())) {
						key.setSelected(true);
					}
				}
				else if(key.getValue().contentEquals(GameManifest.NUMEROSELECT)) {
					GameManifest.SCENARIO.getGameScenario().getControlPanel().setTypeKeyControl(TYPEKEYCONTROL.NONE);
				}
				
				if((indexX + 1) < max) {
					indexX++;
				}
				else {
					indexY++;
					indexX = 0;
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		if(keyList.size() > 0) {
			for(Key key: keyList) {
				if(GameManifest.PROFILE.isUsedNumbers()) {
					if(key.getCount() > 0) {
						key.paint(g);
					}
				}
				else {
					key.paint(g);
				}
			}
		}
	}
}