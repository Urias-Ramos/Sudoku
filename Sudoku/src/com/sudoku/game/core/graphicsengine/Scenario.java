package com.sudoku.game.core.graphicsengine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.sudoku.game.core.GameScenario;
import com.sudoku.game.ui.CreditsMenu;
import com.sudoku.game.ui.DifficultyMenu;
import com.sudoku.game.ui.PauseMenu;
import com.sudoku.game.ui.SettingsMenu;
import com.sudoku.game.ui.SplashScreen;
import com.sudoku.game.ui.StartMenu;
import com.sudoku.game.ui.Winner;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;

/**
 * Esta clase es la responsable de administrar los menu del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Scenario extends Canvas {
	
	public enum Scene {
		SPLASHSCREEN,
		STARTMENU,
		SETTINGMENU,
		DIFFICULTYMENU,
		PLAYING,
		WINNERMENU,
		CREDITSMENU;
	}
	
	private Scene escenarioActual;
	private Scene lastScene;
	
	private SplashScreen splash;
	private StartMenu startMenu;
	private SettingsMenu settingsMenu;
	private DifficultyMenu difficultyMenu;
	private GameScenario game;
	private PauseMenu pauseMenu;
	private Winner winnerMenu;
	private CreditsMenu creditsMenu;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Se crea el scenario con la dimension especificada.
	 * 
	 * @param ancho ancho del scenario.
	 * @param alto alto del scenario.
	 */
	public Scenario(int ancho, int alto) {
		this.setIgnoreRepaint(true);
		this.setPreferredSize(new Dimension(ancho, alto));
		this.addKeyListener(GameManifest.TECLADO);
		this.addMouseListener(GameManifest.RATON);
		this.setFocusable(true);
		this.requestFocus();
		
		escenarioActual = Scene.SPLASHSCREEN;
		setLastScene(Scene.STARTMENU);
		
		splash = new SplashScreen();
		startMenu = new StartMenu();
		settingsMenu = new SettingsMenu();
		difficultyMenu = new DifficultyMenu();
		game = new GameScenario();
		pauseMenu = new PauseMenu();
		winnerMenu = new Winner();
		creditsMenu = new CreditsMenu();
	}
	
	/**
	 * Devuelve el scenario actual en el que se encuentra.
	 * 
	 * @return scenario actual.
	 */
	public Scene getEscenarioActual() {
		return escenarioActual;
	}
	
	/**
	 * Establece el scenario actual que debe estar.
	 * 
	 * @param escenarioActual nuevo scenario para cambiar.
	 */
	public void setEscenarioActual(Scene escenarioActual) {
		this.escenarioActual = escenarioActual;
	}
	
	/**
	 * Devuelve el scenario anterior en el que estuvo el juego.
	 * 
	 * @return scenario anterior.
	 */
	public Scene getLastScene() {
		return lastScene;
	}
	
	/**
	 * Establece el scenario anterior en el que estuvo.
	 * 
	 * @param lastScene scenario anterior en el que estuvo.
	 */
	public void setLastScene(Scene lastScene) {
		this.lastScene = lastScene;
	}
	
	/**
	 * Devuelve el GameScenario para tener acceso a sus atributos y metodos.
	 * 
	 * @return GameScenario
	 */
	public GameScenario getGameScenario() {
		return game;
	}
	
	/**
	 * Actualiza el mouse y el scenario en el que se encuentra.
	 */
	public void upgradeScenario() {
		GameManifest.RATON.upgrade();
		upgradeGame();
	}
	
	/**
	 * Por cada ciclo de dibujo crean un contexto grafico, que sera enviado a los diferentes scenarios.
	 */
	public void paintScenario() {
		BufferStrategy buffer = getBufferStrategy();
		if(buffer == null) {
			createBufferStrategy(4);
			return;
		}
		
		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
		
		paintGame(g);
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
		buffer.show();
	}
	
	/**
	 * Devuelve el StartMenu para tener acceso a los atributos y metodos.
	 * 
	 * @return StartMenu.
	 */
	public StartMenu getStartMenu() {
		return startMenu;
	}
	
	/**
	 * Devuelve el DifficultyMenu para tener acceso a los atributos y metodos.
	 * 
	 * @return DifficultyMenu.
	 */
	public DifficultyMenu getDifficultyMenu() {
		return difficultyMenu;
	}
	
	/**
	 * Devuelve el SettingsMenu para tener acceso a los atributos y metodos.
	 * 
	 * @return SettingsMenu.
	 */
	public SettingsMenu getSettingMenu() {
		return settingsMenu;
	}
	
	/**
	 * Devuelve el CreditsMenu para tener acceso a los atributos y metodos.
	 * 
	 * @return CreditsMenu.
	 */
	public CreditsMenu getCreditsMenu() {
		return creditsMenu;
	}
	
	
	/**
	 * Devuelve el Winner para tener acceso a los atributos y metodos.
	 * 
	 * @return Winner.
	 */
	public Winner getWinnerMenu() {
		return winnerMenu;
	}
	
	/**
	 * Por cada ciclo de actualizacion del juego se actualiza el menu actual.
	 */
	private void upgradeGame() {
		GameManifest.CENTRO_PANTALLA_COMPLETA.x = (GameManifest.ANCHO_PANTALLA_COMPLETA / 2);
		GameManifest.CENTRO_PANTALLA_COMPLETA.y = (GameManifest.ALTO_PANTALLA_COMPLETA / 2);
		
		switch(getEscenarioActual()) {
		case SPLASHSCREEN:
			splash.upgrade();
			break;
		case STARTMENU:
			startMenu.upgrade();
			break;
		case DIFFICULTYMENU:
			difficultyMenu.upgrade();
			break;
		case SETTINGMENU:
			settingsMenu.upgrade();
			break;
		case PLAYING:
			game.upgrade();
			if(GameManifest.PAUSED) {
				pauseMenu.upgrade();
			}
			break;
		case WINNERMENU:
			winnerMenu.upgrade();
			break;
		case CREDITSMENU:
			creditsMenu.upgrade();
			break;
		}
	}
	
	/**
	 * Se pinta el scenario actual.
	 * 
	 * @param g contexto grafico del motor grafico.
	 */
	private void paintGame(Graphics g) {
		Painter.rellenoRectangulo(g, 0, 0, GameManifest.ANCHO_PANTALLA_COMPLETA, GameManifest.ALTO_PANTALLA_COMPLETA, GameManifest.COLORCELLBACKGROUND);
		
		switch(getEscenarioActual()) {
		case SPLASHSCREEN:
			splash.paint(g);
			break;
		case STARTMENU:
			startMenu.paint(g);
			break;
		case DIFFICULTYMENU:
			difficultyMenu.paint(g);
			break;
		case SETTINGMENU:
			settingsMenu.paint(g);
			break;
		case PLAYING:
			Painter.rellenoRectangulo(g, 0, 0, GameManifest.ANCHO_PANTALLA_COMPLETA, GameManifest.ALTO_PANTALLA_COMPLETA, GameManifest.COLORGAMESCENARIOBACKGROUND);
			game.paint(g);
			if(GameManifest.PAUSED) {
				pauseMenu.paint(g);
			}
			break;
		case WINNERMENU:
			winnerMenu.paint(g);
			break;
		case CREDITSMENU:
			creditsMenu.paint(g);
			break;
		}
	}
}