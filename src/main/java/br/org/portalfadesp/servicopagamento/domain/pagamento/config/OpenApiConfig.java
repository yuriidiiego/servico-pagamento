package br.org.portalfadesp.servicopagamento.domain.pagamento.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "Servico de Pagamento API",
    version = "1.0",
    description = "API para gerenciamento de pagamentos de debitos de pessoas físicas e jurídicas.",
    contact = @Contact(
      name = "Yuri Nascimento",
      email = "yuriidiiego@gmail.com",
      url = "https://github.com/yuriidiiego"
    ),
    license = @License(
      name = "Apache 2.0",
      url = "http://www.apache.org/licenses/LICENSE-2.0.html"
    )
  ),
  servers = {
    @Server(
      url = "http://localhost:8080",
      description = "Servidor local de desenvolvimento"
    ),
  }
)
public class OpenApiConfig {}
