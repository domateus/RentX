package com.rent.RentApp.services.Specs;

import java.util.Optional;

import com.rent.RentApp.dtos.SpecDto;
import com.rent.RentApp.forms.SpecForm;
import com.rent.RentApp.models.Cars;
import com.rent.RentApp.models.Specs;
import com.rent.RentApp.repositories.SpecsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SpecService {

  @Autowired
  private SpecsRepository specsRepository;

  private Specs generate() {
    Specs spec = new Specs();
    this.specsRepository.save(spec);

    return spec;
  }

  public SpecDto generateNew(Long id, Cars car) {
    Optional<Specs> opSpecs = this.specsRepository.findById(id);

    if (opSpecs.isEmpty()) {
      Specs spec = this.generate();
      car.setSpec(spec);
      car.setSpecId(spec.getId());
      spec.addCar(car);
      return new SpecDto(spec);
    }
    Specs spec = opSpecs.get();
    car.setSpec(spec);
    car.setSpecId(spec.getId());
    spec.addCar(car);

    return new SpecDto(spec);

  }

  public Page<SpecDto> findPages(Pageable pagination) {
    Page<Specs> specs = this.specsRepository.findAll(pagination);

    return SpecDto.convert(specs);
  }

  public SpecDto create(SpecForm form) {
    Specs spec = new Specs(form);
    this.specsRepository.save(spec);

    return new SpecDto(spec);
  }

  public SpecDto update(Long id, SpecForm form) {
    Optional<Specs> opSpec = this.specsRepository.findById(id);

    if (opSpec.isEmpty()) {
      return null;
    }

    Specs spec = form.update(opSpec.get());
    this.specsRepository.save(spec);

    return new SpecDto(spec);
  }

  public void delete(Long id) {
    this.specsRepository.deleteById(id);
  }

}
