package com.rent.RentApp.controllers;

import javax.validation.Valid;

import com.rent.RentApp.dtos.SpecDto;
import com.rent.RentApp.forms.SpecForm;
import com.rent.RentApp.models.Specs;
import com.rent.RentApp.services.Specs.SpecService;

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
  private SpecService specService;

  @GetMapping
  public Page<SpecDto> listSpecs(
      @PageableDefault(sort = "carType", direction = Direction.ASC, page = 0, size = 5) Pageable pagination) {

    Page<SpecDto> specs = this.specService.findPages(pagination);

    return specs;
  }

  @PostMapping
  public SpecDto createSpec(@Valid @RequestBody SpecForm form) {
    SpecDto spec = this.specService.create(form);

    return spec;
  }

  @PutMapping("/{id}")
  public SpecDto updateSpec(@PathVariable Long id, @Valid @RequestBody SpecForm form) {

    SpecDto spec = this.specService.update(id, form);

    return spec;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deleteSpec(@PathVariable Long id) {
    this.specService.delete(id);
  }
}
