package net.lomeli.ec.entity.render;

import org.lwjgl.opengl.GL11;

import net.lomeli.ec.entity.EntityFriendlyCreeper;
import net.lomeli.ec.entity.model.ModelFriendlyCreeper;
import net.lomeli.ec.lib.Strings;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFriendlyCreeper extends RenderLiving {
    private ModelBase creeperModel = new ModelFriendlyCreeper(2.0F);
    private static final ResourceLocation armoredCreeperTextures = new ResourceLocation("textures/entity/creeper/creeper_armor.png");

    public RenderFriendlyCreeper() {
        super(new ModelFriendlyCreeper(), 0.5F);
    }

    protected void updateCreeperScale(EntityFriendlyCreeper par1EntityCreeper, float par2) {
        float var4 = par1EntityCreeper.getCreeperFlashIntensity(par2);
        float var5 = 1.0F + MathHelper.sin(var4 * 100.0F) * var4 * 0.01F;

        if (var4 < 0.0F)
            var4 = 0.0F;

        if (var4 > 1.0F)
            var4 = 1.0F;

        var4 *= var4;
        var4 *= var4;
        float var6 = (1.0F + var4 * 0.4F) * var5;
        float var7 = (1.0F + var4 * 0.1F) / var5;
        GL11.glScalef(var6, var7, var6);
    }

    protected int updateCreeperColorMultiplier(EntityFriendlyCreeper par1EntityCreeper, float par2, float par3) {
        float var5 = par1EntityCreeper.getCreeperFlashIntensity(par3);

        if ((int) (var5 * 10.0F) % 2 == 0)
            return 0;
        else {
            int var6 = (int) (var5 * 0.2F * 255.0F);

            if (var6 < 0)
                var6 = 0;

            if (var6 > 255)
                var6 = 255;

            short var7 = 255;
            short var8 = 255;
            short var9 = 255;
            return var6 << 24 | var7 << 16 | var8 << 8 | var9;
        }
    }

    protected int func_77061_b(EntityFriendlyCreeper par1EntityCreeper, int par2, float par3) {
        return -1;
    }

    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2) {
        this.updateCreeperScale((EntityFriendlyCreeper) par1EntityLiving, par2);
    }

    protected int getColorMultiplier(EntityLiving par1EntityLiving, float par2, float par3) {
        return this.updateCreeperColorMultiplier((EntityFriendlyCreeper) par1EntityLiving, par2, par3);
    }

    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3) {
        return -1;
    }

    protected int inheritRenderPass(EntityLiving par1EntityLiving, int par2, float par3) {
        return this.func_77061_b((EntityFriendlyCreeper) par1EntityLiving, par2, par3);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation(Strings.MOD_ID.toLowerCase(), ((EntityFriendlyCreeper) entity).tamedTexture());
    }

}
