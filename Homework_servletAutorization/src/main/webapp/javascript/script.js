document.addEventListener( "DOMContentLoaded", hideFormAndSaveButtonOnLoad);


$(document).ready(function(){
    $.fn.wait = function(time, type) {
        time = time || 300;
        type = type || "fx";
        return this.queue(type, function() {
            var self = this;
            setTimeout(function() {
                $(self).dequeue();
            }, time);
        });
    };
    function runIt() {
      $(".blinkingText").wait()
              .animate({"opacity": 0.8},400)
              .wait()
              .animate({"opacity": 1},400,runIt);


    }
    runIt();

    });

function hideFormAndSaveButtonOnLoad(){

    document.getElementById("HideForm").style.display = 'none';

    document.getElementById("CreateCustomer").onclick = unhideFormWhenPressCreateCustomerButton;

    document.getElementById("SaveCustomer").onclick = validateAllForms;
}

    function validateAllForms(){

    var form = document.querySelectorAll('form');
    var validationMessages = document.querySelectorAll('span');


    var countErrors=0;

        for(var i = 0; i < validationMessages.length; i++){

            if(form[0][i].value.length==0) {
            validationMessages[i].style.display = 'block';

            countErrors++;
            }
        }

    if(countErrors>0){
    return;
    }
    else {

        document.getElementById("SaveCustomer").type = "submit";
        document.getElementById("SaveCustomer").onclick = document.getElementById("SaveCustomer").submit();
    }
}

    function unhideFormWhenPressCreateCustomerButton(){

        document.getElementById("CreateCustomer").style.display = 'none';
                document.getElementById("HideForm").style.display = 'block';
                document.getElementById("SaveCustomer").style.display = 'block';

                var validationMessages = document.querySelectorAll('span');

                        for(var i = 0; i < validationMessages.length; i++){

                            validationMessages[i].style.display = 'none';
                        }
}

    function hideFormAfterPressSaveButton(){

        document.getElementById("CreateCustomer").style.display = 'block';
        document.getElementById("HideForm").style.display = 'none';
        document.getElementById("SaveCustomer").style.display = 'none';
}

    function hideErrorMessageOnFocus(element) {

    var form = document.querySelectorAll('form');
    var validationMessages = document.querySelectorAll('span');

        for(var i = 0; i < validationMessages.length; i++){
           if(form[0][i]==element){
            validationMessages[i].style.display = 'none';
           }
        }
}

