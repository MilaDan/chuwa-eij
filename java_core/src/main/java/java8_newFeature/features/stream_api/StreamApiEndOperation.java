package java8_newFeature.features.stream_api;

import java8_newFeature.pojos.Employee;
import java8_newFeature.utils.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Collections 讲的是数据，Stream讲的是计算（CPU）
 * 1. Stream 自己不会存储元素
 * 2. Stream 不会改变源对象，相反，他们会返回一个持有结果的新stream
 * 3. Stream操作是延迟执行的，这意味着他们会等到需要结果的时候才执行。
 * Stream执行流程：
 * 1. 创建Stream： 获取一个数据源
 * 2. 中间操作： 一个操作链： 对数据进行处理
 * 3. 终止操作： 一旦执行中止操作，则会开始执行中间操作链，并产生结果。之后，不会再被使用。
 */
public class StreamApiEndOperation {
    /**
     * 终止操作
     * 一， 匹配与查找
     * 1， allMatch(Predicate p) - 检查是否匹配所有的元素
     * 2， anyMatch(Predicate p) - 检查是否至少匹配一个元素
     * 3， noneMatch(Predicate p) - 检查是否没有匹配的元素
     * 4, findFirst - 返回第一个元素
     * 5， findAny - 返回当前流中的任意元素
     * 6, count - 返回流中元素的个数
     * 7, max(Comparator c) - 返回流中的最大值
     * 8, min(Comparator c) - 返回流中的最小值
     * 9, forEach(Consumer c) - 内部迭代
     *
     * 二，归约
     * 1, reduce(T identity, BinaryOperator) - 可以将流中的元素反复结合起来，得到一个值
     * 2, reduce(BinaryOperator) - 可以将流中的元素反复结合起来，得到一个值
     *
     * 三， 收集
     * 1, collect(Collector c)
     */

    private static final List<Employee> employees = EmployeeData.getEmployees();

    /**
     * 1， allMatch(Predicate p) - 检查是否匹配所有的元素
     * 2， anyMatch(Predicate p) - 检查是否至少匹配一个元素
     * 3， noneMatch(Predicate p) - 检查是否没有匹配的元素
     */
    @Test
    public void testMatch() {
//        1， allMatch(Predicate p) - 检查是否匹配所有的元素
        boolean allMatch = employees.stream().allMatch(e -> e.getName().length() > 2);
        System.out.println(allMatch);

        System.out.println("***************************");

//        2， anyMatch(Predicate p) - 检查是否至少匹配一个元素
        boolean anyMatch = employees.stream().anyMatch(e -> e.getName().length() > 3);
        System.out.println(anyMatch);

        System.out.println("**************************");

//        3， noneMatch(Predicate p) - 检查是否没有匹配的元素
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);
    }

    /**
     * 4, findFirst - 返回第一个元素
     * 5， findAny - 返回当前流中的任意元素
     */
    @Test
    public void testFind() {
//        4, findFirst - 返回第一个元素
        Optional<Employee> employee = employees.stream().findFirst();
        System.out.println(employee);

//        5， findAny - 返回当前流中的任意元素
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);
    }

    /**
     * 6, count - 返回流中元素的个数
     * 7, max(Comparator c) - 返回流中的最大值
     * 8, min(Comparator c) - 返回流中的最小值
     */
    @Test
    public void testCountMaxMin() {
//        6, count - 返回流中元素的个数
        long count = employees.stream().filter(e -> e.getAge() > 40).count();
        System.out.println("num of employees(age > 60): " + count);

//        7, max(Comparator c) - 返回流中的最大值
        Stream<Integer> stream = employees.stream().map(Employee::getAge);
        Optional<Integer> max = stream.max(Integer::compare);
        System.out.println("max age is: " + max);

//        8, min(Comparator c) - 返回流中的最小值
        Optional<Employee> min = employees.stream().min(Comparator.comparingInt(Employee::getId));
        System.out.println("min age is : " + min);
    }

    /**
     * 9, forEach(Consumer c) - 内部迭代
     * 使用集合的迭代
     */
    @Test
    public void testIteration() {
//        9, forEach(Consumer c) - 内部迭代
        employees.stream().forEach(System.out::println);

        System.out.println("\n********************************\n");
        // 使用集合的迭代
        employees.forEach(System.out::println);
    }

    @Test
    public void testReduce() {
//        1, reduce(T identity, BinaryOperator) - 可以将流中的元素反复结合起来，得到一个值
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

//        2, reduce(BinaryOperator) - 可以将流中的元素反复结合起来，得到一个值
        Stream<Integer> stream = employees.stream().map(Employee::getAge);
        Optional<Integer> reduce1 = stream.reduce(Integer::sum);
        System.out.println(reduce1);

        Stream<Integer> stream2 = employees.stream().map(Employee::getAge);
        Optional<Integer> reduce2 = stream2.reduce((d1, d2) -> d1 + d2);
        System.out.println(reduce2.get());
    }

    @Test
    public void testCollect() {
        List<Employee> collect = employees.stream().filter(e -> e.getId() > 1002).collect(Collectors.toList());
        collect.forEach(System.out::println);

        Set<Employee> collect2 = employees.stream().filter(e -> e.getId() > 1002).collect(Collectors.toSet());
        collect2.forEach(System.out::println);
    }
}
