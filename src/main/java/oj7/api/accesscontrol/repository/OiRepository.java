package oj7.api.accesscontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import oj7.api.accesscontrol.model.Oi;
import oj7.api.accesscontrol.model.OiKey;


@Repository
public interface OiRepository extends JpaRepository<Oi, OiKey>{

  @Query("from Oi where cdSupe||cdDept||cdDivs||cdScao||cdSetr like :oi%")
  public List<Oi> findLikeOi (String oi);
}