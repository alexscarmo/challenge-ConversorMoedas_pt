Sejam bem-vindos ao projeto de conversor de moedas.

Ele funciona com 6 opções:

Logo após escolher a opção desejada ele pergunta o valor que se deseja converter e te dá a resposta de acordo com a cotação atual da moeda segundo a API do ExchangeRate,
isso funciona em loop até que se saia do programa escolhendo a opção 7.

O que fazer para o projeto funcionar para você caso queira clonar o repositório:
- Na raiz do seu projeto, um nível acima da pasta src, crie um arquivo chamado (config.properties) e dentro dele insira uma linha com o seguinte
  formato: API_KEY="seu token vindo do site que possui a api que está utilizando".
- Aqui no meu caso estou usando a api ExchangeRate-API (www.exchangerate-api.com), faça seu cadastro se ainda não tiver, gere seu token
 e insira no arquivo mencionado acima.
- Este projeto usa os princípios SOLID, caso não esteja utilizando a API citada acima mas sim uma outra, construa sua própria classe ou
  edite a classe que é específica para essa API para utilizar outra, isso não impactará no funcionamento porque não se tem acoplamento entre as classes.
