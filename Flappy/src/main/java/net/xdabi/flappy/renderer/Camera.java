package net.xdabi.flappy.renderer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.xdabi.flappy.renderer.scenegraph.NodeComponent;
import net.xdabi.flappy.math.Matrix4f;

@AllArgsConstructor
public class Camera extends NodeComponent {

    @Getter
    @Setter
    private Matrix4f projection;

    public Camera() {
        projection = new Matrix4f();
    }
}
