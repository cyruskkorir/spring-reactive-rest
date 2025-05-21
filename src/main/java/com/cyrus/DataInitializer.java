/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.cyrus;

 import org.springframework.boot.CommandLineRunner;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Flux;

 @Configuration
 public class DataInitializer {

     @Bean
     CommandLineRunner initDatabase(UserRepository repository) {
         return args -> {
             // Clear existing data (optional)
             repository.deleteAll()
                       .thenMany(
                           // Add sample data
                           Flux.just(
                               new User(1, "John Doe", "johndoe@example.com"),
                               new User(2, "Jane Smith", "janesmith@example.com"),
                               new User(3, "Bob Johnson", "bobjohnson@example.com")
                           )
                           // Save each product
                           .flatMap(repository::save)
                       )
                       .subscribe(
                           user -> System.out.println("Saved: " + user.getName()), // On next
                           error -> System.err.println("Error saving data: " + error), // On error
                           () -> System.out.println("Sample data initialization complete.") // On complete
                       );
         };
     }
 }
