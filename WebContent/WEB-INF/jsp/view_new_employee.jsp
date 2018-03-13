 <!-- Main content -->
      <!--  <section class="content"> -->
            <div class="row"> 
            <!-- left column  centrar  col-md-offset-3-->
            <div class="col-md-12">
              <!-- general form elements -->
              <div class="box box-primary">
                <div class="box-header with-border">
                  <!--<h3 class="box-title">Registrar Usuario</h3>-->
                </div><!-- /.box-header -->
                <!-- form start -->
                <form  data-toggle="validator" id="${formEmployee}" role="form" >
                  <div class="box-body">
                    <div class="form-group">
                      <label for="txtfull_name">Name Employee</label>
                      <input type="text" class="form-control" id="txtfull_name" name="full_name" value="${EmployeeBean.full_name}" placeholder="Ingrese un nombre" required>
                      <div class="help-block with-errors"></div>
                    </div>
                     <div class="form-group">
                      <label for="txtidcard">Id Car</label>
                      <input type="text" class="form-control" id="txtidcard" name="idcard" value="${EmployeeBean.idcard}" placeholder="Ingrese idcard" required>
                      <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label for="cbogender">Gender</label>
                        <select id="cbogender" name="gender" class="form-control" required >
                        	<option value="" ${ !empty EmployeeBean ? 'selected' : ''}>Seleccione</option>
                            <option value="M" ${ EmployeeBean.gender=="M" ? 'selected' : ''}>Male</option>
                            <option value="F" ${ EmployeeBean.gender=="F" ? 'selected' : ''}>Fermale</option>
                        </select>
                        <div class="help-block with-errors"></div>
                    </div>
                  </div><!-- /.box-body -->
                  <input type="hidden" name="id_employee"  value="${ !empty EmployeeBean ? EmployeeBean.id_employee : '0'}" readonly /> 
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                  </div>
                </form>
                <div id="msjmntEmployee" ></div>
                
                <!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
     <!--   </section><!-- /.content -->  