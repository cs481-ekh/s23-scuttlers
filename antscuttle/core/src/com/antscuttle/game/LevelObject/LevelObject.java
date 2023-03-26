package com.antscuttle.game.LevelObject;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.awt.Point;

public abstract class LevelObject extends Actor {
    // Fields
    protected Texture texture;
    protected Sprite sprite;
    protected int defense;
    protected Point pos, dim;

    // Constructor
    public LevelObject(Texture texture, int defense) {
        this.texture = texture;
        this.dim.x = texture.getWidth();
        this.dim.y = texture.getHeight();
        // TODO: Not sure which of these width/height things we'll need
//        setWidth(texture.getWidth());
//        setHeight(texture.getHeight());
        this.defense = defense;
       
    }
    
    // Methods
    
    public boolean collides(LevelObject o){
        Rectangle r1 = getArea();
        Rectangle r2 = o.getArea();
        return r1.overlaps(r2);
    }
    public boolean collides(Ant a){
        Rectangle r1 = getArea();
        Rectangle r2 = a.getArea();
        return r1.overlaps(r2);
    }
    protected Rectangle getArea(){
        return new Rectangle(pos.x, pos.y, dim.x, dim.y);
    }
    public void setPos(int x, int y){
        pos.x = x;
        pos.y = y;
    }
    protected abstract void init();
    protected abstract void update(float delta);
}
