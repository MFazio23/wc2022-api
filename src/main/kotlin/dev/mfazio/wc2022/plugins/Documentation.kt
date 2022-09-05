package dev.mfazio.wc2022.plugins

import com.google.api.client.json.JsonPolymorphicTypeMap.TypeDef
import dev.mfazio.wc2022.types.api.TeamApiModel
import io.bkbn.kompendium.core.plugin.NotarizedApplication
import io.bkbn.kompendium.json.schema.KotlinXSchemaConfigurator
import io.bkbn.kompendium.json.schema.definition.JsonSchema
import io.bkbn.kompendium.json.schema.definition.TypeDefinition
import io.bkbn.kompendium.oas.OpenApiSpec
import io.bkbn.kompendium.oas.info.Contact
import io.bkbn.kompendium.oas.info.Info
import io.bkbn.kompendium.oas.server.Server
import io.ktor.server.application.*
import java.net.URI
import kotlin.reflect.typeOf

fun Application.configureDocumentation() {
    install(NotarizedApplication()) {
        spec = OpenApiSpec(
            info = Info(
                title = "WC2022 API",
                version = "0.0.1",
                description = "Supporting APIs for the WC2022 fantasy game",
                termsOfService = URI(""), //TODO: Get the URL.
                contact = Contact(
                    //TODO: Enter this info as well.
                    name = "",
                    email = "",
                    url = URI(""),
                )
            ),
            servers = mutableListOf(
                Server(
                    url = URI("https://wc2022-api.mfazio.dev"),
                    description = "Production instance of WC2022 API"
                ),
                Server(
                    url = URI("https://wc2022-api-staging.mfazio.dev"),
                    description = "Staging instance of WC2022 API"
                ),
            )
        )
        schemaConfigurator = KotlinXSchemaConfigurator()
        customTypes = mapOf(
            typeOf<TeamApiModel>() to TypeDefinition(
                type = "string",
                properties = mapOf(
                    "teamId" to TypeDefinition.STRING,
                    "teamName" to TypeDefinition.STRING,
                    "group" to TypeDefinition.STRING,
                )
            )
        )
    }
}