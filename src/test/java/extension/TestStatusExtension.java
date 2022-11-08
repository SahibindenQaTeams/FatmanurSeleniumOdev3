package extension;

import java.util.Optional;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestStatusExtension implements TestWatcher {

  public void testDisabled(ExtensionContext context, Optional<String> reason) {
    System.out.println("Test Disabled");
  }

  public void testSuccessful(ExtensionContext context) {
    System.out.println("Test Success");
  }

  public void testAborted(ExtensionContext context, Throwable cause) {
    System.out.println("Test Aborted");
  }

  public void testFailed(ExtensionContext context, Throwable cause) {
    System.out.println("Test Failed");
  }
}
