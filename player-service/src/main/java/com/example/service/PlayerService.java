package com.example.service;



import com.example.dto.request.RegistrationPlayerRequest;
import com.example.dto.response.RegistrationPlayerResponse;
import com.example.entity.Player;
import com.example.repository.PlayerRepository;
import jakarta.persistence.Cacheable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final RedisTemplate<String, String> redisTemplate;
    public RegistrationPlayerResponse registerPlayer(RegistrationPlayerRequest request){
        Player player = Player.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .jerseyNumber(request.jerseyNumber())
                .gender(request.gender())
                .position(request.position())
                .playerStatus(request.playerStatus())
                .build();

        Player saved_player = playerRepository.save(player);

        return new RegistrationPlayerResponse(
                request.firstname(),
                request.lastname(),
                request.jerseyNumber(),
                request.gender(),
                request.position(),
                request.playerStatus(),
                saved_player.getId()
        );
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        playerRepository.delete(player);
    }

    public Long getTeamId(Long playerId) {
        Optional<String> teamId = Optional.ofNullable(redisTemplate.opsForValue().get(playerId.toString()));
        if (teamId.isPresent()) {
            return Long.valueOf(teamId.get());
        } else {
            Player player = playerRepository.findById(playerId)
                    .orElseThrow(() -> new RuntimeException("Пользователь с таким id не найден"));
            Long teamIdDb = player.getTeamId();
            redisTemplate.opsForValue().set(playerId.toString(), teamIdDb.toString());
            return teamIdDb;
        }
    }
}