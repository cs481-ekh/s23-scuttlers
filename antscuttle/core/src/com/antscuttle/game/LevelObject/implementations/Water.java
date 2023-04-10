
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class Water extends InteractableLevelObject{
    private static final int drowningDamage = 5;
    
    public Water(Texture tex){
        super(tex, 0, 0);
    }
    
    @Override
    public boolean interact(Ant ant) {
        // Call when an ant is in water for drowning
        ant.receiveAttack(drowningDamage, DamageType.Water);
        return false;
    }

    @Override
    public int receiveAttack(int arg0, DamageType arg1) {
        return 0;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void update(float arg0) {
    }
    
}
