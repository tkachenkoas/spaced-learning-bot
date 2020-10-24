package com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.logic;

import com.atstudio.spacedlearningbot.telegram.config.CacheRegions;
import com.atstudio.spacedlearningbot.telegram.updateprocessors.activity.domain.CurrentActivity;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CurrentActivitySessionManagerImpl implements CurrentActivitySessionManager {

    private final CacheManager cacheManager;

    @Override
    public CurrentActivity getCurrentActivityForChat(Long chatId) {
        return getCurrentActivityCache().get(chatId, CurrentActivity.class);
    }

    @Override
    public void setActivityForChat(Long chatId, CurrentActivity currentActivity) {
        getCurrentActivityCache().put(chatId, currentActivity);
    }

    @Override
    public void cleanCurrentActivity(Long chatId) {
        getCurrentActivityCache().evict(chatId);
    }

    private Cache getCurrentActivityCache() {
        return cacheManager.getCache(CacheRegions.CURRENT_ACTIVITY.name());
    }
}
