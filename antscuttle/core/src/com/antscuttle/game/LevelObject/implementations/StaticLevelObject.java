/*
 * StaticLevelObject: implementation of LevelObject for non-interactable
 *                    tiles such as buildings.
 */
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.LevelObject.LevelObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author antho
 */
public class StaticLevelObject extends LevelObject{

    public StaticLevelObject(TextureRegion tex){
        super(tex, 0);
    }

    @Override
    protected void init() {
    }

    
    
}
