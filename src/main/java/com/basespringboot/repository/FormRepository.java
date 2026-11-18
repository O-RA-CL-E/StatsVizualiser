package com.basespringboot.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basespringboot.form.Form;

@Repository 

public interface FormRepository extends JpaRepository<Form, Long> {

}