package dev.mfazio.wc2022.mapping

import dev.mfazio.wc2022.types.domain.ScheduledMatch
import dev.mfazio.wc2022.types.db.ScheduledMatchDbModel
import dev.mfazio.wc2022.types.external.schedule.ExternalLocaleDescription
import dev.mfazio.wc2022.types.external.schedule.ExternalResult
import dev.mfazio.wc2022.types.external.schedule.ExternalSchedule

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
    this.results.map(ExternalResult::mapToScheduledMatches)

fun ExternalResult.mapToScheduledMatches() = ScheduledMatch(
    matchId = this.idMatch,
    stageId = this.idStage,
    homeTeam = this.home?.idCountry ?: this.placeHolderA,
    awayTeam = this.away?.idCountry ?: this.placeHolderB,
    dateTime = this.dateTime,
    group = this.groupName?.value()?.removePrefix("Group ").orEmpty(),
    location = this.stadium.cityName.value().orEmpty(),
    stadium = this.stadium.name.value().orEmpty(),
)

fun List<ExternalLocaleDescription>?.value() = this?.firstOrNull()?.description