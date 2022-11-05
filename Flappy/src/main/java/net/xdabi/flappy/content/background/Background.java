package net.xdabi.flappy.content.background;

import lombok.Getter;
import net.xdabi.flappy.content.bird.BirdRenderConfig;
import net.xdabi.flappy.content.bird.BirdShader;
import net.xdabi.flappy.kernel.Application;
import net.xdabi.flappy.math.Vec2f;
import net.xdabi.flappy.math.Vec3f;
import net.xdabi.flappy.renderer.RenderInfo;
import net.xdabi.flappy.renderer.Renderable;
import net.xdabi.flappy.renderer.image.Image;
import net.xdabi.flappy.renderer.model.Mesh;
import net.xdabi.flappy.renderer.model.Vertex;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;

public class Background extends Renderable {

    @Getter
    private int xScroll = 0;

    @Getter
    private int drawAmount = 0;

    public Background() {
        super();

        Mesh mesh = createMesh();
        RenderInfo renderInfo = new BackgroundRenderInfo(new BackgroundRenderConfig(), new BackgroundShader(), mesh);

        Image image = new Image("assets/bg.jpeg");

        addComponent(NodeComponentType.RENDER_INFO, renderInfo);
        addComponent(NodeComponentType.TEXTURE, image);
    }

    @Override
    public void update() {
        super.update();

        xScroll--;

        if (-xScroll % 300 == 0) {
            drawAmount++;
        }
    }

    private Mesh createMesh() {
        Mesh mesh = new Mesh();
        mesh.create(new Vertex[]{
                new Vertex(new Vec3f(-10, -10.0f * 9.0f / 16.0f, 0), new Vec2f(0, 1)),
                new Vertex(new Vec3f(-10, 10.0f * 9.0f / 16.0f, 0), new Vec2f(0, 0)),
                new Vertex(new Vec3f(0, 10.0f * 9.0f / 16.0f, 0), new Vec2f(1, 0)),
                new Vertex(new Vec3f(0, -10.0f * 9.0f / 16.0f, 0), new Vec2f(1, 1)),
        }, new int[]{
                0, 1, 2, 2, 3, 0
        });

        return mesh;
    }
}
