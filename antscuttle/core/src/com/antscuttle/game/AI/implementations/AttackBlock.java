
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;
import com.antscuttle.game.Level.LevelObject;

/**
 *
 * @author antho
 */
public class AttackBlock extends DecisionBlock{
    private Object target;
    private AttackType attackType;
    //private int duration = Integer.MAX_VALUE; // Attack as long as you can
    public enum AttackType { RANGED, MELEE }
    
    
    public AttackBlock(Object target, AttackType type){
        super();
        this.target = target;
        this.attackType = type;
        this.duration = Integer.MAX_VALUE;
    }
    
    
    @Override
    public void execute(Ant ant){
        int damageDone = 0;
        int damage = (attackType == AttackType.MELEE) ? ant.getMeleeDamage() : ant.getRangedDamage();
        DamageType damageType = (attackType == AttackType.MELEE) ? ant.getMeleeDamageType() : ant.getRangedDamageType();
        if(damageType == null)
            return;
        if(target instanceof LevelObject)
            damageDone = ((LevelObject)target).attack(damage, damageType);
        else if(target instanceof Ant)
            damageDone = ((Ant)target).attack(damage, damageType);
        // If damage is done, set executionResult to true in order to signal that we should still be attacking in a while loop
        if(damageDone > 0)
            super.execute(ant);
    }
}
