package com.fivedalant.allmyrental.Repository;

import com.fivedalant.allmyrental.Jpa.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {
}
