
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author antho
 */
public class Water extends InteractableLevelObject{
    private static final int drowningDamage = 2;
    
    public Water(TextureRegion tex){
        super(tex, 0, 0);
    }
    
    @Override
    public boolean interact(Ant ant, LevelData levelData) {
        // Call when an ant is in water for drowning
        ant.receiveAttack(drowningDamage, DamageType.WATER);
        return false;
    }

    @Override
    public int receiveAttack(int arg0, DamageType arg1) {
        return 0;
    }

    @Override
    protected void init() {
    }

    
    
}
