 # Aula 0

 ## Motivação

 Atender um grande numero de clientes e pedidos com um único servidor.

 Exemplos: 

 * aplicações financeiras, jogos;
 * notificacoes em aplicações mobile;
 * máquina para máquina (M2M)

Ocorreu a primeira vez em 1999 - O problema "c10k"


## Caso de estudo

Simples servidor de chat - redireciona todas as mensagens recebidas para todos os clientes

Forma mais simples de aplicação multi-user baseada em servidor:

* Input de clientes
* Estado e lógica internos
* Output para clientes acionado por Input

Considerar:

* Grande número de clientes
*

### Primeira solução threaded

Para cada conexão - handler via threads

Enquanto escreve, envia para todas as conexoes

Utilizar buffering:

* A nivel de utulizador (streams): minimizar system calls - podemos controlar a memoria ocupada
* A nivel do kernel (socket): para lidar com leitores lentos - nao podemos controlar a memoria

#### Problema

Se alguma thread bloquear, as threads a seguir não vao conseguir receber a informacao, pois os buffers
ficam cheios.

### Thread de escrita

A thread de leitura guarda as mensagens numa fila de espera, da qual a thread de escrita retira a informacao
para escrever. Permitindo que havendo uma thread bloqueante não perturbe as outras.

#### Problema

Podem crescer infinitamente as filas. (pode ser resolvido, eliminar pedidos antigos, deixar de refrescar, etc.)

Má em termos de memória:

* cada thread precisa de alocar memória;
* a fila precisa de alocar memória
* n conexões \* mensagens em trânsito (~ *n²*) - causado por cópias de memória -> Serialização!
* Overhead em alocação e *garbage collection*

### Arquitetura pretendida

N threads de saida e entrada

O estado partilhado seria as filas de mensagens.

Threads de escrita "sabem" qual é a próxima mensagem, e este tenta tirar a mensagem da fila
e enviar, ficando idealmente sem mensagens em fila de espera.

As mensagens podiam ser armazenadas já serializadas.

Mensagens são lidas e colocadas imediatamente na fila

### Introducao de sockets em java.nio

Para implementar a solução pretendida


```java
ServerSocketChannel ss = ServerSocketChannel.open();
ss.bind(new InetSocketAddress(12345));

while(true){
    // nao consegui ler tudo
    // SocketChannel sc = ss.
}
```

Utiliza o objeto ByteBuffer para enviar, encapsulando a alocacao de memoria e o
buffer em si

Temos um conjunto de apontadores privado para cada instância de ByteBuffer mas
os dados em si são alocados e armazenados 1 vez

#### Buffers partilhados

Memória usada: mensagens em trânsito (~*n*)

Idealmente, nunca alocar ou descartar da memoria em operação normal:

* Não tem overhead mas precisa de contagem de referencias para saber quando reutilizar

Bom tradeoff:

* Tirar partido do *garbage collector*
* Reduzir a cópia e a duplicação de objetos grandes

### Resumo threads

Modelo de programacao simples

Problemas:

* Overhead de memória (stacks) - cada thread tem a sua stack
* Contenção de switches e locks de contexto
* Filas escondidas; justiça (*fairness*) - gerido pelo SO, pelo que não pode ser
controlado pelo programador



[link do guiao](https://tinyurl.com/psd2324) 
