package com.atstudio.spacedlearningbot.domain;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private String id;
    private String alias;
    private String name;
}
