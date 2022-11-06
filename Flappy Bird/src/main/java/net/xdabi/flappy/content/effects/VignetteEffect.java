package net.xdabi.flappy.content.effects;

import net.xdabi.flappy.engine.math.Vec2f;
import net.xdabi.flappy.engine.math.Vec3f;
import net.xdabi.flappy.engine.renderer.Mesh;
import net.xdabi.flappy.engine.renderer.MeshBuffer;
import net.xdabi.flappy.engine.renderer.RenderInfo;
import net.xdabi.flappy.engine.renderer.Vertex;
import net.xdabi.flappy.engine.scenegraph.NodeComponentType;
import net.xdabi.flappy.engine.scenegraph.Renderable;

public class VignetteEffect extends Renderable {

    public VignetteEffect() {
        super();

        getWorldTransform().getTranslation().setZ(0.07f);
        Mesh mesh = new Mesh(new Vertex[]{
                new Vertex(new Vec3f(-10f, 10f, 0), new Vec2f(0, 0)),
                new Vertex(new Vec3f(-10f, -10f, 0), new Vec2f(0, 1)),
                new Vertex(new Vec3f(10f, -10f, 0), new Vec2f(1, 1)),
                new Vertex(new Vec3f(10f, 10f, 0), new Vec2f(1, 0)),
        }, new int[]{
                0, 1, 3, 3, 1, 2
        });

        MeshBuffer meshBuffer = new MeshBuffer(mesh);
        RenderInfo renderInfo = new RenderInfo(new VignetteEffectRenderConfig(), new VignetteEffectShader(), meshBuffer);


        addComponent(NodeComponentType.RENDER_INFO, renderInfo);
    }

}
