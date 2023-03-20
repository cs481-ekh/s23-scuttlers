package com.antscuttle.game.Level;
import com.antscuttle.game.Damage.DamageType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class LevelObject extends Actor {
    // Fields
    protected Texture texture;
    protected int defense;

    // Constructor
    public LevelObject(Texture texture, int defense) {
        this.texture = texture;
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        this.defense = defense;
    }

    // Methods
    public abstract int receiveAttack(int damage, DamageType damageType);

    protected abstract void init();
    protected abstract void update(float delta);
}
