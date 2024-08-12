package dance.brain.scbtspring.listener.entity;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class EntityPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationEventPublisher;
    }
}
