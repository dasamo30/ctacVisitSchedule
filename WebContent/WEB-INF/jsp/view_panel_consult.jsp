<%@include file="view_panel_header.jsp" %>
        <!-- Main content -->
        <section class="content">
       <div class="row">
       <div class="col-md-12">
       
		<div class="box box-primary" id="divReporteVoz">
		<div class="box-header with-border">
			<h3 class="box-title">Ingrese los Datos</h3>
			<div class="box-tools pull-right">
				<button class="btn btn-box-tool" data-widget="collapse">
					<i class="fa fa-minus"></i>
				</button>
			</div>
		</div>
		<!-- /.box-header -->
		<form id="formConsultVisitor" class="box-body" data-toggle="validator"  role="form" >
			
			<div class="row">
			
				<div class="col-md-2">
					<div class="form-group">
                      <label for="idcallcod">Visit Code</label>
                      <input type="text" class="form-control" id="idcallcod" name="call_cod"  placeholder="visit code" required>
                      <div class="help-block with-errors"></div>
                    </div>
				</div>
				
<!-- 				<div class="col-md-2"> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label>Fecha Inicio:</label> -->

<!-- 						<div class="input-group date"> -->
<!-- 							<div class="input-group-addon"> -->
<!-- 								<i class="fa fa-calendar "></i> -->
<!-- 							</div> -->
<!-- 							<input type="text" class="form-control pull-right" -->
<!-- 								id="fecha_inicio" required name="fecha_inicio"> -->
<!-- 						</div> -->
<!-- 						<div class="help-block with-errors"></div> -->
<!-- 					</div> -->
<!-- 				</div> -->

<!-- 				<div class="col-md-2"> -->
<!-- 					<div class="form-group"> -->
<!-- 						<label>Fecha Fin:</label> -->
<!-- 						<div class="input-group date"> -->
<!-- 							<div class="input-group-addon"> -->
<!-- 								<i class="fa fa-calendar"></i> -->
<!-- 							</div> -->
<!-- 							<input type="text" class="form-control pull-right" required -->
<!-- 								name="fecha_fin" id="fecha_fin"> -->
<!-- 						</div> -->
<!-- 						<div class="help-block with-errors"></div> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<div class="col-md-2">
					<div class="form-group">
						<label>&nbsp;</label>
						<div class="input-group date">
							<button type="submit" class="btn btn-primary">Buscar</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="box-body" >
			<div id="divConsultVisits">
		</div>
	</div>
       </div>
       </div>
        </section><!-- /.content -->
 <%@include file="view_panel_footer.jsp" %>   