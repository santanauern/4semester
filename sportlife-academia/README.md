# SportLife – Sistema Web para Academia

Sistema corporativo simples para uma academia fictícia (SportLife), implementado com Java, Spring Boot e arquitetura em 3 camadas (Modelo, Negócios e Aplicação), conforme proposta de estudo de caso.

## Cenário da Empresa

A SportLife é uma academia e centro esportivo que oferece planos de mensalidade e modalidades como futebol society, karatê, musculação e outras. Os clientes realizam matrícula em um plano e podem se inscrever em turmas específicas.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3 (Web, Data JPA, Security)
- Maven
- MariaDB (pode ser adaptado para PostgreSQL ou outro banco relacional)
- HTML5, CSS3 e JavaScript (front-end simples)

## Arquitetura em 3 Camadas

- **Modelo / Persistência**
  - Entidades JPA: `Usuario`, `Cliente`, `Plano`, `Matricula`, `Modalidade`, `Turma`, `InscricaoTurma`.
  - Repositórios Spring Data JPA em `br.com.sportlife.repository`.

- **Negócios**
  - Serviços em `br.com.sportlife.service` com as regras de negócio principais, como:
    - Não permitir inscrição em turma lotada.
    - Controle de matrícula ativa/inativa.
    - Listagem de clientes ativos.

- **Aplicação (API / Visão)**  
  - Controladores REST em `br.com.sportlife.controller` expondo endpoints para clientes, planos, matrículas, modalidades, turmas e inscrições.
  - Relatórios simples via `/api/relatorios/*`.
  - Páginas estáticas em `src/main/resources/static` (`login.html`, `clientes.html`).

## Autenticação e Autorização

- Configuradas via Spring Security (HTTP Basic).
- Usuários de teste em memória:
  - `admin` / `admin` – ROLE_ADMIN
  - `atendente` / `atendente` – ROLE_ATENDENTE
- Perfis:
  - ADMIN: acesso total, incluindo cadastro de planos, modalidades e turmas.
  - ATENDENTE: foco em clientes, matrículas, inscrições e relatórios.

## Como Executar o Projeto

1. Certifique-se de ter Java 17+ e Maven instalados.
2. Crie um banco de dados vazio chamado `sportlife` no MariaDB (ou ajuste o `application.properties`).
3. Edite `src/main/resources/application.properties` com usuário e senha do banco.
4. Na raiz do projeto, execute:

   ```bash
   mvn spring-boot:run
   ```

5. Acesse no navegador (autenticação HTTP Basic será solicitada):
   - `http://localhost:8080/clientes.html` – página simples de cadastro/listagem de clientes.
   - Endpoints REST como `http://localhost:8080/api/clientes` etc.

## Endpoints Principais (exemplos)

- `GET /api/clientes` – lista clientes
- `POST /api/clientes` – cria cliente
- `GET /api/planos` – lista planos
- `POST /api/planos` – cria plano
- `GET /api/matriculas/ativas` – lista matrículas ativas
- `POST /api/matriculas?clienteId=1&planoId=1&dataInicio=2025-01-01` – cria matrícula
- `POST /api/inscricoes?turmaId=1&clienteId=1` – inscreve cliente em turma
- `GET /api/relatorios/clientes-ativos` – relatório de clientes ativos
- `GET /api/relatorios/inscricoes` – relatório de inscrições por turma
- Também é possível incrementar com mais páginas HTML/JS, validações e testes automatizados.
