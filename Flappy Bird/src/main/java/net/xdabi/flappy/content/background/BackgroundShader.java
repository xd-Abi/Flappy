package net.xdabi.flappy.content.background;

import net.xdabi.flappy.config.StaticCamera;
import net.xdabi.flappy.engine.math.Matrix4f;
import net.xdabi.flappy.engine.math.Transform;
import net.xdabi.flappy.engine.math.Vec3f;
import net.xdabi.flappy.engine.renderer.Image;
import net.xdabi.flappy.engine.renderer.ShaderProgram;
import net.xdabi.flappy.engine.scenegraph.NodeComponentType;
import net.xdabi.flappy.engine.scenegraph.Renderable;
import net.xdabi.flappy.engine.utils.ResourceLoader;

public class BackgroundShader extends ShaderProgram {

    public BackgroundShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("shaders/bg.vert"));
        addFragmentShader(ResourceLoader.loadShader("shaders/bg.frag"));
        compile();

        addUniform("viewMatrix");
        addUniform("modelMatrix");
        addUniform("sampler");
    }

    @Override
    public void updateUniforms(Renderable parent) {
        Image texture = parent.getComponent(NodeComponentType.TEXTURE);
        texture.noFilter();
        texture.bind();
        setUniform("viewMatrix", StaticCamera.PROJECTION);
    }

    public void updateUniforms(Background background, int bgIndex) {
        Transform transform = background.getWorldTransform();
        transform.setTranslation(new Vec3f(bgIndex  * Background.MESH_WIDTH + background.getXScroll() * 0.008f, 0.0f, 0.1f));
        setUniform("modelMatrix", transform.getWorldMatrix());
        setUniform("sampler", 1);
    }
}