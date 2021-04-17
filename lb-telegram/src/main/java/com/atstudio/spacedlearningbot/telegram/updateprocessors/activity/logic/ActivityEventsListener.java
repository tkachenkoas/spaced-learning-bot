package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.logic;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ActivityFinishedEvent;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ActivityInitializer;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ActivityUpdatedEvent;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.InitActivityEvent;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.ActivityType;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.CurrentActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.api.ByActivityTypeBreaker.breakByActivityType;
import static com.atstudio.spacedlearningbot.telegram.utils.TgBotApiObjectsUtils.getChatId;

@Component
public class ActivityEventsListener {

    private final Map<ActivityType, ActivityInitializer> initializers;
    private final CurrentActivitySessionManager currentActivitySessionManager;

    @Autowired
    public ActivityEventsListener(
            List<ActivityInitializer> initializersList,
            CurrentActivitySessionManager currentActivitySessionManager
    ) {
        this.initializers = breakByActivityType(initializersList);
        this.currentActivitySessionManager = currentActivitySessionManager;
    }

    @EventListener
    public void handleNavigation(InitActivityEvent initialized) {
        ActivityType activityType = initialized.getActivityType();
        ActivityInitializer initializer = initializers.get(activityType);
        CurrentActivity currentActivity = new CurrentActivity().withActivityType(activityType);
        currentActivitySessionManager.setActivityForChat(
                getChatId(initialized.getUpdate()),
                currentActivity
        );
        initializer.initActivity(initialized.getUpdate(), currentActivity);
    }

    @EventListener
    public void updateActivity(ActivityUpdatedEvent updated) {
        currentActivitySessionManager.setActivityForChat(
                updated.getChatId(),
                updated.getActivity()
        );
    }

    @EventListener
    public void cleanupActivity(ActivityFinishedEvent finished) {
        currentActivitySessionManager.cleanCurrentActivity(finished.getChatId());
    }

}
