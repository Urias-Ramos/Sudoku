package com.sudoku.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.core.graphicsengine.Scenario.Scene;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;
import com.sudoku.game.util.Resources;

/**
 * Esta clase representa el menu de inicio del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class StartMenu implements UpdateAndPainting {
	private String cadena;
	private int x, y;
	
	private Rectangle rectangle;
	
	private boolean selected;
	private int buttonSelected;
	private Rectangle[] btnMenu;
	
	private Rectangle btnSetting, btnCredits;
	private BufferedImage iconSetting, iconCredits;
	
	private Font font;
	
	/**
	 * Constructor de la clase
	 * 
	 * Aqui se crean los objetos y se hacen los calculos iniciales de este menu.
	 */
	public StartMenu() {
		x = 0;
		y = 0;
		
		rectangle = new Rectangle(x, y, 300, 300);
		
		font = new Font("Arial", Font.BOLD, 16);
		
		selected = false;
		buttonSelected = 0;
		
		btnMenu = new Rectangle[3];
		for(int i=0; i<btnMenu.length; i++) {
			btnMenu[i] = new Rectangle(0, 0, 132, 48);
		}
		
		btnSetting = new Rectangle(0, 0, 28, 28);
		iconSetting = Resources.getImagen("icon/icon_setting.png");
		
		btnCredits = new Rectangle(0, 0, 28, 28);
		iconCredits = Resources.getImagen("icon/icon_help.png");
	}
	
	/**
	 * Este metodo se encarga de pintar los controles: jugar, como se juega y salir.
	 * 
	 * @param g contexto grafico del motor grafico.
	 * @param cadena texto que trendra el control.
	 * @param id posicion en que se encuentra.
	 */
	private void paintControl(Graphics g, String cadena, int id) {
		x = GameManifest.CENTRO_PANTALLA_COMPLETA.x - (200 / 2);
		y = (GameManifest.CENTRO_PANTALLA_COMPLETA.y - (48 / 2));
		
		Painter.rellenoRoundRect(g, btnMenu[id], 10, 10, GameManifest.COLORCELESTE);
		
		 btnMenu[id].x = (int) rectangle.getCenterX() - (btnMenu[id].width / 2);
		 btnMenu[id].y = (int) rectangle.getCenterY() + (btnMenu[id].height * id) + (10 * id);
		
		x = (int) btnMenu[id].getCenterX() - (Painter.getAnchoString(g, cadena) / 2);
		y = (int) (btnMenu[id].getCenterY() + (Painter.getAltoString(g, cadena) / 4));
		
		if(buttonSelected != id) {
			Painter.string(g, cadena, Color.BLACK, x, y);
		}
		else {
			Painter.string(g, cadena, Color.WHITE, x, y);
		}
	}
	
	/**
	 * Este metodo se encarga de seleccionar un control usando el teclado o el mouse.
	 * 
	 * @param desplazar direccion en la que se seleccionara el control.
	 */
	public void seleccionarOpcion(int desplazar) {
		if(desplazar > -1) {
			if((buttonSelected + 1) < btnMenu.length) {
				buttonSelected++;
			}
			else {
				buttonSelected = 0;
			}
		}
		else if(desplazar < 0) {
			if((buttonSelected - 1) > -1) {
				buttonSelected--;
			}
			else {
				buttonSelected = btnMenu.length - 1;
			}
		}
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
	 * Establece si un control esta seleccionado o no.
	 * 
	 * @param selected valor que se usara para cambiar el atributo selected.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public void upgrade() {
		if(isSelected()) {
			setSelected(false);
			switch(buttonSelected) {
			case 0:
				GameManifest.RATON.setClicIzquierdo(false);
				GameManifest.SCENARIO.setEscenarioActual(Scene.DIFFICULTYMENU);
				break;
			case 1:
				GameManifest.GameEmpty();
				GameManifest.CELLCORRECT = 0;
				GameManifest.NUMEROSELECT = "";
				GameManifest.TUTORIAL.startTutorial();
				GameManifest.TUTORIAL.setVisible(true);
				GameManifest.SCENARIO.setEscenarioActual(Scene.PLAYING);
				break;
			case 2:
				System.exit(0);
				break;
			}
		}
		
		rectangle.x = GameManifest.CENTRO_PANTALLA_COMPLETA.x - (rectangle.width / 2);
		rectangle.y = GameManifest.CENTRO_PANTALLA_COMPLETA.y - (rectangle.height / 2);
		
		btnSetting.x = GameManifest.ANCHO_PANTALLA_COMPLETA - (btnSetting.width * 2);
		btnSetting.y = 10;
		
		btnCredits.x = GameManifest.ANCHO_PANTALLA_COMPLETA - (btnSetting.width * 4);
		btnCredits.y = 10;
		
		if((GameManifest.RATON.getRectanguloPosicion().intersects(btnSetting))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			GameManifest.SCENARIO.setEscenarioActual(Scene.SETTINGMENU);
		}
		else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnCredits))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			GameManifest.SCENARIO.setEscenarioActual(Scene.CREDITSMENU);
		}
		
		if((GameManifest.RATON.getRectanguloPosicion().intersects(btnMenu[0]))) {
			buttonSelected = 0;
			if((GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				GameManifest.SCENARIO.setLastScene(Scene.STARTMENU);
				setSelected(true);
			}
		}
		else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnMenu[1]))) {
			buttonSelected = 1;
			if((GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				setSelected(true);
			}
		}
		else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnMenu[2]))) {
			buttonSelected = 2;
			if((GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				setSelected(true);
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		Painter.setFont(g, new Font("Binner Poster", Font.BOLD, 72));
		cadena = "Sudoku.";
		
		x = (int) rectangle.getCenterX() - (Painter.getAnchoString(g, cadena) / 2);
		y = rectangle.y + Painter.getAltoString(g, cadena);
		
		Painter.string(g, cadena, GameManifest.COLORCELLFOREGROUND, x, y);
		
		Painter.setFont(g, font);
		
		paintControl(g, GameManifest.IDIOME.getProperty("1"), 0);
		paintControl(g, GameManifest.IDIOME.getProperty("2"), 1);
		paintControl(g, GameManifest.IDIOME.getProperty("3"), 2);
		
		Painter.image(g, iconSetting, btnSetting);
		Painter.image(g, iconCredits, btnCredits);
	}
}