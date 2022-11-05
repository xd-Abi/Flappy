package net.xdabi.flappy.content.background;

import lombok.Getter;

import net.xdabi.flappy.renderer.RenderInfo;
import net.xdabi.flappy.renderer.Renderable;
import net.xdabi.flappy.renderer.image.Image;
import net.xdabi.flappy.renderer.model.Mesh;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;
import net.xdabi.flappy.util.MeshGenerator;

public class Background extends Renderable {

    public static final int MAX_DRAW_INDEX = 3;

    @Getter
    private int xScroll = 0;

    public Background() {
        super();

        Mesh mesh = MeshGenerator.createRectangle(10, 10 * 9.0f / 16.0f);
        RenderInfo renderInfo = new BackgroundRenderInfo(new BackgroundRenderConfig(), new BackgroundShader(), mesh);

        Image image = new Image("assets/bg.jpeg");

        addComponent(NodeComponentType.RENDER_INFO, renderInfo);
        addComponent(NodeComponentType.TEXTURE, image);
    }

    @Override
    public void update() {
        super.update();

        if (xScroll == -1250) {
            xScroll = 0;
        }

        xScroll--;
    }
}
