package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity;

public interface CurrentActivitySessionManager {

    CurrentActivity getCurrentActivityForChat(Long chatId);

    void setActivityForChat(Long chatId, CurrentActivity currentActivity);

}
