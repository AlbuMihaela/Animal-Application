package com.sda.project.service;

import com.sda.project.dto.PetDto;
import com.sda.project.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

// unit test (suffixed with Test)
// integration test (suffixed with IT)
// create spring boot context like a real application
@SpringBootTest
class PetServiceIT {

    @Autowired
    PetService petService;

    @Test
    void whenSave_shouldReturnPetDto() {
        // given
        PetDto petDto = new PetDto();
        petDto.setName("rex");
        petDto.setCategory(Category.DOG.name());

        // when
        PetDto actualPet = petService.save(petDto);

        // then
        assertThat(actualPet.getId()).isNotNull();
    }

    @Test
    void whenUpdate_shouldUpdatePet() {
        // given
        // create pet in db
        PetDto petDto = new PetDto();
        petDto.setName("lassie");
        petDto.setCategory(Category.DOG.name());
        PetDto petDtoFromDb = petService.save(petDto);

        // grab data from user
        PetDto updateData = new PetDto();
        // user clicked update pet (navigate)
        updateData.setId(petDtoFromDb.getId());
        // user input data
        updateData.setName("bruno");
        updateData.setCategory(Category.RABBIT.name());

        // when
        // user clicks update pet (submit)
        petService.update(updateData);

        // then
        // find updated pet by id
        PetDto actualPet = petService.findById(petDtoFromDb.getId());

        // check that pet was updated
        String actualName = actualPet.getName();
        String expectedName = updateData.getName();
        assertThat(actualName).isEqualTo(expectedName);
    }
}