jQuery(document).ready( function () {
   
    console.log("smsBroadcasterCampanias.js");
    
    /*setTimeout(function () { 
        //location.reload();
    	console.log("setTimeout:::");
     }, 10 * 1000);*/
    /*
    setInterval(function() {
        //cache_clear()
        //console.log("setTimeout:::");
        loadDataTable("#tbCampanias");
      }, 30 * 1000);
     */	
    
    $('#txtCategoria').on('change', function() {
        //console.log( this.value );
        if(this.value==1){
            $('#sectionmsj').show(); 
        	$('#txMensaje').attr('disabled', false); 
        	$('#txMensaje').attr('required', 'required');
        }else{
            $('#sectionmsj').hide();
        	$('#txMensaje').val("");
        	$('#txMensaje').removeAttr('required');
        	$('#txMensaje').attr('disabled', true);
        }
    });

    
    
    
    $('#containerDateIni').datetimepicker({
    	format: 'DD-MM-YYYY'
    	//minDate: moment(1, 'h')
    });
    /*.on('dp.change', function (e) {  
    	alert("aaa")
    	$('#containerDateIni').datetimepicker({
    		minDate: moment(1, 'h')
        });
    });*/
    
    
    $('#containerHoraIni').datetimepicker({
        format: 'LT'
        //minDate: moment()
    });
    
    $('#chkListaNegra').iCheck({
        checkboxClass: 'icheckbox_flat-green',
        radioClass: 'iradio_flat-green'
      });
    
    $('#chkListaNegra').on('ifChanged', function(e){
        //alert(123);
        console.log(e.target.checked);
        if(e.target.checked){
        	$('#sectioncbolstngra').show();
        	$('#cboListaNegra').attr('disabled', false); 
        	$('#cboListaNegra').attr('required', true);
        }else{
        	$('#sectioncbolstngra').hide();
        	$('#cboListaNegra').attr('disabled', true);
        	$('#cboListaNegra').attr('required', false);
        	
        }
    });
    
    
    //console.log("valida lista negra:: "+$('#chkListaNegra').is(':checked'));
    if($('#chkListaNegra').is(':checked')){
    	$('#cboListaNegra').attr('required', true);
    }
    
    //$('#formNuevaCampaniaSms').
    $(document).on("submit","#formNuevaCampaniaSms",function(e){    
        
        if (e.isDefaultPrevented()) {
            return false;
        }
        e.preventDefault();
        
        console.log("formNuevaCampaniaSms");
        
        var form_data = new FormData(this);   
        
        jqXHR=$.ajax({
        type: "POST",
        url: baseurl+"/smsbroadcaster/ActInsertarNuevaCampania", //enviado arhivos de fechas y numeros
        //contentType: 'application/json',
        contentType: false,
        cache: false,
        processData:false,
        data:form_data,//{data:JSON.stringify(obj.serializeJSON())},
        beforeSend: function() {
          
        },
        success: function(result){
          //  alert(result);
        //  chatWith('9','name');
        	console.log(" primer ajax"+result);
            if(result==0){
            	ezBSAlert({ headerText:"success", messageText: "La campaña se registro con exito", alertType: "success"}).done(function (e) {
            		window.location.href = baseurl+'/smsbroadcaster/campanias';
                });;
            }else if(result==1){
            	ezBSAlert({ headerText:"warning", messageText: "No se pudo completar el proceso !!!", alertType: "warning"});
            }else{
                ezBSAlert({ headerText:"Error", messageText: "No se pudo completar el proceso !!!", alertType: "danger"});
            }
            
            
        },
        error: function(xhr, status, error) {
            //estableceAlerta("#msj_urs","errors","A ocurrido un error interno !!!");
        	var err = JSON.parse(xhr.responseText);
        	console.log(err.Message);
        	ezBSAlert({ headerText:"Error",messageText: "A ocurrido un error interno !!!", alertType: "danger"});
        } 
    });
    });
    
    
    var dataTable = $('#tbCampanias').DataTable({
    	processing: true,
    	bJQueryUI: true,
        //"serverSide": true,
        responsive: true,
        autoWidth: false,
        ordering:false,
        ajax:{
            url :baseurl+"/smsbroadcaster/ActlistaCampanias", // json datasource
            type: "post",  // method  , by default get
            complete: function(){
               table=$('#tbCampanias');
              //alert(dataTable);
              //console.log(table.parent());
              table.parent().addClass("table-responsive");
             // table.parent().addClass('table-responsive');//.removeClass('col-sm-12');
            },
            error: function(){  // error handling
                $(".tbCampanias-grid-error").html("");
                $("#tbCampanias-grid").append('<tbody class="tbCampanias-grid-error"><tr><th colspan="3">No data found in the server</th></tr></tbody>');
                $("#tbCampanias-grid_processing").css("display","none");

            }
        },
        "aoColumns": [
        { "mData": "id_campania" },
        { "mData": "nombre_campania"},
        { "mData": "nombre_tipo_campania"},
        { "mData": "fecha_hora_inicio"},
        { "mData": "descripcion_estado"},
        { "mData":null,
            "bSortable": false,
            "sClass": "text-center",
            "mRender": function(data, type, full) {
            	//console.log(data);
            	//console.log(type);
            	//console.log(full);
              //return '<a class="btn btn-info btn-sm" href=#/' + full[0] + '>' + 'Edit' + '</a>';
            	return '<a data-id="'+data.id_campania+'" id="btnViewEditUsuario" class="btn btn-info btn-xs" href="'+baseurl+'/smsbroadcaster/ActViewModificaCampania/'+data.id_campania+'" title="Editar" ><i style="font-size: 18px;" class="fa fa-edit"></i></a> ' 
            		  +'<button type="button" data-id="'+data.id_campania+'" id="btnEliminaCampania" class="btn btn-danger btn-xs" title="Eliminar" ><i style="font-size: 18px;" class="fa fa-trash"></i></button>';
            }
        }
        
        ] 
    });
    
    //btnEliminaUsuario
    $(document).on("click","#btnEliminaCampania",function(e){    

        var obj = this;
        ezBSAlert({
        type: "confirm",
        headerText:"Confirm",
        messageText: "¿Seguro que quieres hacer esto?",
        alertType: "warning"
        }).done(function (e) {
          var id_campania = $(obj).data('id');
          //console.log("confirma::"+idProduct);
          //var url =baseurl+"/usuarios/ActEliminarUsuario";
          if(e){
              $.ajax({
                url: baseurl+"/smsbroadcaster/ActEliminaCampania",
                type: 'POST',
                data: { id_campania:id_campania} ,
                //contentType: 'application/json; charset=utf-8',
                success: function (result) {
                    if(result==0){
                        //alerts(0,msj,"");   
                        loadDataTable("#tbCampanias");
                        ezBSAlert({ headerText:"success", messageText: "La campaña se elmino con exito", alertType: "success"});
                    }else{
                       // alerts(2,msj,"No se completo el proceso.. !!!");
                        ezBSAlert({ headerText:"Error",messageText: "No se completo el proceso.. !!!", alertType: "danger"});
                    }


                },
                error: function () {
                    //alerts(3,msj,"A ocurrido un error interno !!!");
                    ezBSAlert({ headerText:"Error",messageText: "A ocurrido un error interno !!!", alertType: "danger"});
                }
              });
            
          }
        });
      });
    
    //formModifCampaniaSms
    $(document).on("submit","#formModifCampaniaSms",function(e){    
        
        if (e.isDefaultPrevented()) {
            return false;
        }
        e.preventDefault();    

        //var obj = this;
        var obj=$(this);
        console.log(obj.serializeJSON());
        ezBSAlert({
        type: "confirm",
        headerText:"Confirm",
        messageText: "¿Seguro que quieres modificar esto?",
        alertType: "warning"
        }).done(function (e) {
         // var id_campania = $(obj).data('id');
          //console.log("confirma::"+idProduct);
          //var url =baseurl+"/usuarios/ActEliminarUsuario";
          if(e){
        	  
              $.ajax({
                url: baseurl+"/smsbroadcaster/ActModificaCampania",
                type: 'POST',
                //data:{data:JSON.stringify(obj.serializeJSON())} ,
                //data:JSON.stringify($(obj).serializeJSON()),
                data:{data:JSON.stringify(obj.serializeJSON())},
                dataType : "json",
                //contentType: 'application/json; charset=utf-8',
                success: function (result) {
                    if(result==0){
                        //alerts(0,msj,"");   
                        //loadDataTable("#tbCampanias");
                        ezBSAlert({ headerText:"success", messageText: "La campaña se modifico con exito", alertType: "success"});
                        //window.location.href = baseurl+'/smsbroadcaster/campanias';
                    }else{
                       // alerts(2,msj,"No se completo el proceso.. !!!");
                        ezBSAlert({ headerText:"Error",messageText: "No se completo el proceso.. !!!", alertType: "danger"});
                    }


                },
                error: function () {
                    //alerts(3,msj,"A ocurrido un error interno !!!");
                    ezBSAlert({ headerText:"Error",messageText: "A ocurrido un error interno !!!", alertType: "danger"});
                }
              });
            
          }
        });
      });
    
   /* setInterval( function () {
        //table.ajax.reload( null, false ); // user paging is not reset on reload
    	//console.log("refresh table");
    	loadDataTable("#tbCampanias");
    }, 10000 );*/
});    