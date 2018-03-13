jQuery(document).ready( function () {
   
    $('#btnftp').on('click', function (e)
    {
       alert("envio ftp1");
       if (e.isDefaultPrevented()) {
           return false;
       }
       
       $.ajax({
            type: "POST",
            url: baseurl+"/controladorftp/envio_ftp",
            contentType: 'application/json',
           // data:data,
            success: function(result){
                if(result===0){
                    alert("Se envio correctamente el archivo");
                }else if(result===1){
                    alert("Error el archivo ya existe abn_date_hhmmss");
                }else{
                    alert("error");
                }
            },
            error: function() {
                alert("A ocurrido un error interno !!!");
            } 
        });
    });
   

});