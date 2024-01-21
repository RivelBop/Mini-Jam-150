package com.rivelbop.rivelgdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.rivelbop.rivelgdx.screen.Core;

// Handles all audio files used in game
public class Audio {
	// Play a sound effect from a sound file
	public static Sound playSoundFile(String fileName, float volume, float pitch) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(fileName));
        sound.setPitch(sound.play(volume), pitch);
        return sound;
	}
	
    // Play a sound effect from the sound folder within the asset manager
    public static Sound playSound(String fileName, float volume, float pitch) {
        Sound sound = Core.assets.get(fileName, Sound.class);
        sound.setPitch(sound.play(volume), pitch);
        return sound;
    }
    
    // Play music from music file
    public static Music playMusicFile(String fileName, float volume, boolean loop) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(fileName));
        music.setVolume(volume);
        music.play();
        music.setLooping(loop);
        return music;
    }
    
    // Play music from the music folder within the music folder
    public static Music playMusic(String fileName, float volume, boolean loop) {
        Music music = Core.assets.get(fileName, Music.class);
        music.setVolume(volume);
        music.play();
        music.setLooping(loop);
        return music;
    }
}