/*
 * StaticLevelObject: implementation of LevelObject for non-interactable
 *                    tiles such as buildings.
 */
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.LevelObject.LevelObject;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class StaticLevelObject extends LevelObject{

    public StaticLevelObject(Texture tex){
        super(tex, 0);
    }

    @Override
    protected void init() {
    }

    @Override
    protected void update(float arg0) {
    }
    
}
