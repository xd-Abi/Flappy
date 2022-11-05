package net.xdabi.flappy.content.bird;

import net.xdabi.flappy.renderer.Camera;
import net.xdabi.flappy.renderer.Renderable;
import net.xdabi.flappy.renderer.image.Image;
import net.xdabi.flappy.renderer.pipeline.ShaderProgram;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;
import net.xdabi.flappy.util.ResourceLoader;

public class BirdShader extends ShaderProgram {

    public BirdShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("shaders/bird.vert"));
        addFragmentShader(ResourceLoader.loadShader("shaders/bird.frag"));
        compile();

        addUniform("modelMatrix");
        addUniform("viewMatrix");
        addUniform("sampler");
    }

    @Override
    public void updateUniforms(Renderable parent) {

        Renderable levelParent = (Renderable) parent.getParent();
        Camera camera = levelParent.getComponent(NodeComponentType.CAMERA);

        setUniform("modelMatrix", parent.getWorldTransform().getWorldMatrix());
        setUniform("viewMatrix", camera.getProjection());

        Image texture = parent.getComponent(NodeComponentType.TEXTURE);
        texture.noFilter();
        texture.bind();

        setUniform("sampler", 1);
    }
}
