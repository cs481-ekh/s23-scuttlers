
package com.antscuttle.game.LevelObject.implementations;

import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Ant.BaseAnt.AnimationDirection;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelData;
import com.antscuttle.game.LevelObject.InteractableLevelObject;
import com.antscuttle.game.LevelObject.StaticLevelObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author antho
 */
public class Projectile extends StaticLevelObject{
    
    AnimationDirection direction;
    int speed;
    int damage;
    DamageType damageType;
    boolean dispose = false;
    
    public Projectile(TextureRegion tex, 
            AnimationDirection dir, 
            int speed,
            int damage,
            DamageType damageType){
        super(tex);
        this.direction = dir;
        this.speed = speed;
        this.damage = damage;
        this.damageType = damageType;
    }
    
    @Override
    public void update(float delta){
        
        switch(direction){
            case Left:
                setPos(pos.x-speed*delta, pos.y);
                break;
            case Up:
                setPos(pos.x, pos.y+speed*delta);
                break;
            case Right:
                setPos(pos.x+speed*delta, pos.y);
                break;
            default:
                setPos(pos.x, pos.y-speed*delta);
        }
    }
    public void attack(Object target, LevelData levelData){
        if(target instanceof Ant)
            ((Ant)target).receiveAttack(damage, damageType, levelData);
        else if (target instanceof InteractableLevelObject)
            ((InteractableLevelObject)target).receiveAttack(damage, damageType);
    }
    public void setToDispose(){
        dispose = true;
    }
    public void dispose(LevelData levelData){
        if(dispose){
            levelData.removeProjectile(this);
            levelData.removeFromAllObjects(this);
        }
    }
    public boolean shouldDispose(){
        return dispose;
    }
}
