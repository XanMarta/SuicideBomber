package com.suicidebomber.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.autoload.GameElement;

// Signal: button_pressed

public class Button extends Canvas2D {

    public Vector2 size = new Vector2(100, 100);
    public String mouseInTexture = "";
    public String mouseOutTexture = "";
    
    private Sprite sprite;
    private SoundPlayer soundPlayer;

    public Button() {
        super();
        sprite = new Sprite();
    }

    public void create() {
        super.create();
        addChild(sprite);
        sprite.image = mouseOutTexture;

        soundPlayer = new SoundPlayer();
        addChild(soundPlayer);
    }

    public void render() {
        super.render();
        sprite.image = mouseOutTexture;
        if (Gdx.input.getX() >= global_position.x && Gdx.input.getX() <= global_position.x + size.x) {
            if (GameElement.windowsSize.y - Gdx.input.getY() >= global_position.y
                    && GameElement.windowsSize.y - Gdx.input.getY() <= global_position.y + size.y) {
                sprite.image = mouseInTexture;
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
                    soundPlayer.play("CHOOSE");
                    emit_signal("button_pressed");
                }
            }
        }
    }

}
