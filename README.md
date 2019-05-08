# Zabbix-Core

Este projeto encapsula a complexidade de comunicação com o servidor Zabbix, minimizando a necessidade de configuração e 
dinamizando a criação de alertas nos demais projetos.

## Instalação

### Passo I - Configurar maven
Crie o arquivo `settings.xml` dentro da pasta .m2 com o seguinte conteúdo:

```
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>nexus-stelo</id>
      <username>admin</username>
      <password>admin123</password>
    </server>
  </servers>
</settings>
```

### Passo II - Adicionar repositório e dependência do maven

Repositório Maven:
```
<repositories>
    <repository>
        <id>nexus-stelo</id>
        <url>http://zocker01.stelo.intranet:8081/repository/maven-releases/</url>
    </repository>
</repositories>
```

Dependência Maven:
```
<dependency>
    <groupId>br.com.stelo</groupId>
    <artifactId>zabbix-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Utilização

[Projeto exemplo](http://google.com.br)

### Exemplo I - Adicionando Health Check

#### Passo I - Adicionando propriedades de configuração

É necessário adicionar as seguintes propriedades no `application.properties` ou `application.yml`

```
#Propriedades obrigatórias
zabbix.server.host=zabbix-homolog.hml1.stelo.local
zabbix.data.host=credenciamento
zabbix.data.key=credenciamento.email

#Propriedades não obrigatórias (Valores padrão)
zabbix.server.port=10051
zabbix.data.value=online
zabbix.health-check.interval=5000
```
**zabbix.server.host** - Host do servidor Zabbix (Sem http/https) \
**zabbix.data.host** - Host do grupo de alertas \
**zabbix.data.key** - Identificador do alerta \
**zabbix.server.port** - Porta do servidor Zabbix (Padrão 10051) \
**zabbix.data.value** - Valor enviado no alerta (Padrão 'online')
**zabbix.health-check.interval** - Valor em milissegundos do intervalo do alerta (Padrão 5000)

#### Passo II - Anotar a classe main do Spring Boot
```
@EnableZabbixHealthCheck
@SpringBootApplication
public class SpringApplication {
  
  public static void main(String[] args) {
	SpringApplication.run(SpringApplication.class, args);
  }
  
}
```

### Exemplo II - Criação de alerta dinâmico

```
public static void main(String[] args) throws IOException {

    ZabbixData data = ZabbixData.builder()
            .host("teste")
            .key("teste-key")
            .value("123")
            .clock(System.currentTimeMillis() / 1000)
            .build();

    ZabbixServer server = ZabbixServer.builder()
            .host("zocker01.stelo.intranet")
            .port(10051)
            .build();


    ZabbixAlerta alerta = ZabbixAlerta.builder()
            .server(server)
            .data(data)
            .build();

    ZabbixAlertaServico servico = ZabbixAlertaServico.getInstance();
    servico.enviar(alerta);

}
```

## Autores
* **Ícaro Pinho Esmeraldo** - [Email](mailto:icaro.edeploy@stelo.com.br)
* **Lucas Rodrigues Alves** - [Email](mailto:lucas.edeploy@stelo.com.br)
