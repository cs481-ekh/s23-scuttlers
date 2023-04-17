
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
public class Lava extends InteractableLevelObject{
    private static final int damage = 10;
    private int cooldown = 40;
    private int counter = 0;
    public Lava(TextureRegion tex){
        super(tex, 0, 0);
    }

    @Override
    protected void init() {
    }

    

    @Override
    public boolean interact(Ant ant, LevelData levelData) {
        if(++counter == cooldown){ 
            counter = 0;
            ant.receiveAttack(damage, DamageType.Fire, levelData);
        }
        return false;
    }

    @Override
    public int receiveAttack(int arg0, DamageType arg1) {
        return 0;
    }
    
}
