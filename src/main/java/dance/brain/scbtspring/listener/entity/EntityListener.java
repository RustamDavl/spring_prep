package dance.brain.scbtspring.listener.entity;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.EventObject;

@Component
public class EntityListener {

    @EventListener(condition = "#root.args[0].accessType.name() == 'SELECT' ")
    public void accept(EntityEvent entityEvent) {
        System.out.println("Entity : " + entityEvent);
    }
}
