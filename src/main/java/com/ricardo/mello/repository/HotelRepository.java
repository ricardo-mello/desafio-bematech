package com.ricardo.mello.repository;

import com.ricardo.mello.bean.Hotel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositório para a entidade Hotel.
 *
 * @author Ricardo
 */
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
