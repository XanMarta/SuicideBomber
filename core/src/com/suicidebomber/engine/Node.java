package com.suicidebomber.engine;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;

// Signal: safefree

public class Node {

    protected ArrayList<Node> children = new ArrayList<>();
    protected HashMap<String, SignalPack> signals = new HashMap<>();
    protected Node parent = null;
    protected ArrayList<Node> freechild = new ArrayList<>();
    private boolean isRender = false;
    public String name = "";
    public boolean isSafeFree = false;      // Emit signal 'safefree' after render

    public Node() {
        name = "" + hashCode();
    }

    // ## Core method

//    public void _create() {
//        create();
//    }

    public void _render() {
        isRender = true;
        render();
        for (Node child : children) {
            child._render();
        }
        isRender = false;
        if (freechild.size() > 0) {
            for (Node node : freechild) {
                node.free();
            }
            freechild.clear();
        }
        if (isSafeFree) {               // For scene
            emit_signal("safefree");
        }
    }

//    public void _dispose() {
//        dispose();
//        for (Node child : children) {
//            child._dispose();
//        }
//    }

    // ## Node method

    public void addChild(Node child) {
        children.add(child);
        child.parent = this;
        child.create();
        System.out.println("Add child: " + name + " - " + child.name);
    }

    public void deleteChild(Node child) { // Delete and free
        if (children.contains(child)) {
            child.free();
        }
    }

    public void removeChild(Node child) { // Only remove, doesn't free
        children.remove(child);
        child.parent = null;
    }

    public Node getParent() {
        return parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public Node getChild(String name) {
        for (Node child : children) {
            if (child.name.equals(name)) {
                return child;
            }
        }
        return null;
    }

    public Node getChild(int index) {
        if (index >= children.size()) {
            return null;
        } else {
            return children.get(index);
        }
    }

    public void safefree() {
        if (parent == null) {
            free();
        } else {
            if (parent.isRender) {
                parent.freechild.add(this);
            } else {
                free();
            }
        }
    }

    private void free() {    // Free
        if (parent != null) {
            parent.children.remove(this);
        }
        freeNode();
    }

    protected void freeNode() {
        dispose();
        for (Node child : children) {
            System.out.println("Free node: " + child.name);
            child.freeNode();
        }
        children.clear();
    }

    public void connect_signal(String signal, Node target, String method) {
        if (!signals.containsKey(signal)) {
            signals.put(signal, new SignalPack());
        }
        signals.get(signal).add(target, method);
    }

    public void disconnect_signal(String signal, Node target, String method) {
        signals.get(signal).remove(target, method);
    }

    public void emit_signal(String signal) {
        if (signals.containsKey(signal)) {
            signals.get(signal).execute();
        }
    }

    public void execute_signal(String signal) {

    }

    // ## Implement method

    public void create() {  // Called when a node addChild(thisnode)

    }

    public void render() {

    }

    public void dispose() {

    }
}



class SignalPack {

    public Array<Signal> signals = new Array<>();

    public void add(Node target, String method) {
        signals.add(new Signal(target, method));
    }

    public void remove(Node target, String method) {
        for (Signal signal : signals) {
            if (signal.target.equals(target) && signal.method.equals(method)) {
                signals.removeValue(signal, true);
            }
        }
    }

    public void execute() {
        for (Signal signal : signals) {
            if (signal.target == null) {
                signals.removeValue(signal, true);
            } else {
                signal.execute();
            }
        }
    }

}

class Signal {

    public Node target;
    public String method;

    public Signal(Node target, String method) {
        this.target = target;
        this.method = method;
    }

    public void execute() {
        target.execute_signal(method);
    }
}