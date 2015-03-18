package com.github.copiousdogs.server.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;

import com.github.copiousdogs.server.entity.EntityDogServer;

public class EntityAIReturnToOwner extends EntityAIBase {

	private EntityDogServer dog;
	private float speed;
	
	public EntityAIReturnToOwner(EntityDogServer dog, float speed) {
		
		this.dog = dog;
		this.speed = speed;
	}

	@Override
	public boolean shouldExecute() {
		
		if (dog.getOwner() != null && dog.isWhistled()) {
			
			System.out.println("true");
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean continueExecuting() {
		
		if (dog.getOwner() != null && dog.isWhistled() && !dog.getNavigator().noPath()) {
			
			System.out.println(true);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void startExecuting() {
		
		dog.getNavigator().setPath(dog.getNavigator().getPathToEntityLiving(dog.getOwner()), speed);
	}
	
	@Override
	public void updateTask() {

		dog.getNavigator().setPath(dog.getNavigator().getPathToEntityLiving(dog.getOwner()), speed);	
	}
	
	@Override
	public void resetTask() {
		
		dog.setWhistled(false);
	}
}
