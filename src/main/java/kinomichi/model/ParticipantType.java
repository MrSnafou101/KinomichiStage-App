package kinomichi.model;

import java.util.Arrays;

public enum ParticipantType {
    KID,
    ADULT,
    ANIMATOR;

    public static ParticipantType fromString(String value) {
        return Arrays.stream(ParticipantType.values())
                .filter(pt -> pt.name().equalsIgnoreCase(value))
                .findAny()
                .get();
    }
}
