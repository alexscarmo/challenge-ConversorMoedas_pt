import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter(new ExchangeRateApiClient());
        String menu = """
                    *********************************************************************
                    Seja bem-vindo(a) ao conversor de moedas =]
                    
                    Selecione uma das opções abaixo para continuar:
                    
                    1) Dólar ==> Peso Argentino
                    2) Peso Argentino ==> Dólar
                    3) Dólar ==> Real Brasileiro
                    4) Real Brasileiro ==> Dólar
                    5) Dólar ==> Peso Colombiano
                    6) Peso Colombiano ==> Dólar
                    7) Sair
                    
                    Escolha uma opção válida:
                    *********************************************************************
                    """;

        while (true) {
            System.out.println(menu);

            try {
                int option = leitor.nextInt();

                if (option == 7) {
                    System.out.println("Encerrando o programa...");
                    break;
                }

                String from = "", to = "";
                switch (option) {
                    case 1 -> { from = "USD"; to = "ARS"; }
                    case 2 -> { from = "ARS"; to = "USD"; }
                    case 3 -> { from = "USD"; to = "BRL"; }
                    case 4 -> { from = "BRL"; to = "USD"; }
                    case 5 -> { from = "USD"; to = "COP"; }
                    case 6 -> { from = "COP"; to = "USD"; }
                    default -> {
                        System.out.println("Opção inválida! Tente novamente.");
                        continue;
                    }
                }

                System.out.print("Digite o valor que se deseja converter: ");
                double amount = leitor.nextDouble();
                double result = converter.convert(from, to, amount);
                System.out.println("O valor de " + from + " " + amount + " convertidos em " + to + " ficam em: " + result);

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira apenas números.");
                leitor.nextLine();
            }
        }

        leitor.close();
    }
}
