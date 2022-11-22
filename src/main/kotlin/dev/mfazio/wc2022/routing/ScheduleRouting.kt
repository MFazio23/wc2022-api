package dev.mfazio.wc2022.routing

import dev.mfazio.wc2022.extensions.badRequest
import dev.mfazio.wc2022.extensions.ok
import dev.mfazio.wc2022.repositories.ScheduleRepository
import dev.mfazio.wc2022.types.api.ScheduledMatchApiModel
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun Route.scheduleRouting() {
    route("schedule") {
        get {
            call.respond(
                APIResponse(
                    data = ScheduleRepository.getScheduledMatches()
                )
            )
        }
        put {
            val schedule = ScheduleRepository.updateSchedule()
            call.respond(schedule.map(ScheduledMatchApiModel.Companion::fromScheduledMatch).sortedBy { it.dateTime })
        }
        route("{date}") {
            get {
                val date = LocalDate.parse(call.parameters["date"], DateTimeFormatter.ISO_LOCAL_DATE)
                val scheduledMatches = ScheduleRepository.getScheduledMatchesForDate(date)
                call.respond(
                    APIResponse(
                        data = scheduledMatches.map(ScheduledMatchApiModel.Companion::fromScheduledMatch)
                    )
                )
            }
            put {
                val date = LocalDate.parse(call.parameters["date"], DateTimeFormatter.ISO_LOCAL_DATE)
                val scheduledMatches = ScheduleRepository.updateScheduleForDate(date)

                if (scheduledMatches.none()) return@put call.badRequest("Matches not found.")

                call.ok(scheduledMatches.map(ScheduledMatchApiModel.Companion::fromScheduledMatch))
            }
        }
    }
}