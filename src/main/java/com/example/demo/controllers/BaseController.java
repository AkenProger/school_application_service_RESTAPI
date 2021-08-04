package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseController<S, T> {
    @PostMapping("/save")
    S save(@RequestBody S s);

    @PutMapping("/update")
    S update(@RequestBody S s);

    @GetMapping("/{id}")
    S findById(@PathVariable T id);

    @GetMapping("/all")
    List<S> findAll();

    @GetMapping("/delete/{id}")
    void deleteById(@PathVariable T id);
}
