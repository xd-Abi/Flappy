package net.xdabi.flappy.content.bird;

import net.xdabi.flappy.config.StaticCamera;
import net.xdabi.flappy.engine.renderer.Image;
import net.xdabi.flappy.engine.renderer.ShaderProgram;
import net.xdabi.flappy.engine.scenegraph.NodeComponentType;
import net.xdabi.flappy.engine.scenegraph.Renderable;
import net.xdabi.flappy.engine.utils.ResourceLoader;

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

        setUniform("modelMatrix", parent.getWorldTransform().getWorldMatrix());
        setUniform("viewMatrix", StaticCamera.PROJECTION);

        Image texture = parent.getComponent(NodeComponentType.TEXTURE);
        texture.noFilter();
        texture.bind();

        setUniform("sampler", 1);
    }
}