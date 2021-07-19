package com.atstudio.spacedlearningbot.telegram.study;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelfCheckResult {
    static final SelfCheckResult REPEAT = new SelfCheckResult();

    private String exerciseId;
    private Integer change;

    public boolean isRepeat() {
        return change == null;
    }

    public String serialized() {
        return isRepeat() ? "R" : change.toString();
    }

    public static SelfCheckResult parseSerialized(String serialized) {
        return serialized.equals("R") ? REPEAT : new SelfCheckResult(
                Integer.parseInt(serialized)
        );
    }

}
