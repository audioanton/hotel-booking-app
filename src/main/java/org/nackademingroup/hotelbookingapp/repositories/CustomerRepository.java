package org.nackademingroup.hotelbookingapp.repositories;

import org.nackademingroup.hotelbookingapp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
