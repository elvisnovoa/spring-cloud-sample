package com.sample.cloud.customers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sample.cloud.customers.resource.Customer;

/**
 * DAO/Controller with fundamental CRUD operations and additional searching
 * methods.
 *
 * @author ednovoa
 *
 */
@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	/**
	 * This method translated to a query using keywords. See
	 * http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.
	 * query-methods.query-creation
	 *
	 * @param name
	 * @return a list of Customers matching the query
	 */
	List<Customer> findByLastName(@Param("name") String name);

	/**
	 * Executes the value of the @Query annotation.
	 *
	 * @param name
	 * @return a list of Customers matching the query
	 */
	@Query("from Customer where firstName like %:name% or lastName like %:name%")
	List<Customer> findByName(@Param("name") String name);
}
