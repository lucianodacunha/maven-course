package io.dev.app;

import com.github.javafaker.Faker;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println(new Faker().name().fullName());
    }
}
