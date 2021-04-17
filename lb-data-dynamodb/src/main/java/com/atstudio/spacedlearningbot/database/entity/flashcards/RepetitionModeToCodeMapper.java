package com.atstudio.spacedlearningbot.database.entity.flashcards;

import com.atstudio.spacedlearningbot.domain.RepetitionMode;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

import static com.atstudio.spacedlearningbot.domain.RepetitionMode.SELF_CHECK;

public class RepetitionModeToCodeMapper {

    private static final Map<RepetitionMode, Integer> MODE_TO_CODE = Map.of(SELF_CHECK, 1);
    private static final Map<Integer, RepetitionMode> CODE_TO_MODE = MapUtils.invertMap(MODE_TO_CODE);

    public static Integer getCode(RepetitionMode mode) {
        return MODE_TO_CODE.get(mode);
    }

    public static RepetitionMode getMode(Integer code) {
        return CODE_TO_MODE.get(code);
    }
}
