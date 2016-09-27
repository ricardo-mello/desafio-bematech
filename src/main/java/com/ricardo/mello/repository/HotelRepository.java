package com.ricardo.mello.repository;

import com.ricardo.mello.bean.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a entidade Hotel.
 *
 * @author Ricardo
 */
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
