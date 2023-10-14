package tu.carshop.events;

import org.springframework.context.ApplicationEvent;

public class OfferCreatedEvent extends ApplicationEvent {
  public OfferCreatedEvent(Object source) {
    super(source);
  }
}
