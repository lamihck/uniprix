function montrer(id){

    var objet = document.getElementById(id);
    for(var i = 1; i <= 10; i++){
        if (document.getElementById('sousmenu'+i)){
            document.getElementById('sousmenu'+i).style.display='none';
        }
    }
    if(objet){
        objet.style.display='block';
    }
}