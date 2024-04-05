package ru.gb;

import org.apache.commons.lang3.builder.ToStringBuilder;
import com.google.gson.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person person1 = new Person("Михаил", "Иванов", 41);
        Person person2 = new Person("Иван", "Никитин", 22);
        Person person3 = new Person("Никита", "Иванов", 14);

        ToStringBuilder toStringBuilder = new ToStringBuilder(person1);
        String string = toStringBuilder.build();
        boolean isEqual = person2.equals(person1);
        int hashCode = person1.hashCode();

        Gson gson = new Gson();
        gson.toJson(person3);
    }
}
