package fr.swansky.advancedsurvivalplugin.customItem.models;


import org.bukkit.entity.Entity;

public class CloneableEntityWrapper implements Cloneable {
    private final Entity entity;
    public CloneableEntityWrapper(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public CloneableEntityWrapper clone() {
        try {
            CloneableEntityWrapper clone = (CloneableEntityWrapper) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
