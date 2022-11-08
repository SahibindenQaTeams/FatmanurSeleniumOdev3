package extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LogExtension implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback,
    AfterAllCallback {

  @Override
  public void afterAll(ExtensionContext extensionContext) throws Exception {
    System.out.println("Log afterAll");
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    System.out.println("Log afterEach");

  }

  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {
    System.out.println("Log beforeAll");

  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    System.out.println("Log beforeEach");
  }
}
