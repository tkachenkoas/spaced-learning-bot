package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class ByActivityTypeBreaker {

    public static <T extends ActivityTypeApplicable> Map<ActivityType, T> breakByActivityType(List<T> list) {
        return list.stream().collect(
                Collectors.toMap(ActivityTypeApplicable::applicableForActivityType, identity())
        );
    }

}
