package com.suicidebomber.element;

import com.badlogic.gdx.utils.Array;

import java.util.HashMap;


public class Node {

    protected Array<Node> child = new Array<>();
    protected HashMap<String, SignalPack> signals = new HashMap<>();
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

    // ## Node method

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
        signals.get(signal).execute();
    }

    public void execute_signal(String signal) {

    }

    // ## Implement method

    public void create() {

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
            signal.execute();
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