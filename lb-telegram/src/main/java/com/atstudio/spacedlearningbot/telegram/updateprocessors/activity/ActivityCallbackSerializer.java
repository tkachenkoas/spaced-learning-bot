package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity;

public class ActivityCallbackSerializer {

    private static final String SEPARATOR = "|";

    public static String serialize(ActivityCallback callback) {
        return callback.getActivityType().getCode() + SEPARATOR + callback.getPayload();
    }

    public static ActivityCallback parse(String serialized) {
        String[] split = serialized.split(SEPARATOR);
        return ActivityCallback.builder()
                .activityType(ActivityType.forCode(Integer.parseInt(split[0])))
                .payload(split[1])
                .build();
    }

}
