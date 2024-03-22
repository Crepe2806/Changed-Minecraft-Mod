package net.ltxprogrammer.changed.client.renderer;

import net.ltxprogrammer.changed.Changed;
import net.ltxprogrammer.changed.client.renderer.layers.CustomEyesLayer;
import net.ltxprogrammer.changed.client.renderer.layers.LatexParticlesLayer;
import net.ltxprogrammer.changed.client.renderer.model.DarkLatexYufengModel;
import net.ltxprogrammer.changed.client.renderer.model.armor.ArmorLatexMaleWingedDragonModel;
import net.ltxprogrammer.changed.entity.beast.DarkLatexYufeng;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class DarkLatexYufengRenderer extends AdvancedHumanoidRenderer<DarkLatexYufeng, DarkLatexYufengModel, ArmorLatexMaleWingedDragonModel<DarkLatexYufeng>> {
    public DarkLatexYufengRenderer(EntityRendererProvider.Context context) {
        super(context, new DarkLatexYufengModel(context.bakeLayer(DarkLatexYufengModel.LAYER_LOCATION)),
                ArmorLatexMaleWingedDragonModel::new, ArmorLatexMaleWingedDragonModel.INNER_ARMOR, ArmorLatexMaleWingedDragonModel.OUTER_ARMOR, 0.5f);
        this.addLayer(new LatexParticlesLayer<>(this, getModel(), model::isPartNotMask));
        this.addLayer(new CustomEyesLayer<>(this, context.getModelSet(), CustomEyesLayer.fixedColor(Color3.parseHex("#242424")),
                CustomEyesLayer.fixedIfNotDarkLatexOverrideLeft(Color3.WHITE),
                CustomEyesLayer.fixedIfNotDarkLatexOverrideRight(Color3.WHITE)));
    }

    @Override
    public ResourceLocation getTextureLocation(DarkLatexYufeng p_114482_) {
        return Changed.modResource("textures/dark_latex_yufeng.png");
    }
}