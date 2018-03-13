jQuery(document).ready( function () {
    console.log("listanegra");
    
    ProcLista=function(){
    	$.ajax({
            type: "POST",
            url: baseurl+"/listanegra/listar_listanegra",
            dataType:'json',
            success: function(result){
            	 console.log(result);
                if(result.status==1){
                 	Gridlistanegra(result.aaData);
                }else{
                	Gridlistanegra('');
                }
            },
            error: function() {
                  alerts(3,msjerror,"A ocurrido un error interno !!!");
            } 
        });
    };
    
    ProcDetalle=function(id, indica){
    	
    	//alert('1111');
    	//return;
    	$.ajax({
            type: "POST",
            url: baseurl+"/listanegra/detalle_listanegra",
            data:{idkey:id},
            dataType:'json',
            success: function(result){
            	 console.log(result);
                if(result.status==1){
                	Griddetalle(result.aaData, id, indica);
                }else{
                	Griddetalle('');
                }
            },
            error: function() {
                  alerts(3,msjerror,"A ocurrido un error interno !!!");
            } 
        });
    	
    };
    
    Gridlistanegra=function(data){
   	    newHtml='';
   	    newHtml+='<table class="table table-bordered table-striped" id="example">';
   	    newHtml+='<thead>';
   	    newHtml+='<tr>';
   	    newHtml+='<th class="text-center">Ver detalle</th>';
   	    newHtml+='<th>Descripcion</th>';
   	    newHtml+='<th>Usuario</th>';
   	    newHtml+='<th class="text-center">Editar</th>';
   	    newHtml+='<th class="text-center">Eliminar</th>';
   	    newHtml+='</tr>';
   	    newHtml+='</thead>';
	   	newHtml+='<tbody>';
	   	if(data!= ""){
		$.each(data,function(key,fila){
			 newHtml+='<tr id="'+fila.id_cab_lista_negra+'">';
			 newHtml+='<td class="text-center" id="td_'+fila.id_cab_lista_negra+'" nowrap="nowrap"><a href="javascript:ProcDetalle(\''+fila.id_cab_lista_negra+'\',1)" title="Visualizar Detalle"><img title="Visualizar Detalle" src="'+baseurl+'/images/add.png" border="0"></a></td>';
			 newHtml+='<td>'+fila.descripcion+'</td>';
			 newHtml+='<td>'+fila.login+'</td>';
			 newHtml+='<td class="text-center" nowrap="nowrap"><button data-toggle="modal" data-target="#myModalViewUsuario" data-remote="false" class="btn btn-info btn-xs" href="'+baseurl+'/listanegra/ActViewModifListanegra" type="button" data-id="'+fila.id_cab_lista_negra+'" data-name="'+fila.descripcion+'" ><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></button></td>';
			 newHtml+='<td class="text-center" nowrap="nowrap"><button class="btn btn-danger btn-xs" type="button" data-id="'+fila.id_cab_lista_negra+'" id="btnanularlista"><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button></td>';
			 newHtml+='</tr>';
		 });
	   	};
		newHtml+='</tbody>';
   	    newHtml+='</table>';
   	    $('#div_gridlistanegra').empty().append(newHtml);
   	    $('#example').DataTable();
   	 $('#example_wrapper .top').hide();
     $('#example_filter').hide();
     $('#example_length').hide();
    };
    
    Eventhijo=function(id_table,id_fila,content,indica){
    	if(indica==1){
                var tabla = document.getElementById(id_table);
                var numColumnas = tabla.rows[0].cells.length;
                $('#'+id_fila).after('<tr id="ext_tr_'+id_fila+'"><td colspan="'+numColumnas+'"><div id="div_'+id_fila+'" style="padding 15px 15px 15px 15px;border:2px solid #9966CC;"></div></td></tr>');
                $('#div_'+id_fila).empty().html(content);
    	}
    	else{
    		$('#ext_tr_'+id_fila).remove();
    	}
    };
    
    DeleteHijo=function(id){
		$('#ext_tr_'+id).remove();
		$('#td_'+id).empty().append('<a href="javascript:ProcDetalle(\''+id+'\',1)" ><img src="'+baseurl+'/images/add.png" border="0" ></a>');
	};
    
    Griddetalle=function(data, id, indica){
    	newHtml='';
    	newHtml='<table class="table table-bordered table-striped" id="example'+id+'">';
    	newHtml+='<thead>';
    	newHtml+='<tr>';
    	newHtml+='<th nowrap="nowrap">Numero de Abonado</th>';
    	newHtml+='<th class="text-center">Editar</th>';
    	newHtml+='<th class="text-center">Eliminar</th>';
    	newHtml+='</tr>';
    	newHtml+='</thead>';
    	newHtml+='<tbody>';
    	if(data!= ""){
	    	$.each(data,function(key,d){
	    			newHtml+='<tr id="td_'+d.id_det_lista_negra+'">';
	                newHtml+='<td nowrap="nowrap"><input type="hidden" id="t_numero_'+d.id_det_lista_negra+'" value="'+d.numero+'" title="'+d.numero+'"  size="11" maxlength="11"><span id="t_title_'+d.id_det_lista_negra+'">'+d.numero+'</span></td>';
	                newHtml+='<td class="text-center" nowrap="nowrap"><button class="btn btn-info btn-xs" type="button" id="btnupdatedetalle_'+d.id_det_lista_negra+'" onclick="javascript:Proc_update_list('+d.id_det_lista_negra+');" ><i style=\"font-size: 18px;\" class=\"fa fa-edit\"></i></button>      <button class="btn btn-primary btn-xs btnguardado" type="button" id="btngrabardetalle_'+d.id_det_lista_negra+'" onclick="javascript:Proc_grabar_listdet('+d.id_det_lista_negra+');" ><i style=\"font-size: 18px;\" class=\"fa fa-save\"></i></button></td>';
	                newHtml+='<td class="text-center" nowrap="nowrap"><button class="btn btn-danger btn-xs" type="button" data-id="'+id+'" data-id_det="'+d.id_det_lista_negra+'" id="btndeletedetalle"><i style=\"font-size: 18px;\" class=\"fa fa-trash\"></i></button></td>';
	                newHtml+='</tr>';
	    	});
    	};
    	newHtml+='</tbody>';
    	newHtml+='</table>';
    	Eventhijo('example',id,newHtml,indica);
    	if(indica==1){
    	    $('#td_'+id).empty().append('<a href="javascript:DeleteHijo(\''+id+'\')" ><img src="'+baseurl+'/images/menos.png" border="0" ></a>');
    	}else{
    	}
    	$('.btnguardado').hide();
    	oTable = $('#example'+id).dataTable();
    };
    
    ProcLista();
    
   //$(document).on("click","#btnupdatedetalle",function(e){    
    
    Proc_update_list=function(codigo){
    	//var obj = this;
        //var codigo = $(obj).data('id_detalle');
        $('#t_numero_'+codigo).attr('type','text');
        $('#t_title_'+codigo).hide();
        $('#btngrabardetalle_'+codigo).show();
        $('#btnupdatedetalle_'+codigo).hide();
   };
   
   Proc_grabar_listdet=function(codigo_d){
       ezBSAlert({
       type: "confirm",
       headerText:"Confirmacion",
       messageText: "Esta seguro de editar el registro ?",
       alertType: "warning"
       }).done(function (e) {
         var numero_d = $('#t_numero_'+codigo_d).val();
         if(e){
             $.ajax({
               url: baseurl+"/listanegra/grabar_det_listanegra",
               type: 'POST',
               dataType:'json',
               data: { id_det_lista_negra:codigo_d,numero:$('#t_numero_'+codigo_d).val()} ,
               success: function (result) {
                   if(result.status==0){
                	   
                	   $('#t_numero_'+codigo_d).attr('type','hidden');
                       $('#t_title_'+codigo_d).show();
                       $('#t_title_'+codigo_d).html(numero_d);
                       $('#btngrabardetalle_'+codigo_d).hide();
                       $('#btnupdatedetalle_'+codigo_d).show();
                       
                       ezBSAlert({ headerText:"success", messageText: "El registro se edito con exito", alertType: "success"});
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
  };
    
    $(document).on("click","#btnanularlista",function(e){    

        var obj = this;
        ezBSAlert({
        type: "confirm",
        headerText:"Confirmacion",
        messageText: "Esta seguro de anular el registro ?",
        alertType: "warning"
        }).done(function (e) {
          var codigo = $(obj).data('id');
          if(e){
              $.ajax({
                url: baseurl+"/listanegra/anular_listanegra",
                type: 'POST',
                dataType:'json',
                data: { id_cab_lista_negra:codigo} ,
                success: function (result) {
                    if(result.status==0){ 
                        ProcLista();
                        ezBSAlert({ headerText:"success", messageText: "El registro  se anulo con exito", alertType: "success"});
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
    
   $(document).on("click","#btndeletedetalle",function(e){    
    	var obj = this;
        ezBSAlert({
        type: "confirm",
        headerText:"Confirmacion",
        messageText: "Esta seguro de eliminar el registro ?",
        alertType: "warning"
        }).done(function (e) {
          var codigo = $(obj).data('id');
          var id_det = $(obj).data('id_det');
          if(e){
              $.ajax({
                url: baseurl+"/listanegra/delete_det_listanegra",
                type: 'POST',
                dataType:'json',
                data: { id_cab_lista_negra:codigo, id_det_lista_negra:id_det} ,
                success: function (result) {
                    if(result.status==0){
                    	 $('#td_'+id_det).remove();
                        //ezBSAlert({ headerText:"success", messageText: "El registro  se anulo con exito", alertType: "success"});
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
    
    $('#myModalViewUsuario').on('show.bs.modal', function (e) {  
       console.log("myModalViewUsuario");
       var btn = $(e.relatedTarget);
       var id_cab_lista_negra=btn.data('id');
       var descripcion=btn.data('name');
       var data=null;
       var title="Editar lista";
       var frm='#frmrModificaListanegra';
       data={"id_cab_lista_negra":id_cab_lista_negra, "descripcion":descripcion};
       $.post(btn.attr("href"),data, function( data ) { 
           $('#myModalLabel').html(title);
           $('#modal-body').html(data); 
           $(frm).validator();
       }); 
    });
    
   
    $(document).on("click","#btneditarlista",function(e){    
    	
        var obj = this;
        ezBSAlert({
        type: "confirm",
        headerText:"Confirmacion",
        messageText: "Esta seguro de editar el registro ?",
        alertType: "warning"
        }).done(function (e) {
          if(e){
              $.ajax({
                url: baseurl+"/listanegra/ActModificaListanegra",
                type: 'POST',
                dataType:'json',
                data: { id_cab_lista_negra:$('#id_cab').val(),
                		descripcion:$('#txt_descripcion').val(),
                		id_usuario:$('#txt_usuario').val()
                } ,
                success: function (result) {
                    if(result.status==0){
                        ProcLista();
                        ezBSAlert({ headerText:"success", messageText: "El registro se actualizo con exito", alertType: "success"});
                        $("#frmrModificaListanegra").trigger('reset');
                        $('#myModalViewUsuario').modal('hide');
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
    
   $(document).on("submit","#formReporte",function(e){
      
        if (e.isDefaultPrevented()) {
            return false;
        }

        e.preventDefault();
        var msj=$("#msjgrareport");
        var msjerror=$("#msjgrareporterror");
        
           msj.removeAttr('class');
           msj.html("");
           msjerror.removeAttr('class');
           msjerror.html("");
       
           var form_data = new FormData(this);        
           jqXHR=$.ajax({
            type: "POST",
            url: baseurl+"/listanegra/grabar_listanegra",
            contentType: false,
            cache: false,
            processData:false,
            data:form_data,
            beforeSend: function() {
            	$.blockUI({ 
            		message: '<div>Procesando...</div> <img src="'+baseurl+'/plugins/blockUI/widget-loader-lg.gif" style="width:100%;"/> ',
            		css: {
                        border: 'none',
                        left: ($(window).width() - 200) /2 + 'px', 
                        width: '200px', 
                        padding: '15px', 
                        'background-color': 'transparent',
                        '-webkit-border-radius': '10px',
                        'border-radius': '10px',
                        opacity: 1,
                        color: '#337ab7',
                        'font-weight':'bold',
                        'z-index':2011
                    },
                    overlayCSS:  { backgroundColor: '#FFFFFF'},
                    
                    baseZ: 2000
            		});
            },
            success: function(result){
                if(result===0){ 
                	alerts(1,msj,"Se Grabo Correctamente la Lista Negra");
                	$('#txtdescripcion,#InputFileFtp').val('');
                	$('.form-control').html('');
                	ProcLista();
                }else if(result===2){
                    alerts(2,msjerror,"Error al insertar en la tabla lista negra");
                }else{
                    alerts(3,msjerror,"A ocurrido un error interno !!!");
                }
            },
            error: function() {
                alerts(3,msjerror,"A ocurrido un error interno !!!");
            }
           });  
    });
     
    $("#InputFileFtp").attr("required", "true");
   	$("#idnumeros").removeAttr('required');
       
});
