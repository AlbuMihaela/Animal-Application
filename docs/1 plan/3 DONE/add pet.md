# feature

admin - can add pet

# info

    summary: DEV-4 view add pet form
    estimation: 2
    reporter: trainer
    assignee: student

# design
- see attachment

# acceptance criteria
- admin click `Add pet` and navigates to pet-add form
- admin inputs data
    - name
    - category * must match Enum
    - description
- admin clicks the `Add pet` button, and the pet should be saved in db
- input validation *

# frontend
- form template
    - name input
    - category input
    - description input
    - submit button "Add pet"
    - navigation button "Cancel"

# backend
- create schema
- database connection
    - spring boot parent
    - mysql connector
    - spring data jpa

- main spring boot class
- entity
- repository
- service
- controller
- unit test *
- integration test *

# test *
- go to pets page
- input data
- click `Add pet`
- click `Cancel`
