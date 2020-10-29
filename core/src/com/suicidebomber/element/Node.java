package com.suicidebomber.element;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;


public class Node {

    protected ArrayList<Node> children = new ArrayList<>();
    protected HashMap<String, SignalPack> signals = new HashMap<>();
    protected Node parent = null;
    public String name = "";

    public Node() {
        name = "" + hashCode();
    }

    // ## Core method

    public void _create() {
        create();
        for (Node child : children) {
            child._create();
        }
    }

    public void _render() {
        render();
        for (Node child : children) {
            child._render();
        }
    }

    public void _dispose() {
        dispose();
        for (Node child : children) {
            child._dispose();
        }
    }

    // ## Node method

    public void addChild(Node child) {
        children.add(child);
        child.parent = this;
        System.out.println("Add child: " + name + " - " + child.name);
    }

    public void deleteChild(Node child) { // Delete and free
        child._dispose();
        child.freeNode();
        children.remove(child);
        System.out.println("Free node: " + child.name);
        child = null;
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

    public void free() {    // Free
        if (parent != null) {
            parent.deleteChild(this);
        } else {
            _dispose();
            freeNode();
        }
    }

    protected void freeNode() {
        for (Node child : children) {
            child.freeNode();
            children.remove(child);
            System.out.println("Free node: " + child.name);
            child = null;
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