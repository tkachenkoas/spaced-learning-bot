package com.atstudio.spacedlearningbot.database.enummapper;

import com.atstudio.spacedlearningbot.domain.ExerciseDirection;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

import static com.atstudio.spacedlearningbot.domain.ExerciseDirection.DIRECT;
import static com.atstudio.spacedlearningbot.domain.ExerciseDirection.REVERSED;

public class ExerciseDirectionToCodeMapper {

    private static final Map<ExerciseDirection, Integer> DIRECTION_TO_CODE = Map.of(
            DIRECT, 1,
            REVERSED, 2
    );
    private static final Map<Integer, ExerciseDirection> CODE_TO_DIRECTION = MapUtils.invertMap(DIRECTION_TO_CODE);

    public static Integer getCode(ExerciseDirection mode) {
        return DIRECTION_TO_CODE.get(mode);
    }

    public static ExerciseDirection getDirection(Integer code) {
        return CODE_TO_DIRECTION.get(code);
    }
}
