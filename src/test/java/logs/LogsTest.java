package logs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class LogsTest {

  private static final Logger logger = LogManager.getLogger(LogsTest.class);

  @Test
  public void testLogs() {
    System.out.println("Hello debug logs");

    logger.debug("Hello debug logs");
    logger.info("Hello info logs");
    logger.warn("Hello warning logs");
    logger.error("Hello error logs");
  }

}
