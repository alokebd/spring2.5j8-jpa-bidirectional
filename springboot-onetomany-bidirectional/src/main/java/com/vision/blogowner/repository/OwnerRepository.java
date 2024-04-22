package com.vision.blogowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vision.blogowner.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}
