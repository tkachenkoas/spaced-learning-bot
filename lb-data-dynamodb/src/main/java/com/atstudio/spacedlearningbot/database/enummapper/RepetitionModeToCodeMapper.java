package com.atstudio.spacedlearningbot.database.enummapper;

import com.atstudio.spacedlearningbot.domain.ExcerciseType;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

import static com.atstudio.spacedlearningbot.domain.ExcerciseType.SELF_CHECK;

public class RepetitionModeToCodeMapper {

    private static final Map<ExcerciseType, Integer> MODE_TO_CODE = Map.of(SELF_CHECK, 1);
    private static final Map<Integer, ExcerciseType> CODE_TO_MODE = MapUtils.invertMap(MODE_TO_CODE);

    public static Integer getCode(ExcerciseType mode) {
        return MODE_TO_CODE.get(mode);
    }

    public static ExcerciseType getMode(Integer code) {
        return CODE_TO_MODE.get(code);
    }
}
