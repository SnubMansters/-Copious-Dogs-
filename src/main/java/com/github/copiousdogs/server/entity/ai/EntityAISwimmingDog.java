package com.github.copiousdogs.server.entity.ai;

import net.minecraft.entity.ai.EntityAISwimming;

import com.github.copiousdogs.server.entity.EntityDogServer;


public class EntityAISwimmingDog extends EntityAISwimming {

	private EntityDogServer theEntity;
	
	public EntityAISwimmingDog(EntityDogServer dog) {
		
		super(dog);
		theEntity = dog;
	}
	
	@Override
	public boolean shouldExecute() {
		
		boolean b = super.shouldExecute();
		System.out.println(b);
		return b;
	}
	
	public void updateTask()
    {
		System.out.println(true);
        this.theEntity.getJumpHelper().setJumping();
    }
}
