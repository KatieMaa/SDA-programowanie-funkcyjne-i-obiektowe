package sda.finances;

import sda.finances.model.Expense;
import sda.finances.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RENT on 2017-02-20.
 */
public class Application {
    public static void main(String[] args) {
        List<Expense> expenses = init();

        //1. Wyswietlic wszystkie towary, ktorych cena jednostkowa jest mniejsza od 3
        expenses.forEach(expense -> {
            expense.getProducts().stream()
                    .filter(product -> product.getUnitPrice() <= 3)
                    .forEach(product -> System.out.println(product));
        });
        System.out.println();


        //2. Wyswietlic tylko produkty spozywcze ktorych cena mniejsza od 3
        expenses.stream()
                .filter(expense -> expense.getType().equals("groceries"))
                .forEach(expense -> {
                    expense.getProducts().stream()
                            .filter(product -> product.getUnitPrice() <= 3)
                            .forEach(product -> System.out.println(product));
                });
    }
    //3.Wyswietlic tylko banany
    //4.Suma cen wszytskich produktow spozywczych


    private static List<Expense> init() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("apple", 5, 3));
        products.add(new Product("cheese", 3, 2));
        products.add(new Product("ham", 10, 6));
        products.add(new Product("potato", 8, 1));
        Expense expense = new Expense("groceries", products);

        List<Product> products2 = new ArrayList<>();
        products2.add(new Product("soap", 5, 3));
        products2.add(new Product("shampoo", 3, 2));
        products2.add(new Product("toilet paper", 10, 6));
        products2.add(new Product("dyper", 8, 1));
        Expense expense2 = new Expense("hygienic", products, 2017, 2, 16);

        List<Product> products3 = new ArrayList<>();
        products3.add(new Product("vitamin C", 5, 3));
        products3.add(new Product("vitamin D", 3, 2));
        products3.add(new Product("vitamin K", 10, 6));
        products3.add(new Product("vitamin B6", 8, 1));
        Expense expense3 = new Expense("medicine", products, 2017, 2, 18);

        List<Product> products4 = new ArrayList<>();
        products4.add(new Product("banan", 2, 3));
        products4.add(new Product("kiwi", 4, 2));
        products4.add(new Product("butter", 6, 6));
        products4.add(new Product("ketchup", 8, 1));
        Expense expense4 = new Expense("grocery", products4, 2017, 2, 17);

        return Arrays.asList(expense, expense2, expense3, expense4);
    }
}
