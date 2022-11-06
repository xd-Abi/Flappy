package net.xdabi.flappy.content.pipe;

import net.xdabi.flappy.config.StaticCamera;
import net.xdabi.flappy.content.Level;
import net.xdabi.flappy.engine.math.Matrix4f;
import net.xdabi.flappy.engine.math.Transform;
import net.xdabi.flappy.engine.math.Vec3f;
import net.xdabi.flappy.engine.renderer.Image;
import net.xdabi.flappy.engine.renderer.ShaderProgram;
import net.xdabi.flappy.engine.scenegraph.NodeComponentType;
import net.xdabi.flappy.engine.scenegraph.Renderable;
import net.xdabi.flappy.engine.utils.ResourceLoader;

public class PipeShader extends ShaderProgram {

    public PipeShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("shaders/pipe.vert"));
        addFragmentShader(ResourceLoader.loadShader("shaders/pipe.frag"));
        compile();

        addUniform("modelMatrix");
        addUniform("viewMatrix");
        addUniform("sampler");
        addUniform("top");
    }

    @Override
    public void updateUniforms(Renderable parent) {
        setUniform("viewMatrix", StaticCamera.PROJECTION);
        Image texture = parent.getComponent(NodeComponentType.TEXTURE);
        texture.noFilter();
        texture.bind();

    }

    public void updateUniforms(PipeGenerator parent, int pipeIndex) {
        Transform transform = parent.getChildren().get(pipeIndex).getWorldTransform();
        setUniform("modelMatrix", transform.getWorldMatrix());
        setUniform("sampler", 1);
        setUniform("top", pipeIndex % 2 == 0);
    }
}
