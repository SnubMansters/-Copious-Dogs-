package com.github.copiousdogs.server.entity.ai;

import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;

import com.github.copiousdogs.server.entity.EntityDogServer;

public class EntityAIOwnerHurtByTargetBOA extends EntityAIOwnerHurtByTarget
{
	EntityDogServer dog;
	
	public EntityAIOwnerHurtByTargetBOA(EntityDogServer dog)
	{
		super(dog);
		this.dog = dog;
	}

	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && dog.getAggressiveness() > 3;
	}
}
