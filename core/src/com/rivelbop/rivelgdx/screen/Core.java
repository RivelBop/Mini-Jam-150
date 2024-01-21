package com.rivelbop.rivelgdx.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rivelbop.rivelgdx.graphics.ShapeRenderer;
import com.rivelbop.rivelgdx.utils.Text;
import com.rivelbop.rivelgdx.utils.TextLoader;

// The "Core" of a game containing all the necessary elements for quick startup
public abstract class Core extends Game {
	private final float LOADING_WIDTH = 750f, LOADING_HEIGHT = 50f;
	
	protected static Scaling SCALING;
	protected static int WIDTH, HEIGHT;
	public static Viewport viewport;
	public static AssetManager assets;
    public static SpriteBatch batch, uiBatch, debugBatch;
    public static ShapeRenderer shapeRenderer;
    
    // Create a default core without specifying a window scale effect and screen size (1280x720 by default)
    public Core() {
    	SCALING = Scaling.stretch;
    	WIDTH = 1280;
    	HEIGHT = 720;
    }
    
    // Create a core with the specified window scale effect and screen size
    public Core(Scaling scale, int width, int height) {
    	SCALING = scale;
    	WIDTH = width;
    	HEIGHT = height;
    }
    
    // Replacement methods for base LibGDX methods
	public abstract void init();
	public abstract void update();
	
	// Base LibGDX create() method, used to generate all necessary components
	@Override
	public void create() {
		viewport = new ScalingViewport(SCALING, WIDTH, HEIGHT);
        viewport.getCamera().update();
        
        assets = new AssetManager();
        setAssetLoader();
        loadAssets();
        
        batch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        debugBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        
		init();
	}
	
	// Clears the screen, updates the core, updates the current screen, and renders all necessary components
	@Override
	public void render() {
		// Clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(assets.update()) {
        	update();
        	super.render();
        } else {
        	shapeRenderer.begin();
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect(WIDTH / 2f - LOADING_WIDTH / 2f, HEIGHT / 2f - LOADING_HEIGHT / 2f, LOADING_WIDTH, LOADING_HEIGHT);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(WIDTH / 2f - LOADING_WIDTH / 2f, HEIGHT / 2f - LOADING_HEIGHT / 2f, LOADING_WIDTH * assets.getProgress(), LOADING_HEIGHT);
        	shapeRenderer.end();
        }
	}
	
	// Disposes of all stored/necessary disposables
	public void dispose() {
		batch.dispose();
		uiBatch.dispose();
		debugBatch.dispose();
		shapeRenderer.dispose();
		assets.dispose();
		getScreen().dispose();
	}
	
	// Provides the AssetManager with all necessary file loaders
	private void setAssetLoader() {
		assets.setLoader(
                Text.class,
                new TextLoader(
                        new InternalFileHandleResolver()
                )
        );
        assets.setLoader(
                FreeTypeFontGenerator.class,
                new FreeTypeFontGeneratorLoader(
                        new InternalFileHandleResolver()
                )
        );
        assets.setLoader(
                BitmapFont.class,
                ".ttf",
                new FreetypeFontLoader(
                        new InternalFileHandleResolver()
                )
        );
        assets.setLoader(
                BitmapFont.class,
                ".otf",
                new FreetypeFontLoader(
                        new InternalFileHandleResolver()
                )
        );
        assets.setLoader(
                ParticleEffect.class,
                new ParticleEffectLoader(
                        new InternalFileHandleResolver()
                )
        );
      
	}
	
	// Loads asset files into the 'assets' AssetManager
	private void loadAssets() {
        for(String assetName : new Text(Gdx.files.internal("assets")).getString().split("\n")) {
        	if(assetName.contains(".atlas")) {
        		assets.load(assetName, TextureAtlas.class);
        		System.out.println("TextureAtlas Loaded: " + assetName);
        	} else if(assetName.contains(".map")) {
        		assets.load(assetName, Text.class);
        		System.out.println("TextureAtlas Loaded: " + assetName);
        	} else if(assetName.contains(".png") || assetName.contains(".jpg")) {
        		assets.load(assetName, Texture.class);
        		System.out.println("Texture Loaded: " + assetName);
        	} else if(assetName.contains(".wav")) {
        		assets.load(assetName, Sound.class);
        		System.out.println("Sound Loaded: " + assetName);
        	} else if(assetName.contains(".mp3")) {
        		assets.load(assetName, Music.class);
        		System.out.println("Music Loaded: " + assetName);
        	} else if(assetName.contains(".ttf") || assetName.contains(".otf")) {
        		assets.load(assetName, FreeTypeFontGenerator.class);
        		System.out.println("Font Loaded: " + assetName);
        	} else if(assetName.contains(".p")) {
        		assets.load(assetName, ParticleEffect.class);
        		System.out.println("Particle Loaded: " + assetName);
        	}
        }
	}
	
	// Returns the provided width of the screen
	public static int getWidth() {
		return WIDTH;
	}
	
	// Returns the provided height of the screen
	public static int getHeight() {
		return HEIGHT;
	}
}