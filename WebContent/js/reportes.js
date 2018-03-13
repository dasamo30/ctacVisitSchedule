jQuery(document).ready(
				function() {

					console.log("reportes.js");

					$('#fecha_inicio').datetimepicker(
							{
								format : 'YYYY-MM-DD',
								minDate : moment().subtract(14, 'days')
										.millisecond(0).second(0).minute(0)
										.hour(0),
								maxDate : "moment",
							});
					$('#fecha_fin').datetimepicker(
							{
								format : 'YYYY-MM-DD',
								minDate : moment().subtract(14, 'days')
										.millisecond(0).second(0).minute(0)
										.hour(0),
								maxDate : "moment",
							});

					$(document).on("submit","#formReporte",function(e) {
										
										if (e.isDefaultPrevented()) {
											return false;
										}
										if (($("#fecha_inicio").val() != '')|| ($("#fecha_fin").val() != '')) {
											frm = $("#formReporte");
											$.ajax({
														type : "POST",
														url : baseurl+"/reportes/consultarcampania",
														contentType : 'application/json',
														data : JSON.stringify(frm.serializeJSON()),
														success : function(result) {
															if (result == "[]") {
																$('#div_reporteCampania').html("<h1>No se encontraron Resultados</h1>");
																$('#g2').html("");
																$('#g3').html("");
																$('#g4').html("");
															} else {
																pie_chart(result);
															}

														},
														error : function(err) {
													
															console.log(err);
															alerts("A ocurrido un error interno !!!");
															msj.addClass("alert alert-danger alert-dismissable");
															msj.html("<p>A ocurrido un error interno !!!</p>");
														}
													});
										} else {
											$('#div_reporteCampania').html("<h1>Las fechas no pueden estar vacias</h1>");
										}
										e.preventDefault();
									});

					pie_chart = function(data) {
						if (data != "") {
							var data = JSON.parse(data);
							var pie = "";
							var pie_envio = "";
							var labels3 = "";
							var enviado = "";
							var no_enviado = "";
							var lista_negra="";
							var total = "";
							var cant_enviado1=0;
							var cant_no_enviado=0;
							var cant_total = 0;
							var cant_lista_negra=0;
							var lista_negra_total=0;
							var lista_negra_total1=0;
							$.each(data,function(key, val) {
												if (pie == "") {
													pie = '["' + val.nombre+ '<br>'+val.fecha+'",'+ val.cantidad+ ']';
													labels3 = '"' + val.nombre+ '<br>'+val.fecha+'"';
													enviado = val.enviado;
													cant_enviado = val.enviado;
													no_enviado = val.no_enviado;
													cant_no_enviado = val.no_enviado;
													total = val.cantidad;
													cant_total=val.cantidad;
													lista_negra_total=val.cantidad-(enviado+no_enviado);
													lista_negra=lista_negra_total;
													console.log("fecha:"+val.fecha+"\n\n");
												} else {
													pie = pie + ',["'+ val.nombre + '<br>'+val.fecha+'",'+ val.cantidad+ ']';
													labels3 = labels3 + ',"'+ val.nombre + '<br>'+val.fecha+'"';
													enviado = enviado + ','+ val.enviado;
													lista_negra_total=val.cantidad-(val.enviado+val.no_enviado);
													lista_negra = lista_negra+','+lista_negra_total;
													cant_enviado= cant_enviado+val.enviado;
													no_enviado = no_enviado+ ','+ val.no_enviado;
													cant_no_enviado = cant_no_enviado+val.no_enviado;
													total = total + ','+ val.cantidad;
													cant_total=cant_total+val.cantidad;
													console.log("fecha:"+val.fecha+"\n\n");
												}
											});
							cant_lista_negra=cant_total-(cant_enviado+cant_no_enviado);
							enviado = '[' + enviado + ']';
							enviado = JSON.parse(enviado);
							no_enviado = '[' + no_enviado + ']';
							no_enviado = JSON.parse(no_enviado);
							lista_negra = '[' +lista_negra + ']';
							console.log("Lista Negra: "+lista_negra);
							lista_negra = JSON.parse(lista_negra);
							total = '[' + total + ']';
							total = JSON.parse(total);
							labels3 = '[' + labels3 + ']';
							labels3 = JSON.parse(labels3);
							pie = "[" + pie + "]";
							pie = JSON.parse(pie);
							pie_envio= '[["Mensajes Enviados",'+ cant_enviado + '],["Mensajes No Enviados",'+ cant_no_enviado + '],["Mensajes en Lista Negra",'+ cant_lista_negra + ']]';
							pie_envio = JSON.parse(pie_envio);
							Highcharts.chart('g2',{
												chart : {
													type : 'pie',
													options3d : {
														enabled : true,
														alpha : 45,
														beta : 0
													}
												},
												title : {
													text : 'Reporte General de Campañas'
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.y} </b><br>Representa a la fecha: <b>{point.percentage:.1f}%</b>'
												},
												plotOptions : {
													pie : {
														allowPointSelect : true,
														cursor : 'pointer',
														depth : 35,
														dataLabels : {
															enabled : true,
															format : '{point.name}'
														}
													}
												},
												series : [ {
													type : 'pie',
													name : 'Total de mensajes',
													data : pie
												} ]
											});
							
							Highcharts.chart('g4',{
								chart : {
									type : 'pie',
									options3d : {
										enabled : true,
										alpha : 45,
										beta : 0
									}
								},
								title : {
									text : 'Reporte General de Mensajes'
								},
								tooltip : {
									pointFormat : '{series.name}: <b>{point.y} </b><br>Representa a la fecha: <b>{point.percentage:.1f}%</b>'
								},
								plotOptions : {
									pie : {
										allowPointSelect : true,
										cursor : 'pointer',
										depth : 35,
										dataLabels : {
											enabled : true,
											format : '{point.name}'
										}
									}
								},
								series : [ {
									type : 'pie',
									name : 'Total de Mensajes ',
									data : pie_envio
								} ]
							});
							
							
							Highcharts.chart('g3', {
								chart : {
									type : 'column',
									options3d : {
										enabled : true,
										alpha : 10,
										beta : 25,
										depth : 70
									}
								},
								title : {
									text : 'Reporte por Campañas'
								},
								subtitle : {
									text : 'Clasificación de Mensajes'
								},
								plotOptions : {
									column : {
										depth : 25
									}
								},
								xAxis : {
									categories : labels3,
									labels : {
										skew3d : true,
										style : {
											fontSize : '16px'
										}
									}
								},
								yAxis : {
									title : {
										text : null
									}
								},
								series : [ {
									name : 'Total de Mensajes',
									data : total

								}, {
									name : 'Enviados',
									data : enviado

								}, {
									name : 'No Enviados',
									data : no_enviado

								},{
									name : 'Lista Negra',
									data : lista_negra

								} ]
							});

							$('#div_reporteCampania').html("");

						}
						;

					}

				});