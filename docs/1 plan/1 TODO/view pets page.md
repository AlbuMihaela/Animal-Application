# feature

    as a user 
    I want see the pets
    so I can adopt

# info

    summary: DEV-2 view pets page
    estimation: 3
    reporter: trainer
    assignee: student

# design
- see attachment

# acceptance criteria
- should display only pets that are for adoption
- should be able to click photo and navigate to details

# frontend
- add photo
- add name

# backend
- entity
  - add boolean forAdoption
- repository
- service
- controller
- unit test
- integration test

# test
- user clicks "Pets", and the page is displayed
- user clicks on pet photo, and he is redirected to the pet info page
