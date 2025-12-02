package com.example.dto.response;

import com.example.entity.MatchStatus;
import com.example.entity.MatchType;

import java.time.LocalDateTime;

public record MatchResponse(
        Long id,
        Long homeTeamId,
        Long awayTeamId,
        LocalDateTime dateTime,
        MatchStatus status,
        MatchType type,
        Integer homeTeamScore,
        Integer awayTeamScore
) {}