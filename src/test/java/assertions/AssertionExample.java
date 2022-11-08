package assertions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertionExample {

  private static final Logger logger = LogManager.getLogger(AssertionExample.class);

  @Test
  public void testAssertTrue() {
    int a = 1;
    int b = 5;

    Assertions.assertTrue(a + b == 6);
  }

  @Test
  public void testAssertFalse() {
    int a = 1;
    int b = 2;

    Assertions.assertFalse(a + b == 4);
  }

  @Test
  public void testAssertEquals() {
    String name = "enes";

    Assertions.assertEquals("enes2", name); // String compare
    Assertions.assertEquals(1, 1); // integer compare
    Assertions.assertEquals(true, true); // boolean compare
    Assertions.assertEquals(1F, 2F); // Float compare
  }

  @Test
  public void testAssertSame() throws Exception {
    Assertions.assertSame("a", "ab", "a ile ab eşit değil");
  }

  @Test
  public void testAssertNotSame() {
    Assertions.assertNotSame("a", "ab");
  }

  @Test
  public void testAssertNotNull() {
    Assertions.assertNotNull(true);
    Assertions.assertNotNull(false);
    Assertions.assertNotNull("1");
    Assertions.assertNotNull("123123213");
    Assertions.assertNotNull(1123);
    Assertions.assertNotNull(1F);
    Assertions.assertNotNull(34234);

    Assertions.assertNotNull(null); // Fail
  }

  @Test
  public void testAssertNull() {
    Assertions.assertNull(null);
  }

  @Test
  public void testAssertAll() {

    // browser açıldı
    // sahibinden.com anasayfa açıldı

    Assertions.assertAll
        (
            () -> Assertions.assertTrue(true),
            () -> Assertions.assertFalse(false),
            () -> Assertions.assertEquals("a", "a")
        );
  }

}
