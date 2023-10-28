package com.shaneydv.springbootlibrary.dao;

import com.shaneydv.springbootlibrary.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
