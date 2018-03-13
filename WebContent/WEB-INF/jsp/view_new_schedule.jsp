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
                <form  data-toggle="validator" id="${formSchedule}" role="form" >
                  <div class="box-body">
                  	 <div class="form-group">
                        <label for="cboVisitor">Visitor</label>   
                       	<form:select path="listVisitor" cssClass="form-control" required="required" id="cboVisitor" name="id_visitor" cssStyle="width: 100%;"  >
						      <option value="">-- Seleccione --</option>
						      <c:forEach items="${listVisitor}" var="visitor">
						            <option <c:if test="${visitor.id_visitor eq VisitScheduleBean.id_visitor}">selected="selected"</c:if>  value="${visitor.id_visitor}">${visitor.full_name} </option>
						        </c:forEach>
						</form:select>
                       	<div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label for="cboCompany">Company</label>   
                       	<form:select path="listCompany" cssClass="form-control" required="required" id="cboCompany" name="id_company" cssStyle="width: 100%;"  >
						      <option value="">-- Seleccione --</option>
						      <c:forEach items="${listCompany}" var="company">
						            <option <c:if test="${company.id_company eq VisitScheduleBean.id_company}">selected="selected"</c:if>  value="${company.id_company}">${company.company_name} </option>
						        </c:forEach>
						</form:select>
                       	<div class="help-block with-errors"></div>
                    </div>                      
                    <section class="col-md-6 col-print-6">                          
                            <div class="form-group">
                            <label for="txtDate">Date visit: </label>    
                            <div class="input-group date" id="containerDateIni">
								<input class="form-control pull-right" id="txtDate" value="<fmt:formatDate pattern = "dd-MM-yyyy" value = "${campaniaBean.fecha_hora_inicio}"/>"  name="date" type="text" required >
								<div class="input-group-addon open-datetimepicker">
								<i class="fa fa-calendar" id="fa-calendar_ini"></i>
								</div>
								</div>
                            <div class="help-block with-errors"></div>
                            </div>  
	                </section>
	                <section class="col-md-6 col-print-6">
	                <div class="bootstrap-timepicker">
                    <div class="form-group">
                      <label for="txtHour">Hour visit:</label>
                      <div class="input-group date" id="containerHoraIni">
                        <input type="text" class="form-control timepicker" value="<fmt:formatDate pattern = "HH:mm" value = "${campaniaBean.fecha_hora_inicio}"/>"   id="txtHour" name="hour" required >
                        <div class="input-group-addon">
                          <i class="fa fa-clock-o"></i>
                        </div>
                      </div><!-- /.input group -->
                    </div><!-- /.form group -->
                  </div>
                  </section>
                  
                  <div class="form-group">
                        <label for="cboEmployee">Employee</label>   
                       	<form:select path="listEmployee" cssClass="form-control" required="required" id="cboEmployee" name="id_employee" cssStyle="width: 100%;"  >
						      <option value="">-- Seleccione --</option>
						      <c:forEach items="${listEmployee}" var="employee">
						            <option <c:if test="${employee.id_employee eq VisitScheduleBean.id_employee}">selected="selected"</c:if>  value="${employee.id_employee}">${employee.full_name} </option>
						        </c:forEach>
						</form:select>
                       	<div class="help-block with-errors"></div>
                    </div>
                    
                    <div class="form-group">
                        <label for="cboReason">Reason</label>   
                       	<form:select path="listReason" cssClass="form-control" required="required" id="cboReason" name="id_reason" cssStyle="width: 100%;"  >
						      <option value="">-- Seleccione --</option>
						      <c:forEach items="${listReason}" var="reason">
						            <option <c:if test="${reason.id_reason eq VisitScheduleBean.id_reason}">selected="selected"</c:if>  value="${reason.id_reason}">${reason.reasons_name} </option>
						        </c:forEach>
						</form:select>
                       	<div class="help-block with-errors"></div>
                    </div>
                    
                    
                     
                  </div><!-- /.box-body -->
                  <input type="hidden" name="id_visit_schedule"  value="${ !empty VisitScheduleBean ? VisitScheduleBean.id_visit_schedule : '0'}" readonly /> 
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save</button>
                  </div>
                </form>
                <div id="msjmntSchedule" ></div>
                
                <!-- /.box -->
            </div><!--/.col (left) -->
            <!-- right column -->
          </div>   <!-- /.row -->
     <!--   </section><!-- /.content -->  