# Serviços e Casos de Uso

Este documento descreve os principais serviços que o sistema `CuboLab Inteligente` deve expor e como eles se relacionam com os casos de uso do usuário. A classe `SistemaReservas` atuará como a camada de serviço principal.

---

## 1. Definição da Camada de Serviço (`SistemaReservas`)

A classe `SistemaReservas` será a fachada (Facade) para todas as operações do sistema. Ela orquestra as interações entre os espaços, agendas e reservas.

```java
public class SistemaReservas {
    // Armazena todos os espaços, com acesso rápido por ID.
    private Map<UUID, Espaco> espacos;

    // Construtor
    public SistemaReservas() {
        this.espacos = new HashMap<>();
    }

    // ... métodos de serviço
}
```

---

## 2. Casos de Uso e Métodos de Serviço Correspondentes

### Caso de Uso 1: Administrador registra um novo espaço
- **Descrição:** Um administrador adiciona um novo espaço (sala, mesa ou auditório) ao sistema.
- **Método de Serviço:**
  ```java
  public void registrarEspaco(Espaco espaco) {
      // Adiciona o espaço ao mapa de espaços gerenciados.
      // Valida se o ID já não existe.
  }
  ```

### Caso de Uso 2: Cliente solicita uma reserva
- **Descrição:** Um cliente quer reservar um espaço, fornecendo seus requisitos (tipo de espaço, capacidade, data/hora, etc.).
- **Método de Serviço:**
  ```java
  public Reserva solicitarReserva(SolicitacaoReserva solicitacao) {
      // 1. Busca um espaço compatível com a solicitação.
      // 2. Se um espaço for encontrado:
      //    a. Verifica a disponibilidade na agenda do espaço.
      //    b. Se estiver disponível, cria e adiciona a reserva.
      //    c. Retorna a Reserva confirmada.
      // 3. Se não houver espaço, lança uma exceção ou retorna null.
  }
  ```
- **Classes de Apoio:**
  - `SolicitacaoReserva`: Um DTO (Data Transfer Object) que carrega os dados do pedido do cliente (ex: `capacidadeMinima`, `intervalo`, `tipoEspaco`).

### Caso de Uso 3: Cliente cancela uma reserva
- **Descrição:** Um cliente decide cancelar uma reserva previamente agendada.
- **Método de Serviço:**
  ```java
  public void cancelarReserva(UUID reservaId) {
      // 1. Encontra a reserva pelo seu ID.
      // 2. Encontra o espaço associado a essa reserva.
      // 3. Remove a reserva da agenda do espaço.
  }
  ```

### Caso de Uso 4: Administrador agenda uma manutenção
- **Descrição:** Um administrador bloqueia um espaço para manutenção em um determinado período.
- **Método de Serviço:**
  ```java
  public void agendarManutencao(UUID espacoId, PeriodoManutencao manutencao) {
      // 1. Encontra o espaço pelo ID.
      // 2. Adiciona o período de manutenção à agenda do espaço.
      //    (A agenda deve impedir reservas nesse período).
  }
  ```

### Caso de Uso 5: Usuário consulta a agenda de um espaço
- **Descrição:** Um usuário quer ver todas as reservas e manutenções de um espaço para uma data específica.
- **Método de Serviço:**
  ```java
  public Agenda consultarAgenda(UUID espacoId, LocalDate data) {
      // 1. Encontra o espaço pelo ID.
      // 2. Retorna os eventos (reservas e manutenções) da agenda para a data especificada.
  }
  ```

### Caso de Uso 6 (Interno): Buscar espaço alternativo
- **Descrição:** Se o espaço primário não estiver disponível, o sistema tenta encontrar outro que atenda aos requisitos.
- **Método de Serviço (privado ou protegido):**
  ```java
  private Optional<Espaco> buscarAlternativa(SolicitacaoReserva solicitacao) {
      // Itera sobre todos os espaços e retorna o primeiro que seja
      // compatível com a solicitação e esteja disponível no intervalo desejado.
  }
  ```
