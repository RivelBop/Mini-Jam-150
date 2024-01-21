package com.rivelbop.minijam150;

import com.badlogic.gdx.Gdx;
import com.rivelbop.rivelgdx.utils.Text;

public class TileMap {
	public static int[][] readToID(String mapName) {
		int[][] tileMap = null;
		String[] mapRows = new Text(Gdx.files.internal(mapName + ".map")).getString().split("\n");
		for(int y = mapRows.length - 1; y > -1; y--) {
			String[] mapColumns = mapRows[y].split(" ");
			if(tileMap == null) {
				tileMap = new int[mapRows.length][mapColumns.length];
			}
			for(int x = 0; x < mapColumns.length; x++) {
				tileMap[y][x] = Integer.valueOf(mapColumns[x]);
			}
		}
		return tileMap;
	}
}