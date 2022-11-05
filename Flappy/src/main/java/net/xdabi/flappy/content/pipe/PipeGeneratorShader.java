package net.xdabi.flappy.content.pipe;

import net.xdabi.flappy.math.Transform;
import net.xdabi.flappy.renderer.Camera;
import net.xdabi.flappy.renderer.Renderable;
import net.xdabi.flappy.renderer.image.Image;
import net.xdabi.flappy.renderer.pipeline.ShaderProgram;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;
import net.xdabi.flappy.util.ResourceLoader;

public class PipeGeneratorShader extends ShaderProgram {

    public PipeGeneratorShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("shaders/pipe.vert"));
        addFragmentShader(ResourceLoader.loadShader("shaders/pipe.frag"));
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

    public void updateUniforms(PipeGenerator parent, int bgIndex) {
        Transform transform = parent.getChildren().get(bgIndex).getWorldTransform();
        setUniform("modelMatrix", transform.getWorldMatrix());
        setUniform("sampler", 1);
    }
}
