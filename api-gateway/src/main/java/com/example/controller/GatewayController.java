//package com.example.controller;
//
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//@RequestMapping("/api")
//public class GatewayController {
//
//    private final RestTemplate restTemplate;
//
//    private static final String PLAYER_SERVICE = "http://localhost:8081";
//    private static final String TEAM_SERVICE = "http://localhost:8082";
//    private static final String MATCH_SERVICE = "http://localhost:8083";
//
//    public GatewayController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @GetMapping("/players")
//    public ResponseEntity<String> getPlayers() {
//        return restTemplate.getForEntity(PLAYER_SERVICE + "/api/players", String.class);
//    }
//
//    @PostMapping("/players")
//    public ResponseEntity<String> createPlayer(@RequestBody Object playerRequest) {
//        return restTemplate.postForEntity(PLAYER_SERVICE + "/api/players", playerRequest, String.class);
//    }
//
//    @GetMapping("/players/{id}")
//    public ResponseEntity<String> getPlayerById(@PathVariable Long id) {
//        return restTemplate.getForEntity(PLAYER_SERVICE + "/api/players/" + id, String.class);
//    }
//
//    @DeleteMapping("/players/{id}")
//    public ResponseEntity<String> deletePlayer(@PathVariable Long id) {
//        restTemplate.delete(PLAYER_SERVICE + "/api/players/delete/" + id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/teams")
//    public ResponseEntity<String> getTeams() {
//        return restTemplate.getForEntity(TEAM_SERVICE + "/api/teams", String.class);
//    }
//
//    @PostMapping("/teams")
//    public ResponseEntity<String> createTeam(@RequestBody Object teamRequest) {
//        return restTemplate.postForEntity(TEAM_SERVICE + "/api/teams", teamRequest, String.class);
//    }
//
//    @GetMapping("/teams/{id}")
//    public ResponseEntity<String> getTeamById(@PathVariable Long id) {
//        return restTemplate.getForEntity(TEAM_SERVICE + "/api/teams/" + id, String.class);
//    }
//
//    @PutMapping("/teams/{id}")
//    public ResponseEntity<String> updateTeam(@PathVariable Long id, @RequestBody Object teamRequest) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> entity = new HttpEntity<>(teamRequest, headers);
//
//        return restTemplate.exchange(
//                TEAM_SERVICE + "/api/teams/" + id,
//                HttpMethod.PUT,
//                entity,
//                String.class
//        );
//    }
//
//    @DeleteMapping("/teams/{id}")
//    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
//        restTemplate.delete(TEAM_SERVICE + "/api/teams/" + id);
//        return ResponseEntity.noContent().build();
////    }
//
//    @PostMapping("/teams/players")
//    public ResponseEntity<String> addPlayerToTeam(@RequestBody Object request) {
//        return restTemplate.postForEntity(TEAM_SERVICE + "/api/teams/players", request, String.class);
//    }
//
//    @DeleteMapping("/teams/{teamId}/players/{playerId}")
//    public ResponseEntity<String> removePlayerFromTeam(
//            @PathVariable Long teamId,
//            @PathVariable Long playerId) {
//        restTemplate.delete(TEAM_SERVICE + "/api/teams/" + teamId + "/players/" + playerId);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PatchMapping("/teams/{id}/stats")
//    public ResponseEntity<String> updateTeamStats(
//            @PathVariable Long id,
//            @RequestBody Object request) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
//
//        return restTemplate.exchange(
//                TEAM_SERVICE + "/api/teams/" + id + "/stats",
//                HttpMethod.PATCH,
//                entity,
//                String.class
//        );
//    }
//
//    @GetMapping("/teams/category/{category}")
//    public ResponseEntity<String> getTeamsByCategory(@PathVariable String category) {
//        return restTemplate.getForEntity(TEAM_SERVICE + "/api/teams/category/" + category, String.class);
//    }
//
//    @GetMapping("/teams/active")
//    public ResponseEntity<String> getActiveTeams() {
//        return restTemplate.getForEntity(TEAM_SERVICE + "/api/teams/active", String.class);
//    }
//
//
//    @GetMapping("/matches")
//    public ResponseEntity<String> getMatches() {
//        return restTemplate.getForEntity(MATCH_SERVICE + "/api/matches", String.class);
//    }
//
//    @PostMapping("/matches")
//    public ResponseEntity<String> createMatch(@RequestBody Object matchRequest) {
//        return restTemplate.postForEntity(MATCH_SERVICE + "/api/matches", matchRequest, String.class);
//    }
//
//    @GetMapping("/matches/{id}")
//    public ResponseEntity<String> getMatchById(@PathVariable Long id) {
//        return restTemplate.getForEntity(MATCH_SERVICE + "/api/matches/" + id, String.class);
//    }
//
//    @PutMapping("/matches/{id}/score")
//    public ResponseEntity<String> updateScore(
//            @PathVariable Long id,
//            @RequestBody Object request) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
//
//        return restTemplate.exchange(
//                MATCH_SERVICE + "/api/matches/" + id + "/score",
//                HttpMethod.PUT,
//                entity,
//                String.class
//        );
//    }
//
//    @PatchMapping("/matches/{id}/status/{status}")
//    public ResponseEntity<String> updateStatus(
//            @PathVariable Long id,
//            @PathVariable String status) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Void> entity = new HttpEntity<>(headers);
//
//        return restTemplate.exchange(
//                MATCH_SERVICE + "/api/matches/" + id + "/status/" + status,
//                HttpMethod.PATCH,
//                entity,
//                String.class
//        );
//    }
//
//    @GetMapping("/matches/status/{status}")
//    public ResponseEntity<String> getMatchesByStatus(@PathVariable String status) {
//        return restTemplate.getForEntity(MATCH_SERVICE + "/api/matches/status/" + status, String.class);
//    }
//
//    @GetMapping("/matches/{matchId}/stats")
//    public ResponseEntity<String> getMatchStats(@PathVariable Long matchId) {
//        return restTemplate.getForEntity(MATCH_SERVICE + "/api/matches/" + matchId + "/stats", String.class);
//    }
//
//    @PostMapping("/matches/{matchId}/stats")
//    public ResponseEntity<String> addPlayerStats(
//            @PathVariable Long matchId,
//            @RequestBody Object request) {
//        return restTemplate.postForEntity(
//                MATCH_SERVICE + "/api/matches/" + matchId + "/stats",
//                request,
//                String.class
//        );
//    }
//
//    @PutMapping("/matches/stats/{statsId}")
//    public ResponseEntity<String> updatePlayerStats(
//            @PathVariable Long statsId,
//            @RequestBody Object request) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
//
//        return restTemplate.exchange(
//                MATCH_SERVICE + "/api/matches/stats/" + statsId,
//                HttpMethod.PUT,
//                entity,
//                String.class
//        );
//    }
//
//    @GetMapping("/matches/{matchId}/stats/team/{teamId}")
//    public ResponseEntity<String> getTeamStatsInMatch(
//            @PathVariable Long matchId,
//            @PathVariable Long teamId) {
//        return restTemplate.getForEntity(
//                MATCH_SERVICE + "/api/matches/" + matchId + "/stats/team/" + teamId,
//                String.class
//        );
//    }
//
//
//    @GetMapping("/health")
//    public ResponseEntity<HealthResponse> health() {
//        return ResponseEntity.ok(new HealthResponse("API Gateway is running"));
//    }
//
//    record HealthResponse(String status) {}
//}