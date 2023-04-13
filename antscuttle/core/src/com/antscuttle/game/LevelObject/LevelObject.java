package com.antscuttle.game.LevelObject;
import com.antscuttle.game.Ant.Ant;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class LevelObject extends Actor{
    // Fields
    protected TextureRegion texture;
    protected Sprite sprite;
    protected int defense;
    protected Vector2 pos, dim;

    // Constructor
    public LevelObject(TextureRegion texture, int defense) {
        
        this.texture = texture;
        this.dim = new Vector2();
        this.pos = new Vector2();
        this.dim.x = 16;
        this.dim.y = 16;
        this.defense = defense;
        if(texture != null){
            this.sprite = new Sprite(texture);
            sprite.setSize(dim.x, dim.y);
            sprite.setPosition(pos.x, pos.y);
        }
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
    public void setPos(float x, float y){
        pos.x = x;
        pos.y = y;
        if(sprite != null)
            sprite.setPosition(x, y);
        
    }
    public Vector2 getPos(){
        return pos;
    }
    protected abstract void init();
    public void update(float delta){}
    public void render(SpriteBatch batch){
        if(sprite != null)
            sprite.draw(batch);
    }
    
}
