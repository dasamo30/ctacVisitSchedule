jQuery(document).ready( function () {
	console.log("visitor log.js");
	
	$("#fomrSearchVisit").validator();
	
	$(document).on("submit","#fomrSearchVisit",function(e){    
	      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       frm=$(this);
      

    	//$("#boxVisits").append('<button id="btnregiter" type="submit" class="btn btn-success pull-right "><i class="fa  fa-sign-in"></i> Generate Income</button>')
        //return;
       $.ajax({
            type: "POST",
            url: baseurl+"/visitorLog/ActSearchVisit",
            //contentType: 'application/text',
            //data:JSON.stringify(frm.serializeJSON()),
            data:frm.serialize(),
            success: function(data){
           
            	$("#divListVisits").html(data);
                frm.trigger('reset');
              
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                console.log("errorr");
                //alerts(3,msj,"A ocurrido un error interno !!!");
            } 
        });
    });
	
	$(document).on("submit","#fomrSearchVisit222",function(e){    
	      
        if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       $("#divdataVisit").hide();
       frm=$(this);
      
       //$( "#frmRegisterVisit" ).remove("#btnregiter");
       $('#btnregiter').remove();
       console.log("ssss");
       //lert("ssss");
       var msj=$("#msjSearchVisit");
           msj.removeAttr('class');
           msj.html("");
           
        
        console.log(":::fomrSearchVisit");
      //$('#tbRegister').dataTable().fnClearTable();
    	$( "#tbRegister tbody tr" ).each( function(){
    		  this.parentNode.removeChild( this ); 
    		});
    	$("#boxVisits").append('<button id="btnregiter" type="submit" class="btn btn-success pull-right "><i class="fa  fa-sign-in"></i> Generate Income</button>')
        return;
       $.ajax({
            type: "POST",
            url: baseurl+"/visitorLog/ActSearchVisit",
            //contentType: 'application/text',
            //data:JSON.stringify(frm.serializeJSON()),
            data:frm.serialize(),
            success: function(data){
                //alert(result);
            //  chatWith('9','name');
            	result = JSON.parse(data);
            	console.log(result)
            	//console.log(result)
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
                	
                	$("#department_name").text(result.data.department_name)
                	$("#occupation_employee").text(result.data.occupation_employee)
                	
                	$("#frmRegisterVisit").attr('data-options', '{ "id_visit_schedule" : "'+result.data.id_visit_schedule+'", "type" : "'+result.data.status+'" }');
                	
                	if(result.data.listVisitorLog){
                		rows="";
                		//{id_visit_schedule: 18, badge_number: "12222-33333", type: 1, registration_date: "17-03-2018 17:09:11"}
	                	$.each(result.data.listVisitorLog, function( key, value ) {
	                		arry = value.registration_date.split(" ");
	                		txt=(value.type==1)? "Ingreso":"Salida";
	                		label=(value.type==1)? "label-success":"label-primary";
	                		rows+=' <tr class="text-light-blue">\n'+
	                        '<td><span class="label '+label+'">'+txt+'</span></td>\n'+
	                        '<td>'+value.badge_number+'</td>\n'+
	                        '<td>'+arry[0]+'</td>\n'+
	                       '<td>'+arry[1]+'</td>\n'+
	                       '</tr>';
	                	});
	                	
                	
                		$( "#tbRegister tbody" ).append(rows);
                	}
                	
                	if(result.data.status==1){
                		console.log("1")
                		$("#tbRegister").hide();
                		$("#frmRegisterVisit").append('<button id="btnregiter" type="submit" class="btn btn-success pull-right "><i class="fa  fa-sign-in"></i> Generate Income</button>')
                		$("#frmRegisterVisit").show();
                	}else if(result.data.status==2){
                		console.log("2")
                		$("#tbRegister").show();
                		$("#frmRegisterVisit").append('<button id="btnregiter" type="submit" class="btn btn-primary pull-right"><i class="fa  fa-sign-out"></i> Generate Output</button>')
                		$("#frmRegisterVisit").show();
                	}else{
                		console.log("3")
                		$("#tbRegister").show();
                		$("#frmRegisterVisit").hide();
                	}
                	
                	
                    //alerts(0,msj,"la company se grabo con exito");   
                    //loadDataTable("#tbCompany");
                    frm.trigger('reset');
                }else{
                    alerts(2,msj,"You do not have a scheduled visit for today");
                }
                
                
            },
            error: function() {
                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
                
                alerts(3,msj,"An internal error has occurred !!!");
            } 
        });
    });
	
//	$('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
//        checkboxClass: 'icheckbox_flat-green',
//        radioClass: 'iradio_flat-green'
//      });
	
	
	
	$(document).on("submit","#frmRegisterVisit",function(e){
		if (e.isDefaultPrevented()) {
            return false;
        }
       e.preventDefault();
       
       frm=$(this);
       
       //var ids = [];
       var visits=$('.ids:checked');
       
       console.log(visits.length)
    
       
      var badge_number=$("#badge_number").val();
      var cboType=$("#cboType").val();
      var cboReason=$("#cboReason").val();
      
      console.log(visits.val());
      //var data = $(form).serializeArray();
      

      var dataJson =frm.serializeJSON();
      //dataJson.push({name: 'id_visit_schedule', value: '1'});
      
      
      console.log(dataJson);
      
      listVisit = new Array();
      
      visits.each(function(i, e) {
    	  console.log($(this).val());
    	   
    	   //var data2=dataJson;
    	   //data2['id_visit_schedule'] = $(this).val();
    	   console.log($('#checkbadge').is(":checked"))
    	   if($('#checkbadge').is(":checked")){
    		   listVisit.push({ "id_visit_schedule": $(this).val(), "badge_number":badge_number , "type": cboType});
    	   }else{
    		   listVisit.push({ "id_visit_schedule": $(this).val() , "type": cboType, "reason":cboReason });
    	   }
    	   //var frm=Object.assign(dataJson, {id_visit_schedule:$(this).val()});
    	   
    	  //listVisit.push(data2);
    	  
    	  //console.log(listVisit);
    	  //console.log($(this).val()+"--------------------------------")
           //cities.push(obj);
       });

      //listVisit.push({ "id_visit_schedule": 25, "badge_number":badge_number , "type": cboType, "reason":cboReason });
       console.log(listVisit);

     //  return;
       $.ajax({
    	   type: "POST",
           url: baseurl+"/visitorLog/ActRegisterVisit",
           //dataType: "html",          
           contentType: 'application/json',
           data:JSON.stringify(listVisit),
           //data:{ listObject: JSON.stringify(listVisit)},
           success: function(result){
        	   
        	   	console.log(result);
        	   	//alerts(0,msj,"");
        	   	if(result==0){
        	   		text='<img src="'+baseurl+'/images/green_success.png" alt="user image" class="img-responsive center-block" > \n'+
        	   		 '<p class="text-center">successful</p>';
        	   		ezBSAlert({ headerText:"success", messageText:text, alertType: "success"});
        	   		
        	   		$("#divListVisits").html("");
        	   		//$("#cboVisitSearch").empty().trigger('change');
        	   		$('#cboVisitSearch').val(null).trigger('change');
        	   		//$('#cboVisitSearch').select2('val', '');

        	   		//$('#btnregiter').remove();
        	   		//$("#frmRegisterVisit").hide();
        	   		//$("#divdataVisit").hide();
        	   	}else{
        	   		text='<img src="'+baseurl+'/images/refuse.png" alt="user image" class="img-responsive center-block" > \n'+
       	   		 	'<p class="text-center">Error</p>';
        	   		ezBSAlert({ headerText:"Error",messageText: text, alertType: "danger"});
        	   	}
           },
           error: function() {
        	   
        	   text='<img src="'+baseurl+'/images/refuse.png" alt="user image" class="img-responsive center-block" > \n'+
  	   		 	'<p class="text-center">Error</p>';
        	   ezBSAlert({ headerText:"success", messageText:text, alertType: "success"});
           }
       });
	});
	
	 //$('#checkbadge').change(function() {
     $(document).on("change","#checkbadge",function(e){	 
  	   //$('#idnumeros').tagsinput('removeAll');
    	 $("#badge_number").val("");
    	 $('#cboReason, #cboType').prop('selectedIndex',-1);
    	// $('#cboReason').prop('selectedIndex',-1);
    	 
    	 //$('#frmRegisterVisit').trigger("reset");
    	 
  	   if(this.checked) {
      	 console.log($(this).val());
      	    //$('#form-cm').show();
      	    $('#divReason').hide();
      	    $('#badge_number').prop('readonly', false);
 	    	$("#badge_number").attr("required", "true");
 	    	$("#cboReason").removeAttr('required');
         }else{
 	    	console.log($(this).val());
 	    	//$('#form-cm').hide();
 	    	$('#divReason').show();
 	    	$('#badge_number').prop('readonly', true);
 	    	$("#cboReason").attr("required", "true");
 	    	$("#badge_number").removeAttr('required');
	    }        
     });
	
	 $('#cboVisitSearch').select2({
	        placeholder: 'Select an item',
	        minimumInputLength: 2,
	        allowClear: true,
	        ajax: {
	          type: "POST",   
	          url: baseurl+"/visitorLog/searchByName",
	          dataType: 'json',
	          delay: 250,
	          data: function (params) {

	            var queryParameters = {
	                term: params.term
	            };
	            return queryParameters;
	        },
	        processResults: function (data) {
	            return {
	                results: $.map(data, function (item) {
	                    return {
	                        text: item.full_name_visitor+" - "+item.company_name,
	                        id: item.id_visitor 
	                        
	                    };
	                })
	            };
	        },
	          cache: true
	        }
	      });/*.on("change", function () {
	         // console.log($(this));
	        //var str = $("#s2id_search_code .select2-choice span").text();
	         var data = $(this).select2('data')[0];
	        console.log(data); 
	         if(data){
	         //$("#txtPriceProduct").val(data.sellPrice);
	         //$("#txtAmountProduct").val(1);
	         //$("#txtProductId").val(data.idProduct);
	         }
	      });*/
});
	