@file:OptIn(KtorExperimentalLocationsAPI::class)

package dev.mfazio.wc2022.routing.locations

import io.ktor.server.locations.*

@Location("/teams")
class TeamLocation {
    @Location("/ranked")
    class RankedTeamsLocation
}