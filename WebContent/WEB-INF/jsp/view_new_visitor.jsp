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
                <form  data-toggle="validator" id="${formVisitor}" role="form" >
                  <div class="box-body">
                    <div class="form-group">
                      <label for="txtfull_name">Name Visitor</label>
                      <input type="text" class="form-control" id="txtfull_name" name="full_name" value="${VisitorBean.full_name}" placeholder="Ingrese un nombre" required>
                      <div class="help-block with-errors"></div>
                    </div>
                     <div class="form-group">
                      <label for="txtnumber_license">number license</label>
                      <input type="text" class="form-control" id="txtnumber_license" name="number_license" value="${VisitorBean.number_license}" placeholder="Ingrese number_license" required>
                      <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                      <label for="txtcitizen_ship">citizen ship</label>
                      <input type="text" class="form-control" id="txtcitizen_ship" name="citizen_ship" value="${VisitorBean.citizen_ship}" placeholder="Ingrese citizen_ship" required>
                      <div class="help-block with-errors"></div>
                    </div>
                     <div class="form-group">
                      <label for="txtemail">email</label>
                      <input type="text" class="form-control" id="txtemail" name="email" value="${VisitorBean.email}" placeholder="Ingrese email" required>
                      <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                      <label for="txtphone_number">phone number</label>
                      <input type="text" class="form-control" id="txtphone_number" name="phone_number" value="${VisitorBean.phone_number}" placeholder="Ingrese phone_number" required>
                      <div class="help-block with-errors"></div>
                    </div>
                  </div><!-- /.box-body -->
                  <input type="hidden" name="id_visitor"  value="${ !empty VisitorBean ? VisitorBean.id_visitor : '0'}" readonly /> 
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                  </div>
                </form>
                <div id="msjmntVisitor" ></div>
                
                <!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
     <!--   </section><!-- /.content -->  