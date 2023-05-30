package com.sudoku.game.core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sudoku.game.core.generator.Board;
import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;
import com.sudoku.game.util.Resources;

/**
 * Esta clase es la encargada de actualizar y dibujar el tablero del sudoku, y el panel de contro.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class GameScenario implements UpdateAndPainting {
	private Board board;
	private ControlPanel controlPanel;
	
	private Rectangle rectangle;
	
	private Rectangle btnTheme;
	private BufferedImage image;
	
	private boolean showControlPanel;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Aqui se crean e inicializan los objetos de la clase.
	 */
	public GameScenario() {
		board = new Board();
		
		controlPanel = new ControlPanel();
		
		rectangle = new Rectangle(0, 0, board.getDimension() + 330, board.getDimension());
		
		btnTheme = new Rectangle(0, 0, 32, 32);
		
		image = Resources.getImagen("icon/icon_pause.png");
	}
	
	/**
	 * Devuelve el Board para tener acceso a sus atributos y metodos.
	 * 
	 * @return Board.
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Devuelve el NumericKeyboard para tener acceso a sus atributos y metodos.
	 * 
	 * @return NumericKeyboard.
	 */
	public NumericKeyboard getKeyboard() {
		return controlPanel.getKeyboard();
	}
	
	/**
	 * Devuelve el ControlPanel para tener acceso a sus atributos y metodos.
	 * 
	 * @return ControlPanel.
	 */
	public ControlPanel getControlPanel() {
		return controlPanel;
	}
	
	/**
	 * Devuelve el esatdo del panel de control.
	 * 
	 * @return true si esta visible y false si no esta visible
	 */
	public boolean isShowControlPanel() {
		return showControlPanel;
	}
	
	/**
	 * Establece si el panel de control debe estar visible o no.
	 * 
	 * @param showControlPanel almacena el nuevo estado.
	 */
	public void setShowControlPanel(boolean showControlPanel) {
		this.showControlPanel = showControlPanel;
	}
	
	/**
	 * Inicial el sodoku, pone visible los objetos del tablero.
	 */
	public void stratSudoku() {
		GameManifest.SCENARIO.getGameScenario().getBoard().startSudoku();
		board.setVisible(true);
		board.setRegionVisible(true);
		board.setCellVisible(true);
	}
	
	@Override
	public void upgrade() {
		rectangle.x = (int) GameManifest.CENTRO_PANTALLA_COMPLETA.x - (rectangle.width / 2);
		rectangle.y = 60;
		
		if((GameManifest.TUTORIAL.isVisible())&&(!GameManifest.TUTORIAL.isShowPanelOption())) {
			rectangle.x = (int) GameManifest.CENTRO_PANTALLA_COMPLETA.x - (board.getRectangle().width / 2);
		}
		
		btnTheme.x = 10;
		btnTheme.y = 10;
		
		board.setCoordinates(rectangle.x, rectangle.y);
		board.upgrade();
		
		controlPanel.setSizePanel(300, board.getRectangle().height);
		
		controlPanel.setCoordinates(rectangle.x + rectangle.width, rectangle.y);
		controlPanel.upgrade();
		
		if((GameManifest.RATON.getRectanguloPosicion().intersects(btnTheme))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			if((!GameManifest.TUTORIAL.isVisible())) {
				GameManifest.PAUSED = true;
				GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().pause();
			}
		}
		
		GameManifest.TUTORIAL.setCoordinates((int) board.getRectangle().getCenterX(), (int) board.getRectangle().getCenterY());
		if((GameManifest.TUTORIAL.isVisible())) {
			GameManifest.TUTORIAL.upgrade();
			board.setVisible(GameManifest.TUTORIAL.isShowTablero());
			board.setRegionVisible(GameManifest.TUTORIAL.isShowRegion());
			board.setCellVisible(GameManifest.TUTORIAL.isShowCell());
		}
	}

	@Override
	public void paint(Graphics g) {
		board.paint(g);
		
		if(isShowControlPanel()) {
			controlPanel.paint(g);
		}
		
		Painter.image(g, image, btnTheme);
		
		if(GameManifest.TUTORIAL.isVisible()) {
			GameManifest.TUTORIAL.paint(g);
		}
	}
}