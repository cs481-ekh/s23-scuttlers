
package com.antscuttle.game.AI.implementations;

import com.antscuttle.game.AI.BlockOptions;
import com.antscuttle.game.AI.DecisionBlock;
import com.antscuttle.game.AI.options.AttackOptions;
import com.antscuttle.game.Ant.Ant;
import com.antscuttle.game.Damage.DamageType;

/**
 *
 * @author antho
 */
public class AttackBlock extends DecisionBlock{
    
    public AttackBlock(AttackOptions options){
        super(options);
        this.duration = Integer.MAX_VALUE;
    }
    
    
    @Override
    public void execute(Ant ant){
        String attackType = options.getFirstOptionChoice();
        int damageDone = 0;
        int damage = (attackType.equals("Melee")) ? ant.getMeleeDamage() : ant.getRangedDamage();
        DamageType damageType = (attackType.equals("Melee")) ? ant.getMeleeDamageType() : ant.getRangedDamageType();
        if(damageType == null)
            return;
        // if(target instanceof LevelObject)
        //     damageDone = ((LevelObject)target).attack(damage, damageType);
        // else if(target instanceof Ant)
        //     damageDone = ((Ant)target).attack(damage, damageType);
        
        // Set Excecution result:
        // We may need 3 indicators: false for not done, true for done, null for 
        // damage was taken but we're not done attacking (in which case we
        // should use 'Boolean' instead of 'boolean'
        if(damageDone > 0)
            super.execute(ant);
    }
    
    public static Class<? extends BlockOptions> getOptionsClass(){
        return AttackOptions.class;
    }
    

}
