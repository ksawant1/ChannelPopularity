package channelpopularity.state;

import java.util.HashMap;
import java.util.Map;

public enum StateName {
    UNPOPULAR, HIGHLY_POPULAR, MILDLY_POPULAR, ULTRA_POPULAR;

    private static Map<String, StateName> stateNameEnumMap = new HashMap<>();

    static {
        for (StateName stateName : StateName.values()) {
            stateNameEnumMap.put(stateName.toString(), stateName);
        }
    }

    public static StateName enumValueOf(String state) {
        StateName stateName = stateNameEnumMap.get(state);
        return stateName;
    }

}
