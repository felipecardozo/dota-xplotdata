package com.dota.team.pro.model.matches.single;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Players {
	
	@JsonProperty("match_id")
	private Long matchId;
	
	@JsonProperty("player_slot")
	private Integer playerSlot;
	
	@JsonProperty("ability_targets")
	private Map<String, Map<String, Integer>> abilityTargets;
	
	@JsonProperty("ability_upgrades_arr")
	private List<Integer> abilityUpgradesArr;
	
	@JsonProperty("ability_uses")
	private Map<String, Integer> abilityUses;
	
	@JsonProperty("account_id")
	private Long accountId;
	
	@JsonProperty("actions")
	private Map<String, Integer> actions;
	
	@JsonProperty("assists")
	private Integer assists;
	
	@JsonProperty("backpack_0")
	private Integer backpack0;
	
	@JsonProperty("backpack_1")
	private Integer backpack1;
	
	@JsonProperty("backpack_2")
	private Integer backpack2;
	
	@JsonProperty("backpack_3")
	private Integer backpack3;
	
	@JsonProperty("camps_stacked")
	private Integer campsStacked;
	
	@JsonProperty("creeps_stacked")
	private Integer creepsStacked;
	
	@JsonProperty("damage")
	private Map<String, Integer> damage;
	
	@JsonProperty("damage_inflictor")
	private Map<String, Integer> damageInflictor;
	
	@JsonProperty("damage_inflictor_received")
	private Map<String, Integer> damageInflictorReceived;
	
	@JsonProperty("damage_taken")
	private Map<String, Integer> damageTaken;
	
	@JsonProperty("damage_targets")
	private Map<String, Map<String, Integer>> damageTargets;
	
	@JsonProperty("deaths")
	private Integer deaths;
	
	@JsonProperty("denies")
	private Integer denies;
	
	@JsonProperty("firstblood_claimed")
	private Integer firstbloodClaimed;
	
	@JsonProperty("gold")
	private Integer gold;
	
	@JsonProperty("gold_per_min")
	private Integer goldPerMin;
	
	@JsonProperty("gold_reasons")
	private Map<String, Integer> goldReasons;
	
	@JsonProperty("gold_spent")
	private Integer goldSpent;
	
	@JsonProperty("gold_t")
	private List<Integer> goldT;
	
	@JsonProperty("hero_damage")
	private Integer heroDamage;
	
	@JsonProperty("hero_healing")
	private Integer heroHealing;
	
	@JsonProperty("hero_hits")
	private Map<String, Integer> heroHits;
	
	@JsonProperty("hero_id")
	private Integer heroId;
	
	@JsonProperty("item_0")
	private Integer item0;
	
	@JsonProperty("item_1")
	private Integer item1;
	
	@JsonProperty("item_2")
	private Integer item2;
	
	@JsonProperty("item_3")
	private Integer item3;
	
	@JsonProperty("item_4")
	private Integer item4;
	
	@JsonProperty("item_5")
	private Integer item5;
	
	@JsonProperty("item_uses")
	private Map<String, Integer> itemUses;
	
	@JsonProperty("killed")
	private Map<String, Integer> killed;
	
	@JsonProperty("killed_by")
	private Map<String, Integer> killedBy;
	
	@JsonProperty("kills")
	private Integer kills;
	
	@JsonProperty("last_hits")
	private Integer lastHits;
	
	@JsonProperty("leaver_status")
	private Integer leaverStatus;
	
	@JsonProperty("level")
	private Integer level;
	
	@JsonProperty("life_state")
	private Map<String, Integer> lifeState;
	
	@JsonProperty("max_hero_hit")
	private MaxHeroHit maxHeroHit;
	
	@JsonProperty("obs_left_log")
	private List<ObsLeftLog> obsLeftLog;
	
	@JsonProperty("obs_log")
	private List<ObsLeftLog> obsLog;
	
	@JsonProperty("obs_placed")
	private Integer obsPlaced;
	
	@JsonProperty("party_id")
	private Integer partyId;
	
	@JsonProperty("party_size")
	private Integer partySize;
	
	@JsonProperty("purchase")
	private Map<String, Integer> purchase;
	
	@JsonProperty("purchase_log")
	private List<TimeKeyPair> purchaseLog;
	
	@JsonProperty("randomed")
	private Boolean randomed;
	
	@JsonProperty("roshans_killed")
	private Integer roshansKilled;
	
	@JsonProperty("rune_pickups")
	private Integer runePickups;
	
	@JsonProperty("runes")
	private Map<String, Integer> runes;
	
	@JsonProperty("runes_log")
	private List<TimeKeyPair> runesLog;
	
	@JsonProperty("sen_left_log")
	private List<ObsLeftLog> senLeftLog;
	
	@JsonProperty("sen_log")
	private List<ObsLeftLog> senLog;
	
	@JsonProperty("sen_placed")
	private Integer senPlaced;
	
	@JsonProperty("stuns")
	private Float stuns;
	
	@JsonProperty("teamfight_participation")
	private Double teamfightParticipation;
	
	@JsonProperty("times")
	private List<Integer> times;
	
	@JsonProperty("tower_damage")
	private Integer towerDamage;
	
	@JsonProperty("towers_killed")
	private Integer towersKilled;
	
	@JsonProperty("xp_per_min")
	private Integer xpPerMin;
	
	@JsonProperty("xp_reasons")
	private Map<String, Integer> xpReasons;
	
	@JsonProperty("xp_t")
	private List<Integer> xpT;
	
	@JsonProperty("radiant_win")
	private Boolean radiantWin;
	
	@JsonProperty("duration")
	private Integer duration;
	
	@JsonProperty("lobby_type")
	private Integer lobbyType;
	
	@JsonProperty("game_mode")
	private Integer gameMode;
	
	@JsonProperty("patch")
	private Integer patch;
	
	@JsonProperty("isRadiant")
	private Boolean isRadiant;
	
	@JsonProperty("win")
	private Integer win;
	
	@JsonProperty("lose")
	private Integer lose;
	
	@JsonProperty("total_gold")
	private Integer totalGold;
	
	@JsonProperty("total_xp")
	private Integer totalXp;
	
	@JsonProperty("kills_per_min")
	private Float killsPerMin;
	
	@JsonProperty("kda")
	private Integer kda;
	
	@JsonProperty("neutral_kills")
	private Integer neutralKills;

	@JsonProperty("tower_kills")
	private Integer towerKills;
	
	@JsonProperty("courier_kills")
	private Integer courierKills;
	
	@JsonProperty("lane_kills")
	private Integer laneKills;
	
	@JsonProperty("hero_kills")
	private Integer heroKills;
	
	@JsonProperty("observer_kills")
	private Integer observerKills;
	
	@JsonProperty("sentry_kills")
	private Integer sentryKills;
	
	@JsonProperty("roshan_kills")
	private Integer roshanKills;
	
	@JsonProperty("necronomicon_kills")
	private Integer necronomiconKills;
	
	@JsonProperty("ancient_kills")
	private Integer ancientKills;
	
	@JsonProperty("buyback_count")
	private Integer buybackCount;
	
	@JsonProperty("observer_uses")
	private Integer observerUses;
	
	@JsonProperty("sentry_uses")
	private Integer sentryUses;
	
	@JsonProperty("lane_efficiency")
	private Float laneEfficiency;
	
	@JsonProperty("lane_efficiency_pct")
	private Integer laneEfficiencyPct;
	
	@JsonProperty("lane")
	private Integer lane;
	
	@JsonProperty("lane_role")
	private Integer laneRole;
	
	@JsonProperty("is_roaming")
	private Boolean isRoaming;
	
	@JsonProperty("purchase_time")
	private Map<String, Integer> purchaseTime;
	
	@JsonProperty("first_purchase_time")
	private Map<String, Integer> firstPurchaseTime;
	
	@JsonProperty("item_win")
	private Map<String, Integer> itemWin;
	
	@JsonProperty("item_usage")
	private Map<String, Integer> itemUsage;
	
	@JsonProperty("purchase_ward_observer")
	private Integer purchaseWardObserver;
	
	@JsonProperty("purchase_ward_sentry")
	private Integer purchaseWardSentry;
	
	@JsonProperty("purchase_tpscroll")
	private Integer purchaseTpscroll;
	
	@JsonProperty("actions_per_min")
	private Integer actionsPerMin;
	
	@JsonProperty("life_state_dead")
	private Integer lifeStateDead;
	
	@JsonProperty("rank_tier")
	private Integer rankTier;
	
	@JsonProperty("benchmarks")
	private Map<String, BenchMark> benchmarks;
	

}










