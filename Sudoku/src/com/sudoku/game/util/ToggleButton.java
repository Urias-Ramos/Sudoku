package com.sudoku.game.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;

public class ToggleButton implements UpdateAndPainting {
	private int x, y;
	
	private boolean activated;
	private Rectangle hitBox;
	
	private BufferedImage icon;
	private BufferedImage maskOnButton;
	private BufferedImage maskOffButton;
	
	private Dimension dimensionIcon;

	public ToggleButton(boolean activated, BufferedImage icon, BufferedImage maskOnButton, BufferedImage maskOffButton, Dimension dimensionButton, Dimension dimensionIcon) {
		x = 0;
		y = 0;
		
		setActivated(activated);
		setIcon(icon);
		setMaskOnButton(maskOnButton);
		setMaskOffButton(maskOffButton);
		this.dimensionIcon = dimensionIcon;
		
		hitBox = new Rectangle(x, y, dimensionButton.width, dimensionButton.height);
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}
	
	public BufferedImage getIcon() {
		return icon;
	}
	
	public void setIcon(BufferedImage icon) {
		this.icon = icon;
	}
	
	public BufferedImage getMaskOnButton() {
		return maskOnButton;
	}
	
	public void setMaskOnButton(BufferedImage maskOnButton) {
		this.maskOnButton = maskOnButton;
	}
	
	public BufferedImage getMaskOffButton() {
		return maskOffButton;
	}
	
	public void setMaskOffButton(BufferedImage maskOffButton) {
		this.maskOffButton = maskOffButton;
	}
	
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void upgrade() {
		getHitBox().x = x;
		getHitBox().y = y;
	}

	@Override
	public void paint(Graphics g) {
		if(isActivated()) {
			Painter.image(g, getMaskOnButton(), getHitBox());
		}
		else {
			Painter.image(g, getMaskOffButton(), getHitBox());
		}
		
		if(getIcon() != null) {
			x = (int) getHitBox().getCenterX() - (dimensionIcon.width / 2);
			y = (int) getHitBox().getCenterY() - (dimensionIcon.height / 2);
			Painter.image(g, getIcon(), x, y, dimensionIcon.width, dimensionIcon.height);
		}
	}
}