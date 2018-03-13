<%@include file="view_panel_header.jsp" %>
		<!-- Main content -->
        <section class="content">
          <!--<div class="row">  
          <div class="col-md-12">
              <div class="box-body">
                <button class="btn btn-primary">Nuevo usuario</button>
            </div>
          </div>
          </div>-->    
            <div class="row"> 
            <!-- left column  centrar  col-md-offset-3-->
            <div class="col-md-12">
              <!-- general form elements -->
              <div class="box box-primary">
                <div class="box-header with-border">
                    <!-- Link trigger modal -->
                    <a href="${contextPath}/visit/visitor/ActViewVisitor" data-toggle="modal" data-target="#myModalViewVisitor" data-remote="false" class="btn btn-primary">
                        new Visitor
                    </a>
                </div><!-- /.box-header -->
                <!-- form start -->
                
                <!--<div id="msjregusu" > </div>-->
              </div><!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
          
          <!-- Default bootstrap modal example -->
            <div class="modal fade" id="myModalViewVisitor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="modal-title"></h4>
                  </div>
                    <div class="modal-body" id="modal-body">
                    
                  </div>
                  <!--<div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                  </div>-->
                </div>
              </div>
            </div>
          
           <div class="row">
            <div class="col-xs-12">
          <div class="box">
                <div class="box-header">
                  <h3 class="box-title">List Company</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table id="tbVisitor" class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>full_name</th>
                        <th>number license</th>
                        <th>citizen_ship</th>
                        <th>email</th>
                        <th>phone number</th>
                        <th>Edit</th>
                        <th>Remove</th>
                      </tr>
                    </thead>
                    <tfoot>
                      <tr>
                        <th>full_name</th>
                        <th>number license</th>
                        <th>citizen_ship</th>
                        <th>email</th>
                        <th>phone number</th>
                        <th>Edit</th>
                        <th>Remove</th>
                      </tr>
                    </tfoot>
                  </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div>
          </div>
    
        </section><!-- /.content -->
 <%@include file="view_panel_footer.jsp" %>   