package net.xdabi.flappy.engine.scenegraph;


import lombok.Getter;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RenderList {

    @Getter
    public Map<String, Renderable> objectList;

    public RenderList() {
        objectList = new LinkedHashMap<>();
    }

    public void add(Renderable object) {

        objectList.put(object.getId(), object);
    }

    public void remove(Renderable object) {

        objectList.remove(object.getId());
    }

    public void remove(String key) {

        objectList.remove(key);
    }

    public boolean contains(String id) {

        return objectList.containsKey(id);
    }

    public Renderable get(String key) {

        return objectList.get(key);
    }

    public Set<String> getKeySet() {

        return objectList.keySet();
    }

    public Set<Map.Entry<String, Renderable>> getEntrySet() {

        return objectList.entrySet();
    }

    public Collection<Renderable> getValues() {

        return objectList.values();
    }

}
