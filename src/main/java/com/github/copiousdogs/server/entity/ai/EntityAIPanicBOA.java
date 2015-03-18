package com.github.copiousdogs.server.entity.ai;

import net.minecraft.entity.ai.EntityAIPanic;

import com.github.copiousdogs.server.entity.EntityDogServer;

public class EntityAIPanicBOA extends EntityAIPanic {

	EntityDogServer dog;
	
	public EntityAIPanicBOA(EntityDogServer p_i1645_1_, double p_i1645_2_) {
		
		super(p_i1645_1_, p_i1645_2_);
		this.dog = p_i1645_1_;
	}

	@Override
	public boolean shouldExecute() {
		
		return super.shouldExecute() && dog.getAggressiveness() < 6 && !dog.isTamed();
	}
}
