// self invoked function (function(){})();
// define function function(){ }
(function(){
    'use strict';
    // add listener on page load
    window.addEventListener('load', function() {
    console.log('validating...');
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      // add submit listener
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
    }, false);
})();

/*
private addListenerOnLoadPage() {
    List<Form> forms = getForms();
    forms.forEach(form -> addSubmitListener(form);
}

private addSubmitListener(Form form) {
    if (form == false) {
        preventDefaultBrowserBehavior();
    }
}
*/