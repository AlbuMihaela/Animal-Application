# feature

    as an admin
    I want to edit a pet
    to fix some mistakes

# info

    summary: DEV-5 edit pet form
    estimation: 2
    reporter: trainer
    assignee: student

# design
- see attachment

# acceptance criteria
- admin click `Update pet` and navigates to pet-add form
- admin inputs data
    - name
    - category * must match Enum
    - description
- admin clicks the `Update pet` button, and the pet should be saved in db
- input validation *

# frontend
- form template
    - name input
    - category input
    - description input
    - submit button "Update pet"
    - navigation button "Cancel"

# backend
- entity
- repository
- service
- controller
- unit test *
- integration test *

# test *
- go to pets page
- click `Update pet`
- input data
- click `Update pet`
