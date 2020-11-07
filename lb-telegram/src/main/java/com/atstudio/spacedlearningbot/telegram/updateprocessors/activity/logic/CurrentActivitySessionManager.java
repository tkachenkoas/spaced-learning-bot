package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.logic;

import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.CurrentActivity;

public interface CurrentActivitySessionManager {

    CurrentActivity getCurrentActivityForChat(Long chatId);

    void setActivityForChat(Long chatId, CurrentActivity currentActivity);

    void cleanCurrentActivity(Long chatId);

}
