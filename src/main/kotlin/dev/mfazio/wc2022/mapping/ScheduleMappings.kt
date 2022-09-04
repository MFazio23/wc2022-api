package dev.mfazio.wc2022.mapping

import dev.mfazio.wc2022.types.ScheduledMatch
import dev.mfazio.wc2022.types.db.ScheduledMatchDbModel
import dev.mfazio.wc2022.types.external.schedule.ExternalSchedule
import dev.mfazio.wc2022.types.external.schedule.ExternalScheduleMatch

fun Map<String, ScheduledMatchDbModel>.mapToScheduledMatches() = this.map { (matchId, dbMatch) ->
    ScheduledMatch(
        matchId = matchId,
        stageId = dbMatch.stageId,
        homeTeam = dbMatch.homeTeam,
        awayTeam = dbMatch.awayTeam,
        dateTime = dbMatch.dateTime,
        group = dbMatch.group,
        location = dbMatch.location,
        stadium = dbMatch.stadium,
        matchStatus = dbMatch.matchStatus.toMatchStatus(),
        homeScore = dbMatch.homeScore,
        awayScore = dbMatch.awayScore,
        currentMinute = dbMatch.currentMinute,
    )
}.sortedBy { it.dateTime }

fun List<ExternalSchedule>.mapToScheduledMatches() = this.flatMap(ExternalSchedule::mapToScheduledMatches)

fun ExternalSchedule.mapToScheduledMatches() =
    this.competition?.activeSeasons?.firstOrNull()?.matches?.map(ExternalScheduleMatch::mapToScheduledMatches) ?: emptyList()

fun ExternalScheduleMatch.mapToScheduledMatches() = ScheduledMatch(
    matchId = this.idMatch.orEmpty(),
    stageId = this.idStage.orEmpty(),
    homeTeam = this.home?.countryId ?: this.placeholderA.orEmpty(),
    awayTeam = this.away?.countryId ?: this.placeholderB.orEmpty(),
    dateTime = this.date.orEmpty(),
    group = this.groupName?.removePrefix("Group ").orEmpty(),
    location = this.stadiumCityName.orEmpty(),
    stadium = this.stadiumName.orEmpty(),
)