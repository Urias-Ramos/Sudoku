package com.sudoku.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sudoku.game.core.generator.Dificulty.DificultyMode;
import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.core.graphicsengine.Scenario.Scene;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;
import com.sudoku.game.util.Resources;
import com.sudoku.game.util.Sound;

/**
 * Esta clase representa el menu de dificultad del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class DifficultyMenu implements UpdateAndPainting {
	private int x, y;
	private Rectangle rectangle;
	private Rectangle[] btnDifficulty;
	private Rectangle btnBack;
	private BufferedImage iconAtras;
	
	private String value;
	private int margen;
	private Font font;
	
	private String[] textButton;
	private int buttonSelected;
	private boolean selected;
	
	private DificultyMode dificultyMode;
	private Sound soundPage;
	
	/**
	 * Constructor de la clase
	 * 
	 * Aqui se crean e inicializan los objetos de la clase.
	 */
	public DifficultyMenu() {
		x = 0;
		y = 0;
		
		soundPage = new Sound("sound/sfx-paperflip.wav");
		
		margen = 10;
		setValue("");
		font = new Font("Arial", Font.BOLD, 42);
		
		rectangle = new Rectangle(x, y, 320, 400);
		btnBack = new Rectangle(10, 10, 28, 28);
		iconAtras = Resources.getImagen("icon/icon_atras.png");
		
		textButton = new String[] {"Facil", "Normal", "Dificil"};
		btnDifficulty = new Rectangle[textButton.length];
		for(int i=0; i<btnDifficulty.length; i++) {
			btnDifficulty[i] = new Rectangle(0, 0, 132, 32);
		}
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
	 * Devuelve el valor actual del atributo selected.
	 * 
	 * @return elvalor actual del atributo selected.
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Establece un nuevo valor para el atributo selected.
	 * 
	 * @param selected nuevo valor para el atributo selected.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Este metodo se encarga de seleccionar un control usando el teclado o el mouse.
	 * 
	 * @param desplazar direccion en la que se seleccionara el control.
	 */
	public void seleccionarOpcion(int desplazar) {
		if(desplazar > -1) {
			if((buttonSelected + 1) < btnDifficulty.length) {
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
				buttonSelected = btnDifficulty.length - 1;
			}
		}
	}
	
	/**
	 * Este metodo se encarga de pintar los controles: jugar, como se juega y salir.
	 * 
	 * @param g contexto grafico del motor grafico.
	 * @param cadena texto que trendra el control.
	 * @param id posicion en que se encuentra.
	 * @param btnMenu objeto Rectangle donde se dibujara el bton y el texto.
	 */
	private void paintControl(Graphics g, String cadena, int id, Rectangle btnMenu) {
		btnMenu.x = (int) rectangle.getCenterX() - (btnMenu.width / 2);
		btnMenu.y = (int) (rectangle.getCenterY() - 58) + (btnMenu.height * id) + (margen * id);
		
		x = (int) btnMenu.getCenterX() - (Painter.getAnchoString(g, cadena) / 2);
		y = (int) (btnMenu.getCenterY() + (Painter.getAltoString(g, cadena) / 4));
		
		Painter.rellenoRoundRect(g, btnMenu, 10, 10, GameManifest.COLORCELESTE);
		
		if(buttonSelected != id) {
			Painter.string(g, cadena, Color.BLACK, x, y);
		}
		else {
			Painter.string(g, cadena, Color.WHITE, x, y);
		}
	}
	
	/**
	 * Cambia el menu actual al anterior.
	 * 
	 * Si se estaba jugando volvera a renaudar el cronometro.
	 * 
	 */
	public void back() {
		buttonSelected = 3;
		GameManifest.RATON.setClicIzquierdo(false);
		setSelected(false);
		if(GameManifest.SCENARIO.getLastScene() == Scene.PLAYING) {
			GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().resume();
		}
		
		GameManifest.SCENARIO.setEscenarioActual(GameManifest.SCENARIO.getLastScene());
	}
	
	@Override
	public void upgrade() {
		rectangle.width = GameManifest.ANCHO_PANTALLA_COMPLETA;
		rectangle.height = GameManifest.ALTO_PANTALLA_COMPLETA;
		
		x = (GameManifest.CENTRO_PANTALLA_COMPLETA.x) - (rectangle.width / 2);
		y = (GameManifest.CENTRO_PANTALLA_COMPLETA.y) - (rectangle.height / 2);
		
		rectangle.x = x;
		rectangle.y = y;
		
		textButton[0] = GameManifest.IDIOME.getProperty("3002");
		textButton[1] = GameManifest.IDIOME.getProperty("3003");
		textButton[2] = GameManifest.IDIOME.getProperty("3004");
		
		if(btnDifficulty[0].intersects(GameManifest.RATON.getRectanguloPosicion())) {
			buttonSelected = 0;
			dificultyMode = DificultyMode.EASY;
			if(GameManifest.RATON.isClicIzquierdo()) {
				GameManifest.RATON.setClicIzquierdo(false);
				setSelected(true);
			}
		}
		else if(btnDifficulty[1].intersects(GameManifest.RATON.getRectanguloPosicion())) {
			buttonSelected = 1;
			dificultyMode = DificultyMode.MEDIUM;
			if(GameManifest.RATON.isClicIzquierdo()) {
				GameManifest.RATON.setClicIzquierdo(false);
				setSelected(true);
			}
		}
		else if(btnDifficulty[2].intersects(GameManifest.RATON.getRectanguloPosicion())) {
			buttonSelected = 2;
			dificultyMode = DificultyMode.HARD;
			if(GameManifest.RATON.isClicIzquierdo()) {
				GameManifest.RATON.setClicIzquierdo(false);
				setSelected(true);
			}
		}
		else if(btnBack.intersects(GameManifest.RATON.getRectanguloPosicion())) {
			buttonSelected = 3;
			if(GameManifest.RATON.isClicIzquierdo()) {
				GameManifest.RATON.setClicIzquierdo(false);
				back();
			}
		}
		
		if(isSelected()) {
			setSelected(false);
			if(buttonSelected != 3) {
				soundPage.play();
				GameManifest.GameEmpty();
				GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().stop();
				GameManifest.SCENARIO.getGameScenario().stratSudoku();
				GameManifest.SCENARIO.getGameScenario().setShowControlPanel(true);
				GameManifest.SCENARIO.getGameScenario().getBoard().setDifficulty(dificultyMode);
				GameManifest.SCENARIO.setEscenarioActual(Scene.PLAYING);
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		Painter.image(g, iconAtras, btnBack);
		
		setValue(GameManifest.IDIOME.getProperty("3001"));
		Painter.setFont(g, font);
		x = (int) rectangle.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
		y = rectangle.y + (100 + Painter.getAltoString(g, getValue()));
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
		
		Painter.setFont(g, font.deriveFont(Font.BOLD, 14));
		for(int i=0; i<btnDifficulty.length; i++) {
			paintControl(g, textButton[i], i, btnDifficulty[i]);
		}
	}
}