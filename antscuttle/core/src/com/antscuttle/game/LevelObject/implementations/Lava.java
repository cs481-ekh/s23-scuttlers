
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class Lava extends InteractableLevelObject{
    private static final int damage = 10;
    
    public Lava(Texture tex){
        super(tex, 0, 0);
    }

    @Override
    protected void init() {
    }

    @Override
    protected void update(float arg0) {
    }

    @Override
    public boolean interact(Ant ant) {
        ant.receiveAttack(damage, DamageType.FIRE);
        return false;
    }

    @Override
    public int receiveAttack(int arg0, DamageType arg1) {
        return 0;
    }
    
}
