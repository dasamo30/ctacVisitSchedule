  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
                        <label for="cboOccupation">Occupation</label>   
                       	<form:select path="listOccupation" cssClass="form-control" required="required" id="cboOccupation" name="id_occupation" cssStyle="width: 100%;" >
						      <option value="">-- Seleccione --</option>
						      <c:forEach items="${listOccupation}" var="occupation">
						            <option <c:if test="${occupation.id_occupation eq EmployeeBean.id_occupation}">selected="selected"</c:if>  value="${occupation.id_occupation}">${occupation.occupation} </option>
						        </c:forEach>
						</form:select>
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