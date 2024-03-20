O Banquinho é uma aplicação Java simples que simula algumas interações de banco utilizando conceitos simples de Programação Orientada a Objetos e Arquivos. 

Nesta aplicação, o usuário é capaz de:
- Criar uma conta, recebendo com isso um número de conta, número da agência, um saldo de 0 reais e um limite pré aprovado de 1000 reais. Para isso é necessário fornecer as seguintes informações:
    - Nome
    - CPF (Necessário inserir um CPF válido)
    - Tipo (se é um cartão de crédito ou debito)
    - Uma senha (Minimo de 6 digitos, com uma letra maíuscula, uma minúscula, um caracter especial e um número)

- Logar em uma conta criada, fornecendo o número da conta, agência e senha;
- Uma vez logado, é possível:
  - Transferir dinheiro para outra conta, basta fornecer o número, agência e valor (este valor precisa estar dentro do limite);
  - Depositar dinheiro: Basta inserir a quantia depositada;
  - Retirar dinheiro: Basta inserir a quantia removida. É necessário que o valor esteja abaixo do limite aprovado;
  - Alterar o limite: É possível alterar o limite para o valor que quiser. Porém, no período de 22h até 6h só é possível diminuir o limite;
  - Ver extrato: Visualizar por texto o seu histórico de transações;
  - Gerar extrato: Gerar um arquivo .csv com seu histórico de transação.
 
- Sempre que o usuário clicar em desligar a máquina, todas as informações serão salvas em um arquivo para que possa ser lido assim que a máquina for reiniciada.
Toda a aplicação está dentro de loops para simular o funcionamento contínuo da ATM.

Sugestão de usuário:
Gil Gomes
Conta: 14
Agencia: 123
Senha: Teste@123


Abaixo, alguns diagramas de sequência:

Logar e sair.
<img width="750" alt="mermaid-diagram-2024-03-19-203333" src="https://github.com/joaocosta12/javaBankProject/assets/22662376/dd0a4c2d-aae3-4b1d-908c-86bc61d17493">


Criação de conta e sair
<img width="750" alt="mermaid-diagram-2024-03-19-205403" src="https://github.com/joaocosta12/javaBankProject/assets/22662376/18371612-2c06-48ce-a086-738a67f9e4ca">


Transferir dinheiro:
<img width="750" alt="mermaid-diagram-2024-03-19-210253" src="https://github.com/joaocosta12/javaBankProject/assets/22662376/1877ec3a-47e5-4fd1-a6d1-bfc6b4b63423">


Depositar dinheiro:
<img width="750" alt="mermaid-diagram-2024-03-19-210533" src="https://github.com/joaocosta12/javaBankProject/assets/22662376/0688533c-6dd8-4e14-9971-11b6f4b5a8bc">


Retirar dinheiro:
<img width="750" alt="mermaid-diagram-2024-03-19-210656" src="https://github.com/joaocosta12/javaBankProject/assets/22662376/9afbee53-ef44-49c2-b8b9-ccb6646d38c1">


Alterar o limite
<img width="750" alt="mermaid-diagram-2024-03-19-211037" src="https://github.com/joaocosta12/javaBankProject/assets/22662376/a5aee07f-84af-438c-b927-24091c23d6d2">


Ver Extrato:
<img width="750" alt="mermaid-diagram-2024-03-19-211216" src="https://github.com/joaocosta12/javaBankProject/assets/22662376/a8216878-1c67-4781-927d-6b8b29f499d2">


Gerar extrato:
![mermaid-diagram-2024-03-19-211636](https://github.com/joaocosta12/javaBankProject/assets/22662376/5c58b76e-e0be-4a8a-a456-5280ddce9ff6)


