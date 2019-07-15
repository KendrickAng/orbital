package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Is a text
public class TextUI extends UI {
	private BitmapFont font;
	private String text;
	private Color color;

	public TextUI(UIAlign align, BitmapFont font) {
		super(align);
		this.text = "";
		this.font = font;
		this.color = Color.WHITE;
	}

	@Override
	public TextUI setX(float x) {
		super.setX(x);
		return this;
	}

	@Override
	public TextUI setY(float y) {
		super.setY(y);
		return this;
	}

	public TextUI setText(String text) {
		this.text = text;

		GlyphLayout layout = new GlyphLayout(font, text);
		super.setW(layout.width);
		super.setH(layout.height);
		return this;
	}

	public TextUI setColor(Color color) {
		this.color = color;
		return this;
	}

	@Override
	public void render(SpriteBatch batch) {
		font.setColor(color);
		font.draw(batch, text, getX(), getY() + getH());
	}
}
