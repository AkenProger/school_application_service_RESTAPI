package com.example.demo.service;

import java.util.List;

public interface BaseCrudService <S ,T >{
    S save(S s);
    S update(S s);
    List<S> findAll();
    S findById(T t);
    void deleteById(T t);
}
