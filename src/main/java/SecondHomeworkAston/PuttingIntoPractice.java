package SecondHomeworkAston;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
        1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).

        2. Вывести список неповторяющихся городов, в которых работают трейдеры.

        3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.

        4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.

        5. Выяснить, существует ли хоть один трейдер из Милана.

        6. Вывести суммы всех транзакций трейдеров из Кембриджа.

        7. Какова максимальная сумма среди всех транзакций?

        8. Найти транзакцию с минимальной суммой.
*/

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> sortedTransactionsIn2011YearList = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted((o1, o2) -> o1.getValue() - o2.getValue())
                .toList();
        System.out.println("Список отсортированных транзакций в 2011 году: " + sortedTransactionsIn2011YearList);

        List<String> unRepeatedTowns = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .toList();
        System.out.println("Список неповторяющихся городов: " + unRepeatedTowns);

        List<String> tradersFromCambridge = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(traderName -> traderName.getTrader().getName())
                .distinct()
                .sorted()
                .toList();
        System.out.println("Список трейдеров из Кембриджа: " + tradersFromCambridge);

        String allTradersString = transactions.stream()
                .map(traderName -> traderName.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(" "));
        System.out.println("Строка из имен трейдеров - " + allTradersString);

        boolean areTradersFromMilan = transactions.stream()
                .anyMatch(town -> town.getTrader().getCity().equals("Milan"));
        System.out.println("Есть трейдеры из Милана? " + areTradersFromMilan);

        List<Integer> allSumTradersValuesFromCambridge = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(x -> x.getValue()).toList();
        System.out.println("Суммы транзакций всех трейдеров из Кембриджа = " + allSumTradersValuesFromCambridge);

        Integer maxTradersValue = transactions.stream()
                .max((o1, o2) -> o1.getValue() - o2.getValue())
                .map(x -> x.getValue())
                .get();
        System.out.println("Транзакция с максимальной суммой = " + maxTradersValue);

        Integer minTradersValue = transactions.stream()
                .min((o1, o2) -> o1.getValue() - o2.getValue())
                .map(x -> x.getValue())
                .get();
        System.out.println("Транзакция с минимальной суммой = " + minTradersValue);
    }
}