package net.xdabi.flappy.content.effects;

import net.xdabi.flappy.FlappyApplication;
import net.xdabi.flappy.config.StaticCamera;
import net.xdabi.flappy.engine.device.Window;
import net.xdabi.flappy.engine.math.Vec2f;
import net.xdabi.flappy.engine.renderer.ShaderProgram;
import net.xdabi.flappy.engine.scenegraph.Renderable;
import net.xdabi.flappy.engine.utils.ResourceLoader;

public class VignetteEffectShader extends ShaderProgram {

    public VignetteEffectShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("shaders/effects/vignette.vert"));
        addFragmentShader(ResourceLoader.loadShader("shaders/effects/vignette.frag"));
        compile();

        addUniform("modelMatrix");
        addUniform("viewMatrix");
        addUniform("resolution");
        addUniform("color");
    }

    @Override
    public void updateUniforms(Renderable parent) {
        Window window = FlappyApplication.getInstance().getWindow();
        setUniform("resolution", new Vec2f(window.getWidth(), window.getHeight()));
        setUniform("modelMatrix", parent.getWorldTransform().getWorldMatrix());
        setUniform("viewMatrix", StaticCamera.PROJECTION);
        setUniform("color", ((VignetteEffect)parent).getColor());
    }
}