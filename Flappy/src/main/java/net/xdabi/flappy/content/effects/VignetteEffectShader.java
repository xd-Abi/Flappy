package net.xdabi.flappy.content.effects;

import net.xdabi.flappy.kernel.Application;
import net.xdabi.flappy.kernel.Window;
import net.xdabi.flappy.math.Vec2f;
import net.xdabi.flappy.math.Vec3f;
import net.xdabi.flappy.renderer.Camera;
import net.xdabi.flappy.renderer.Renderable;
import net.xdabi.flappy.renderer.image.Image;
import net.xdabi.flappy.renderer.pipeline.ShaderProgram;
import net.xdabi.flappy.renderer.scenegraph.NodeComponentType;
import net.xdabi.flappy.util.ResourceLoader;

public class VignetteEffectShader extends ShaderProgram {

    float time = 0;

    public VignetteEffectShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("shaders/effects/vignette.vert"));
        addFragmentShader(ResourceLoader.loadShader("shaders/effects/vignette.frag"));
        compile();
        addUniform("resolution");
        addUniform("modelMatrix");
    }

    @Override
    public void updateUniforms(Renderable parent) {
        Window window = Application.getInstance().getWindow();
        setUniform("resolution", new Vec2f(window.getWidth(), window.getHeight()));
        setUniform("modelMatrix", parent.getWorldTransform().getWorldMatrix());
    }
}
