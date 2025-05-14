<div align="center">

# 👋 Bem-vindo ao Projeto de Conversor de Moedas

### 💱 Converta moedas em tempo real com base nas cotações atualizadas da [ExchangeRate API](https://www.exchangerate-api.com)

<img src="images/Menu.png" alt="Menu de opções do conversor" width="500"/>

</div>

---

Este projeto em Java permite converter moedas em tempo real, utilizando dados da API ExchangeRate. Ele apresenta um menu com **6 opções de conversão**, funcionando em **loop** até que o usuário selecione a **opção 7 para sair**.

A cada escolha, o sistema solicita o valor desejado e retorna o valor convertido, com base na **cotação mais atualizada** da moeda selecionada.

---

Para rodar o projeto localmente:

1. Na **raiz do projeto** (um nível acima da pasta `src`), crie um arquivo chamado:  
   `config.properties`

2. Dentro desse arquivo, adicione:
   ```properties
   API_KEY=seu_token_vindo_da_api
   ```

3. A API usada neste projeto é a [ExchangeRate-API](https://www.exchangerate-api.com).  
   - Cadastre-se no site  
   - Gere sua chave de acesso (API Key)  
   - Insira a chave no arquivo `config.properties`

---

Este projeto foi desenvolvido seguindo os princípios do **SOLID**, o que significa que:

- As classes são **desacopladas** e **independentes**.
- Você pode facilmente adaptar o projeto para usar **outra API de conversão de moedas**.
- Basta criar uma nova classe para sua API ou modificar a existente sem afetar o funcionamento geral do sistema.

---

Você pode clonar o repositório e testar o sistema localmente:

🔗 [Acessar repositório no GitHub](https://github.com/alexscarmo/challenge-ConversorMoedas_pt)

```bash
git clone https://github.com/alexscarmo/challenge-ConversorMoedas_pt
cd challenge-ConversorMoedas_pt
```

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

public class ExchangeRateService {

    private final String apiKey;

    public ExchangeRateService() {
        this.apiKey = carregarApiKey();
    }

    private String carregarApiKey() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
            return prop.getProperty("API_KEY").replace("\"", "").trim();
        } catch (IOException ex) {
            throw new RuntimeException("Erro ao carregar API_KEY do arquivo config.properties", ex);
        }
    }

    public double obterTaxa(String de, String para) {
        String urlStr = String.format(
            "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s",
            apiKey, de, para
        );

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            Scanner scanner = new Scanner(conexao.getInputStream());
            StringBuilder resposta = new StringBuilder();
            while (scanner.hasNext()) {
                resposta.append(scanner.nextLine());
            }
            scanner.close();

            String json = resposta.toString();
            int start = json.indexOf("\"conversion_rate\":") + 18;
            int end = json.indexOf(",", start);
            String taxaStr = json.substring(start, end);
            return Double.parseDouble(taxaStr);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao buscar a taxa de câmbio", e);
        }
    }

    public static void main(String[] args) {
        ExchangeRateService service = new ExchangeRateService();
        double taxa = service.obterTaxa("USD", "BRL");
        System.out.printf("Taxa USD → BRL: %.2f%n", taxa);
    }
}
```

---

<div align="center">

Feito com 💻 e ☕ por [Alex S. Carmo](https://github.com/alexscarmo)

</div>

