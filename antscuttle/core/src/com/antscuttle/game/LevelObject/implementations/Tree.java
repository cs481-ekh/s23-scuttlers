
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class Tree extends InteractableLevelObject{
    private static final int def = 10;
    private static final int intelligenceReq = 1;
    public Tree(Texture tex){
        super(tex, def, intelligenceReq);
    }
    @Override
    public boolean interact(Ant arg0) {
        return false;
    }

    @Override
    public int receiveAttack(int dam, DamageType damType) {
        if(dam < def)
            return 0;
        // TODO: destruct door
        update(Gdx.graphics.getDeltaTime());
        return dam;
    }

    @Override
    protected void init() {
    }

    @Override
    protected void update(float arg0) {
    }
    
}
