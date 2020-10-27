package com.suicidebomber.element;

import com.badlogic.gdx.utils.Array;


public class Node {

    protected Array<Node> child = new Array<>();
    protected Node parent = null;

    // ## Core method

    public void _create() {
        create();
        for (Node children : child) {
            children._create();
        }
    }

    public void _render() {
        render();
        for (Node children : child) {
            children._render();
        }
    }

    public void _dispose() {
        dispose();
        for (Node children : child) {
            children._dispose();
        }
    }

    // ## Implement method

    public void addChild(Node children) {
        child.add(children);
        children.parent = this;
    }

    public void deleteChild(Node children) {
        removeChild(children);
        children._dispose();
    }

    public void removeChild(Node children) {
        child.removeValue(children, true);
        children.parent = null;
    }

    public void free() {
        if (parent != null) {
            parent.removeChild(this);
        }
    }

    public void create() {

    }

    public void render() {

    }

    public void dispose() {

    }
}
