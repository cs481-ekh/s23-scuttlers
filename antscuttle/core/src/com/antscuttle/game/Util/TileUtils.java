
package com.antscuttle.game.Util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *  Texture getters
 * @author antho
 */
public class TileUtils {
    public static TextureRegion[][] getAllTextures(){
        TextureRegion allTiles = new TextureRegion(new Texture("levels/textures/tiles.png"));
        TextureRegion[][] trimmed = allTiles.split(17, 17);
        for (TextureRegion[] row : trimmed) {
            for (int j = 0; j<trimmed[0].length; j++) {
                row[j] = new TextureRegion(row[j], 0, 0, 16, 16);
            }
        }
        return trimmed;
    }
    public static TextureRegion[][] getPressurePlateTextures(){
        TextureRegion allTiles = new TextureRegion(new Texture("animations/levelobj/pressure_plate.png"));
        TextureRegion[][] trimmed = allTiles.split(17, 16);
        for (TextureRegion[] row : trimmed) {
            for (int j = 0; j<trimmed[0].length; j++) {
                row[j] = new TextureRegion(row[j], 0, 0, 16, 16);
            }
        }
        return trimmed;
    }
}
