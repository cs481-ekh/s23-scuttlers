/*
 * InteractableLevelObject: implementation of LevelObject for interactable
 *                    tiles such as doors.
 */
package com.antscuttle.game.LevelObject;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public abstract class InteractableLevelObject extends LevelObject{
    private int intelligenceRequirement;
    public InteractableLevelObject(Texture tex, int def, int intelligenceRequired){
        super(tex, def);
        this.intelligenceRequirement = intelligenceRequired;
    }
    public abstract boolean interact(Ant ant);
    public abstract int receiveAttack(int damage, DamageType damageType);
}
