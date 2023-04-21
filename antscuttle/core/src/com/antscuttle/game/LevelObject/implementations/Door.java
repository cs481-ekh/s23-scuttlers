
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.antscuttle.game.Util.GraphUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;

/**
 *
 * @author antho
 */
public class Door extends InteractableLevelObject{
    private static final int intelligenceReq = 5;
    private static final int def = 100;
    private boolean isOpen;
    private TextureRegion closedTex, openTex;
    
    public Door(TextureRegion closedTex, TextureRegion openTex){
        super(closedTex, def, intelligenceReq);
        isOpen = false;
        this.closedTex = closedTex;
        this.openTex = openTex;
    }
    @Override
    public boolean interact(Ant ant, LevelData levelData) {
        if(ant.getIntelligence() < intelligenceReq)
            return false;
        isOpen = !isOpen;
        Point p = new Point((int)pos.x/16, (int)pos.y/16);
        // Adjust collidability
        if(isOpen){
            levelData.removeCollidableObject(this);
            levelData.addToGraphs(this);
            texture = openTex;
            sprite.setRegion(texture);
            levelData.removeAttackableObject(this);
            levelData.removeInteractableObject(this);
        }
        else{
            levelData.addCollidableObject(this);
            levelData.removeFromGraphs(this);
            texture = closedTex;
            sprite.setRegion(texture);
        }
        
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
