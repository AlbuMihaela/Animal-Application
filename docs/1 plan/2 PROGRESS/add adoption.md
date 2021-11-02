# feature

user - can add adoption
# info

    summary: DEV-4 view add pet form
    estimation: 2
    reporter: trainer
    assignee: student

# design
- see attachment

# acceptance criteria
- user click `Adopt` and navigates to adoption-add form
- user inputs data
    - date
    - proof of address
    - proof of financial situation
    - identity card
- user clicks the `Submit` button, and the adoption should be saved in db 

- input validation *

# frontend
- form template
    - date input
    - proof of address input
    - proof of financial situation input
    - identity card input
    - submit button "Submit"
    - navigation button "Cancel"

# backend
- entity
- repository
- service
- controller
- unit test *
- integration test *

# test *
- go to adoptions page
- input data
- click `Submit`
- click `Cancel`
