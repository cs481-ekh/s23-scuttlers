
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author antho
 */
public class PressurePlate extends InteractableLevelObject{

    private InteractableLevelObject affectedObject;
    private boolean isPressed;
    private TextureRegion anim[];
    private int pressedTime;
    private boolean triggered = false;
    
    public PressurePlate(TextureRegion tex[], InteractableLevelObject o){
        super(tex[0], 0, 0);
        this.anim = tex; 
        this.affectedObject = o;
        isPressed = false;
        pressedTime = 0;
    }
    
    @Override
    public boolean interact(Ant ant, LevelData levelData) {
        if(!isPressed && !triggered){
            //affectedObject.interact(ant, levelData);
            isPressed = triggered = true;
            sprite.setRegion(anim[1]);
            if(affectedObject != null)
                affectedObject.interact(ant, levelData);
            if(triggered){
                levelData.removeInteractableObject(this);
            }
        }
        return true;
    }

    @Override
    public int receiveAttack(int arg0, DamageType arg1) {
        return 0;
    }

    @Override
    protected void init() {
    }
    @Override
    public void update(float delta){
        super.update(delta);
        if(isPressed){
            if(pressedTime++ == 40){
                pressedTime = 0;
                isPressed = false;
                sprite.setRegion(anim[0]);
            }
        }
    }
}
