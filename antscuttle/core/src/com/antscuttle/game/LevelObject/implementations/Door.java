
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author antho
 */
public class Door extends InteractableLevelObject{
    private static final int intelligenceReq = 5;
    private static final int def = 10;
    private boolean isOpen;
    
    public Door(TextureRegion tex){
        super(tex, def, intelligenceReq);
        isOpen = false;
    }
    @Override
    public boolean interact(Ant ant) {
        if(ant.getIntelligence() < intelligenceReq)
            return false;
        isOpen = !isOpen;
        update(Gdx.graphics.getDeltaTime());
        return true;
        
    }

    @Override
    public int receiveAttack(int dam, DamageType damType) {
        if(dam < defense)
            return 0;
        //TODO: delete this object because door breaks
        return dam;
    }

    @Override
    protected void init() {
    }

    
    
}
