# Projeto e Implementação de Arquitetura de Micro serviços

## Descrição Geral:
Desenvolver uma arquitetura de micro serviços para um sistema de e-commerce que precisa lidar com autenticação robusta, gerenciamento eficiente de produtos e processamento ágil de pedidos. Este sistema deve ser altamente disponível, escalável e manter a integridade dos dados em operações concorrentes.

## Requisitos Específicos:
- Definir a arquitetura e as interações entre os serviços: Planejar como os micro serviços irão se comunicar e se integrar, definindo padrões de comunicação como REST ou mensageria assíncrona.
- Implementar serviços usando Docker: Utilizar Docker para criar, configurar e implantar cada serviço de forma independente, facilitando a escalabilidade e a manutenção.
- Assegurar comunicação eficiente e segura entre serviços: Implementar mecanismos de segurança como autenticação de serviços, criptografia de tráfego e outras práticas recomendadas de segurança para proteger as comunicações entre os serviços.

## Tecnologias Sugeridas:
- Docker para conteinerização.
- Docker Compose para orquestração local.
- Java e Frameworks como Spring Boot e/ou Quarkus para o desenvolvimento dos serviços.
- Sistemas de mensageria como RabbitMQ ou Kafka para comunicação assíncrona.

## Cenário Proposto

### Contexto:
Você é o arquiteto de sistemas em uma grande empresa de varejo online que está migrando sua plataforma monolítica existente para uma arquitetura baseada em micro serviços. A principal motivação para esta migração é melhorar a escalabilidade, a flexibilidade e a capacidade de manutenção do sistema, além de facilitar a integração contínua e a entrega contínua (CI/CD).

### Desafio:
Projete e implemente a arquitetura de microservices começando com três serviços essenciais: autenticação, gerenciamento de produtos e processamento de pedidos. Cada serviço deve ser autônomo e comunicar-se com os outros de maneira segura e eficiente.

### Objetivos do Desafio:
- Criar diagramas de arquitetura que detalham componentes, responsabilidades e interações.
- Desenvolver cada micro serviço usando Docker para garantir a consistência entre os ambientes de desenvolvimento e produção.
- Implementar mecanismos de segurança para as comunicações entre os serviços, usando técnicas como HTTPS, autenticação JWT, etc.
- Documentar o processo de design e implementação, incluindo decisões arquiteturais tomadas, problemas enfrentados e soluções adotadas.

### Critérios de Aceitação:
- Cada micro serviço é completamente funcional e opera de forma independente.
- A comunicação entre serviços é segura e não possui gargalos perceptíveis.
- A documentação é clara e detalhada, incluindo diagramas de arquitetura e descrições dos protocolos de comunicação.

---

# Decisões

- Implementado usando Java 17

O motivo da escolha é o fato de querer trazer maior segurança pra aplicação usando que é mais recente, mas entendendo que nem toda infra suporta as versões mais recentes como Java 20+;

- Uso de banco de dados H2 independente (in memory)

Aqui o uso do H2 foi mais para trazer agilidade no desenvolvimento, já que não tem obrigação de usar. Como cada sistema deve conseguir comunicar com o outro, o uso de H2 em memória força que um microsserviço necessite do outro para conseguir coletar as informações necessárias como as de autenticação, por exemplo.

- Arquitetura DDD

A motivação para uso de DDD é pela organização de código trazendo maior visibilidade dos domínios e de onde encontrar cada componente. Fiz uma combinação de DDD com MVC trazendo maior distribuição sobre o que é de comunicação externa como os controladores e comunicação com banco de dados com os repositories e entidades proporcionando maior abstração sobre o que é trafegado internamente na aplicação.
