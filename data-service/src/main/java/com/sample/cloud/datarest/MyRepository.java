package com.sample.cloud.datarest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "entities", path = "entities")
public interface MyRepository extends PagingAndSortingRepository<MyEntity, Long> {
}
