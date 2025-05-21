package com.cyrus;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;


/**
 *
 * @author cyrus
 */
public interface  UserRepository extends ReactiveMongoRepository<User, String>{
    Mono<User> findByName(String username);

}
