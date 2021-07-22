package org.patatesmaison.msreservation.dao;

import org.patatesmaison.msreservation.entity.Reservation;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Long> {

        Set<Reservation> findAllByUserId(Long id);

        Set<Reservation> findTop3ByUserIdOrderByDateTimeDesc(Long id);

//    Optional<Reservation> findById(Long id);

//    Optional<Reservation> findFirstByRegionNameContaining(String containedString);
//
//    @Query("SELECT i FROM Item i WHERE i.name LIKE :prefix%")
//    List<Reservation> getItemNameByPrefix(String prefix);

}
