package dev.mfazio.wwc2023.mapping

import dev.mfazio.wwc2023.types.domain.ScheduledMatch
import dev.mfazio.wwc2023.types.db.ScheduledMatchDbModel
import dev.mfazio.wwc2023.types.domain.MatchStatus
import dev.mfazio.wwc2023.types.external.matchdetails.ExternalMatchDetails
import dev.mfazio.wwc2023.types.external.ExternalLocaleDescription
import dev.mfazio.wwc2023.types.external.schedule.ExternalResult
import dev.mfazio.wwc2023.types.external.schedule.ExternalSchedule
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Map<String, ScheduledMatchDbModel>.mapToScheduledMatches() = this.map { (matchId, dbMatch) ->
    ScheduledMatch(
        matchId = matchId,
        stageId = dbMatch.stageId,
        homeTeam = dbMatch.homeTeam ?: "N/A",
        awayTeam = dbMatch.awayTeam ?: "N/A",
        dateTime = LocalDateTime.parse(dbMatch.dateTime, matchDateTimeFormat),
        group = dbMatch.group,
        location = dbMatch.location,
        stadium = dbMatch.stadium,
        matchStatus = MatchStatus.valueOfOrUnknown(dbMatch.matchStatus),
        homeScore = dbMatch.homeScore,
        awayScore = dbMatch.awayScore,
        homePenaltyScore = dbMatch.homePenaltyScore,
        awayPenaltyScore = dbMatch.awayPenaltyScore,
        matchTime = dbMatch.matchTime,
    )
}.sortedBy { it.dateTime }

fun ExternalSchedule.mapToScheduledMatches() =
    this.results.map(ExternalResult::mapToScheduledMatches)

fun ExternalResult.mapToScheduledMatches() = ScheduledMatch(
    matchId = this.idMatch,
    stageId = this.idStage,
    homeTeam = this.home?.idCountry ?: this.home?.abbreviation ?: this.placeHolderA,
    awayTeam = this.away?.idCountry ?: this.away?.abbreviation ?: this.placeHolderB,
    homeScore = this.homeScore,
    awayScore = this.awayScore,
    homePenaltyScore = this.homePenaltyScore,
    awayPenaltyScore = this.awayPenaltyScore,
    dateTime = LocalDateTime.parse(this.dateTime, matchDateTimeFormat),
    group = this.groupName?.value()?.removePrefix("Group ").orEmpty(),
    location = this.stadium.cityName.value().orEmpty(),
    stadium = this.stadium.name.value().orEmpty(),
    matchStatus = this.matchStatus,
    matchTime = this.matchTime,
    winner = when (this.winner) {
        null -> null
        this.home?.idTeam -> this.home.idCountry ?: this.home.abbreviation
        this.away?.idTeam -> this.away.idCountry ?: this.away.abbreviation
        else -> null
    },
)

fun ExternalMatchDetails.mapToScheduledMatch() = ScheduledMatch(
    matchId = this.idMatch,
    stageId = this.idStage,
    homeTeam = this.homeTeam.idCountry ?: this.homeTeam.abbreviation,
    awayTeam = this.awayTeam.idCountry ?: this.awayTeam.abbreviation,
    homeScore = this.homeTeam.score,
    awayScore = this.awayTeam.score,
    homePenaltyScore = this.homeTeamPenaltyScore,
    awayPenaltyScore = this.awayTeamPenaltyScore,
    dateTime = LocalDateTime.parse(this.date, matchDateTimeFormat),
    group = this.groupName.value()?.removePrefix("Group ").orEmpty(),
    location = this.stadium.cityName.value().orEmpty(),
    stadium = this.stadium.name.value().orEmpty(),
    matchStatus = this.matchStatus,
    matchTime = this.matchTime,
    winner = this.winner,
)

fun List<ExternalLocaleDescription>?.value() = this?.firstOrNull()?.description

val matchDateTimeFormat = DateTimeFormatter.ISO_DATE_TIME