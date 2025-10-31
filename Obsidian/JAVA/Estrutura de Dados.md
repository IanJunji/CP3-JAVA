# Escolha e Justificativa das Estruturas de Dados

A escolha correta das estruturas de dados é fundamental para atender aos requisitos de desempenho e integridade do sistema CuboLab Inteligente.

---

## 1. Armazenamento de Espaços no Sistema

**Requisito:** O sistema precisa de acesso rápido aos espaços por identificador (ID).

- **Estrutura Escolhida:** `java.util.HashMap<UUID, Espaco>`
  - **Chave:** O `UUID` do espaço.
  - **Valor:** O objeto `Espaco` correspondente.

- **Justificativa:**
  - **Desempenho:** O `HashMap` oferece tempo de acesso médio constante, **O(1)**, para operações de busca, inserção e remoção pelo ID. Isso é ideal para o `SistemaReservas` encontrar rapidamente um espaço específico.
  - **Simplicidade:** É uma estrutura de fácil implementação e uso para o cenário de busca por chave única.

---

## 2. Armazenamento de Reservas e Manutenções na Agenda

**Requisito:** A `Agenda` de cada espaço precisa verificar eficientemente se há conflitos de horário para novas reservas. As reservas são naturalmente ordenadas por data/hora.

- **Estrutura Escolhida:** `java.util.ArrayList<Reserva>` ou `java.util.TreeSet<Reserva>`

### Opção A: `ArrayList<Reserva>`
- **Justificativa:**
  - **Simplicidade:** É a estrutura mais simples de implementar.
  - **Cenário de Poucas Reservas:** Para um número pequeno de reservas por espaço, percorrer a lista para verificar conflitos é suficientemente rápido. A complexidade seria **O(n)**, onde 'n' é o número de reservas.
  - **Implementação da Verificação:** A lógica de verificação de sobreposição de intervalos teria que ser implementada manualmente, iterando sobre cada `Reserva` e `PeriodoManutencao` na lista.

### Opção B: `TreeSet<Reserva>` (com `Comparator` por data de início)
- **Justificativa:**
  - **Ordenação Automática:** O `TreeSet` mantém os elementos ordenados. Se usarmos um `Comparator` que ordena as reservas pela data de início, as verificações de conflito podem ser mais eficientes.
  - **Desempenho de Busca:** Permite buscas, inserções e remoções em tempo **O(log n)**.
  - **Otimização:** É possível implementar uma lógica de verificação de conflitos mais otimizada, aproveitando a ordenação para não precisar verificar a lista inteira. Por exemplo, pode-se buscar o "piso" (floor) ou "teto" (ceiling) de uma reserva e verificar apenas os vizinhos.

- **Decisão:** Para este projeto, começar com `ArrayList` é aceitável pela simplicidade. Se o desempenho se tornar um problema, a refatoração para um `TreeSet` ou uma estrutura de dados de intervalo (Interval Tree) seria o próximo passo lógico.

---

## 3. Conclusão

- **`SistemaReservas`:** Usará um `HashMap<UUID, Espaco>` para gerenciar todos os espaços.
- **`Agenda`:** Usará um `ArrayList<Reserva>` e `ArrayList<PeriodoManutencao>` pela simplicidade de implementação, que é adequada para o escopo do desafio. A lógica de verificação de conflitos será uma iteração simples pela lista.
