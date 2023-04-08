
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.LevelObject;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author antho
 */
public class Wall extends InteractableLevelObject{

    public Wall(TextureRegion texture, int defense){
        super(texture, defense, 0);
    }
    @Override
    public int receiveAttack(int damage, DamageType damageType) {
        if( damageType == DamageType.EXPLOSIVE && damage > defense){
            //TODO: destroy this object
            return damage;
        }
        return 0;
    }

    @Override
    protected void init() {
        
    }

    

    @Override
    public boolean interact(Ant ant, LevelData levelData) {
        return false;
    }
    
}
