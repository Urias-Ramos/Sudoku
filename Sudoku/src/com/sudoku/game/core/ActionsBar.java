package com.sudoku.game.core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.sudoku.game.core.ControlPanel.TYPEKEYCONTROL;
import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.util.ButtonSudoku;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Resources;

/**
 * Esta clase representa la barra de accion donde existen botones con acciones como:
 * Lapiz, borrador y pistas.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class ActionsBar implements UpdateAndPainting {
	private int x, y;
	private int buttonSize;
	private int margen;
	private LinkedList<ButtonSudoku> btnList;
	
	/**
	 * Construtor de la clase.
	 * 
	 * Aqui crea e inicializa los botones de acciones.
	 */
	public ActionsBar() {
		String[] btnName = {"Borrador", "Lapiz", "Pista"};
		BufferedImage[] imagen = new BufferedImage[3];
		imagen[0] = Resources.getImagen("icon/icon_eraser.png");
		imagen[1] = Resources.getImagen("icon/icon_pencil.png");
		imagen[2] = Resources.getImagen("icon/icon_hint.png");
		
		buttonSize = 64;
		
		btnList = new LinkedList<ButtonSudoku>();
		for(int i=0; i<3; i++) {
			btnList.add(new ButtonSudoku(buttonSize, btnName[i], imagen[i]));
		}
		
		x = 0;
		y = 0;
		
		margen = 5;
	}
	
	/**
	 * Se actualiza el esatdo de los controles.
	 */
	private void updateKey() {
		
		//si una tecla numerica ya gasto sus usos entonces los controles de acciones pierden el foco. para que el usuario seleccione otra tecla.
		if(!GameManifest.NUMEROSELECT.isEmpty()) {
			btnList.get(0).setSelected(false);
			btnList.get(1).setSelected(false);
			btnList.get(2).setSelected(false);
		}
		
		if((btnList.get(0).getRectangle().intersects(GameManifest.RATON.getRectanguloPosicion()))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			GameManifest.NUMEROSELECT = "";
			btnList.get(0).setSelected(true);
			btnList.get(1).setSelected(false);
			btnList.get(2).setSelected(false);
			GameManifest.SCENARIO.getGameScenario().getControlPanel().setTypeKeyControl(TYPEKEYCONTROL.ERASER);
		}
		if((btnList.get(1).getRectangle().intersects(GameManifest.RATON.getRectanguloPosicion()))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			GameManifest.SHOWNOTES =  !GameManifest.SHOWNOTES;
			btnList.get(0).setSelected(false);
			btnList.get(1).setSelected(true);
			btnList.get(2).setSelected(false);
			GameManifest.SCENARIO.getGameScenario().getControlPanel().setTypeKeyControl(TYPEKEYCONTROL.OTHER);
		}
		if((btnList.get(2).getRectangle().intersects(GameManifest.RATON.getRectanguloPosicion()))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			btnList.get(0).setSelected(false);
			btnList.get(1).setSelected(false);
			btnList.get(2).setSelected(true);
			GameManifest.SCENARIO.getGameScenario().getControlPanel().setTypeKeyControl(TYPEKEYCONTROL.HINT);
		}
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
		y -= (buttonSize * btnList.size()) + (margen * (btnList.size() - 1));
		
		btnList.get(0).setValue(GameManifest.IDIOME.getProperty("4001"));
		btnList.get(1).setValue(GameManifest.IDIOME.getProperty("4002"));
		btnList.get(2).setValue(GameManifest.IDIOME.getProperty("4003"));
		
		for(int i=0; i<btnList.size(); i++) {
			btnList.get(i).setCoordenadas((x + (btnList.get(i).getDimension() * (i)) + (margen * (i))), y);
			btnList.get(i).upgrade();
			
			if(!GameManifest.PAUSED) {
				updateKey();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		for(int i=0; i<btnList.size(); i++) {
			btnList.get(i).paint(g);
		}
	}
}