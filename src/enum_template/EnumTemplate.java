package enum_template;

import java.util.Map;
import java.util.Set;

public interface EnumTemplate {
    String getCaption();
    static Set<String> getEnumCaptions(Map<String, ? extends EnumTemplate> lookupMap) {
        return lookupMap.keySet();
    }

    static <T extends EnumTemplate> T getEnumByCaption(Map<String, T> lookupMap, String caption) {
        return lookupMap.get(caption);
    }
}
