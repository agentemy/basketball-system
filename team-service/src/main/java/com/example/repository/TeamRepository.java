package com.example.repository;

import com.example.entity.Team;
import com.example.entity.TeamCategory;
import com.example.entity.TeamStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByName(String name);

    boolean existsByName(String name);

    List<Team> findByStatus(TeamStatus status);

    @Query("SELECT t FROM Team t WHERE t.category = :category")
    List<Team> findByCategory(@Param("category") String category);

    List<Team> findByStatusAndCategory(TeamStatus status, TeamCategory category);

    @Query("SELECT t FROM Team t WHERE t.status = 'ACTIVE' AND t.category = :category")
    List<Team> findActiveTeamsByCategory(@Param("category") TeamCategory category);

    @Query("SELECT t FROM Team t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Team> findByNameContainingIgnoreCase(@Param("name") String name);

    List<Team> findByWinsGreaterThan(Integer wins);

    List<Team> findByLossesLessThan(Integer losses);

    @Query("SELECT t FROM Team t WHERE t.captainId = :captainId")
    List<Team> findByCaptainId(@Param("captainId") Long captainId);

    @Query("SELECT t FROM Team t WHERE :playerId MEMBER OF t.playerIds")
    List<Team> findTeamsByPlayerId(@Param("playerId") Long playerId);

    @Query("SELECT t FROM Team t ORDER BY t.wins DESC, t.losses ASC")
    List<Team> findTopTeamsByWins();

    @Query("SELECT t FROM Team t WHERE t.wins BETWEEN :minWins AND :maxWins")
    List<Team> findByWinsBetween(@Param("minWins") Integer minWins, @Param("maxWins") Integer maxWins);

    @Query("SELECT COUNT(t) FROM Team t WHERE t.status = :status")
    Long countByStatus(@Param("status") TeamStatus status);

    @Query("SELECT COUNT(t) FROM Team t WHERE t.category = :category")
    Long countByCategory(@Param("category") String category);

    @Query("SELECT t FROM Team t WHERE SIZE(t.playerIds) = (SELECT MAX(SIZE(t2.playerIds)) FROM Team t2)")
    List<Team> findTeamsWithMaxPlayers();

    @Query("SELECT t FROM Team t WHERE SIZE(t.playerIds) = (SELECT MIN(SIZE(t2.playerIds)) FROM Team t2)")
    List<Team> findTeamsWithMinPlayers();

    @Query("UPDATE Team t SET t.wins = :wins, t.losses = :losses WHERE t.id = :teamId")
    void updateTeamStats(@Param("teamId") Long teamId, @Param("wins") Integer wins, @Param("losses") Integer losses);
}