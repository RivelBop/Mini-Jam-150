package com.rivelbop.rivelgdx.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.rivelbop.rivelgdx.Main;
import com.rivelbop.rivelgdx.screen.Core;
import com.rivelbop.rivelgdx.utils.Audio;

// An Abstract "Default" Button
public abstract class Button {
    private Font font;
    private final ButtonListener listener;
    private final GlyphLayout fontLayout;
    private final String text;
    private Sprite sprite;
    private boolean hovered;

    // Create a button with the provided text, position, and action on pressed
    public Button(String text, float x, float y, ButtonListener listener) {
        sprite = Core.assets.get(atlas(), TextureAtlas.class).createSprite(buttonImg());
        sprite.setCenter(x, y);
        
        font = new Font(font(), fontSize(), fontColor());
        fontLayout = new GlyphLayout(font.getFont(), text);
        
        this.text = text;
        this.listener = listener;
    }

    // Checks to see if the cursor is hovering over the button, if it is, the button is highlighted
    private boolean isHovered() {
    	
        if (sprite.getBoundingRectangle().contains(Gdx.input.getX(), -Gdx.input.getY() + Main.getHeight())) {
            if (!hovered) {
                sprite.setColor(Color.YELLOW);
                Audio.playSound(hoverSound(), 1.0f, 1.0f);
            }
            hovered = true;
            return true;
        }
        hovered = false;
        if (sprite.getColor().equals(Color.YELLOW)) {
            sprite.setColor(Color.WHITE);
        }
        return false;
    }

    // Checks to see if the mouse is hovering over the button and if the mouse is pressed
    public boolean isPressed() {
        if (isHovered() && Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
            Audio.playSound(clickSound(), 1.0f, 1.0f);
            onPressed();
            return true;
        }
        return false;
    }

    // Triggers the listeners onPressed() method
    public void onPressed() {
        listener.onPressed();
    }
    
    // Render the button to the GameCore's UI SpriteBatch
    public void render() {
        // Renders the button sprite
        sprite.draw(Core.uiBatch);

        // Renders the font/text of the button
        font.render(sprite.getX() + sprite.getWidth() / 2f - fontLayout.width / 2f, sprite.getY() + sprite.getHeight() / 2f + fontLayout.height / 2f, text, Core.uiBatch);
    }
    
    // Render the button to the provided SpriteBatch
    public void render(SpriteBatch batch) {
        // Renders the button sprite
        sprite.draw(batch);

        // Renders the font/text of the button
        font.render(sprite.getX() + sprite.getWidth() / 2f - fontLayout.width / 2f, sprite.getY() + sprite.getHeight() / 2f + fontLayout.height / 2f, text, batch);
    }
    
    // Dispose of the BitMapFont stored within the button
    public void dispose() {
        font.dispose();
    }
    
    public abstract String atlas();
    public abstract String buttonImg();
    public abstract String buttonPressedImg();
    public abstract String font();
    public abstract String hoverSound();
    public abstract String clickSound();
    public abstract int fontSize();
    public abstract Color fontColor();
}