# Estrutura de Classes Sugerida

Esta é uma proposta de estrutura de classes para o sistema CuboLab Inteligente, focada em extensibilidade, coesão e baixo acoplamento.

---

## Diagrama de Classes (Conceitual)

```
[Espaco] (abstract)
  - id: UUID
  - nome: String
  - localizacao: String
  - capacidade: int
  - agenda: Agenda
  + isDisponivel(intervalo): boolean
  + adicionarReserva(reserva): void

      |
      |--- [SalaReuniao]
      |      - temRecursosAV: boolean
      |
      |--- [MesaIndividual]
      |
      |--- [Auditorio]


[Agenda]
  - reservas: List<Reserva>
  - manutencoes: List<PeriodoManutencao>
  + verificarConflito(intervalo): boolean

[Reserva]
  - id: UUID
  - intervalo: IntervaloDeTempo
  - solicitante: String
  - numParticipantes: int
  - finalidade: FinalidadeReserva (Enum)
  - espaco: Espaco

[PeriodoManutencao]
  - intervalo: IntervaloDeTempo
  - descricao: String

[SistemaReservas]
  - espacos: Map<UUID, Espaco>
  + registrarEspaco(espaco): void
  + fazerReserva(solicitacao): Reserva
  + cancelarReserva(reservaId): void
  + buscarEspacoDisponivel(requisitos): Espaco

[FinalidadeReserva] (Enum)
  - REUNIAO
  - WORKSHOP
  - CHAMADA
  - ESTUDO
```

---

## Descrição dos Componentes

### 1. `Espaco` (Classe Abstrata)
- **Responsabilidade:** Define a estrutura e o comportamento comuns a todos os espaços de trabalho.
- **Atributos:** `id`, `nome`, `localizacao`, `capacidade`, e uma instância de `Agenda`.
- **Métodos:** `isDisponivel()`, `adicionarReserva()`, etc.

### 2. Classes Concretas de Espaço (`SalaReuniao`, `MesaIndividual`, `Auditorio`)
- **Responsabilidade:** Representam os tipos específicos de espaços.
- **Herança:** Herdam da classe `Espaco`.
- **Atributos Específicos:** Podem ter atributos próprios, como `temRecursosAV` em `SalaReuniao`.

### 3. `Agenda`
- **Responsabilidade:** Gerenciar a lista de reservas e os períodos de manutenção de um único espaço. É o principal componente para verificar conflitos de horário.
- **Atributos:** Uma lista de `Reserva` e uma lista de `PeriodoManutencao`.

### 4. `Reserva`
- **Responsabilidade:** Representa uma alocação de um espaço por um cliente para um determinado período e finalidade.
- **Atributos:** Contém todas as informações da reserva, incluindo o vínculo com o `Espaco` reservado.

### 5. `SistemaReservas` (Orquestrador)
- **Responsabilidade:** Atua como a fachada (Facade) do sistema. Centraliza as principais operações, como registrar novos espaços, processar solicitações de reserva e buscar alternativas.
- **Atributos:** Mantém uma coleção de todos os `Espacos` disponíveis, preferencialmente em uma estrutura de dados que permita busca rápida (ex: `Map<UUID, Espaco>`).

### 6. Enums e Tipos de Valor
- **`FinalidadeReserva` (Enum):** Padroniza os tipos de finalidade de uma reserva.
- **`IntervaloDeTempo` (Record/Classe):** Um objeto de valor para representar o início e o fim de um evento, facilitando a lógica de verificação de sobreposição.
