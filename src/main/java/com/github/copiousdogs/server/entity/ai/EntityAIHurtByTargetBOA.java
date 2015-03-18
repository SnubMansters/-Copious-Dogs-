package com.github.copiousdogs.server.entity.ai;

import net.minecraft.entity.ai.EntityAIHurtByTarget;

import com.github.copiousdogs.server.entity.EntityDogServer;

public class EntityAIHurtByTargetBOA extends EntityAIHurtByTarget
{
	EntityDogServer dog;
	
	public EntityAIHurtByTargetBOA(EntityDogServer dog, boolean par0)
	{
		super(dog, par0);
		this.dog = dog;
	}

	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && dog.getAggressiveness() >= 6;
	}
}
