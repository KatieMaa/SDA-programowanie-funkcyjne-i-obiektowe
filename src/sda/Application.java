package sda;

import sda.workers.AbstractEmployee;
import sda.workers.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by RENT on 2017-02-18.
 */
public class Application {
    public static void main(String[] args) {
        List<AbstractEmployee> employeeList = new ArrayList<>();
        employeeList.add(new AbstractEmployee("Szymon", "Nowak", 2000, "Java"));
        employeeList.add(new AbstractEmployee("Jan", "Kowalski", 2700, "Java"));
        employeeList.add(new AbstractEmployee("Katarzyna", "Solna", 5000, "HR"));
        employeeList.add(new AbstractEmployee("Magdalena", "Myszkowska", 3000, "PM"));
        employeeList.add(new AbstractEmployee("Bartosz", "Wolny", 7000, "Director"));

        employeeList.stream()
                .filter(e -> e.getDepartment().equals("Java"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        employeeList.stream()
                .filter(e -> e.getFirstName().endsWith("a"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        employeeList.stream()
                .filter(e -> e.getSalary() >= 3000)
                .forEach(e -> System.out.println(e));
        System.out.println();

        employeeList.stream()
                .filter(e -> e.getDepartment().equals("Java"))
                .filter(e -> e.getSalary() >= 3000)
                .forEach(e -> System.out.println(e));
        System.out.println();

        //za pomoca filter oraz collect zebrac wszystkie osoby
        List<AbstractEmployee> javaEmployees = employeeList.stream()
                .filter(e -> e.getDepartment().equals("Java"))
                .collect(Collectors.toList());
        System.out.println(javaEmployees);
        System.out.println();

        employeeList.stream()
                .filter(e -> e.getLastName().equals("Nowak"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        employeeList.stream()
                .filter(e -> e.getLastName().startsWith("Now"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        //8.Dzielimy liste na mape ludzi, gdzie klucz to ich imie, a wartosc to AbstractEmployee
        Map<String, AbstractEmployee> map = employeeList.stream()
                .collect(Collectors.toMap((e -> e.getFirstName()), e -> e));
        map.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println();

        //9.zwracamy liste Employee szukaj po imie + " " + nazwisko
        employeeList.stream()
                .filter(e -> (e.getFirstName() + " " + e.getLastName()).equals("szymon Nowak"))
                .forEach(e -> System.out.println(e));
        System.out.println();

        //10. Posortuj po salary
        employeeList.sort((e1, e2) ->
                e1.getSalary() > e2.getSalary() ? 1 :
                        e1.getSalary() == e2.getSalary() ? 0 : -1);
        employeeList.forEach(e -> System.out.println(e.getFirstName() + ": " + e.getSalary()));
        System.out.println();

        //11.wyswietl employee, ktory zarabia najwiecej
        AbstractEmployee richestEmployee = employeeList.stream()
                .max((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(richestEmployee);

        //12.wyswietl employee, ktory zarabia najmniej
        AbstractEmployee poorestEmployee = employeeList.stream()
                .min((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(poorestEmployee);

        Map<String, List<AbstractEmployee>> map1 = listToMAp(employeeList);
        List<AbstractEmployee> tmpList = new ArrayList<>();
        map1.entrySet().stream()
                .forEach(e -> {
                    List<AbstractEmployee> value = e.getValue();
                    value.stream()
                            .filter(e1 -> e1.getSalary() > 3000)
                            .forEach(e1 -> tmpList.add(e1));
                });
    }

    public static Map<String, List<AbstractEmployee>> listToMAp(List<AbstractEmployee> employees) {
        Map<String, List<AbstractEmployee>> map = new HashMap<>();
        for (AbstractEmployee employee : employees) {
            if (map.containsKey(employee.getDepartment())) {
                List<AbstractEmployee> listOfEmployees = map.get(employee.getDepartment());
                listOfEmployees.add(employee);
            } else {
                ArrayList<AbstractEmployee> listOFEmployees = new ArrayList<>();
                listOFEmployees.add(employee);
                map.put(employee.getDepartment(), listOFEmployees);
            }
        }
        return map;
    }

}

