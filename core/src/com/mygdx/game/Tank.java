package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.mygdx.game.MyGdxGame.*;

/**
 * Represents the Tank playable character.
 */
public class Tank extends Character {
    private static final int SHIELD_OFFSET = PLAYER_HEIGHT / 10;
    private static final int SWORD_LENGTH = PLAYER_HEIGHT;
    private static final int SWORD_WIDTH = PLAYER_WIDTH / 2;

    private TankState state;

    /**
     * Initliases the tank at coordinates (0, MAP_HEIGHT).
     */
    public Tank() {
        super();
        state = new TankState();
    }

    // TODO: Have render check if the skills are still persisting, and draw the necessary stuff.
    @Override
    public void render() {
        // draw character
        ShapeRenderer shape = super.getRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLACK);
        shape.rect(getX(), getY(), getWidth(), getHeight());
        shape.end();

        // update persist states
        if (state.isPrimaryPressed()) {
            primary(); // no cooldown timer for primary skill
            state.setPrimaryPressed(false);
        }

        if (state.isSecondaryPressed()) {
            if (super.isSkillAvailable(state.getSecondaryTimeSince(), state.SECONDARY_COOLDOWN)) {
                // case 1 : skill is available to cast.
                // update cast time to now
                Gdx.app.log("Tank.java", "Secondary skill pressed");
                state.updateSecondaryTimeSince(); // update last cast timing
                secondary();
            } else if(super.isSkillPersisting(state.getSecondaryTimeSince(), state.SECONDARY_PERSIST_TIME)) {
                // case 2: skill is on cooldown, but is persisting.
                secondary();
            } else {
                state.setSecondaryPressed(false);
            }
        }

        if (state.isTertiaryPressed()) {
            if (super.isSkillAvailable(state.getTertiaryTimeSince(), state.TERTIARY_COOLDOWN)) {
                Gdx.app.log("Tank.java", "Tertiary skill pressed");
                state.updateTertiaryTimeSince();
                tertiary();
            } else if(super.isSkillPersisting(state.getTertiaryTimeSince(), state.TERTIARY_PERSIST_TIME)) {
                tertiary();
            } else {
                state.setTertiaryPressed(false);
            }
        }
    }

    // Block
    @Override
    public void primary() {
        ShapeRenderer shape = super.getRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.GOLD);
        shape.rect(super.getX() - SHIELD_OFFSET, super.getY() - SHIELD_OFFSET,
                super.getWidth() + 2 * SHIELD_OFFSET, super.getHeight() + 2 * SHIELD_OFFSET);
        shape.end();
    }

    // Slash
    @Override
    public void secondary() {
        ShapeRenderer shape = super.getRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.GOLD);
        shape.rect(getX() + getWidth() / 2, getY() + getHeight() / 2,
                SWORD_LENGTH, SWORD_WIDTH);
        shape.end();
    }

    // Fortress
    @Override
    public void tertiary() {
        ShapeRenderer shape = super.getRenderer();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.GOLD);
        shape.rect(getX(), getY(), getWidth(), getHeight());
        shape.end();
    }

    @Override
    public void setPrimaryPressed(boolean flag) {
        this.state.setPrimaryPressed(flag);
    }

    @Override
    public void setSecondaryPressed(boolean flag) {
        this.state.setSecondaryPressed(flag);
    }

    @Override
    public void setTertiaryPressed(boolean flag) {
        this.state.setTertiaryPressed(flag);
    }
}
