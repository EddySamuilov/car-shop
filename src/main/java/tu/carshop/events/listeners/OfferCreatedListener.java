package tu.carshop.events.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tu.carshop.events.OfferCreatedEvent;

@Component
public class OfferCreatedListener {

  public static final Logger LOG = LoggerFactory.getLogger(OfferCreatedListener.class);

  @EventListener(OfferCreatedEvent.class)
  public void onOfferCreated() {
    LOG.info("Offer was created");
  }
}
