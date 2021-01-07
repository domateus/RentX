package com.rent.RentApp.controllers;

import com.rent.RentApp.forms.SpecForm;
import com.rent.RentApp.models.Specs;
import com.rent.RentApp.repositories.SpecsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specs")
public class SpecsController {

  @Autowired
  private SpecsRepository repository;

  @GetMapping
  public Page<Specs> listSpecs(
      @PageableDefault(sort = "carType", direction = Direction.ASC, page = 0, size = 5) Pageable pagination) {

    Page<Specs> specs = this.repository.findAll(pagination);

    return specs;
  }

  @PostMapping
  public Specs createSpec(@RequestBody SpecForm form) {
    Specs spec = new Specs(form);
    this.repository.save(spec);

    return spec;
  }

  @PutMapping("/{id}")
  public Specs updateSpec(@PathVariable Long id, @RequestBody SpecForm form) {

    Specs spec = form.update(repository, id);

    return spec;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteSpec(@PathVariable Long id) {
    this.repository.deleteById(id);
  }
}
