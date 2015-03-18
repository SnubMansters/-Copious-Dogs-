package com.github.copiousdogs.server.entity.ai;

import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;

import com.github.copiousdogs.server.entity.EntityDogServer;

public class EntityAIOwnerHurtTargetBOA extends EntityAIOwnerHurtTarget
{
	EntityDogServer dog;
	
	public EntityAIOwnerHurtTargetBOA(EntityDogServer dog)
	{
		super(dog);
		this.dog = dog;
	}
	
	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && dog.getAggressiveness() >= 6;
	}
}
