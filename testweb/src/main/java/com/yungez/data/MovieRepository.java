package com.yungez.data;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
    List<Movie> findByName(@Param("name") String name);

    //TODO: use findOne, instead of returning a list.
    List<Movie> findById(@Param("id") Long id);
}
