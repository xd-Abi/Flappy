package net.xdabi.flappy.engine.scenegraph;

import lombok.Getter;
import lombok.Setter;
import net.xdabi.flappy.engine.math.Transform;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Node {

    @Getter
    protected String id;

    @Getter
    @Setter
    private Node parent;

    @Getter
    private List<Node> children;

    @Getter
    @Setter
    private Transform worldTransform;

    public Node() {
        id = UUID.randomUUID().toString();
        parent = null;
        children = new ArrayList<>();
        worldTransform = new Transform();
    }

    public void addChild(Node child) {
        child.setParent(this);
        children.add(child);
    }

    public void removeChild(Node child) {
        child.setParent(null);
        children.remove(child);
    }

    public void update() {
        children.forEach(Node::update);
    }

    public void render() {
        children.forEach(Node::render);
    }
}