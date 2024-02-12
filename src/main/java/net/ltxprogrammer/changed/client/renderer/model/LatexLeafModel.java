package net.ltxprogrammer.changed.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ltxprogrammer.changed.Changed;
import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.entity.beast.LatexLeaf;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;

import java.util.List;

public class LatexLeafModel extends AdvancedHumanoidModel<LatexLeaf> implements AdvancedHumanoidModelInterface<LatexLeaf, LatexLeafModel> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Changed.modResource("latex_leaf"), "main");
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart Head;
    private final ModelPart Torso;
    private final ModelPart Tail;
    private final HumanoidAnimator<LatexLeaf, LatexLeafModel> animator;

    public LatexLeafModel(ModelPart root) {
        super(root);
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
        this.Head = root.getChild("Head");
        this.Torso = root.getChild("Torso");
        this.Tail = Torso.getChild("Tail");
        this.RightArm = root.getChild("RightArm");
        this.LeftArm = root.getChild("LeftArm");

        var tailPrimary = Tail.getChild("TailPrimary");
        var tailSecondary = tailPrimary.getChild("TailSecondary");
        var tailTertiary = tailSecondary.getChild("TailTertiary");

        var leftLowerLeg = LeftLeg.getChild("LeftLowerLeg");
        var leftFoot = leftLowerLeg.getChild("LeftFoot");
        var rightLowerLeg = RightLeg.getChild("RightLowerLeg");
        var rightFoot = rightLowerLeg.getChild("RightFoot");

        animator = HumanoidAnimator.of(this).hipOffset(-1.5f)
                .addPreset(AnimatorPresets.dragonLike(
                        Head, Torso, LeftArm, RightArm,
                        Tail, List.of(tailPrimary, tailSecondary, tailTertiary),
                        LeftLeg, leftLowerLeg, leftFoot, leftFoot.getChild("LeftPad"), RightLeg, rightLowerLeg, rightFoot, rightFoot.getChild("RightPad")));
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create(), PartPose.offset(-2.5F, 10.5F, 0.0F));

        PartDefinition RightThigh_r1 = RightLeg.addOrReplaceChild("RightThigh_r1", CubeListBuilder.create().texOffs(32, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition RightLowerLeg = RightLeg.addOrReplaceChild("RightLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

        PartDefinition RightCalf_r1 = RightLowerLeg.addOrReplaceChild("RightCalf_r1", CubeListBuilder.create().texOffs(32, 43).addBox(-1.99F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

        PartDefinition RightFoot = RightLowerLeg.addOrReplaceChild("RightFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

        PartDefinition RightArch_r1 = RightFoot.addOrReplaceChild("RightArch_r1", CubeListBuilder.create().texOffs(48, 45).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

        PartDefinition RightPad = RightFoot.addOrReplaceChild("RightPad", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 4.325F, -4.425F));

        PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create(), PartPose.offset(2.5F, 10.5F, 0.0F));

        PartDefinition LeftThigh_r1 = LeftLeg.addOrReplaceChild("LeftThigh_r1", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition LeftLowerLeg = LeftLeg.addOrReplaceChild("LeftLowerLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 6.375F, -3.45F));

        PartDefinition LeftCalf_r1 = LeftLowerLeg.addOrReplaceChild("LeftCalf_r1", CubeListBuilder.create().texOffs(16, 43).addBox(-2.01F, -0.125F, -2.9F, 4.0F, 6.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, -2.125F, 1.95F, 0.8727F, 0.0F, 0.0F));

        PartDefinition LeftFoot = LeftLowerLeg.addOrReplaceChild("LeftFoot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.8F, 7.175F));

        PartDefinition LeftArch_r1 = LeftFoot.addOrReplaceChild("LeftArch_r1", CubeListBuilder.create().texOffs(52, 0).addBox(-2.0F, -8.45F, -0.725F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.005F)), PartPose.offsetAndRotation(0.0F, 7.075F, -4.975F, -0.3491F, 0.0F, 0.0F));

        PartDefinition LeftPad = LeftFoot.addOrReplaceChild("LeftPad", CubeListBuilder.create().texOffs(44, 38).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 2.0F, 5.0F, CubeDeformation.NONE), PartPose.offset(0.0F, 4.325F, -4.425F));

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, CubeDeformation.NONE)
                .texOffs(44, 31).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, CubeDeformation.NONE)
                .texOffs(20, 16).addBox(-1.5F, -1.0F, -5.0F, 3.0F, 1.0F, 1.0F, CubeDeformation.NONE), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition TopLeaflet = Head.addOrReplaceChild("TopLeaflet", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -4.0F, 1.5F, 0.6109F, 0.0F, 0.0F));

        PartDefinition Leaftlet_r1 = TopLeaflet.addOrReplaceChild("Leaftlet_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -1.5F, -2.0F, 2.0F, 1.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, -4.5F, 0.5F, 0.0F, 0.0F, -0.7854F));

        PartDefinition Leaftlet_r2 = TopLeaflet.addOrReplaceChild("Leaftlet_r2", CubeListBuilder.create().texOffs(42, 54).addBox(0.0F, -1.5F, -2.0F, 2.0F, 1.0F, 4.0F, CubeDeformation.NONE)
                .texOffs(50, 30).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 6.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, -4.5F, 0.5F, 0.0F, 0.0F, 0.7854F));

        PartDefinition RightLeaflets = Head.addOrReplaceChild("RightLeaflets", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

        PartDefinition Leaflet_r1 = RightLeaflets.addOrReplaceChild("Leaflet_r1", CubeListBuilder.create().texOffs(37, 56).addBox(2.85F, -26.2F, 6.8F, 1.0F, 2.0F, 3.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, 0.3054F, -0.3491F, -0.2618F));

        PartDefinition Leaflet_r2 = RightLeaflets.addOrReplaceChild("Leaflet_r2", CubeListBuilder.create().texOffs(30, 53).addBox(-6.6F, -26.6F, 7.2F, 1.0F, 2.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, 0.1745F, -0.5236F, 0.1309F));

        PartDefinition Leaflet_r3 = RightLeaflets.addOrReplaceChild("Leaflet_r3", CubeListBuilder.create().texOffs(0, 55).addBox(8.0F, -22.5F, -27.0F, 2.0F, 6.0F, 3.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, -0.8727F, -0.1745F, -0.3927F));

        PartDefinition Leaflet_r4 = RightLeaflets.addOrReplaceChild("Leaflet_r4", CubeListBuilder.create().texOffs(60, 27).addBox(4.5F, -22.6F, -24.4F, 1.0F, 6.0F, 3.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, -0.8727F, -0.3491F, -0.3054F));

        PartDefinition LeftLeaflets = Head.addOrReplaceChild("LeftLeaflets", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

        PartDefinition Leaflet_r5 = LeftLeaflets.addOrReplaceChild("Leaflet_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-3.85F, -26.2F, 6.8F, 1.0F, 2.0F, 3.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, 0.3054F, 0.3491F, 0.2618F));

        PartDefinition Leaflet_r6 = LeftLeaflets.addOrReplaceChild("Leaflet_r6", CubeListBuilder.create().texOffs(20, 53).addBox(5.6F, -26.6F, 7.2F, 1.0F, 2.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, 0.1745F, 0.5236F, -0.1309F));

        PartDefinition Leaflet_r7 = LeftLeaflets.addOrReplaceChild("Leaflet_r7", CubeListBuilder.create().texOffs(10, 55).addBox(-10.0F, -22.5F, -27.0F, 2.0F, 6.0F, 3.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, -0.8727F, 0.1745F, 0.3927F));

        PartDefinition Leaflet_r8 = LeftLeaflets.addOrReplaceChild("Leaflet_r8", CubeListBuilder.create().texOffs(56, 18).addBox(-5.5F, -22.6F, -24.4F, 1.0F, 6.0F, 3.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 24.5F, 0.0F, -0.8727F, 0.3491F, 0.3054F));

        PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 10.5F, 0.0F));

        PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition TailBase_r1 = TailPrimary.addOrReplaceChild("TailBase_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -8.25F, 11.6F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.18F)), PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, 1.0036F, 0.0F, 0.0F));

        PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5F, 3.5F));

        PartDefinition TailBase_r2 = TailSecondary.addOrReplaceChild("TailBase_r2", CubeListBuilder.create().texOffs(40, 20).addBox(-2.0F, -1.75F, 12.6F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 14.5F, -3.5F, 1.1781F, 0.0F, 0.0F));

        PartDefinition TailTertiary = TailSecondary.addOrReplaceChild("TailTertiary", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 6.5F));

        PartDefinition TailBase_r3 = TailTertiary.addOrReplaceChild("TailBase_r3", CubeListBuilder.create().texOffs(36, 9).addBox(-2.0F, 9.0F, 10.3F, 4.0F, 7.0F, 4.0F, CubeDeformation.NONE), PartPose.offsetAndRotation(0.0F, 11.5F, -10.0F, 1.4835F, 0.0F, 0.0F));

        PartDefinition TailQuaternary = TailTertiary.addOrReplaceChild("TailQuaternary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 5.5F));

        PartDefinition TailBase_r4 = TailQuaternary.addOrReplaceChild("TailBase_r4", CubeListBuilder.create().texOffs(52, 9).addBox(-1.5F, 17.3F, 7.1F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.0F, 10.5F, -15.5F, 1.7017F, 0.0F, 0.0F));

        PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(24, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(-5.0F, 1.5F, 0.0F));

        PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE), PartPose.offset(5.0F, 1.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 96, 96);
    }

    @Override
    public void prepareMobModel(LatexLeaf p_102861_, float p_102862_, float p_102863_, float p_102864_) {
        this.prepareMobModel(animator, p_102861_, p_102862_, p_102863_, p_102864_);
    }

    public void setupHand() {
        animator.setupHand();
    }

    @Override
    public void setupAnim(LatexLeaf entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }

    public ModelPart getArm(HumanoidArm p_102852_) {
        return p_102852_ == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
    }

    public ModelPart getHead() {
        return this.Head;
    }

    public ModelPart getTorso() {
        return Torso;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        RightLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        Torso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public HumanoidAnimator<LatexLeaf, LatexLeafModel> getAnimator() {
        return animator;
    }
}
