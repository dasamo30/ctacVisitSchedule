jQuery(document).ready( function () {
	console.log("visits.js");
	/************************ Company ******************************/
	 $('#myModalViewCompany').on('show.bs.modal', function (e) { 
	        
	        console.log("myModalViewCompany");
	        
	        var btn = $(e.relatedTarget);
	        var id_company=btn.data('id');
	        var company_name=btn.data('name');
	       var data=null;
	       var title="New  Company";
	       var frm='#formRegisterCompany';
	       
	       if(btn.attr("id")==="btnViewEditCompany"){ 
	           console.log("btnViewEditCompany");
	            data={"id_company":id_company,"company_name":company_name};
	            title="Modif Company";
	            frm='#formModifCompany';
	       }
	       
	        $.post(btn.attr("href"),data, function( data ) { 
	        	
	        	$('#modal-title').html(title);
	            $('#modal-body').html(data); 
	            $(frm).validator();
	        }); 
	    });

	 
	 
	 $(document).on("submit","#formRegisterCompany",function(e){    
	      
	        if (e.isDefaultPrevented()) {
	            return false;
	        }
	       e.preventDefault();
	       
	       frm=$(this);
	       
	       var msj=$("#msjmntCompany");
	           msj.removeAttr('class');
	           msj.html("");
	           
	        
	        console.log(":::frmrRegisterCompany");
	        //return;
	       $.ajax({
	            type: "POST",
	            url: baseurl+"/visit/company/ActRegisterCompany",
	            contentType: 'application/json',
	            data:JSON.stringify(frm.serializeJSON()),
	            success: function(result){
	              //  alert(result);
	            //  chatWith('9','name');
	                if(result==0){
	                    alerts(0,msj,"la company se grabo con exito");   
	                    loadDataTable("#tbCompany");
	                    frm.trigger('reset');
	                }else{
	                    alerts(2,msj,"La company ya existe");
	                }
	                
	                
	            },
	            error: function() {
	                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
	                
	                alerts(3,msj,"A ocurrido un error interno !!!");
	            } 
	        });
	      //  alert("ajax");
	    });
	 
	 $(document).on("submit","#formModifCompany",function(e){    
	      
	        if (e.isDefaultPrevented()) {
	            return false;
	        }
	       e.preventDefault();
	       
	       frm=$(this);
	       
	       var msj=$("#msjmntCompany");
	           msj.removeAttr('class');
	           msj.html("");
	           
	        
	        console.log(":::formModifCompany");
	        //return;
	       $.ajax({
	            type: "POST",
	            url: baseurl+"/visit/company/ActModifCompany",
	            contentType: 'application/json',
	            data:JSON.stringify(frm.serializeJSON()),
	            success: function(result){
	              //  alert(result);
	            //  chatWith('9','name');
	                if(result==0){
	                    alerts(0,msj,"El perfil se modifico con exito");   
	                    loadDataTable("#tbCompany");
	                    frm.trigger('reset');
	                }else{
	                    alerts(2,msj,"No se completo el proceso.. !!!");
	                }
	                
	                
	            },
	            error: function() {
	                alerts(3,msj,"A ocurrido un error interno !!!");
	            } 
	        });
	    });
	    
	    
	    //btnEliminaUsuario
	    $(document).on("click","#btnDeleteCompany",function(e){    

	        var obj = this;
	        ezBSAlert({
	        type: "confirm",
	        headerText:"Confirm",
	        messageText: "Are you sure about this ?",
	        alertType: "warning"
	        }).done(function (e) {
	          var id_company = $(obj).data('id');
	          //console.log("confirma::"+idProduct);
	          //var url =baseurl+"/usuarios/ActEliminarUsuario";
	          if(e){
	              $.ajax({
	                url: baseurl+"/visit/company/ActDeleteCompany",
	                type: 'POST',
	                data: { id_company:id_company} ,
	                //contentType: 'application/json; charset=utf-8',
	                success: function (result) {
	                    if(result==0){
	                        //alerts(0,msj,"");   
	                        loadDataTable("#tbCompany");
	                        ezBSAlert({ headerText:"success", messageText: "El company se elmino con exito", alertType: "success"});
	                    }else{
	                        ezBSAlert({ headerText:"Error",messageText: "No se completo el proceso.. !!!", alertType: "danger"});
	                    }


	                },
	                error: function () {
	                    ezBSAlert({ headerText:"Error",messageText: "A ocurrido un error interno !!!", alertType: "danger"});
	                }
	              });
	            
	          }
	        });
	   });
	 
	 var dataTable = $('#tbCompany').DataTable({
	    	processing: true,
	    	bJQueryUI: true,
	        //"serverSide": true,
	        responsive: true,
	        autoWidth: false,
	        ordering:false,
	        ajax:{
	            url :baseurl+"/visit/company/ActlistCompany", // json datasource
	            type: "post",  // method  , by default get
	            complete: function(){
	               table=$(this);
	              //alert(dataTable);
	              //console.log(table.parent());
	              table.parent().addClass("table-responsive");
	             // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
	            },
	            error: function(){  // error handling
	                $(".tbCompany-grid-error").html("");
	                $("#tbCompany-grid").append('<tbody class="tbCompany-grid-error"><tr><th colspan="3">No data found in the server</th></tr></tbody>');
	                $("#tbCompany-grid_processing").css("display","none");

	            }
	        },
	        "aoColumns": [
	        { "mData": "company_name","width":"70%" },
	        { "mData":null,
	            "bSortable": false,
	            "sClass": "text-center",
	            "mRender": function(data, type, full) {
	            	return '<button data-toggle="modal" data-target="#myModalViewCompany" data-remote="false" type="button" data-id="'+data.id_company+'" data-name="'+data.company_name+'" id="btnViewEditCompany" class="btn btn-info btn-xs" href="'+baseurl+'/visit/company/ActViewModifCompany" ><i style="font-size: 18px;" class="fa fa-edit"></i></button>';
	            }
	        },
	        { "mData":null,
	            "bSortable": false,
	            "sClass": "text-center",
	            "mRender": function(data, type, full) {
	            	//console.log(data);
	            	return '<button type="button" data-id="'+data.id_company+'" id="btnDeleteCompany" class="btn btn-danger btn-xs" title="Eliminar" ><i style="font-size: 18px;" class="fa fa-trash"></i></button>';
	            }
	        }
	        
	        ] 
	    });
	 
	 /************************ Employee ******************************/
	 $('#myModalViewEmployee').on('show.bs.modal', function (e) { 
	        
	        console.log("myModalViewEmployee");
	        
	        var btn = $(e.relatedTarget);
	        var id_employee=btn.data('id');
	        var company_name=btn.data('name');
	       var data=null;
	       var title="New  Employee";
	       var frm='#formRegisterEmployee';
	       
	       if(btn.attr("id")==="btnViewEditEmployee"){ 
	           console.log("btnViewEditEmployee");
	            data={"id_employee":id_employee};
	            title="Modif Employee";
	            frm='#formModifEmployee';
	       }
	       
	        $.post(btn.attr("href"),data, function( data ) { 
	        	
	        	$('#modal-title').html(title);
	            $('#modal-body').html(data); 
	            $("#cboOccupation").select2({
	    		    placeholder: "Select a employee",
	    		    dropdownParent: $('#myModalViewEmployee')
	            });
	            $(frm).validator();
	        }); 
	    });

	 
	 
	 $(document).on("submit","#formRegisterEmployee",function(e){    
	      
	        if (e.isDefaultPrevented()) {
	            return false;
	        }
	       e.preventDefault();
	       
	       frm=$(this);
	       
	       var msj=$("#msjmntEmployee");
	           msj.removeAttr('class');
	           msj.html("");
	           
	        
	        console.log(":::frmrRegisterEmployee");
	        //return;
	       $.ajax({
	            type: "POST",
	            url: baseurl+"/visit/employee/ActRegisterEmployee",
	            contentType: 'application/json',
	            data:JSON.stringify(frm.serializeJSON()),
	            success: function(result){
	              //  alert(result);
	            //  chatWith('9','name');
	                if(result==0){
	                    alerts(0,msj,"la Employee se grabo con exito");   
	                    loadDataTable("#tbEmployee");
	                    frm.trigger('reset');
	                }else{
	                    alerts(2,msj,"La Employee ya existe");
	                }
	                
	                
	            },
	            error: function() {
	                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
	                
	                alerts(3,msj,"A ocurrido un error interno !!!");
	            } 
	        });
	      //  alert("ajax");
	    });
	 
	 $(document).on("submit","#formModifEmployee",function(e){    
	      
	        if (e.isDefaultPrevented()) {
	            return false;
	        }
	       e.preventDefault();
	       
	       frm=$(this);
	       
	       var msj=$("#msjmntEmployee");
	           msj.removeAttr('class');
	           msj.html("");
	           
	        
	        console.log(":::formModifCompany");
	        //return;
	       $.ajax({
	            type: "POST",
	            url: baseurl+"/visit/employee/ActModifEmployee",
	            contentType: 'application/json',
	            data:JSON.stringify(frm.serializeJSON()),
	            success: function(result){
	              //  alert(result);
	            //  chatWith('9','name');
	                if(result==0){
	                    alerts(0,msj,"El employee se modifico con exito");   
	                    loadDataTable("#tbEmployee");
	                    frm.trigger('reset');
	                }else{
	                    alerts(2,msj,"No se completo el proceso.. !!!");
	                }
	                
	                
	            },
	            error: function() {
	                alerts(3,msj,"A ocurrido un error interno !!!");
	            } 
	        });
	    });
	    
	    
	    //btnEliminaUsuario
	    $(document).on("click","#btnDeleteEmployee",function(e){    

	        var obj = this;
	        ezBSAlert({
	        type: "confirm",
	        headerText:"Confirm",
	        messageText: "Are you sure about this ?",
	        alertType: "warning"
	        }).done(function (e) {
	          var id_employee = $(obj).data('id');
	          //console.log("confirma::"+idProduct);
	          //var url =baseurl+"/usuarios/ActEliminarUsuario";
	          if(e){
	              $.ajax({
	                url: baseurl+"/visit/employee/ActDeleteEmployee",
	                type: 'POST',
	                data: { id_employee:id_employee} ,
	                //contentType: 'application/json; charset=utf-8',
	                success: function (result) {
	                    if(result==0){
	                        //alerts(0,msj,"");   
	                        loadDataTable("#tbEmployee");
	                        ezBSAlert({ headerText:"success", messageText: "El Employee se elmino con exito", alertType: "success"});
	                    }else{
	                        ezBSAlert({ headerText:"Error",messageText: "No se completo el proceso.. !!!", alertType: "danger"});
	                    }


	                },
	                error: function () {
	                    ezBSAlert({ headerText:"Error",messageText: "A ocurrido un error interno !!!", alertType: "danger"});
	                }
	              });
	            
	          }
	        });
	   });
	 
	 var dataTable = $('#tbEmployee').DataTable({
	    	processing: true,
	    	bJQueryUI: true,
	        //"serverSide": true,
	        responsive: true,
	        autoWidth: false,
	        ordering:false,
	        ajax:{
	            url :baseurl+"/visit/employee/ActlistEmployee", // json datasource
	            type: "post",  // method  , by default get
	            complete: function(){
	               table=$(this);
	              //alert(dataTable);
	              //console.log(table.parent());
	              table.parent().addClass("table-responsive");
	             // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
	            },
	            error: function(){  // error handling
	                $(".tbEmployee-grid-error").html("");
	                $("#tbEmployee-grid").append('<tbody class="tbEmployee-grid-error"><tr><th colspan="3">No data found in the server</th></tr></tbody>');
	                $("#tbEmployee-grid_processing").css("display","none");

	            }
	        },
	        "aoColumns": [
	        { "mData": "full_name","width":"50%" },
	        { "mData": "occupation_name" },
	        { "mData": "idcard" },
	        { "mData":null,
	            "bSortable": false,
	            "sClass": "text-center",
	            "mRender": function(data, type, full) {
	            	return '<button data-toggle="modal" data-target="#myModalViewEmployee" data-remote="false" type="button" data-id="'+data.id_employee+'"  id="btnViewEditEmployee" class="btn btn-info btn-xs" href="'+baseurl+'/visit/employee/ActViewModifEmployee" ><i style="font-size: 18px;" class="fa fa-edit"></i></button>';
	            }
	        },
	        { "mData":null,
	            "bSortable": false,
	            "sClass": "text-center",
	            "mRender": function(data, type, full) {
	            	//console.log(data);
	            	return '<button type="button" data-id="'+data.id_employee+'" id="btnDeleteEmployee" class="btn btn-danger btn-xs" title="Delete" ><i style="font-size: 18px;" class="fa fa-trash"></i></button>';
	            }
	        }
	        
	        ] 
	    });
	 
	 /************************ Visitors ******************************/
	 $('#myModalViewVisitor').on('show.bs.modal', function (e) { 
	        
	        console.log("myModalVieVisitor");
	        
	        var btn = $(e.relatedTarget);
	        var id_visitor=btn.data('id');
	       var data=null;
	       var title="New  Visitor";
	       var frm='#formRegisterVisitor';
	       
	       if(btn.attr("id")==="btnViewEditVisitor"){ 
	           console.log("btnViewEditVisitor");
	            data={"id_visitor":id_visitor};
	            title="Modif Visitor";
	            frm='#formModifVisitor';
	       }
	       
	        $.post(btn.attr("href"),data, function( data ) { 
	        	
	        	$('#modal-title').html(title);
	            $('#modal-body').html(data); 
	            $(frm).validator();
	        }); 
	    });

	 
	 
	 $(document).on("submit","#formRegisterVisitor",function(e){    
	      
	        if (e.isDefaultPrevented()) {
	            return false;
	        }
	       e.preventDefault();
	       
	       frm=$(this);
	       
	       var msj=$("#msjmntVisitor");
	           msj.removeAttr('class');
	           msj.html("");
	           
	        
	        console.log(":::frmrRegisterVisitor");
	        //return;
	       $.ajax({
	            type: "POST",
	            url: baseurl+"/visit/visitor/ActRegisterVisitor",
	            contentType: 'application/json',
	            data:JSON.stringify(frm.serializeJSON()),
	            success: function(result){
	              //  alert(result);
	            //  chatWith('9','name');
	                if(result==0){
	                    alerts(0,msj,"la Employee se grabo con exito");   
	                    loadDataTable("#tbVisitor");
	                    frm.trigger('reset');
	                }else{
	                    alerts(2,msj,"La Employee ya existe");
	                }
	                
	                
	            },
	            error: function() {
	                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
	                
	                alerts(3,msj,"A ocurrido un error interno !!!");
	            } 
	        });
	      //  alert("ajax");
	    });
	 
	 $(document).on("submit","#formModifVisitor",function(e){    
	      
	        if (e.isDefaultPrevented()) {
	            return false;
	        }
	       e.preventDefault();
	       
	       frm=$(this);
	       
	       var msj=$("#msjmntVisitor");
	           msj.removeAttr('class');
	           msj.html("");
	           
	        
	        console.log(":::formModifVisitor");
	        //return;
	       $.ajax({
	            type: "POST",
	            url: baseurl+"/visit/visitor/ActModifVisitor",
	            contentType: 'application/json',
	            data:JSON.stringify(frm.serializeJSON()),
	            success: function(result){
	              //  alert(result);
	            //  chatWith('9','name');
	                if(result==0){
	                    alerts(0,msj,"El Visitor se modifico con exito");   
	                    loadDataTable("#tbVisitor");
	                    //frm.trigger('reset');
	                }else{
	                    alerts(2,msj,"No se completo el proceso.. !!!");
	                }
	                
	                
	            },
	            error: function() {
	                alerts(3,msj,"A ocurrido un error interno !!!");
	            } 
	        });
	    });
	    
	    
	    //btnEliminaUsuario
	    $(document).on("click","#btnDeleteVisitor",function(e){    

	        var obj = this;
	        ezBSAlert({
	        type: "confirm",
	        headerText:"Confirm",
	        messageText: "Are you sure about this ?",
	        alertType: "warning"
	        }).done(function (e) {
	          var id_visitor = $(obj).data('id');
	          //console.log("confirma::"+idProduct);
	          //var url =baseurl+"/usuarios/ActEliminarUsuario";
	          if(e){
	              $.ajax({
	                url: baseurl+"/visit/visitor/ActDeleteVisitor",
	                type: 'POST',
	                data: { id_visitor:id_visitor} ,
	                //contentType: 'application/json; charset=utf-8',
	                success: function (result) {
	                    if(result==0){
	                        //alerts(0,msj,"");   
	                        loadDataTable("#tbVisitor");
	                        ezBSAlert({ headerText:"success", messageText: "El Visitor se elmino con exito", alertType: "success"});
	                    }else{
	                        ezBSAlert({ headerText:"Error",messageText: "No se completo el proceso.. !!!", alertType: "danger"});
	                    }


	                },
	                error: function () {
	                    ezBSAlert({ headerText:"Error",messageText: "A ocurrido un error interno !!!", alertType: "danger"});
	                }
	              });
	            
	          }
	        });
	   });
	 
	 var dataTable = $('#tbVisitor').DataTable({
	    	processing: true,
	    	bJQueryUI: true,
	        //"serverSide": true,
	        responsive: true,
	        autoWidth: false,
	        ordering:false,
	        ajax:{
	            url :baseurl+"/visit/visitor/ActlistVisitor", // json datasource
	            type: "post",  // method  , by default get
	            complete: function(){
	               table=$(this);
	              //alert(dataTable);
	              //console.log(table.parent());
	              table.parent().addClass("table-responsive");
	             // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
	            },
	            error: function(){  // error handling
	                $(".tbVisitor-grid-error").html("");
	                $("#tbVisitor-grid").append('<tbody class="tbVisitor-grid-error"><tr><th colspan="3">No data found in the server</th></tr></tbody>');
	                $("#tbVisitor-grid_processing").css("display","none");

	            }
	        },
	        "aoColumns": [
	        { "mData": "full_name","width":"20%" },
	        { "mData": "number_license" },
	        { "mData": "citizen_ship" },
	        { "mData": "email" },
	        { "mData": "phone_number" },
	        { "mData":null,
	            "bSortable": false,
	            "sClass": "text-center",
	            "mRender": function(data, type, full) {
	            	return '<button data-toggle="modal" data-target="#myModalViewVisitor" data-remote="false" type="button" data-id="'+data.id_visitor+'"  id="btnViewEditVisitor" class="btn btn-info btn-xs" href="'+baseurl+'/visit/visitor/ActViewModifVisitor" ><i style="font-size: 18px;" class="fa fa-edit"></i></button>';
	            }
	        },
	        { "mData":null,
	            "bSortable": false,
	            "sClass": "text-center",
	            "mRender": function(data, type, full) {
	            	//console.log(data);
	            	return '<button type="button" data-id="'+data.id_visitor+'" id="btnDeleteVisitor" class="btn btn-danger btn-xs" title="Delete" ><i style="font-size: 18px;" class="fa fa-trash"></i></button>';
	            }
	        }
	        
	        ] 
	    });
	 
	 /************************ schedule ******************************/
	   
	 
	 $('#myModalViewSchedule').on('show.bs.modal', function (e) { 
	        
	        console.log("myModalViewSchedule");
	        
	        var btn = $(e.relatedTarget);
	        var id_visitor=btn.data('id');
	       var data=null;
	       var title="New  Schedule";
	       var frm='#formRegisterSchedule';
	       
	       if(btn.attr("id")==="btnViewEditSchedule"){ 
	           console.log("btnViewEditVisitor");
	            data={"id_visitor":id_visitor};
	            title="Modif Schedule";
	            frm='#formModifSchedule';
	       }
	       
	        $.post(btn.attr("href"),data, function( data ) { 
	        	
	        	$('#modal-title').html(title);
	            $('#modal-body').html(data); 
	            $(frm).validator();
	            $("#cboVisitor").select2({
	    		    placeholder: "Select a visitor"
	            });
	            $("#cboEmployee").select2({
	    		    placeholder: "Select a employee"
	            });
	            $("#cboCompany").select2({
	    		    placeholder: "Select a cboCompany"
	            });
	            $("#cboDepartment").select2({
	    		    placeholder: "Select a Department"
	            });
	            $('#containerDateIni,#containerDateFin').datetimepicker({
	            	//format: 'DD-MM-YYYY'
	            	format: 'YYYY-MM-DD'
	            	//minDate: moment(1, 'h')
	            });
	            
	            $.fn.modal.Constructor.prototype.enforceFocus = function () {};
	            $('#containerHoraIni').datetimepicker({
	                format: 'LT'
	                //minDate: moment()
	            });
	        }); 
	    });

	 
	 
	 $(document).on("submit","#formRegisterSchedule",function(e){    
	      
	        if (e.isDefaultPrevented()) {
	            return false;
	        }
	       e.preventDefault();
	       
	       frm=$(this);
	       
	       var msj=$("#msjmntSchedule");
	           msj.removeAttr('class');
	           msj.html("");
	           
	        
	        console.log(":::formRegisterSchedule");
	        //return;
	       $.ajax({
	            type: "POST",
	            url: baseurl+"/visit/schedule/ActRegisterSchedule",
	            contentType: 'application/json',
	            data:JSON.stringify(frm.serializeJSON()),
	            success: function(result){
	              //  alert(result);
	            //  chatWith('9','name');
	                //if(result > 0){
	                    alerts(0,msj,"El codigo de ingreso generado es:"+result);   
	                    loadDataTable("#tbSchedule");
	                    frm.trigger('reset');
	                /*}else{
	                    alerts(2,msj,"A ocurrido un error interno !!!");
	                }*/
	                
	                
	            },
	            error: function() {
	                //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
	                
	                alerts(3,msj,"A ocurrido un error interno !!!");
	            } 
	        });
	      //  alert("ajax");
	    });
	 
	 $(document).on("submit","#formModifSchedule",function(e){    
	      
	        if (e.isDefaultPrevented()) {
	            return false;
	        }
	       e.preventDefault();
	       
	       frm=$(this);
	       
	       var msj=$("#msjmntVisitor");
	           msj.removeAttr('class');
	           msj.html("");
	           
	        
	        console.log(":::formModifVisitor");
	        //return;
	       $.ajax({
	            type: "POST",
	            url: baseurl+"/visit/visitor/ActModifVisitor",
	            contentType: 'application/json',
	            data:JSON.stringify(frm.serializeJSON()),
	            success: function(result){
	              //  alert(result);
	            //  chatWith('9','name');
	                if(result==0){
	                    alerts(0,msj,"El Visitor se modifico con exito");   
	                    loadDataTable("#tbVisitor");
	                    //frm.trigger('reset');
	                }else{
	                    alerts(2,msj,"No se completo el proceso.. !!!");
	                }
	                
	                
	            },
	            error: function() {
	                alerts(3,msj,"A ocurrido un error interno !!!");
	            } 
	        });
	    });
	    
	    
	    //btnEliminaUsuario
	    $(document).on("click","#btnDeleteSchedule",function(e){    

	        var obj = this;
	        ezBSAlert({
	        type: "confirm",
	        headerText:"Confirm",
	        messageText: "Are you sure about this ?",
	        alertType: "warning"
	        }).done(function (e) {
	          var id_visit_schedule = $(obj).data('id');
	          //console.log("confirma::"+idProduct);
	          //var url =baseurl+"/usuarios/ActEliminarUsuario";
	          if(e){
	              $.ajax({
	                url: baseurl+"/visit/schedule/ActDeleteSchedule",
	                type: 'POST',
	                data: { id_visit_schedule:id_visit_schedule} ,
	                //contentType: 'application/json; charset=utf-8',
	                success: function (result) {
	                    if(result==0){
	                        //alerts(0,msj,"");   
	                        loadDataTable("#tbSchedule");
	                        ezBSAlert({ headerText:"success", messageText: "El Schedule se elmino con exito", alertType: "success"});
	                    }else{
	                        ezBSAlert({ headerText:"Error",messageText: "No se completo el proceso.. !!!", alertType: "danger"});
	                    }


	                },
	                error: function () {
	                    ezBSAlert({ headerText:"Error",messageText: "A ocurrido un error interno !!!", alertType: "danger"});
	                }
	              });
	            
	          }
	        });
	   });
	 
	 var dataTable = $('#tbSchedule').DataTable({
	    	processing: true,
	    	bJQueryUI: true,
	        //"serverSide": true,
	        responsive: true,
	        autoWidth: false,
	        ordering:false,
	        ajax:{
	            url :baseurl+"/visit/schedule/ActlistSchedule", // json datasource
	            type: "post",  // method  , by default get
	            complete: function(){
	               table=$(this);
	              //alert(dataTable);
	              //console.log(table.parent());
	              table.parent().addClass("table-responsive");
	             // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
	            },
	            error: function(){  // error handling
	                $(".tbSchedule-grid-error").html("");
	                $("#tbSchedule-grid").append('<tbody class="tbSchedule-grid-error"><tr><th colspan="3">No data found in the server</th></tr></tbody>');
	                $("#tbSchedule-grid_processing").css("display","none");

	            }
	        },
	        "aoColumns": [
	        { "mData": "call_cod"},
	        { "mData": "full_name_visitor" },
	        { "mData": "company_name" },
	        { "mData": "date_ini" },
	        { "mData": "date_end" },
	        { "mData": "hour" },
	        { "mData": "full_name_employee" },
	        { "mData":null,
	            "bSortable": false,
	            "sClass": "text-center",
	            "mRender": function(data, type, full) {
	            	return '<button data-toggle="modal" data-target="#myModalViewVisitor" data-remote="false" type="button" data-id="'+data.id_visit_schedule+'"  id="btnViewEditVisitor" class="btn btn-info btn-xs" href="'+baseurl+'/visit/visitor/ActViewModifVisitor" ><i style="font-size: 18px;" class="fa fa-edit"></i></button>';
	            }
	        },
	        { "mData":null,
	            "bSortable": false,
	            "sClass": "text-center",
	            "mRender": function(data, type, full) {
	            	//console.log(data);
	            	return '<button type="button" data-id="'+data.id_visit_schedule+'" id="btnDeleteSchedule" class="btn btn-danger btn-xs" title="Delete" ><i style="font-size: 18px;" class="fa fa-trash"></i></button>';
	            }
	        }
	        
	        ] 
	    });
	 
	 
});