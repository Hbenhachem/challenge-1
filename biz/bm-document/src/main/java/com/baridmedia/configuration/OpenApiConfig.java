package com.baridmedia.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "hicham benhachem",
                        email = "hichambenhachemsec@gmail.com"
                ),
                description = "Coding challenge",
                title = "OpenApi specification"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8084"
                )
        }
)
public class OpenApiConfig {
}
