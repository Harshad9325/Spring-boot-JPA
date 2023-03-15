package com.api.Dao;

import org.springframework.data.repository.CrudRepository;

import com.api.model.book;

public interface bookRepo extends CrudRepository<book, Integer> {
public book findById(int id);
}
