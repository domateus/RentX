package com.rent.RentApp.services.Specs;

import com.rent.RentApp.models.Specs;
import com.rent.RentApp.repositories.SpecsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSpecService {

  @Autowired
  private SpecsRepository specsRepository;

  public Specs create() {
    Specs spec = new Specs();
    this.specsRepository.save(spec);

    return spec;
  }

}
