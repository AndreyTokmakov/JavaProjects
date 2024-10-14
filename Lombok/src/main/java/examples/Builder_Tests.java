/****************************************************************************
 * Copyright 2020 (C) Andrey Tokmakov
 * Builder_Tests.java class
 *
 * @name    : Builder_Tests.java
 * @author  : Tokmakov Andrey
 * @version : 1.0
 * @since   : Dec 21, 2020
 ****************************************************************************/

package examples;

import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
class Employee
{
    private String name;
    private int age;

    @Singular
    private Set<String> occupations;
}


public class Builder_Tests
{
    public static void createEmployee_Test1()
    {
        Employee example = Employee.builder().name("Name").age(111).build();

        System.out.println(example.getAge());
        System.out.println(example.getName());
        System.out.println(example);
    }

    public static void main(String[] args)
    {
        createEmployee_Test1();
    }
}
