package dance.brain.scbtspring.listener.entity;

import org.springframework.stereotype.Component;

import java.util.EventObject;

public class EntityEvent extends EventObject {

    private final AccessType accessType;

    public EntityEvent(Object source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }

    public AccessType getAccessType() {
        return accessType;
    }
}
