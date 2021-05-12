/*function formsubmit(event)
{
    var allInput = document.querySelectorAll("input");

    let all_required_ok = true;
    for (i=0; i<allInput.length; i++)
    {
        if( allInput[i].required == true )
        {
            text_add     = allInput[i].value == "" ? 'is-invalid' : 'is-valid';
            text_remove  = allInput[i].value == "" ? 'is-valid' : 'is-invalid';

            if(text_add == "is-invalid")
            {
                all_required_ok = false;
            }

            if( ! allInput[i].classList.contains(text_add) )
            {
                allInput[i].classList.add(text_add);
            }
            if( allInput[i].classList.contains(text_remove) )
            {
                allInput[i].classList.remove(text_remove);
            }
        }
    }

    if (all_required_ok)
    {
        document.getElementById('verifyaddstudentbody').innerHTML = "Etudiant:"+
        document.getElementById('lastname').value+" "+document.getElementById('firstname').value;

        document.getElementById('btnsubmithidden').click();
    }
};

var btnSubmit = document.getElementById('btnsubmit');
btnSubmit.addEventListener('click', formsubmit);*/

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()