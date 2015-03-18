package com.github.copiousdogs.server.entity.ai;

import net.minecraft.entity.ai.EntityAITargetNonTamed;

import com.github.copiousdogs.server.entity.EntityDogServer;

public class EntityAITargetNonTamedBOA extends EntityAITargetNonTamed
{
	EntityDogServer dog;
	
	public EntityAITargetNonTamedBOA(EntityDogServer dog,
			Class p_i1666_2_, int p_i1666_3_, boolean p_i1666_4_)
	{
		super(dog, p_i1666_2_, p_i1666_3_, p_i1666_4_);
		this.dog = dog;
	}
	
	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && dog.getAggressiveness() >= 8;
	}
}
