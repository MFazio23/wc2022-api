package dev.mfazio.wc2022.routing

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
            println(ScheduleRepository.getScheduledMatches())
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
                //TODO: update to pull from DB
                val date = LocalDate.parse(call.parameters["date"], DateTimeFormatter.ISO_LOCAL_DATE)
                val scheduledMatches = ScheduleRepository.getExternalScheduleForDate(date)
                call.respond(
                    APIResponse(
                        data = scheduledMatches
                    )
                )
            }
            put {
                //TODO: Update a single day
                call.respond(
                    APIResponse.notYetImplemented
                )
            }
        }
    }
}