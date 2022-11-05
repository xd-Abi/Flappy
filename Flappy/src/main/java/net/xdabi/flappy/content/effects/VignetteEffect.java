package net.xdabi.flappy.content.effects;

import net.xdabi.flappy.renderer.RenderInfo;
import net.xdabi.flappy.renderer.Renderable;
import net.xdabi.flappy.renderer.model.Mesh;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;
import net.xdabi.flappy.util.MeshGenerator;

public class VignetteEffect extends Renderable {

    public VignetteEffect() {
        super();

        getWorldTransform().getTranslation().setZ(0.07f);
        Mesh mesh = MeshGenerator.createQuad();
        RenderInfo renderInfo = new RenderInfo(new VignetteEffectConfig(), new VignetteEffectShader(), mesh);

        addComponent(NodeComponentType.RENDER_INFO, renderInfo);
    }
}
