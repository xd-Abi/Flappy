package net.xdabi.flappy.content.background;

import lombok.Getter;
import net.xdabi.flappy.engine.math.Vec3f;
import net.xdabi.flappy.engine.renderer.Image;
import net.xdabi.flappy.engine.renderer.Mesh;
import net.xdabi.flappy.engine.renderer.MeshBuffer;
import net.xdabi.flappy.engine.renderer.RenderInfo;
import net.xdabi.flappy.engine.scenegraph.NodeComponentType;
import net.xdabi.flappy.engine.scenegraph.Renderable;
import net.xdabi.flappy.engine.utils.MeshGenerator;

public class Background extends Renderable {

    public static final int MAX_DRAW_INDEX = 3;
    public static final float SCROLL_SPEED = 1f;

    public static final float MESH_WIDTH = 10f;

    @Getter
    private float xScroll = 0;

    public Background() {
        super();

        Mesh mesh = MeshGenerator.createRectangle(MESH_WIDTH, 20f);
        MeshBuffer meshBuffer = new MeshBuffer(mesh);
        RenderInfo renderInfo = new BackgroundRenderInfo(new BackgroundRenderConfig(), new BackgroundShader(), meshBuffer);

        Image image = new Image("images/bg.jpeg");

        addComponent(NodeComponentType.RENDER_INFO, renderInfo);
        addComponent(NodeComponentType.TEXTURE, image);
    }

    @Override
    public void update() {
        super.update();

        if (xScroll < -1200) {
            xScroll = 0;
        }

        xScroll -= SCROLL_SPEED;
    }
}
