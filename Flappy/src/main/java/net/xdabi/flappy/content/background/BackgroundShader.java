package net.xdabi.flappy.content.background;

import net.xdabi.flappy.math.Transform;
import net.xdabi.flappy.math.Vec3f;
import net.xdabi.flappy.renderer.Camera;
import net.xdabi.flappy.renderer.Renderable;
import net.xdabi.flappy.renderer.image.Image;
import net.xdabi.flappy.renderer.pipeline.ShaderProgram;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;
import net.xdabi.flappy.util.ResourceLoader;

public class BackgroundShader extends ShaderProgram {

    public BackgroundShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("shaders/bg.vert"));
        addFragmentShader(ResourceLoader.loadShader("shaders/bg.frag"));
        compile();

        addUniform("modelMatrix");
        addUniform("viewMatrix");
        addUniform("sampler");
    }

    @Override
    public void updateUniforms(Renderable parent) {

        Renderable levelParent = (Renderable) parent.getParent();
        Camera camera = levelParent.getComponent(NodeComponentType.CAMERA);

        setUniform("viewMatrix", camera.getProjection());

        Image texture = parent.getComponent(NodeComponentType.TEXTURE);
        texture.noFilter();
        texture.bind();
    }

    public void updateUniforms(Background background, int bgIndex) {
        Transform transform = background.getWorldTransform();
        transform.setTranslation(new Vec3f(bgIndex * 10 + background.getXScroll() * 0.008f, 0.0f, 0.1f));
        setUniform("modelMatrix", transform.getWorldMatrix());
        setUniform("sampler", 1);

    }
}
