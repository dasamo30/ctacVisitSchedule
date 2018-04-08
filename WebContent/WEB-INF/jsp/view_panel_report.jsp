<%@include file="view_panel_header.jsp" %>
        <!-- Main content -->
        <section class="content">
       <div class="row">
       <div class="col-md-12">
         <div class="box box-primary">
                <div class="box-header with-border">
              <h3 class="box-title">Ingrese los Datos</h3>
            </div>
              <form id="formReporteVisits" class="box-body" data-toggle="validator" role="form">
            
                      <div class="form-group col-md-4">
                            <label for="txtFechaIni">Fecha desde: </label>    
                            <div class="input-group date" id="datetimepicker6">
								<input class="form-control pull-right" id="txtFechaIni" name="fechaIni" type="text" required  >
								<div class="input-group-addon open-datetimepicker">
								<i class="fa fa-calendar" id="fa-calendar_ini"></i>
								</div>
								</div>
                            <div class="help-block with-errors"></div>
                       </div>
                  <!-- Date and time range -->
                 <div class="form-group col-md-4">
                            <label for="txtFechaFin">Fecha Hasta: </label>    
                            <div class="input-group date" id="datetimepicker7">
								<input class="form-control pull-right" id="txtFechaFin"  name="fechaFin" type="text" required  >
								<div class="input-group-addon open-datetimepicker">
								<i class="fa fa-calendar" id="fa-calendar_fin"></i>
								</div>
								</div>
                            <div class="help-block with-errors"></div>
                       </div>
                  <div class="form-group col-md-1">
                  <div class="input-group">
                   <label>&nbsp;</label>
                   <button type="submit" class="btn btn-primary ">Generar Reporte</button>
                   </div>
                  </div>     
               </form>
               <hr>
            
            <div class="box-body">
               <div id="divReportVisit">
               <div class="box-body">
                  <table id="tbReportVisit" class="table table-bordered table-striped table-hover box-solid">
                    <thead>
                      <tr>
                        <th>Visit code</th>
                        <th>Visitor</th>
                        <th>Company</th>
                        <th>Date from</th>
                        <th>Date to</th>
                        <th>Hour</th>
                        <th>Employee</th>
                        <th>View</th>
                      </tr>
                    </thead>
                  </table>
                </div>
               
               </div>
               </div>
       </div>
       </div> 
        </section><!-- /.content -->
 <%@include file="view_panel_footer.jsp" %>   