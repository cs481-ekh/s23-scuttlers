/*
 * InteractableLevelObject: implementation of LevelObject for interactable
 *                    tiles such as doors, and attackable tiles.
 */
package com.antscuttle.game.LevelObject;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author antho
 */
public abstract class InteractableLevelObject extends LevelObject{
    private final int intelligenceRequirement;
    public InteractableLevelObject(TextureRegion tex, int def, int intelligenceRequired){
        super(tex, def);
        this.intelligenceRequirement = intelligenceRequired;
    }
    public abstract boolean interact(Ant ant, LevelData levelData);
    public abstract int receiveAttack(int damage, DamageType damageType);
}
