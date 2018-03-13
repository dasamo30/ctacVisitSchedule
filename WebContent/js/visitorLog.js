jQuery(document).ready( function () {
	console.log("visitor log.js");
	
	$("#fomrSearchVisit").validator();
	
	$(document).on("submit","#fomrSearchVisit",function(e){    
	      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       $("#divdataVisit").hide();
       frm=$(this);
       
       var msj=$("#msjSearchVisit");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::fomrSearchVisit");
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/visitorLog/ActSearchVisit",
            //contentType: 'application/text',
            //data:JSON.stringify(frm.serializeJSON()),
            data:frm.serialize(),
            success: function(result){
              //  alert(result);
            //  chatWith('9','name');
            	console.log(result.op)
            	console.log(result.data)
                if(result.op==0){
                	$("#divdataVisit").show();
                	$("#full_name_visitor").text(result.data.full_name_visitor);
                	$("#full_name_employee").text(result.data.full_name_employee);
                	$("#id_visit_schedule").text("Number "+result.data.id_visit_schedule)
                	$("#phone_number").text(result.data.phone_number)
                	$("#email").text(result.data.email)
                	$("#citizen_ship").text(result.data.citizen_ship)
                	$("#idNumber").text(result.data.number_license)
                	$("#company_name").text(result.data.company_name)
                	$("#reasons_name").text(result.data.reasons_name)
                	$("#date_hour").text(result.data.date_hour)
                	
                    //alerts(0,msj,"la company se grabo con exito");   
                    //loadDataTable("#tbCompany");
                    frm.trigger('reset');
                }else{
                    alerts(2,msj,"No cuenta con una visita progrmada para el dia de hoy");
                }
                
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                alerts(3,msj,"A ocurrido un error interno !!!");
            } 
        });
    });
});
	