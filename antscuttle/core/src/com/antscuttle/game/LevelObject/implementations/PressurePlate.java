
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author antho
 */
public class PressurePlate extends InteractableLevelObject{

    private InteractableLevelObject affectedObject;
    private boolean isPressed;
    
    public PressurePlate(Texture tex, InteractableLevelObject o){
        super(tex, 0, 0);
        this.affectedObject = o;
    }
    
    @Override
    public boolean interact(Ant ant) {
        return affectedObject.interact(ant);
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