package com.sudoku.game.core.gamepad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sudoku.game.util.GameManifest;

/**
 * Esta clase es la encargada de administar los eventos del teclado dentro del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class GameKeyboard implements KeyListener {
	
	/**
	 * Constuctor de la clase.
	 */
	public GameKeyboard() {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_V:
			System.exit(0);
			break;
		case KeyEvent.VK_UP:
			switch(GameManifest.SCENARIO.getEscenarioActual()) {
			case DIFFICULTYMENU:
				GameManifest.SCENARIO.getDifficultyMenu().seleccionarOpcion(-1);
				break;
			case STARTMENU:
				GameManifest.SCENARIO.getStartMenu().seleccionarOpcion(-1);
				break;
			default:
			}
			break;
		case KeyEvent.VK_DOWN:
			switch(GameManifest.SCENARIO.getEscenarioActual()) {
			case DIFFICULTYMENU:
				GameManifest.SCENARIO.getDifficultyMenu().seleccionarOpcion(1);
				break;
			case STARTMENU:
				GameManifest.SCENARIO.getStartMenu().seleccionarOpcion(1);
				break;
			default:
			}
			break;
		case KeyEvent.VK_ENTER:
			enter();
			break;
		case KeyEvent.VK_SPACE:
			enter();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_1:
			setNumericSelect(1);
			break;
		case KeyEvent.VK_2:
			setNumericSelect(2);
			break;
		case KeyEvent.VK_3:
			setNumericSelect(3);
			break;
		case KeyEvent.VK_4:
			setNumericSelect(4);
			break;
		case KeyEvent.VK_5:
			setNumericSelect(5);
			break;
		case KeyEvent.VK_6:
			setNumericSelect(6);
			break;
		case KeyEvent.VK_7:
			setNumericSelect(7);
			break;
		case KeyEvent.VK_8:
			setNumericSelect(8);
			break;
		case KeyEvent.VK_9:
			setNumericSelect(9);
			break;
		case KeyEvent.VK_ESCAPE:
			switch(GameManifest.SCENARIO.getEscenarioActual()) {
			case DIFFICULTYMENU:
				GameManifest.SCENARIO.getDifficultyMenu().back();
				break;
			case SETTINGMENU:
				GameManifest.SCENARIO.getSettingMenu().back();
				break;
			case CREDITSMENU:
				GameManifest.SCENARIO.getCreditsMenu().back();
				break;
			case WINNERMENU:
				GameManifest.SCENARIO.getWinnerMenu().back();
				break;
			case PLAYING:
				if((!GameManifest.TUTORIAL.isVisible())) {
					GameManifest.PAUSED = ! GameManifest.PAUSED;
					if(GameManifest.PAUSED) {
						GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().pause();
					}
					else {
						GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().resume();
					}
				}
				break;
				default:
			}
			break;
		}
	}
	
	/**
	 * Establece cual tecla del panel numerico se presiono.
	 * 
	 * @param numero valor de la tecla presionada.
	 */
	private void setNumericSelect(int numero) {
		GameManifest.NUMEROSELECT = ""+numero;
		GameManifest.SCENARIO.getGameScenario().getKeyboard().setSelect(true);
	}
	
	/**
	 * Se presiono la tecla ENTER y dependiendo del menu actual se toman acciones.
	 */
	private void enter() {
		switch(GameManifest.SCENARIO.getEscenarioActual()) {
		case DIFFICULTYMENU:
			GameManifest.SCENARIO.getDifficultyMenu().setSelected(true);
			break;
		case STARTMENU:
			GameManifest.SCENARIO.getStartMenu().setSelected(true);
			break;
			default:
		}
	}
}