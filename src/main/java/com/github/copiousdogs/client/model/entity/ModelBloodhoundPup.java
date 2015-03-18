package com.github.copiousdogs.client.model.entity;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelBloodhoundPup extends ModelDog {

	public ModelBloodhoundPup() {
		
		super("bloodhound_pup");
		textureWidth = 64;
		textureHeight = 32;

		WolfHead = new ModelRenderer(this, 0, 0);
		WolfHead.addBox(-3F, -3F, -2F, 6, 7, 4);
		WolfHead.setRotationPoint(-1F, 14.5F, -7F);
		WolfHead.setTextureSize(64, 32);
		WolfHead.mirror = true;
		setRotation(WolfHead, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 32, 5);
		Body.addBox(-4F, -2F, -3F, 3, 8, 3);
		Body.setRotationPoint(1.5F, 17F, -4F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 1.570796F, 0F, 0F);
		Mane = new ModelRenderer(this, 17, 12);
		Mane.addBox(-4F, -3F, -3F, 3, 4, 1);
		Mane.setRotationPoint(1.5F, 18F, -3F);
		Mane.setTextureSize(64, 32);
		Mane.mirror = true;
		setRotation(Mane, 1.570796F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 0, 21);
		Leg1.addBox(-1F, 0F, -1F, 1, 4, 1);
		Leg1.setRotationPoint(-1.5F, 20F, 1F);
		Leg1.setTextureSize(64, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 0, 21);
		Leg2.addBox(-1F, 0F, -1F, 1, 4, 1);
		Leg2.setRotationPoint(0.5F, 20F, 1F);
		Leg2.setTextureSize(64, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 0, 21);
		Leg3.addBox(-1F, 0F, -1F, 1, 3, 1);
		Leg3.setRotationPoint(-1.5F, 21F, -4F);
		Leg3.setTextureSize(64, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 0, 21);
		Leg4.addBox(-1F, 0F, -1F, 1, 3, 1);
		Leg4.setRotationPoint(0.5F, 21F, -4F);
		Leg4.setTextureSize(64, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 0F);
		Tail = new ModelRenderer(this, 9, 22);
		Tail.addBox(-1F, 0F, -1F, 1, 4, 1);
		Tail.setRotationPoint(-0.5F, 17.2F, 1F);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, 1.799284F, 0F, 0F);
		Ear1 = new ModelRenderer(this, 18, 23);
		Ear1.addBox(0F, 0F, 0F, 1, 5, 3);
		Ear1.setRotationPoint(-5F, 11.5F, -8.5F);
		Ear1.setTextureSize(64, 32);
		Ear1.mirror = true;
		setRotation(Ear1, 0F, 0F, 0F);
		Ear2 = new ModelRenderer(this, 18, 23);
		Ear2.addBox(1F, -5F, 0F, 1, 5, 3);
		Ear2.setRotationPoint(1F, 16.5F, -8.5F);
		Ear2.setTextureSize(64, 32);
		Ear2.mirror = true;
		setRotation(Ear2, 0F, 0F, 0F);
		Nose = new ModelRenderer(this, 0, 12);
		Nose.addBox(-2F, 0F, -5F, 3, 5, 3);
		Nose.setRotationPoint(-0.5F, 14F, -7F);
		Nose.setTextureSize(64, 32);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(offsetX * f5, offsetY * f5, offsetZ * f5);

			GL11.glPushMatrix();
			{
				GL11.glTranslatef(WolfHead.rotationPointX * f5,
						WolfHead.rotationPointY * f5, WolfHead.rotationPointZ
								* f5);
				GL11.glRotatef((float) (headRotY * 180f / Math.PI), 0f, 1f, 0f);
				GL11.glRotatef((float) (headRotX * 180f / Math.PI), 1f, 0f, 0f);
				GL11.glRotatef((float) (headRotZ * 180f / Math.PI), 0f, 0f, 1f);

				GL11.glPushMatrix();
				{
					GL11.glTranslatef(-WolfHead.rotationPointX * f5,
							-WolfHead.rotationPointY * f5,
							-WolfHead.rotationPointZ * f5);

					WolfHead.render(f5);
					Ear1.render(f5);
					Ear2.render(f5);
					Nose.render(f5);
				}
				GL11.glPopMatrix();
			}
			GL11.glPopMatrix();
			Body.render(f5);
			Mane.render(f5);
			Leg1.render(f5);
			Leg2.render(f5);
			Leg3.render(f5);
			Leg4.render(f5);
			GL11.glPushMatrix();
			{
				GL11.glTranslatef(Tail.rotationPointX * f5, Tail.rotationPointY
						* f5, Tail.rotationPointZ * f5);
				GL11.glRotatef((float) (tailRotY * 180f / Math.PI), 0f, 1f, 0f);
				GL11.glRotatef((float) (tailRotX * 180f / Math.PI), 1f, 0f, 0f);
				GL11.glRotatef((float) (tailRotZ * 180f / Math.PI), 0f, 0f, 1f);

				GL11.glPushMatrix();
				GL11.glTranslatef(-Tail.rotationPointX * f5,
						-Tail.rotationPointY * f5, -Tail.rotationPointZ * f5);

				Tail.render(f5);
				GL11.glPopMatrix();
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
	}
}
