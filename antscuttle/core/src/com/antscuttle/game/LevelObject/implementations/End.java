
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Anthony
 */
public class End extends InteractableLevelObject{

    public End() {
        super(null, 0, 0);
    }

    @Override
    public boolean interact(Ant ant, LevelData levelData) {
        levelData.setGameWon(true);
        levelData.setGameFinished(true);
        return true;
    }

    @Override
    public int receiveAttack(int damage, DamageType damageType) {
        return 0;
    }

    @Override
    protected void init() {
    }
    
}
