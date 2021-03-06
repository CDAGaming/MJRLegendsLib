package com.mjr.mjrlegendslib.client.model;

import java.util.List;

import javax.vecmath.Matrix4f;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;

import org.apache.commons.lang3.tuple.Pair;

/*
 * Class from Galacticraft Core
 * Credit micdoodle8, radfast
 */

@SuppressWarnings("deprecation")
public abstract class ModelTransformWrapper implements IPerspectiveAwareModel {
	private final IBakedModel iBakedModel;

	public ModelTransformWrapper(IBakedModel i_modelToWrap) {
		this.iBakedModel = i_modelToWrap;
	}

	protected abstract Matrix4f getTransformForPerspective(TransformType cameraTransformType);

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
		Matrix4f matrix4f = getTransformForPerspective(cameraTransformType);

		if (matrix4f == null) {
			return Pair.of(this, TRSRTransformation.blockCornerToCenter(new TRSRTransformation(ItemTransformVec3f.DEFAULT)).getMatrix());
		}

		return Pair.of(this, matrix4f);
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		return iBakedModel.getQuads(state, side, rand);
	}

	@Override
	public ItemOverrideList getOverrides() {
		return iBakedModel.getOverrides();
	}

	@Override
	public boolean isAmbientOcclusion() {
		return iBakedModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return iBakedModel.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return iBakedModel.isBuiltInRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return iBakedModel.getParticleTexture();
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}
}
