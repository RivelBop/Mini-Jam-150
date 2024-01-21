package com.rivelbop.rivelgdx.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.rivelbop.rivelgdx.screen.Core;

// Used to generate a BitmapFont with ease
public class Font {
    private final FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;
    private BitmapFont font;

    // Generate a font with the provided TTF file, font size, font color, and whether the font is in debug mode
    public Font(String file, int size, Color color, boolean isDebug) {
        if (isDebug) {
            // If in debug mode, do not get the TTF font from the GameCore AssetManager
            generator = new FreeTypeFontGenerator(Gdx.files.internal(file));
            parameter = new FreeTypeFontParameter();
            parameter.size = size;
            parameter.color = color;
            font = generator.generateFont(parameter);
        } else {
            generator = null;
        }
    }
    
    // Generate a font with the provided TTF file from the GameCore AssetManager, font size, and font color
    public Font(String file, int size, Color color) {
        generator = Core.assets.get(file, FreeTypeFontGenerator.class);
        parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        font = generator.generateFont(parameter);
    }

    // Change the color of the font
    public void setColor(Color color) {
        parameter.color = color;
        font = generator.generateFont(parameter);
    }

    // Change the size of the font
    public void setSize(int size) {
        parameter.size = size;
        font = generator.generateFont(parameter);
    }

    // Render the font on the GameCore UI SpriteBatch
    public void render(float x, float y, String text) {
        font.draw(Core.uiBatch, text, x, y);
    }
    
    // Render the font on the specified SpriteBatch
    public void render(float x, float y, String text, SpriteBatch batch) {
        font.draw(batch, text, x, y);
    }

    // Render the font on the GameCore UI SpriteBatch based on the center of the font
    public void renderCenter(float x, float y, String text) {
        GlyphLayout layout = new GlyphLayout(font, text);
        font.draw(Core.uiBatch, text, x - layout.width / 2f, y - layout.height / 2f);
    }
    
    // Render the font on the specified SpriteBatch based on the center of the font
    public void renderCenter(float x, float y, String text, SpriteBatch batch) {
        GlyphLayout layout = new GlyphLayout(font, text);
        font.draw(batch, text, x - layout.width / 2f, y - layout.height / 2f);
    }

    // Render the font on the GameCore Debug SpriteBatch
    public void render(float x, float y, String text, boolean isDebug) {
        if (isDebug) {
            font.draw(Core.debugBatch, text, x, y);
        }
    }

    // Get the generated BitmapFont of the font
    public BitmapFont getFont() {
        return font;
    }

    // Dispose of the BitmapFont
    public void dispose() {
        font.dispose();
    }
}