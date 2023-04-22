/*
 * StaticLevelObject: implementation of LevelObject for non-interactable
 *                    tiles such as buildings.
 */
package com.antscuttle.game.LevelObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author antho
 */
public abstract class StaticLevelObject extends LevelObject{

    public StaticLevelObject(TextureRegion tex){
        super(tex, 0);
    }

    @Override
    protected void init() {
    }

    
    
}
