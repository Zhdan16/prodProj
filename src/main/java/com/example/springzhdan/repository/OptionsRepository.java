package com.example.springzhdan.repository;

import com.example.springzhdan.enity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OptionsRepository extends JpaRepository<Option, Long> {

}
