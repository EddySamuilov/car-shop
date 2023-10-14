package tu.carshop.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * IMPORTANT: If we want to use CRON we should enable scheduling in the app
 */
@Component
public class CronScheduler {

  public static final Logger LOG = LoggerFactory.getLogger(CronScheduler.class);

  @Scheduled(cron = "${schedulers.cron}")
  public void showCurrentTime() {
    LOG.info("I am showing the current time {}", LOG.getName());
  }
}
