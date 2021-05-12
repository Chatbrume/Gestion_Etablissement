function formsubmit(event)
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
};