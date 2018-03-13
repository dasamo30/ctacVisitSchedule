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
                <form  data-toggle="validator" id="${formCompany}" role="form" >
                  <div class="box-body">
                    <div class="form-group">
                      <label for="txtcompany_name">Name Company</label>
                      <input type="text" class="form-control" id="txtcompany_name" name="company_name" value="${CompanyBean.company_name}" placeholder="Ingrese un nombre" required>
                      <div class="help-block with-errors"></div>
                    </div>
                  </div><!-- /.box-body -->
                  <input type="hidden" name="id_company"  value="${ !empty CompanyBean ? CompanyBean.id_company : '0'}" readonly /> 
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                  </div>
                </form>
                <div id="msjmntCompany" ></div>
                
                <!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
     <!--   </section><!-- /.content -->  