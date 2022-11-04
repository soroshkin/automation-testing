package automation.testing.e2e.common;

import java.util.HashMap;
import java.util.Map;

public class StoryContext {

  private static final Map<String, String> CONTEXT = new HashMap<>();

  public static String getFromContext(String key) {
    return CONTEXT.get(key);
  }

  public static void putInContext(String key, String value) {
    CONTEXT.put(key, value);
  }

  public static void clearInContext(String key) {
    CONTEXT.put(key, null);
  }

  public static void clearContext() {
    CONTEXT.clear();
  }
}
