package dev.mfazio.wc2022.extensions

import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

val utcZoneId = ZoneId.of("UTC")

fun LocalDate.atStartOfDayUtc() = this.atStartOfDay(utcZoneId)
fun LocalDate.atEndOfDay() = this.atStartOfDay().plusDays(1).minusSeconds(1)
fun LocalDate.atEndOfDay(zoneId: ZoneId) = this.atStartOfDay(zoneId).plusDays(1).minusSeconds(1)
fun LocalDate.atEndOfDayUtc() = this.atEndOfDay(utcZoneId)