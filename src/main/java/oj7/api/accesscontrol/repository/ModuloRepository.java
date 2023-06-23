package oj7.api.accesscontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import oj7.api.accesscontrol.model.Modulo;
import oj7.api.accesscontrol.model.ModuloKey;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, ModuloKey> {

}
