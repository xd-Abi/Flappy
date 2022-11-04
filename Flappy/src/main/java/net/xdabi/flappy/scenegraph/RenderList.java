package net.xdabi.flappy.scenegraph;

import lombok.Getter;
import lombok.Setter;
import net.xdabi.flappy.kernel.Input;
import net.xdabi.flappy.math.Matrix4f;

import java.util.ArrayList;

public class RenderList {

    @Getter
    @Setter
    private ArrayList<Renderable> renderables;

    public RenderList() {
        renderables = new ArrayList<>();
    }

    public void add(Renderable renderable) {
        renderables.add(renderable);
    }

    public void remove(Renderable renderable) {
        renderables.remove(renderable);
    }

    public void update(Input input) {
        for (Renderable renderable : renderables) {
            renderable.update(input);
        }
    }

    public void render(Matrix4f worldMatrix) {
        for (Renderable renderable : renderables) {
            renderable.render(worldMatrix);
        }
    }

    public void delete() {
        for (Renderable renderable : renderables) {
            renderable.delete();
        }
    }
}
