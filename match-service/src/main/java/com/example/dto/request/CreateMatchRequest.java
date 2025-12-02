package com.example.dto.request;

import com.example.entity.MatchType;

import java.time.LocalDateTime;

public record CreateMatchRequest(
        Long homeTeamId,
        Long awayTeamId,
        LocalDateTime dateTime,
        MatchType type
) {}